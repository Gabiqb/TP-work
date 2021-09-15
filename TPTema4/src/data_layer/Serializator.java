package data_layer;

import model.BaseProduct;
import model.Menu;
import business_layer.Order;
import model.ExtragereDate;
import presentation_layer.AdminFrame;
import presentation_layer.OrdersFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;

public class Serializator{


    public static int nrOfLines(String file)
    {
        File fil=new File(file);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fil);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] byteArray = new byte[(int)fil.length()];
        try {
            fis.read(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String d = new String(byteArray);
        String[] stringArray = d.split("\r\n");
        return stringArray.length;
    }
    public static void citireProduse()
    {
        try {
            BufferedInputStream bf = new BufferedInputStream(new FileInputStream(AdminFrame.getFisierT().getText()));
            BufferedReader r = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8));
            String row;
            String[][] data=new String[nrOfLines(AdminFrame.getFisierT().getText())][10];
            int i=0;
            r.readLine();
            Menu.setProduse(new HashSet<>());
            while((row=r.readLine())!=null)
            {
                String[] aux=row.split(",");
                data[i][0]=String.valueOf(i+1);
                for(int j=0;j<aux.length;j++)
                    data[i][j+1]=aux[j];
                Menu.getProduse().add(new BaseProduct(data[i][1],Double.parseDouble(data[i][2]),Integer.parseInt(data[i][3]),
                                Integer.parseInt(data[i][4]),Integer.parseInt(data[i][5]),Integer.parseInt(data[i][6]),Integer.parseInt(data[i][7])));
                i++;
            }

            PreparedStatement ps=DBConnection.getC().prepareStatement("TRUNCATE TABLE products");
            ps.execute();

            int pIndex=0;
            for(BaseProduct p:Menu.getProduse()){

                CallableStatement s = DBConnection.getC().prepareCall("{call insertProdus(?,?,?,?,?,?,?,?)}");
                s.setInt(1, pIndex+1);
                s.setString(2, p.getTitle());
                s.setDouble(3, p.getRating());
                s.setInt(4, p.getCalories());
                s.setInt(5, p.getProtein());
                s.setInt(6, p.getFat());
                s.setInt(7, p.getCarbs());
                s.setInt(8, p.getPrice());
                s.execute();
                pIndex++;
            }
            ps = DBConnection.getC().prepareStatement("select max(id) from products");
            ResultSet rs = ps.executeQuery();
            int nextId = 1;
            while (rs.next()) {
                nextId = rs.getInt(1) + 1;
            }
            AdminFrame.getIdUT().setText(String.valueOf(nextId));
            data=ExtragereDate.getData(DBConnection.getC(),"products");
            ((DefaultTableModel)AdminFrame.getTab2().getT().getModel()).setDataVector(data,AdminFrame.getHeader1());
            JOptionPane.showMessageDialog(null, "Produse incarcate!");

        } catch (Exception sqe) {
            AdminFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "products"));
            ((DefaultTableModel) AdminFrame.getTab2().getT().getModel()).setDataVector(AdminFrame.getDate1(), AdminFrame.getHeader1());
            JOptionPane.showMessageDialog(null,"Eroare, reincearca");
        }
    }
    public static void serializeOrder(ArrayList<Order> ar)
    {
        try {
            FileOutputStream file=new FileOutputStream("orders.txt");
            ObjectOutputStream out=new ObjectOutputStream(file);
            out.writeObject(ar);
            out.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void deserializeOrder( )
    {
        try {
            FileInputStream file=new FileInputStream("orders.txt");
            ObjectInputStream in=new ObjectInputStream(file);
            ArrayList<Order> ar= (ArrayList<Order>) in.readObject();
            OrdersData.setComenzi(ar);
            for(Order i:ar)
            {
                OrdersFrame.getQueue().append("Order id: " + i.getOrderId()+ "\nTotal: "+i.getTotal() +" lei\nDate: " +  i.getOrderDate() + " \n" );
                int qtIdx=0;
                for(BaseProduct j:i.getProducts())
                {
                    OrdersFrame.getQueue().append("Produs: " + j.getTitle() + "\nRating: "+j.getRating()+ "\nCalories: "+j.getCalories() + "\nProtein: "
                            +j.getProtein() + "\nFat: "+j.getFat() + "\nCarbs: " +
                            j.getCarbs() + "\nPrice: "+j.getPrice() + " lei\nCantitate: "+i.getCantitati().get(qtIdx)+" \n\n");
                    qtIdx++;
                }
                OrdersFrame.getQueue().append("\n");
                OrdersFrame.getQueue().append("Detalii: " + i.getDetalii() + "\nNume client: "+i.getClient() +"\nNumar telefon: "+i.getNr_tel() +"\n------------------------------------------" +
                        "----------\n");
            }
            in.close();
            file.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }

    }

}
