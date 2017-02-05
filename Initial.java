import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by user on 05.02.2017.
 */
public class Initial {
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/apartment"; //расположение базы, порт 3306(ПОРТ MY SQL)
    private static final String DB_USER = "root"; //user My SQL
    private static final String DB_PASSWORD = "******"; // пароль к базе

    public Initial() {
    }

    public Connection getConnectionToDB() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static String getDbConnection() {
        return DB_CONNECTION;
    }

    public static String getDbUser() {
        return DB_USER;
    }

    public static String getDbPassword() {
        return DB_PASSWORD;
    }


}
