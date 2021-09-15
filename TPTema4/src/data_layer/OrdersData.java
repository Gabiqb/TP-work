package data_layer;

import business_layer.DeliveryService;
import business_layer.Order;

import presentation_layer.OrdersFrame;
import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersData {
    private static ArrayList<Order> comenzi=new ArrayList<>();

    public static ArrayList<Order> getComenzi() {
        return comenzi;
    }

    public static void setComenzi(ArrayList<Order> comenzi) {
        OrdersData.comenzi = comenzi;
    }

    public static void modificaUser()
    {
        try {
            CallableStatement s = DBConnection.getC().prepareCall("call updateUser(?,?,?,?,?,?,?,?)");
            s.setInt(1, Integer.parseInt(OrdersFrame.getTab2().getT().getValueAt(0, 0).toString()));
            s.setString(2, OrdersFrame.getTab2().getT().getValueAt(0, 1).toString());
            s.setString(3, OrdersFrame.getTab2().getT().getValueAt(0, 2).toString());
            s.setString(4, OrdersFrame.getTab2().getT().getValueAt(0, 3).toString());
            s.setString(5, OrdersFrame.getTab2().getT().getValueAt(0, 4).toString());
            s.setString(6, OrdersFrame.getTab2().getT().getValueAt(0, 5).toString());
            s.setString(7, OrdersFrame.getTab2().getT().getValueAt(0, 6).toString());
            s.setString(8,OrdersFrame.getTab2().getT().getValueAt(0, 7).toString());
            s.execute();
            OrdersFrame.getUpdateb().setVisible(false);
        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(null, "Date invalide, reincercati");
        }
    }

}
