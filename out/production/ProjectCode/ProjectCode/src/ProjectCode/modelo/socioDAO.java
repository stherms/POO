package ProjectCode.modelo;

import org.hibernate.Session;

import java.sql.SQLException;

public class socioDAO {
    /**
     * Método para mostar todos los socios de la tabla socio de la BBDD
     */
    public void mostrarSocios() throws SQLException {
        Session connection = null;
        try {
            connection =  new ConexionHibernate().getSession();

            /*
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
            statement.close();*/
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
     * Método CRUD "delete" eliminar el socio infantil elegido según el número de socio del padre de la BBDD
     */
    public static boolean eliminarSocio(int numSocio) throws SQLException {
        Session connection = null;
        try {
            connection =  new ConexionHibernate().getSession();

            // Consulta SQL para borrar un socio
            String sql1 = "DELETE FROM socioInfantil WHERE numSocio = ?";
            String sql2 = "DELETE FROM socioEstandar WHERE numSocio = ?";
            String sql3 = "DELETE FROM socioFederado WHERE numSocio = ?";

            // Prepara y ejecuta la sentencia SQL
            /*PreparedStatement statement = connection.prepareStatement(sql1);
            statement.setInt(1, numSocio);
            int rowsAffected = statement.executeUpdate();

            statement = connection.prepareStatement(sql2);
            statement.setInt(1, numSocio);
            rowsAffected += statement.executeUpdate();

            statement = connection.prepareStatement(sql3);
            statement.setInt(1, numSocio);
            rowsAffected += statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Socio infantil borrado exitosamente.");
            } else {
                System.out.println("No se encontró ningún socio infantil con el número especificado.");
            }

            // Cerrar recursos
            statement.close();*/

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
