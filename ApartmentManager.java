import java.sql.*;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * Created by user on 05.02.2017.
 */
public class ApartmentManager implements DataBaseManager {
    private String apartmentLocationRegion;
    private String apartmentAdress;
    private float apartmentArea;
    private int apartmentRoomQuantity;
    private float apartmentPrice;
    private String quaryAll;
    private String userQuary;

    Connection conn = new Initial().getConnectionToDB();

    Scanner scanner = new Scanner(in);

    public ApartmentManager() throws SQLException {
    }

    public void addNewItemToDB() {

        System.out.print("Enter apartment address: ");
        apartmentAdress = scanner.nextLine();
        System.out.print("Enter apartment region: ");
        apartmentLocationRegion = scanner.nextLine();
        System.out.print("Enter apartment area: ");
        String apartmentAreaS = scanner.nextLine();
        apartmentArea = Integer.parseInt(apartmentAreaS);
        System.out.print("Enter apartment room quantity: ");
        String apartmentRoomQuantityS = scanner.nextLine();
        apartmentRoomQuantity = Integer.parseInt(apartmentRoomQuantityS);
        System.out.print("Enter apartment price: ");
        String apartmentPriceS = scanner.nextLine();
        apartmentPrice = Integer.parseInt(apartmentPriceS);


        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Apartment (apartmentAdress, apartmentLocationRegion, apartmentArea, apartmentRoomQuantity, apartmentPrice) VALUES(?, ?, ?, ?, ?)");

            try {
                ps.setString(1, apartmentLocationRegion);
                ps.setString(2, apartmentAdress);
                ps.setFloat(3, apartmentArea);
                ps.setInt(4, apartmentRoomQuantity);
                ps.setFloat(5, apartmentPrice);
                ps.executeUpdate();

            } finally {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void findItemInDB() {
        System.out.println("Find your apartment: ");
        System.out.println("<1> Find apartment by price: ");
        System.out.println("<2> Find apartment by room quantity: ");
        System.out.println("<3> Find apartment by room region location: ");
        String userAnswer = scanner.nextLine();
        switch (userAnswer) {
            case "1":
                userQuary = "SELECT apartmentPrice FROM apartment";
                sqlQuery(userQuary);
                break;
            case "2":
                userQuary = "SELECT apartmentRoomQuantity FROM apartment";
                sqlQuery(userQuary);
                break;
            case "3":
                userQuary = "SELECT apartmentLocationRegion FROM apartment";
                sqlQuery(userQuary);
                break;
        }
    }


    public void showAllItems() {
        quaryAll = "SELECT * FROM Apartment";
        sqlQuery(quaryAll);
    }


    public void sqlQuery(String quary) {
        try {
            PreparedStatement ps = conn.prepareStatement(quary);

            // table of data representing a database result set,
            ResultSet rs = ps.executeQuery(); //реализация интерфейса

            // can be used to get information about the types and properties of the columns in a ResultSet object
            ResultSetMetaData md = rs.getMetaData();

            for (int i = 1; i <= md.getColumnCount(); i++)
                System.out.print(md.getColumnName(i) + "\t\t");
            System.out.println();

            while (rs.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + "\t\t");
                }
                System.out.println();
            }
            rs.close(); // rs can't be null according to the docs

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
