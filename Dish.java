import javax.persistence.*;

/**
 * Created by user on 12.02.2017.
 */
@Entity
@Table//(name="Dish")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column //(name = "name")
    private String name;

    @Column //(name = "price")
    private double price;

    @Column //(name = "salePrice")
    private double salePrice;

    @Column //(name = "weight")
    private double weight;

    public Dish (String name, double price, double weight, double salePrice ){
        this.name = name;
        this.price = price;
        this.salePrice = salePrice;
        this.weight = weight;
    }

    public Dish (String name, double price, double weight ){
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public Dish() {
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
