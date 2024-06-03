package ProjectCode.modelo;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class socioFederadoDAO {
    
    /**
     * Método CRUD "create" insertar un nuevo socio federado en la BBDD
     */
    public static void crearSocioFederado(int numSocio, String nombre, String nif, String codigoFederacion,
                                          String nomFederacion, ConexionHibernate conexion) throws SQLException {

        Session session = conexion.getSession();
        //Creo la transaccion a partir de la sesion de hibernate
        Transaction tx = session.beginTransaction();

        try {
            //Crear el objeto socio federado a partir de los datos de los parametros
            B2_SocioFederado socioFederado = new B2_SocioFederado(numSocio, nombre, nif, codigoFederacion, nomFederacion);
            //guardo el objeto
            session.save(socioFederado);
            //guardo los cambios
            tx.commit();
        }
        catch (Exception ex) {
            System.out.println("Error al crear el socio federado.");
            tx.rollback();
        }
    }

    /**
     *Método CRUD "read" mostrar todos los socios federados o filtrado por número de socio de la BBDD
     */

    public static ArrayList<B2_SocioFederado> mostrarSocioFederado(ConexionHibernate conexion) throws SQLException {
        ArrayList<B2_SocioFederado> listaSocios = new ArrayList<>();
        Session session;
        Criteria criteria = null;

        session =  conexion.getSession();
        //criterio de seleccion todos los objetos de la clase Socio federado
        criteria = session.createCriteria(B2_SocioFederado.class);

        if (criteria.list().size() > 0) {
            listaSocios = (ArrayList<B2_SocioFederado>) criteria.list();
        }

        return listaSocios;
    }

    /**
     * Método CRUD "update" actualizar el socio federado seleccionado según el número de socio de la BBDD
     */

    public void actualizarSocioFederado(int numSocio, String nombre, String nif, String codigoFederacion,
                                        double DES_CUOTA_MENSUAL, double DES_PRECIO_EXCURSION,
                                        ConexionHibernate conexion) throws SQLException {
        Session connection = null;

        /*try {
            connection =  new ConexionHibernate().getSession();

            // Consulta SQL para actualizar los campos de un socio federado según el número de socio
            String sql = "UPDATE socioFederado SET nombre = ?, nif = ?, codigoFederacion = ?, DES_CUOTA_MENSUAL = ?, DES_PRECIO_EXCURSION WHERE numSocio = ?";

            /*
            // Prepara y ejecuta la sentencia SQL
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, nif);
            statement.setString(3,codigoFederacion);
            statement.setDouble(4,DES_CUOTA_MENSUAL);
            statement.setDouble(5,DES_PRECIO_EXCURSION);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Socio federado actualizado exitosamente.");
            } else {
                System.out.println("No se encontró ningún socio federado con el número de socio especificado.");
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
