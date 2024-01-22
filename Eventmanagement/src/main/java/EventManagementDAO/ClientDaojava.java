package EventManagementDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import EventManagementDto.Admin;
import EventManagementDto.Client;

public class ClientDaojava {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("data");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	public Client SaveClient(Client c)
	{
		et.begin();
		em.persist(c);
		et.commit();
		return c;
		
		
	}
	public Client findClient(int id)
	{
		Client cli=em.find(Client.class, id);
		if(cli!=null)
		{
			return cli;
		}
		return null;
	}
	public Client updateClient(Client client, int clientId)
	{
		Client exClient = em.find(Client.class, clientId);
		if(exClient != null)
		{
			em.getTransaction().begin();
			client.setClientid(clientId);
			Client cl = em.merge(client);
			em.getTransaction().commit();
			return cl;
		}
		return null;
	}



}
