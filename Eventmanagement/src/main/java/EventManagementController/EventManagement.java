package EventManagementController;

import java.time.LocalDate;
import EventManagementDto.EventType;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.type.EnumType;

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

public class EventManagement {
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
		EventManagement em=new EventManagement();
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
//	public ClientService saveClientService() {
//		ClientService cls=new ClientService();
//		
//		Scanner sc=new Scanner(System.in);
//		System.out.println("enter the clientserviceid");
//		cls.setClientserviceid(sc.nextInt());
//		System.out.println("enter the clientname");
//		cls.setClientServicename(sc.next());
//		System.out.println("enter the clientserivcecost");
//		cls.setClientServiceCost(sc.nextDouble());
//		System.out.println("enter the days ");
//		cls.setClientServicenoofdays(sc.nextInt());
//		System.out.println("enter the duration");
//		cls.setClientServiceDuration(sc.nextDouble());
//		System.out.println("enter the clienservice cost per person");
//		cls.setClientServicecostperperson(sc.nextDouble());
//		List<ClientService>cs=new ArrayList<>();
//		cs.add(cls);
//		List<ClientService> clientServices = new ArrayList<ClientService>();
//		ClientEvent clev=new ClientEvent();
//		ClientEvent exclient=new ClientEvent();
//		List<ClientEvent>clv=new ArrayList<>();
//		double eventcost=0;
//		for(int i=0;i<5;i++)
//		{
//			ClientService cs1 = new ClientService();
//			System.out.println("Press -1- for Catering");
//			System.out.println("Press -2- for Dance");
//		System.out.println("Press -3- for Food");
//			List<Service> listOfServices = getAllservice();
//			System.out.println("\t ----- Service Lists -----");
//			for (Service service : listOfServices) 
//			{
//				System.out.println(service);
//			}
//			System.out.println("Enter Service Id :");
//			int svalue = sc.nextInt();
//			Service s1 = sdao.findService(svalue);
//			System.out.println(s1);
//			cls.setClientServicename(s1.getServicename());
//			cls.setClientServicenoofdays(clev.getClientEventnoofdays());
//			cls.setClientServicecostperperson(s1.getServiceCostperperson());
////			cs.setClientServiceCost(ce.getClientEventNoOfPeople() * cs.getClientServiceCostPerPerson());
//			cls.setClientServiceCost(clev.getClientEventnoofpeople()*cls.getClientServicecostperperson()*cls.getClientServicenoofdays());
//			eventcost = eventcost + cls.getClientServiceCost();
//			clientServices.add(cls);
//			ClientService cs11= csa.SaveService(cls);
//		}
//		
		
//		clev.setClientEventcost(eventcost);
//		clev.setClientService(clientServices);
//		clv.add(clev);
//		
//	return "Client Event Not Added";
//}
//		}
//		
//		
//		
//	}
	public String saveClientEvent(){
		ClientEvent exclient=new ClientEvent();
//		Scanner sc=new Scanner(System.in);
//		System.out.println("enter the no of people");
//		exclient.setClientEventnoofpeople(sc.nextInt());
//		System.out.println("start date");
//		exclient.setStartDate(LocalDate.now());
//		System.out.println("enter client event cost");
//		exclient.setClientEventcost(sc.nextDouble());
//		System.out.println("enter the no of days");
//		exclient.setClientEventnoofdays(sc.nextInt());
//		System.out.println("enter the clienteventlocation");
//		exclient.setClientEventLocation(sc.next());
//		exclient.getEventtype();
//		List<ClientEvent>clientEvents=new ArrayList<>();
		Client exClient=ClientLogin();
		if(exClient!=null){
			
		{
			ClientEvent ce = new ClientEvent();
			Scanner sc=new Scanner(System.in);
//			System.out.println("enter the no of people");
//			exclient.setClientEventnoofpeople(sc.nextInt());
			System.out.println("start date");
			exclient.setStartDate(LocalDate.now());
//			System.out.println("enter client event cost");
//			exclient.setClientEventcost(sc.nextDouble());
//			System.out.println("enter the no of days");
//			exclient.setClientEventnoofdays(sc.nextInt());
			System.out.println("enter the clienteventlocation");
			exclient.setClientEventLocation(sc.next());
			exclient.getEventtype();
			List<ClientEvent>clientEvents=new ArrayList<>();
//			
			ce.setClient(exClient);
//			ce.setStartDate(LocalDate.now());
//			System.out.println("enter the clienteventlocation");
//
//			ce.setClientEventLocation(sc.next());
			
			System.out.println("\tEnter Event Type  ");
			System.out.println("Press -1- for Marriage");
			System.out.println("Press -2- for Engagement");
			System.out.println("Press -3- for Birthday");
			System.out.println("Press -4- for Anniversary");
			System.out.println("Press -5- for babyshower");
			System.out.println("Press -6- for reunion");
			System.out.println("Press -7- for NamingCeremony");
			
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
			System.out.println("no of days ");
			ce.setClientEventnoofdays(sc.nextInt());
			System.out.println("no of people");
//			ce.setClientEventnoofpeople(sc.nextInt());
//			System.out.println("location");
			ce.setClientEventLocation(sc.next());
			clientEvents.add(ce);
			exClient.setClientevent(clientEvents);
			ce.setClient(exClient);
			//System.out.println("hiii");
			//new ClientEventDao().SaveClientvent(ce);
			
			//ClientEvent ev=d1.SaveClientvent(ce);
			Client updatedClient=cdao.updateClient(exClient, exClient.getClientid());
			ClientEvent ev=d1.SaveClientvent(ce);
			ClientService css=ClientService();
			ce.getClientEventcost();
		
			
//			et.begin();
//			em.persist(ev);
//			et.commit();
			
			
		}
		
	}
		return "exclient";
	}

	public List<Service> getAllListServices()
	{
		Query query = em.createQuery("select s from Service s");
		List<Service> listOfServices= query.getResultList();
		return listOfServices;
		
		
	}
	public ClientService ClientService(){
		ClientEvent clientevent=new ClientEvent();
		List<ClientService >cs=new ArrayList<ClientService>();
		
		System.out.println("client choosing service");
		System.out.println("1 for food");
		System.out.println("2 for dance");
		ClientService cs1=new ClientService();
		List<Service> s=getAllListServices();
		for(Service service:s) {
			System.out.println(s);
			
		}
		System.out.println("enter the service id");
		Scanner sc=new Scanner(System.in);
		int value=sc.nextInt();
		Service s1=sdao.findService(value);
		System.out.println("enter the service cost");
		cs1.setClientServiceCost(sc.nextDouble());
		System.out.println("enter the costperperson");
		cs1.setClientServicecostperperson(sc.nextInt());
		System.out.println("enter the client duration");
		cs1.setClientServiceDuration(sc.nextDouble());
		System.out.println("enter the clientservice name");
		cs1.setClientServicename(sc.next());
		System.out.println("enter the clientservicenoofdays");
		cs1.setClientServicenoofdays(sc.nextInt());
		cs.add(cs1);
		ClientService cs2=csa.SaveService(cs1);
		return ClientService();
		
		
	}
	public String addClientEventOnly() {
		// TODO Auto-generated method stub
		return null;
	}
}

		

			
			
				
			
	
	

		
		
			
			
			
			
		
		
	

	


