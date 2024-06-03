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
    public static ArrayList<B3_SocioInfantil> mostrarSocioInfantil(ConexionHibernate conexion, String opcion, int id) throws SQLException {
        ArrayList<B3_SocioInfantil> listaSocios = new ArrayList<>();
        Session session = null;
        Criteria criteria = null;
        session = conexion.getSession();

        if (opcion.equals("T")) {
            //Establecer como criterio todos los objetos de la clase Socio Infantil
            criteria = session.createCriteria(B3_SocioInfantil.class);

            if (criteria.list().size() > 0) {
                listaSocios = (ArrayList<B3_SocioInfantil>) criteria.list();
            }
        }
        else if (opcion.equals("F")) {

            //Añadir al criterio de seleccion el numero de socio padre elegido
            criteria = session.createCriteria(B3_SocioInfantil.class);
            criteria.add(Restrictions.eq("numSocio", id));

            if (criteria.list().size() > 0) {
                listaSocios = (ArrayList<B3_SocioInfantil>) criteria.list();
            }
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
