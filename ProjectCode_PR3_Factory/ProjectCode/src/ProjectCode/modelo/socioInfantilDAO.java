package ProjectCode.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class socioInfantilDAO {

    private static ConexionDB conexion;

    public socioInfantilDAO() throws SQLException, ClassNotFoundException {
        this.conexion = new ConexionDB();
    }

    /**
     * Método CRUD "create" insertar un nuevo socio infantil en la BBDD
     */
    public static void crearSocioInfantil(B3_SocioInfantil socioInfantil) throws SQLException {
        Connection connection = null;
        try {
            connection =  new ConexionDB().getConnection();

            // Consulta SQL para insertar un nuevo socio
            String sql = "INSERT INTO socioInfantil (numSocio, nombre, numSocioPadre) VALUES (?, ?, ?)";

            // Prepara y ejecuta la sentencia SQL
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, socioInfantil.getNumSocio());
            statement.setString(2, socioInfantil.getNombre());
            statement.setInt(3, socioInfantil.getNumSocioPadre());
            statement.executeUpdate();

            System.out.println("Socio infantil creado exitosamente.");

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
     *Método CRUD "read" mostrar todos los socios infantiles o filtrados por número socio de la BBDD
     */
    public static ArrayList<B3_SocioInfantil> mostrarSocioInfantil() throws SQLException {
        ArrayList<B3_SocioInfantil> listaSocio = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Scanner scanner = new Scanner(System.in);
        String option = null; // Declaración de la variable option

        try {
            connection = new ConexionDB().getConnection();

            // Pregunta al usuario qué quiere ver
            System.out.println("Seleccione, mostrar todos los socios infantiles (T) o filtrar por número socio del padre (F):");
            // Asignación de valor a la variable option
            option = scanner.nextLine().toUpperCase();

            // Según la opción elegida
            if (option.equals("T")) {
                statement = connection.prepareStatement("SELECT * FROM socioInfantil");
            } else if (option.equals("F")) {
                System.out.println("Ingrese el número de socio del padre:");
                int numSocioPadre = scanner.nextInt();
                statement = connection.prepareStatement("SELECT * FROM socioInfantil WHERE numSocioPadre = ?");
                statement.setInt(1, numSocioPadre);
            } else {
                System.out.println("Opción no válida.");
            }

            // Ejecuta la sentencia SQL de la opción elegida
            resultSet = statement.executeQuery();

            // Procesa los resultados
            while (resultSet.next()) {
                int numSocio = resultSet.getInt("numSocio");
                String nombre = resultSet.getString("nombre");
                int numSocioPadre = resultSet.getInt("numSocioPadre");

                B3_SocioInfantil socioInfantil = new B3_SocioInfantil(numSocio, nombre, numSocioPadre);
                listaSocio.add(socioInfantil);

            }
        }
        catch (SQLException | ClassNotFoundException ex) {
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
     * Método CRUD "update" actualizar el socio infantil seleccionado según el número de socio de la BBDD
     */
    public void actualizarSocioInfantil(int numSocioPadre, String nombre, int cuota, double DESCUENTO) throws SQLException {
        Connection connection = null;
        try {
            connection =  new ConexionDB().getConnection();

            // Consulta SQL para actualizar los campos de un socio infantil según el número de socio del padre
            String sql = "UPDATE socioInfantil SET nombre = ?, cuota = ?, DESCUENTO = ? WHERE numSocioPadre = ?";

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
        }
    }
}
