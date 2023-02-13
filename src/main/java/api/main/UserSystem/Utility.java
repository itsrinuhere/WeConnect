package api.main.UserSystem;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import api.main.UserSystem.HibernateUtility;

public class Utility {
	public boolean saveUser(User user){
		Transaction transaction = null;
		Session session = null;
		try{
			session = api.main.UserSystem.HibernateUtility.getsessionfactory().openSession();
			transaction = session.beginTransaction();
			session.persist(user);
			transaction.commit();
			return true;
		}catch(Exception ex){
			if(transaction!= null)transaction.rollback();	
				System.out.println(ex.toString());
		return false;
	}
	 finally 
     {
		 if(session!=null) session.close();
     }
} 
	public List<User> getUser(String username) {
		Transaction transaction = null;
		Session session = null;
		List<User> data = null;
		try{
			session  = api.main.UserSystem.HibernateUtility.getsessionfactory().openSession();
			transaction = session.beginTransaction();
			Query<User> query = session.createQuery("FROM api.main.UserSystem.User object WHERE object.username = : username",User.class);
			query.setParameter("username",username);
			System.out.println("DEBUG : Transaction success");
			data =  query.list();
			session.flush();
		}catch(Exception ex) {
			System.out.println("DEBUG : failed and roll backed");
			if(transaction!= null) transaction.rollback();
			ex.printStackTrace();
		}finally{
			 if(session!=null)
				 session.close(); 
	      }
		return data;
	}
}
