package ProjectCode.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class socioEstandarDAO {
   private static ConexionDB conexion;

    public socioEstandarDAO() throws SQLException, ClassNotFoundException {
        this.conexion = new ConexionDB();
    }

    /**
     * Método CRUD "create" insertar un nuevo socio estándar de la BBDD
     */
    public static void crearSocioEstandar(B1_SocioEstandar socioEstandar) throws SQLException {
        Connection connection = null;
        try {
            connection = new ConexionDB().getConnection();

            // Consulta SQL para insertar un nuevo socio
            String sql = "INSERT INTO socioEstandar (numSocio, nombre, nif, seguro) VALUES (?, ?, ?, ?)";

            // Prepara y ejecuta la sentencia SQL
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, socioEstandar.getNumSocio());
            statement.setString(2, socioEstandar.getNombre());
            statement.setString(3, socioEstandar.getNif());
            statement.setString(4, String.valueOf(socioEstandar.getSeguro().getTipoSeguro()));
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
        }
    }

    /**
     *Método CRUD "read" mostrar los socios estándar en la BBDD
     */

    public static ArrayList<B1_SocioEstandar> mostrarSocioEstandar() throws SQLException {
        ArrayList<B1_SocioEstandar> listaSocios = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Scanner scanner = new Scanner(System.in);
        String option = null; // Declaración de la variable option

        try {
            connection = new ConexionDB().getConnection();

            // Pregunta al usuario qué quiere ver
            System.out.println("Seleccione, mostrar todos los socios estandar (T) o filtrar por número socio (F):");
            // Asignación de valor a la variable option
            option = scanner.nextLine().toUpperCase();

            // Según la opción elegida
            if (option.equals("T")) {
                statement = connection.prepareStatement("SELECT * FROM socioEstandar");
            } else if (option.equals("F")) {
                System.out.println("Ingrese el número de socio:");
                int numSocio = scanner.nextInt();
                statement = connection.prepareStatement("SELECT * FROM socioEstandar WHERE numSocio = ?");
                statement.setInt(1, numSocio);
            } else {
                System.out.println("Opción no válida.");
            }

            // Ejecuta la sentencia SQL de la opción elegida
            resultSet = statement.executeQuery();


            // Procesa los resultados
            while (resultSet.next()) {
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
                listaSocios.add(socioEstandar);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Cierra la conexión a la BBDD, garantiza el cierre incluso si hay una excepción
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            //scanner.close();
        }

        return listaSocios;
    }

    /**
     * Método CRUD "update" actualizar el socio estándar seleccionado según el número de socio de la BBDD
     */

    public void actualizarSocioEstandar(int numSocio, String nombre, String nif, int cuota, String seguro, int DES_CUOTA_MENSUAL) throws SQLException {
        Connection connection = null;
        try {
            connection = new ConexionDB().getConnection();

            // Consulta SQL para actualizar los campos de un socio estandar
            String sql = "UPDATE socioEstandar SET nombre = ?, nif = ?, cuota = ?, seguro = ?, DES_CUOTA_MENSUAL = ?, WHERE numSocio = ?";

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
        }
    }
}
