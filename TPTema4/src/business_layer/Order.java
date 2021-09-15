package business_layer;

import model.BaseProduct;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private int orderId;
    private ArrayList<BaseProduct> products;
    private String orderDate;
    private String client;
    private ArrayList<Integer> cantitati;
    private int total;
    private String detalii;
    private String nr_tel;

    public Order(int id, ArrayList<BaseProduct> prod, String orderDate, String client, ArrayList<Integer> cantitati, int total, String detalii, String nr_tel)
    {
        this.orderId=id;
        this.products=prod;
        this.orderDate=orderDate;
        this.client=client;
        this.cantitati=cantitati;
        this.total=total;
        this.detalii=detalii;
        this.nr_tel=nr_tel;
    }

    public int getOrderId() {
        return orderId;
    }

    public ArrayList<BaseProduct>  getProducts() {
        return products;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public ArrayList<Integer>  getCantitati() {
        return cantitati;
    }

    public int getTotal() {
        return total;
    }

    public String getDetalii() {
        return detalii;
    }

    public String getNr_tel() {
        return nr_tel;
    }

    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException
    {
        orderId=aInputStream.readInt();
        products=(ArrayList<BaseProduct>)aInputStream.readObject();
        orderDate=aInputStream.readUTF();
        cantitati=(ArrayList<Integer>) aInputStream.readObject();
        client=aInputStream.readUTF();
        detalii=aInputStream.readUTF();
        nr_tel=aInputStream.readUTF();
        total=aInputStream.readInt();
    }

    private void writeObject(ObjectOutputStream aOutputStream) throws IOException
    {
        aOutputStream.writeInt(orderId);
        aOutputStream.writeObject(products);
        aOutputStream.writeUTF(orderDate);
        aOutputStream.writeObject(cantitati);
        aOutputStream.writeUTF(client);
        aOutputStream.writeUTF(detalii);
        aOutputStream.writeUTF(nr_tel);
        aOutputStream.writeInt(total);

    }
}
