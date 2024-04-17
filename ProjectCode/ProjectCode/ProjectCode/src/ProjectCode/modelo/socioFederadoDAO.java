package ProjectCode.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class socioFederadoDAO {

    private static ConexionDB conexion;

    public socioFederadoDAO() throws SQLException, ClassNotFoundException {
        this.conexion = new ConexionDB();
    }

    /**
     * Método CRUD "create" insertar un nuevo socio federado en la BBDD
     */
    public static void crearSocioFederado(int numSocio, String nombre, String nif, String codigoFederacion, String nomFederacion) throws SQLException {
        Connection connection = null;
        try {
            connection = new ConexionDB().getConnection();

            // Consulta SQL para insertar un nuevo socio
            String sql = "INSERT INTO socioFederado (numSocio, nombre, nif, codigoFederacion, nomFederacion) VALUES (?, ?, ?, ?, ?)";

            // Prepara y ejecuta la sentencia SQL
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, numSocio);
            statement.setString(2, nombre);
            statement.setString(3, nif);
            statement.setString(4,codigoFederacion);
            statement.setString(5,nomFederacion);
            statement.executeUpdate();

            System.out.println("Socio federado creado exitosamente.");

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

    /**
     *Método CRUD "read" mostrar todos los socios federados o filtrado por número de socio de la BBDD
     */

    public static ArrayList<B2_SocioFederado> mostrarSocioFederado() throws SQLException {
        ArrayList<B2_SocioFederado> listaSocio = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Scanner scanner = new Scanner(System.in);
        String option = null; // Declaración de la variable option

        try {
            connection =  new ConexionDB().getConnection();

            // Pregunta al usuario qué quiere ver
            System.out.println("Seleccione, mostrar todos los socios federados (T) o filtrar por número socio (F):");
            // Asignación de valor a la variable option
            option = scanner.nextLine().toUpperCase();

            // Según la opción elegida
            if (option.equals("T")) {
                statement = connection.prepareStatement("SELECT * FROM socioFederado");
            } else if (option.equals("F")) {
                System.out.println("Ingrese el número de socio:");
                int numSocio = scanner.nextInt();
                statement = connection.prepareStatement("SELECT * FROM socioFederado WHERE numSocio = ?");
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
                String codigoFederacion = resultSet.getString("codigoFederacion");
                String nomFederacion = resultSet.getString("nomFederacion");

                B2_SocioFederado socioFederado = new B2_SocioFederado(numSocio, nombre, nif, codigoFederacion, nomFederacion);
                listaSocio.add(socioFederado);
            }
        }  catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        } finally {
            // Cierra la conexión a la BBDD, garantiza el cierre incluso si hay una excepción
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            //scanner.close();
        }

        return listaSocio;
    }

    /**
     * Método CRUD "update" actualizar el socio federado seleccionado según el número de socio de la BBDD
     */

    public void actualizarSocioFederado(int numSocio, String nombre, String nif, String codigoFederacion, double DES_CUOTA_MENSUAL, double DES_PRECIO_EXCURSION) throws SQLException {
        Connection connection = null;
        try {
            connection =  new ConexionDB().getConnection();

            // Consulta SQL para actualizar los campos de un socio federado según el número de socio
            String sql = "UPDATE socioFederado SET nombre = ?, nif = ?, codigoFederacion = ?, DES_CUOTA_MENSUAL = ?, DES_PRECIO_EXCURSION WHERE numSocio = ?";

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
        }
    }
}
