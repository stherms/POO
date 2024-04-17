package ProjectCode.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class socioDAO {

    private static ConexionDB conexion;

    public socioDAO() throws SQLException, ClassNotFoundException {
        this.conexion = new ConexionDB();
    }

    /**
     * Método para mostar todos los socios de la tabla socio de la BBDD
     */
    public void mostrarSocios() throws SQLException {
        Connection connection = null;
        try {
            connection =  new ConexionDB().getConnection();

            // Consulta SQL para mostrar todos los socios
            String sql = "SELECT * FROM socio";

            // Prepara y ejecuta la sentencia SQL
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            // Procesa los resultados
            while (resultSet.next()) {
                int numSocio = resultSet.getInt("numSocio");
                String nombre = resultSet.getString("nombre");
                // Los sacamos por pantalla
                System.out.println("Número de socio: " + numSocio + ", Nombre: " + nombre);
            }

            // Cerrar recursos
            resultSet.close();
            statement.close();
        }
        catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        finally {
            // Cerrar la conexión a la BBDD, garantiza el cierre incluso si hay una excepción
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Método CRUD "delete" eliminar el socio elegido según el número de socio del padre de la BBDD
     */
    public static boolean eliminarSocio(int numSocio) throws SQLException {
        Connection connection = null;
        try {
            connection =  new ConexionDB().getConnection();

            // Consulta SQL para borrar un socio
            String sql1 = "DELETE FROM socioInfantil WHERE numSocio = ?";
            String sql2 = "DELETE FROM socioEstandar WHERE numSocio = ?";
            String sql3 = "DELETE FROM socioFederado WHERE numSocio = ?";

            // Prepara y ejecuta la sentencia SQL
            //PreparedStatement statement = connection.prepareStatement(sql1);
            //statement.setInt(1, numSocio);
            //int rowsAffected = statement.executeUpdate();
            //int rowsAffectedInfantil = statement.executeUpdate();

            //statement = connection.prepareStatement(sql2);
            //statement.setInt(1, numSocio);
            //rowsAffected += statement.executeUpdate();

            //statement = connection.prepareStatement(sql3);
            //statement.setInt(1, numSocio);
            //rowsAffected += statement.executeUpdate();

            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setInt(1, numSocio);
            int rowsAffectedInfantil = statement1.executeUpdate();

            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setInt(1, numSocio);
            int rowsAffectedEstandar = statement2.executeUpdate();

            PreparedStatement statement3 = connection.prepareStatement(sql3);
            statement3.setInt(1, numSocio);
            int rowsAffectedFederado = statement3.executeUpdate();

            int totalRowsAffected = rowsAffectedInfantil + rowsAffectedEstandar + rowsAffectedFederado;

            if (totalRowsAffected > 0) {
                System.out.println("Socio borrado exitosamente.");
                if (rowsAffectedInfantil > 0) {
                    System.out.println("También se eliminó el socio infantil asociado.");
                }
            } else {
                System.out.println("No se encontró ningún socio con el número especificado.");
            }

            // Cerrar recursos
            statement1.close();
            statement2.close();
            statement3.close();
            return true;

        }
        catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        finally
        {
            // Cerrar la conexión a la BBDD
            if (connection != null) {
                connection.close();
            }
        }

        return false;
    }
}
