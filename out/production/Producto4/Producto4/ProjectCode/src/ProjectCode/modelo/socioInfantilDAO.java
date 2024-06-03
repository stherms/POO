package ProjectCode.modelo;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class socioInfantilDAO {

    /**
     * Método CRUD "create" insertar un nuevo socio infantil en la BBDD
     */
    public static void crearSocioInfantil(int numSocio, String nombre, int numSocioPadre, ConexionHibernate conexion) throws SQLException {
        Session connection = null;

        Session session = conexion.getSession();
        //Creo la transaccion a partir de la sesion de hibernate
        Transaction tx = session.beginTransaction();

        try {
            //Crear el objeto socio infantil a partir de los datos de los parametros
            B3_SocioInfantil socioInfantil = new B3_SocioInfantil(numSocio, nombre, numSocioPadre);
            //guardo el objeto
            session.save(socioInfantil);
            //guardo los cambios
            tx.commit();
        }
        catch (Exception ex) {
            System.out.println("Error al crear el socio infantil.");
            tx.rollback();
        }
    }

    /**
     *Método CRUD "read" mostrar todos los socios infantiles o filtrados por número socio de la BBDD
     */
    public static ArrayList<B3_SocioInfantil> mostrarSocioInfantil(ConexionHibernate conexion) throws SQLException {
        ArrayList<B3_SocioInfantil> listaSocios = new ArrayList<>();
        Session session = null;
        Criteria criteria = null;
        Scanner scanner = new Scanner(System.in);
        String option = null; // Declaración de la variable option

        session = conexion.getSession();

            // Pregunta al usuario qué quiere ver
        System.out.println("Seleccione, mostrar todos los socios infantiles (T) o filtrar por número socio del padre (F):");
        // Asignación de valor a la variable option
        option = scanner.nextLine().toUpperCase();

        // Según la opción elegida
        if (option.equals("T")) {
            //Establecer como criterio todos los objetos de la clase Socio Infantil
            criteria = session.createCriteria(B3_SocioInfantil.class);

            if (criteria.list().size() > 0) {
                listaSocios = (ArrayList<B3_SocioInfantil>) criteria.list();
            }
        } else if (option.equals("F")) {
            System.out.println("Ingrese el número de socio del padre:");
            int numSocioPadre = scanner.nextInt();

            //Añadir al criterio de seleccion el numero de socio padre elegido
            criteria = session.createCriteria(B3_SocioInfantil.class);
            criteria.add(Restrictions.eq("numSocioPadre",  numSocioPadre));

            if (criteria.list().size() > 0) {
                listaSocios = (ArrayList<B3_SocioInfantil>) criteria.list();
            }
        } else {
            System.out.println("Opción no válida.");
        }

        return listaSocios;
    }

    /**
     * Método CRUD "update" actualizar el socio infantil seleccionado según el número de socio de la BBDD
     */
    public void actualizarSocioInfantil(int numSocioPadre, String nombre, int cuota, double DESCUENTO,
                                        ConexionHibernate conexion) throws SQLException {
        Session connection = null;

        /*try {
            connection =  new ConexionHibernate().getSession();

            // Consulta SQL para actualizar los campos de un socio infantil según el número de socio del padre
            String sql = "UPDATE socioInfantil SET nombre = ?, cuota = ?, DESCUENTO = ? WHERE numSocioPadre = ?";

            /*
            // Prepara y ejecuta la sentencia SQL
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setInt(2, cuota);
            statement.setDouble(3,DESCUENTO);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Socio infantil actualizado exitosamente.");
            } else {
                System.out.println("No se encontró ningún socio infantil con el número de socio del padre especificado.");
            }

            // Cerrar recursos
            statement.close();
        }
        catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        finally {
            // Cerrar la conexión a la BBDD
            if (connection != null) {
                connection.close();
            }
        }*/
    }
}
