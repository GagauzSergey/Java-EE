import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * Created by user on 12.02.2017.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        String text;

            System.out.println("Choose next activities:");
            System.out.println(">>> 1 : Add new dish to restaurant menu");
            System.out.println(">>> 2 : Choose dish price in next range:");
            System.out.println(">>> 3 : Choose dishes with sale price:");
            System.out.println(">>> 4 : Choose dishes with weight not greater then 1 kg:");

            text = scanner.nextLine();
            switch (text) {
                case "1":
                    new UserOrder().addDish();
                    break;
                case "2":
                    new UserOrder().orderBoundary();
                    break;
                case "3":
                    new UserOrder().salePrice();
                    break;
                case "4":
                    new UserOrder().sortWeight();
            }
        }
    }
