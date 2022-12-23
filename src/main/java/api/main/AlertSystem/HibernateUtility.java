package api.main.AlertSystem;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import java.util.Properties;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
public class HibernateUtility {
	public static SessionFactory sessionfactory;
	public static SessionFactory getsessionfactory() {
		if(sessionfactory == null) {
			try {
				Configuration configuration= new Configuration();
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "org.postgresql.Driver");//?autoReconnect=true
				settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/WeConnect?createDatabaseIfNotExist=true&&autoReconnect=true");
				settings.put(Environment.USER, "postgres");
				settings.put(Environment.PASS, "root");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.FORMAT_SQL,true);
				settings.put(Environment.USE_SQL_COMMENTS,true);
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(Environment.HBM2DDL_AUTO, "validate");
				//settings.put(Environment.HBM2DDL_AUTO, "create");
				configuration.setProperties(settings);
				configuration.addAnnotatedClass(Alert.class);
				ServiceRegistry serviceregistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
				sessionfactory = configuration.buildSessionFactory(serviceregistry);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return sessionfactory;
	}
	}