package EventManagementDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import EventManagementDto.ClientEvent;

public class ClientEventDao {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("data");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	public ClientEvent SaveClientvent(ClientEvent c)
	{
		et.begin();
		em.persist(c);
		et.commit();
		return c;
		
		
	}
	public ClientEvent findClientEvent(int id)
	{
		ClientEvent cli=em.find(ClientEvent.class, id);
		if(cli!=null)
		{
			return cli;
		}
		return cli;
	}
	public ClientEvent updateClientEvent(ClientEvent clientEvent, int clientEventId)
	{
		ClientEvent exClientEvent = em.find(ClientEvent.class, clientEventId);
		if(exClientEvent != null)
		{
			em.getTransaction().begin();
			clientEvent.setClientEventid(clientEventId);
			ClientEvent cl = em.merge(clientEvent);
			em.getTransaction().commit();
			return cl;
		}
		return null;
	}


}
