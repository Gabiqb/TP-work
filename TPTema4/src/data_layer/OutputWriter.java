package data_layer;

import model.BaseProduct;
import business_layer.DeliveryService;
import business_layer.Order;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
public class OutputWriter {

    public static void scrieChitanta(DeliveryService d, Order o)
    {

        try {
             File f=new File("receipt-"+o.getOrderId()+".txt");
             FileWriter wr=new FileWriter(f);
             wr.write("Order id: " + o.getOrderId()+ "\nTotal: "+o.getTotal() +" lei\nDate: " +  o.getOrderDate() + " \n");
             int qtIdx=0;
            for(BaseProduct j:o.getProducts())
            {
                wr.write("Produs: " + j.getTitle() + "\nRating: "+j.getRating()+ "\nCalories: "+j.getCalories() + "\nProtein: "
                        +j.getProtein() + "\nFat: "+j.getFat() + "\nCarbs: " +
                        j.getCarbs() + "\nPrice: "+j.getPrice() + " lei\nCantitate: "+o.getCantitati().get(qtIdx)+" \n\n");
                qtIdx++;
            }
            wr.write("\n");
            wr.write("Estimated delivery time: "+d.estimateDeliveryTime(o.getOrderDate() )+"\n");
            wr.write("Detalii: " + o.getDetalii() + "\nNume client: "+o.getClient() +"\nNumar telefon: "+o.getNr_tel() +"\n------------------------------------------" +
                    "----------\n");

            JOptionPane.showMessageDialog(null, "Chitanta eliberata cu succes");


            wr.close();

        } catch (Exception throwables) {
            JOptionPane.showMessageDialog(null, "Eroare, reincearca");
        }
    }
}
