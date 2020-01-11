package dao.imp;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private static SessionFactory factory = null;
	
	private HibernateUtil() {
		
	}
	public static SessionFactory getInstance() {
		if(factory==null) {
			StandardServiceRegistry registre = new StandardServiceRegistryBuilder().configure().build();
			factory = new MetadataSources(registre).buildMetadata().buildSessionFactory();
		}
		return factory;
	}
}
