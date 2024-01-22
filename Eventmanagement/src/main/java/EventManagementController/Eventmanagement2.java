package EventManagementController;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import EventManagementDAO.AdminDao;
import EventManagementDAO.ClientDaojava;
import EventManagementDAO.ClientEventDao;
import EventManagementDAO.ClientServiceDao;
import EventManagementDAO.ServiceDaojava;
import EventManagementDto.Admin;
import EventManagementDto.Client;
import EventManagementDto.ClientEvent;
import EventManagementDto.ClientService;
import EventManagementDto.EventType;
import EventManagementDto.Service;





public class Eventmanagement2 {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("data");
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		AdminDao adao=new AdminDao();
		ServiceDaojava sdao=new ServiceDaojava();
		ClientDaojava cdao=new ClientDaojava();
		ClientServiceDao csa=new ClientServiceDao();
		ClientEventDao d1=new ClientEventDao();
		ClientServiceDao d2=new ClientServiceDao();
		public static void main(String[] args) {
			Eventmanagement2 em=new Eventmanagement2();
			System.out.println(em.ClientService());
			
		}
		public Admin saveAdmin() {
			Admin admin=new Admin();
			Scanner sc=new Scanner(System.in);
			System.out.println("enter the name");
			admin.setName(sc.next());
			System.out.println("enter the mail");
			admin.setEmail(sc.next());
			System.out.println("enter the password");
			admin.setPassword(sc.next());
			System.out.println("enter the contact");
			admin.setContact(sc.nextLong());
			
			return adao.SaveAdmin(admin);
			
			
		}
		public Admin AdminLogin() {
			Scanner sc=new Scanner(System.in);
			System.out.println("enter the email");
			String email=sc.next();
			System.out.println("enter the password");
			String password=sc.next();
			Query query=Persistence.createEntityManagerFactory("data").createEntityManager().createQuery("select a from Admin a where a.email=?1") ;
			query.setParameter(1, email);		
			Admin exAdmin=(Admin)query.getSingleResult();
			if(exAdmin != null)
			{
				if(exAdmin.getPassword().equals(password)) {
					return exAdmin;
					
				}
			}
			return null;

			
			
			
			
		}
		public  Service saveService() {
			Admin exAdmin=AdminLogin();
			if(exAdmin!=null) {
				Service service=new Service();
				Scanner sc= new Scanner(System.in);
				System.out.println("enter the service name");
				service.setServicename(sc.next());
				System.out.println("enter the service cost perday");
				service.setServiceCostperday(sc.nextDouble());
				System.out.println("enter the cost per person");
				service.setServiceCostperperson(sc.nextInt());
				System.out.println("enter the no of days ");
				service.setServicecostnoofdays(sc.nextInt());
				
				Service saveservice=sdao.SaveService(service);
				exAdmin.getServices().add(service);
				adao.updateAdmin(exAdmin, exAdmin.getAdminid());
				
				return service;
			}
			return null;
			
		}
		public List<Service>getAllservice(){
			System.out.println("enter admin credentials to proceed");
			Admin exAdmin=AdminLogin();
			if(exAdmin!=null)
			{
				Query q=Persistence.createEntityManagerFactory("data").createEntityManager().createQuery("select a from Service a");
				List<Service>service=q.getResultList();
				return service;
			}
			return null;
			
		}
		public String updateService() {
			Scanner sc=new Scanner(System.in);
			List<Service>service=getAllservice();
			for(Service s:service) {
				System.out.println("serviceid"+"servicename"+"cost_per_person"+"cost_per_day");
				System.out.println(" "+s.getServiceid());
				
			}
			System.out.println("@@@@@chooose an service id from above to update @@@@@@");
			int id=sc.nextInt();
			Service tobeupdated=sdao.findService(id);
			System.out.println("enter the updated cost per person");
			double costperperson=sc.nextDouble();
			System.out.println("enter the updated cost per day");
			double costperday=sc.nextDouble();
			tobeupdated.setServiceCostperday(costperday);
			tobeupdated.setServiceCostperperson(costperperson);
			Service updated=sdao.updateService(tobeupdated, id);
			if(updated!=null)
			{
				return "service update sucess";
			}
			return "invalid service id";
			
			
			
		}
		public Service deleteService() {
			Scanner sc=new Scanner(System.in);
			Admin exAdmin=AdminLogin();
			if(exAdmin!=null)
			{
				List<Service>service=exAdmin.getServices();
				for(Service s:service) {
					System.out.println("serviceid"+"servicename"+"cost_per_day");
					System.out.println(" "+s.getServiceid());
					
					
				}
				System.out.println("@@@@@choose service id from above to delete@@@@@@");
				int id=sc.nextInt();
				Service tobedeleted=sdao.findService(id);
				service.remove(tobedeleted);
				exAdmin.setServices(service);
				adao.updateAdmin(exAdmin, exAdmin.getAdminid());
				return tobedeleted;
			
				
				
			}
			return null;
			
		}
		public Client Clientsignup(){
			Client cli=new Client();
			Scanner sc=new Scanner(System.in);
			System.out.println("enter the clientname");
			cli.setName(sc.next());
			System.out.println("enter the contact");
			cli.setContact(sc.nextInt());
			System.out.println("enter the mail");
			cli.setMail(sc.next());
			System.out.println("enter the password");
			cli.setPassword(sc.next());
			return cdao.SaveClient(cli);
		
	}
		public Client ClientLogin() {
			Scanner sc= new Scanner(System.in);
			System.out.println("enter the mail");
			String email=sc.next();
			System.out.println("enter the password");
			String password=sc.next();
			Query query=Persistence.createEntityManagerFactory("data").createEntityManager().createQuery("select a from Client a where a.mail=?1") ;
			query.setParameter(1, email);
			Client exClient=(Client)query.getSingleResult();
			if(exClient != null)
			{
				if(exClient.getPassword().equals(password)) {
					System.out.println("successS");
					return exClient;
					
				}
				else {
					System.out.println("not success");
				}
			}
			return null;
			
		}
		
	        public String addClientEventOnly() {
		
				System.out.println("------Add Client Event Only (Without Services)--------");
				Client exClient = ClientLogin();
				List<ClientEvent> clientEventList = exClient.getClientevent();
				if(exClient != null)
				{
					ClientEvent ce = new ClientEvent();
					
					System.out.println("\tEnter Event Type  ");
					System.out.println("Press -1- for Marriage");
					System.out.println("Press -2- for Engagement");
					System.out.println("Press -3- for Birthday");
					System.out.println("Press -4- for Babyshower");
					System.out.println("Press -5- for Anniversary");
					System.out.println("Press -6- for BachelorParty");
					System.out.println("Press -7- for NamingCeremony");
					System.out.println("Press -8- for Reunion");
					Scanner sc=new Scanner(System.in);
					
					int value = sc.nextInt();
					if(value==1) {
						ce.setEventtype(EventType.marriage);
					}
					else if(value == 2) {
						ce.setEventtype(EventType.Engagment);
					}
						
					else if(value == 3) {
						ce.setEventtype(EventType.birthday);
					}
					else if(value == 4) {
						ce.setEventtype(EventType.Anniversary);
					}
					else if(value == 5) {
						ce.setEventtype(EventType.BabyShower);
					}
					else if(value==6) {
						ce.setEventtype(EventType.Reunion);
						
					}
					else if(value==7) {
						ce.setEventtype(EventType.NamingCeremony);
					}
					ce.setStartDate(LocalDate.now());
					Scanner sc1=new Scanner(System.in);
					System.out.print("No Of Days : "); ce.setClientEventnoofdays(sc1.nextInt());
					System.out.print("No Of People : "); ce.setClientEventnoofpeople(sc1.nextInt());
					System.out.print("Event Location : "); ce.setClientEventLocation(sc1.next());
					ce.setClient(exClient);
					clientEventList.add(ce);
					exClient.setClientevent(clientEventList);
					Client updatedClient = cdao.updateClient(exClient, exClient.getClientid());
	
					if(updatedClient != null)
						return "Client Event Added Successfully";
				}
				return "Client User Not Found";
			}
	        public String ClientService(){
	        	Client exclient=ClientLogin();
	        	if(exclient!=null) {
	        		List<ClientEvent>clv=exclient.getClientevent();
	        		System.out.println(" Client Event Id : "); 
	        		Scanner sc1=new Scanner(System.in);
	        		int exClientEventId = sc1.nextInt();
	    			int countvalue = 0;
	    			for(ClientEvent events : clv)
	    			{
	    				if(events.getClientEventid() == exClientEventId)
	    				{
	    					countvalue ++;
	    					double eventCost = events.getClientEventcost();
	    					List<ClientService> exClientServices = events.getClientService();
	    					Scanner sc=new Scanner(System.in);
	    					System.out.println("Enter Service Adding Count : "); 
	    					int serviceCount = sc.nextInt();
	    					for(int i=1;i<=serviceCount;i++)
	    					{
	    						ClientService cs = new ClientService();
	    						List<Service> listOfServices = getAllListServices();
	    						for (Service service : listOfServices) 
	    						{
	    							System.out.println(service);
	    						}
	    						System.out.print(" Service Id :");
	    						int svalue = sc.nextInt();
	    						Service s1 = sdao.findService(svalue);
	    						cs.setClientServicename(s1.getServicename());
	    						cs.setClientServicenoofdays(events.getClientEventnoofdays());
	    						cs.setClientServicecostperperson(s1.getServiceCostperperson());
	    						cs.setClientServiceCost(events.getClientEventnoofpeople() * cs.getClientServicecostperperson() * cs.getClientServicenoofdays());
	    						eventCost = eventCost + cs.getClientServiceCost();
	    						exClientServices.add(cs);
	    						ClientService cs1= d2.SaveService(cs);
	    					}
	    					
	    					events.setClientEventcost(eventCost);
	    					events.setClientService(exClientServices);
	    					ClientEvent ce1 = d1.updateClientEvent(events, events.getClientEventid());
	    					if(ce1 != null)
	    					{
	    						return " Service Added";
	    					}
	    				}
	    			}
	    		}
	    		return " Service Not Added";
	    	}
	        	
	    		
	    		
	        public List<Service> getAllListServices()
	    	{
	    		Query query = em.createQuery("select s from Service s");
	    		List<Service> listOfServices= query.getResultList();
	    		return listOfServices;
	    		
	    		
	    	}
	        
}
	    	
	        




