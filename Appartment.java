/**
 * Created by user on 05.02.2017.
 */
public class Appartment {

    private long id;

    private String appartmentLocationRegion;
    private String appartmentAdress;
    private float appartmentArea;
    private int appartmentRoomQuantity;
    private float appartmentPrice;

    public double getAppartmentPrice() {
        return appartmentPrice;
    }

    public void setAppartmentPrice(float appartmentPrice) {
        this.appartmentPrice = appartmentPrice;
    }

    public Appartment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppartmentLocationRegion() {
        return appartmentLocationRegion;
    }

    public void setAppartmentLocationRegion(String appartmentLocationRegion) {
        this.appartmentLocationRegion = appartmentLocationRegion;
    }

    public String getAppartmentAdress() {
        return appartmentAdress;
    }

    public void setAppartmentAdress(String appartmentAdress) {
        this.appartmentAdress = appartmentAdress;
    }

    public float getAppartmentArea() {
        return appartmentArea;
    }

    public void setAppartmentArea(float appartmentArea) {
        this.appartmentArea = appartmentArea;
    }

    public int getAppartmentRoomQuantity() {
        return appartmentRoomQuantity;
    }

    public void setAppartmentRoomQuantity(int appartmentRoomQuantity) {
        this.appartmentRoomQuantity = appartmentRoomQuantity;
    }
}
