package ProjectCode.modelo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.SQLException;

public class ConexionHibernate {

    private static Session session;

    public static Session getSession() {
        return session;
    }

    private static Session conectarHibenate() {

        try {
            //Cargamos fichero de Hibernate
            Configuration cfg = new Configuration().configure();
            System.out.println("Fichero de configuración Hibernate cargado");

            //A partir del fichero de configuracion creamos y abrimos la sesión Hibernate
            SessionFactory sessionFactory = cfg.buildSessionFactory();
            System.out.println("Sesion abierta");
            session = sessionFactory.openSession();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    public ConexionHibernate() throws SQLException, ClassNotFoundException {
        conectarHibenate();
    }
}
