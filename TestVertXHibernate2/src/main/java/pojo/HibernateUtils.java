package pojo;

import entity.Candidate;
import entity.EntryTest;
import entity.Interview;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;


public class HibernateUtils {
    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

//        conf.configure("hibernate.cfg.xml");
        Properties props = new Properties();

        props.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        props.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        props.put(Environment.URL, "jdbc:mysql://localhost:3306/test2");
        props.put(Environment.USER, "root");
        props.put(Environment.PASS, "root");
        props.put(Environment.SHOW_SQL, "true");
        props.put(Environment.HBM2DDL_AUTO, "update");

        conf.setProperties(props);

        conf.addAnnotatedClass(Candidate.class);
        conf.addAnnotatedClass(EntryTest.class);
        conf.addAnnotatedClass(Interview.class);

//        conf.addAnnotatedClass(Department.class);
//        conf.addAnnotatedClass(Employee.class);
//        conf.addAnnotatedClass(Seat.class);
//        conf.addAnnotatedClass(CinemaRoom.class);
//        conf.addAnnotatedClass(CinemaRoomDetails.class);


        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);
    }

    public static SessionFactory getFactory() {
        return FACTORY;
    }

}
