package ProjectCode.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }


    public ConexionDB() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/estherMS"+
                "?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=utf8&useConfigs=maxPerformance";
        String username = "root";
        String password = "root";
        this.connection = DriverManager.getConnection(url, username, password);
    }


}
