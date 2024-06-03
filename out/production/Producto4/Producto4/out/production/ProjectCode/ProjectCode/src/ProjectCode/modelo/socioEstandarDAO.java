package ProjectCode.modelo;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class socioEstandarDAO {
    /**
     * Método CRUD "create" insertar un nuevo socio estándar de la BBDD
     */
    public static void crearSocioEstandar(int numSocio, String nombre, String nif, String seguro, ConexionHibernate conexion) throws SQLException {
        /*try {


            // Consulta SQL para insertar un nuevo socio
            String sql = "INSERT INTO socioEstandar (numSocio, nombre, nif, seguro) VALUES (?, ?, ?, ?)";

            /*
            // Prepara y ejecuta la sentencia SQL
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, numSocio);
            statement.setString(2, nombre);
            statement.setString(3, nif);
            statement.setString(4,seguro);
            statement.executeUpdate();

            System.out.println("Socio estándar creado exitosamente.");

            // Cerrar recursos
            statement.close();
        }
        catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        finally {
            // Cerrar la conexión a la BBDD
            if (connection != null) {
                connection.close();
            }
        }*/
    }

    /**
     *Método CRUD "read" mostrar los socios estándar en la BBDD
     */

    public static ArrayList<B1_SocioEstandar> mostrarSocioEstandar(ConexionHibernate conexion) throws SQLException {
        ArrayList<B1_SocioEstandar> listaSocios = new ArrayList<>();
        Session session = null;
        Query query = null;
        Scanner scanner = new Scanner(System.in);
        String option = null; // Declaración de la variable option

        //try {
            session = conexion.getSession();

            // Pregunta al usuario qué quiere ver
            System.out.println("Seleccione, mostrar todos los socios estandar (T) o filtrar por número socio (F):");
            // Asignación de valor a la variable option
            option = scanner.nextLine().toUpperCase();

            // Según la opción elegida
            if (option.equals("T")) {
                Criteria criteria = session.createCriteria(B1_SocioEstandar.class);
                listaSocios = (ArrayList<B1_SocioEstandar>) criteria.list();
            } else if (option.equals("F")) {
                System.out.println("Ingrese el número de socio:");
                int numSocio = scanner.nextInt();

                Criteria criteria = session.createCriteria(B1_SocioEstandar.class);
                criteria.add(Restrictions.eq("numSocio",  numSocio));
                listaSocios = (ArrayList<B1_SocioEstandar>) criteria.list();
            } else {
                System.out.println("Opción no válida.");
            }

            // Procesa los resultados
            /*
            while (query..next()) {
                int numSocio = resultSet.getInt("numSocio");
                String nombre = resultSet.getString("nombre");
                String nif = resultSet.getString("nif");
                String seguro = resultSet.getString("seguro");

                C0_Seguro objSeguro;
                if (seguro.equals("BASICO")) {
                    objSeguro = new C0_Seguro(C0_Seguro.tipoSeguro.valueOf(seguro), 15);
                }
                else {
                    objSeguro = new C0_Seguro(C0_Seguro.tipoSeguro.valueOf(seguro), 30);
                }

                B1_SocioEstandar socioEstandar = new B1_SocioEstandar(numSocio, nombre, nif, objSeguro);
                listaSocios.add(socioEstandar);*/

        /*} catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        return listaSocios;
    }

    /**
     * Método CRUD "update" actualizar el socio estándar seleccionado según el número de socio de la BBDD
     */

    public void actualizarSocioEstandar(int numSocio, String nombre, String nif, int cuota, String seguro, int DES_CUOTA_MENSUAL, ConexionHibernate conexion) throws SQLException {
        //try {

            /*
            // Consulta SQL para actualizar los campos de un socio estandar
            String sql = "UPDATE socioEstandar SET nombre = ?, nif = ?, cuota = ?, seguro = ?, DES_CUOTA_MENSUAL = ?, WHERE numSocio = ?";

            /*
            // Prepara y ejecuta la sentencia SQL
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, numSocio);
            statement.setString(2, nombre);
            statement.setString(3, nif);
            statement.setInt(4,cuota);
            statement.setString(5,seguro);
            statement.setInt(6,DES_CUOTA_MENSUAL);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Socio estándar actualizado exitosamente.");
            } else {
                System.out.println("No se encontró ningún socio estándar con el número de socio especificado.");
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
