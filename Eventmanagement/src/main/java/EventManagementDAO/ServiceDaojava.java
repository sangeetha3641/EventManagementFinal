package EventManagementDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import EventManagementDto.Service;

public class ServiceDaojava 
{
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("data");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	public Service SaveService(Service a)
	{
		et.begin();
		em.persist(a);
		et.commit();
		return a;
		
		
	}
	public Service findService(int id)
	{
		Service service=em.find(Service.class, id);
		if(service!=null)
		{
			return service;
		}
		return null;
	}
	
	public Service updateService(Service a,  int Serviceid)
	{
		Service a1 = em.find(Service.class, Serviceid);
		if(a1 != null)
		{
			a1.setServiceid(Serviceid);;
			et.begin();
			em.merge(a1);
			et.commit();
			
		}
		return null;
	}
	public Service deleteService(int id)
	{
		Service a=em.find(Service.class, id);
		if(a!=null)
		{
			et.begin();
			em.remove(a);
			et.commit();
			return a;
		}
		return null;
		
	}
}
