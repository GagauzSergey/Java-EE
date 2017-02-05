import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Created by user on 05.02.2017.
 */
public class ConnectionToDB {

    static Connection conn;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            try {
                // create connection, реализуем интерфейс connection из библ java.sql.*
                // по сути это соединение с базой
                conn = new Initial().getConnectionToDB();
                initDB();

                /*
                * Далее выводим меню и в зависимости от выбранного метода выполняем
                * действие с базой
                * */
                while (true) {
                    System.out.println("1: add apartment");
                    System.out.println("2: find apartment");
                    System.out.println("3: view all apartments");
                    System.out.print("-> ");

                    String ss = sc.nextLine();

                    if (ss.equals("1")) {
                        new ApartmentManager().addNewItemToDB();

                    } else if (ss.equals("2")) {
                        new ApartmentManager().findItemInDB();

                    } else if (ss.equals("3")) {
                        new ApartmentManager().showAllItems();

                    } else {
                        return;
                    }
                }
            } finally {
                sc.close();
                if (conn != null) conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
    }

    public static void initDB() throws SQLException {
        Statement st = conn.createStatement(); // все запросы (просто запрос)в БД производятся через Statement
        /*
        * Получает тем самым объект типа Statement и вызываем у него метод execute
        * и хардкодим
        * */
        try {
            st.execute("DROP TABLE IF EXISTS apartment");
            st.execute("CREATE TABLE apartment (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, apartmentLocationRegion VARCHAR(20) NOT NULL , apartmentAdress VARCHAR(20) NOT NULL , apartmentArea FLOAT (10, 2), apartmentRoomQuantity INT , apartmentPrice FLOAT (10, 2))");
            System.out.println("Check");
            // создать табличку с id, name, age
        } finally {
            st.close();
        }
    }

}
