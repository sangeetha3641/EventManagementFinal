package EventManagementDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import EventManagementDto.*;

public class AdminDao {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("data");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	public Admin SaveAdmin(Admin a)
	{
		et.begin();
		em.persist(a);
		et.commit();
		return a;
		
		
	}
	public Admin findAdmin(int id)
	{
		Admin admin=em.find(Admin.class, id);
		if(admin!=null)
		{
			return admin;
		}
		return null;
	}
	
	public Admin updateAdmin(Admin a, int adminId)
	{
		Admin a1 = em.find(Admin.class, adminId);
		if(a1 != null)
		{
			a.setAdminid(adminId);
			et.begin();
			em.merge(a);
			et.commit();
			
		}
		return null;
	}
	public Admin deleteAdmin(int id)
	{
		Admin a=em.find(Admin.class, id);
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
