import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * Created by user on 12.02.2017.
 */
public class UserOrder implements IOrder {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATest");
    EntityManager em = emf.createEntityManager();
    Query query;
    List<Dish> dishesFromDataBase;
    Scanner scanner = new Scanner(in);
    String limitMin;
    String limitMax;

    List<String> menuDish = new ArrayList<>();

    @Override
    public void addDish() {
        em.getTransaction().begin();
        Dish dish1 = new Dish("Salad", 25, 300, 20);
        em.persist(dish1);
        Dish dish2 = new Dish("Soup", 50, 300, 25);
        em.persist(dish2);
        Dish dish3 = new Dish("Meat", 150, 500);
        em.persist(dish3);
        em.getTransaction().commit();
    }

    @Override
    public void sortWeight() {
        double count = 0;
        query = em.createQuery("select weight from Dish");
        dishesFromDataBase = query.getResultList();
        for (Dish d : dishesFromDataBase) {
            count += d.getWeight();
            System.out.println(d.getName());
            if (count > 0)
                break;
        }
    }

    @Override
    public void salePrice() {
        query = em.createQuery("select name from Dish where salePrice>0");
        dishesFromDataBase = query.getResultList();
        for (Dish d : dishesFromDataBase) {
            System.out.println(d.getName());
        }
    }

    @Override
    public void orderBoundary() {
        System.out.println("Enter min price:");
        limitMin = scanner.nextLine();
        double limMin = Double.parseDouble(limitMin);
        System.out.println("Enter max price:");
        limitMax = scanner.nextLine();
        double limMax = Double.parseDouble(limitMax);
        query = em.createQuery("select name from Dish where price BETWEEN " + limMin + " AND " + limMax);
        dishesFromDataBase = query.getResultList();
        for (Dish d : dishesFromDataBase) {
            System.out.println(d.getName());
        }
    }
}
