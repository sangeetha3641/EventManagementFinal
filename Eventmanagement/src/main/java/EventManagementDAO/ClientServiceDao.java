package EventManagementDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import EventManagementDto.ClientService;

public class ClientServiceDao {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("data");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	public ClientService SaveService(ClientService c)
	{
		et.begin();
		em.persist(c);
		et.commit();
		return c;
		
		
	}
	public ClientService findClientService(int id)
	{
		ClientService cli=em.find(ClientService.class, id);
		if(cli!=null)
		{
			return cli;
		}
		return null;
	}
	public ClientService updateClientService(ClientService a, int ClientServiceid)
	{
		ClientService c = em.find(ClientService.class, ClientServiceid);
		if(c != null)
		{
			c.setClientserviceid(ClientServiceid);;
			et.begin();
			em.merge(c);
			et.commit();
			
		}
		return null;
	}
	public ClientService deleteClientService(int ClientServiceid)
	{
		ClientService clientService = em.find(ClientService.class, ClientServiceid);
		System.out.println("Sangeeth ID: "+ClientServiceid);
		if(clientService != null)
		{
			em.getTransaction().begin();
			em.remove(clientService);
			em.getTransaction().commit();
			System.out.println("Sangeeth ID 1: "+ClientServiceid);
			return clientService;
			
		}
		System.out.println("Sangeeth ID 2: "+ClientServiceid);
		return null;
	}
	
	


}
