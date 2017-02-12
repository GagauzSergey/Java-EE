import javax.persistence.EntityManager;

/**
 * Created by user on 12.02.2017.
 */


public interface IOrder {

    public void addDish ();

    public void sortWeight ();

    public void salePrice();

    public void orderBoundary ();
}
