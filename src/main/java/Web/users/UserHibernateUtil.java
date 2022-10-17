package Web.users;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import java.util.Properties;
import org.hibernate.service.ServiceRegistry;
import Web.users.Users;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
public class UserHibernateUtil {
public static SessionFactory sessionfactory;
public static SessionFactory getsessionfactory() {
	if(sessionfactory == null) {
		try {
			Configuration configuration= new Configuration();
			Properties settings = new Properties();
			settings.put(Environment.DRIVER, "org.postgresql.Driver");//?autoReconnect=true
			settings.put(Environment.URL, "jdbc:postgresql://ec2-52-207-90-231.compute-1.amazonaws.com:5432/davqjdb86nhmk0?sslmode=require&&createDatabaseIfNotExists=true&&autoReconnect=true");
			settings.put(Environment.USER, "vcoijvillvlmdg");
			settings.put(Environment.PASS, "aa0df7dd3612dfad97870c52f97f3b38a9a38c4a878f7fd0a2b63295015e85dc");
			settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
			settings.put(Environment.SHOW_SQL, "true");
			settings.put(Environment.FORMAT_SQL,true);
			settings.put(Environment.USE_SQL_COMMENTS,true);
			settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
			settings.put(Environment.HBM2DDL_AUTO, "validate");
			//settings.put(Environment.HBM2DDL_AUTO, "create");
			configuration.setProperties(settings);
			configuration.addAnnotatedClass(Users.class);
			ServiceRegistry serviceregistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			sessionfactory = configuration.buildSessionFactory(serviceregistry);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return sessionfactory;
}//end of gestsessionfactory
}
