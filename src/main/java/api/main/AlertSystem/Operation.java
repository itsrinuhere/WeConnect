package api.main.AlertSystem;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
public class Operation {

	public List<Alert> getAlerts(){
		Transaction transaction = null;
		Session session = null;

		List<Alert> data = null;
		try {
			System.out.println("GET ALETS FUNCTION INVOKED");
			session = HibernateUtility.getsessionfactory().openSession();
			transaction = session.beginTransaction();
			Query<Alert> query = session.createQuery("from Alert",Alert.class);
			data = query.getResultList();
			System.out.println("FINAL executed");
			}catch(Exception ex) {
				System.out.println(ex.toString());
				System.out.println("ERROR");
				if(transaction != null) {
					transaction.rollback();
				}
		}finally {
			if(session!=null)session.close();
		}
		return data;
	}
	public List<Alert> getAlert(long id){
		Transaction transaction = null;
		Session session = null;
		List<Alert> data = null;
		try{
			session  = HibernateUtility.getsessionfactory().openSession();
			transaction = session.beginTransaction();
			Query<Alert> query = session.createQuery("FROM api.main.AlertSystem.Alert object WHERE object.alertId = : id",Alert.class);
			query.setParameter("id",id);
			data =  query.list();
			session.flush();
		}catch(Exception ex) {
			if(transaction!= null) transaction.rollback();
			ex.printStackTrace();
		}finally{
			 if(session!=null)
				 session.close(); 
	      }
		return data;
	}
	public boolean saveAlert(Alert model){
		Transaction transaction = null;
		Session session = null;
		try{
			session = HibernateUtility.getsessionfactory().openSession();
			transaction = session.beginTransaction();
			session.persist(model);
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
	public Boolean updateAlert(Alert alert){
		Session session = null;
		try {
			session = HibernateUtility.getsessionfactory().openSession();
			@SuppressWarnings ("deprecation" )
			Query<?> query = session.createQuery("UPDATE api.main.AlertSystem.Alert object SET object.alertMessage= : msg ,object.alertTitle= : title where object.alertId= : id");
			query.setParameter("msg", alert.getAlertMessage());
			query.setParameter("title",alert.getAlertTitle());
			query.setParameter("id",alert.getAlertId());
			session.beginTransaction();
			int res = query.executeUpdate();
			session.flush();
			if(res>0)return true;
		}catch(Exception ex) {
			System.out.println(ex.toString());
			return false;
		}finally {
			 if(session!=null)
				 session.close(); 
		}
		return false;
	}
	public Boolean DeleteAlert(long id) {
		//Transaction transaction = null;
		Session session = null;
		try {
			session  = HibernateUtility.getsessionfactory().openSession();
			//transaction = session.beginTransaction();
			@SuppressWarnings("deprecation")
			Query<?> query = session.createQuery("DELETE FROM api.main.AlertSystem.Alert object WHERE object.alertId= : id");
			query.setParameter("id",id);
			session.beginTransaction();
			int result =query.executeUpdate();
			//session.getTransaction().commit();
			//transaction.commit();	
			session.flush();
			if(result>0) return true;
			
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}finally {
			 if(session!=null)
				 session.close(); 
		}
		return false;
		}
	
}

