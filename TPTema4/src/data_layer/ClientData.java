package data_layer;

import model.BaseProduct;
import model.Menu;
import business_layer.Order;
import model.Model;
import presentation_layer.ClientsFrame;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClientData {

    public static void stergeProdus() {
        try {
            if (ClientsFrame.getTotalT().getText().equals("0")) {
                ClientsFrame.getCosProduse().setSelectedItem("");
                ClientsFrame.getCosProduse().removeAllItems();
            }
            String[] aux = ClientsFrame.getCosProduse().getSelectedItem().toString().split(": ");
            aux = aux[2].split(" ");
            ClientsFrame.getTotalT().setText(String.valueOf(Integer.parseInt(ClientsFrame.getTotalT().getText()) - Integer.parseInt(aux[0])));
            String deleted = ClientsFrame.getCosProduse().getSelectedItem().toString();
            ClientsFrame.getCosProduse().removeItem(deleted);
        } catch (Exception eq) {
            ClientsFrame.getCosProduse().setSelectedItem("");
            ClientsFrame.getCosProduse().removeAllItems();
            JOptionPane.showMessageDialog(null, "Cosul este gol");
        }
    }

    public static void adaugaProdus() {
        try {
            if (ClientsFrame.getCantitateT().getText().equals(""))
                throw new Exception();
            else {
                String[] aux = ClientsFrame.getProduseCombo().getSelectedItem().toString().split(":");
                String[] aux2 = aux[1].split(":");
                aux2 = aux2[0].split(" ");
                int sum = Integer.parseInt(aux2[1]);
                String newPrice = String.valueOf(Integer.parseInt(ClientsFrame.getCantitateT().getText()) * sum);
                String newItem = aux[0] + ", bucati: " + ClientsFrame.getCantitateT().getText() + ", total: " + newPrice + " lei";
                ClientsFrame.getCosProduse().addItem(newItem);

                ClientsFrame.getTotalT().setText(String.valueOf(Integer.parseInt(newPrice) + Integer.parseInt(ClientsFrame.getTotalT().getText())));
            }
        } catch (Exception eq) {
            JOptionPane.showMessageDialog(null, "Introduceti o cantitate numerica");
        }
    }
    public static void computeCart(ArrayList<BaseProduct> p, ArrayList<Integer> c)
    {

        for (int i = 0; i < ClientsFrame.getCosProduse().getItemCount(); i++) {
            String s = ClientsFrame.getCosProduse().getItemAt(i).toString();
            String[] aux = s.split(", ");
            String[] pr=aux[2].split(": ");
            pr=pr[1].split(" ");
            aux[1] = aux[1].replace(" ", "");
            String[] aux2 = aux[1].split(":");
            c.add(Integer.parseInt(aux2[1]));
            int currPrice=Integer.parseInt(pr[0])/Integer.parseInt(aux2[1]);
            for(BaseProduct j:Menu.getProduse()) {
                if (j.getTitle().equals(aux[0]) && j.getPrice()==currPrice) {
                    p.add(j);
                    break;
                }
            }

        }

    }
    public static void confirmaComanda() {
        try {
            int nextId = OrdersData.getComenzi().size()+1;
            CallableStatement s = DBConnection.getC().prepareCall("call insertOrder(?,?,?,?,?,?,?,?,?,?)");
            String client=ClientsFrame.getTab2().getT().getValueAt(0, 1).toString() + " "+ ClientsFrame.getTab2().getT().getValueAt(0, 2).toString();
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat format=new SimpleDateFormat("YYYY-MM-dd/HH:mm");
            ArrayList<BaseProduct> computedProducts=new ArrayList<>();
            ArrayList<Integer> computedQts=new ArrayList<>();
            computeCart(computedProducts,computedQts);
            Order o=new Order(nextId,computedProducts, format.format(date),client,computedQts,Integer.parseInt(ClientsFrame.getTotalT().getText()),
                    ClientsFrame.getDetaliiT().getText(),ClientsFrame.getTab2().getT().getValueAt(0, 3).toString());
            OrdersData.getComenzi().add(o);
            Serializator.serializeOrder(OrdersData.getComenzi());
            OutputWriter.scrieChitanta(Model.getdService(),o);
            JOptionPane.showMessageDialog(null, "Comanda noua!");
            ClientsFrame.getCosProduse().removeAllItems();
            ClientsFrame.getCantitateT().setText("");
            ClientsFrame.getDetaliiT().setText("");
        } catch (Exception eq) {
            JOptionPane.showMessageDialog(null, "Eroare, reincercati");
        }

    }

    public static void modificaClient() {
        try {
            CallableStatement s = DBConnection.getC().prepareCall("call updateUser(?,?,?,?,?,?,?,?)");
            s.setInt(1, Integer.parseInt(ClientsFrame.getTab2().getT().getValueAt(0, 0).toString()));
            s.setString(2, ClientsFrame.getTab2().getT().getValueAt(0, 1).toString());
            s.setString(3, ClientsFrame.getTab2().getT().getValueAt(0, 2).toString());
            s.setString(4, ClientsFrame.getTab2().getT().getValueAt(0, 3).toString());
            s.setString(5, ClientsFrame.getTab2().getT().getValueAt(0, 4).toString());
            s.setString(6, ClientsFrame.getTab2().getT().getValueAt(0, 5).toString());
            s.setString(7, ClientsFrame.getTab2().getT().getValueAt(0, 6).toString());
            s.setString(8, ClientsFrame.getTab2().getT().getValueAt(0, 7).toString());
            s.execute();

            ClientsFrame.getUpdateb().setVisible(false);
        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(null, "Date invalide, reincercati");
        }
    }
}

