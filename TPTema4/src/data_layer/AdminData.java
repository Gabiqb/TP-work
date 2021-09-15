package data_layer;

import model.BaseProduct;
import model.Menu;
import business_layer.Order;
import model.ExtragereDate;
import presentation_layer.AdminFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


public class AdminData {

    public static void stergeUser()
    {
        try {
            int selRow=AdminFrame.getTab1().getT().getSelectedRow();
            CallableStatement s = DBConnection.getC().prepareCall("call stergeUser(?)");
            s.setInt(1, Integer.parseInt(AdminFrame.getTab1().getT().getValueAt(selRow,0).toString()));
            s.execute();
            JOptionPane.showMessageDialog(null, "User sters!");
            AdminFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "users"));
            ((DefaultTableModel) AdminFrame.getTab1().getT().getModel()).setDataVector(AdminFrame.getDate(),AdminFrame.getHeader());
        } catch (Exception eq) {
            JOptionPane.showMessageDialog(null, "Eroare, reincercati");
            AdminFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "users"));
            ((DefaultTableModel) AdminFrame.getTab1().getT().getModel()).setDataVector(AdminFrame.getDate(),AdminFrame.getHeader());
        }
    }
    public static void stergeProdus()
    {
        AdminFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
        int selectedRow=AdminFrame.getTab2().getT().getSelectedRow();
        if (selectedRow>= 0) {
            if (AdminFrame.getDate1().length > selectedRow) {
                try {
                    int id=Integer.parseInt(AdminFrame.getTab2().getT().getValueAt(selectedRow,0).toString());
                    PreparedStatement s = DBConnection.getC().prepareCall("{call stergeProdus(?)}"); ///sterge un utilizator
                    s.setInt(1, id);
                    s.execute();
                    PreparedStatement ps = DBConnection.getC().prepareStatement("select max(id) from products");
                    ResultSet rs = ps.executeQuery();
                    int nextId = 1;
                    while (rs.next()) {
                        nextId = rs.getInt(1) + 1;
                    }
                    AdminFrame.getIdT().setText(String.valueOf(nextId));
                    AdminFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "products"));
                    ((DefaultTableModel) AdminFrame.getTab2().getT().getModel()).setDataVector(AdminFrame.getDate1(), AdminFrame.getHeader1());
                    AdminFrame.getUpdateb().setVisible(false);
                    loadProducts();
                    JOptionPane.showMessageDialog(null, "Produs sters!");
                } catch (Exception sqe) {
                    AdminFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "products"));
                }
            } else if (AdminFrame.getTab2().getT().getSelectedRow() >= 0) {
                AdminFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "products"));
                ((DefaultTableModel) AdminFrame.getTab2().getT().getModel()).setDataVector(AdminFrame.getDate1(), AdminFrame.getHeader1());
            }
        }
    }
    public static void interval_Comenzi(String interval)
    {
        String after,before;
        String[] spl=interval.split("-");
        after=spl[0];
        before=spl[1];
        AdminFrame.getRaport().setText("");
        for(Order i:OrdersData.getComenzi())
        {
            Date date1 = null;
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd/HH:mm");
            try {
                date1=format.parse(i.getOrderDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar now = Calendar.getInstance();
            now.set(Calendar.HOUR_OF_DAY, Integer.parseInt(after));
            now.set(Calendar.MINUTE, 0);
            Calendar givenDate = Calendar.getInstance();
            givenDate.setTime(date1);

            if(now.before(givenDate))
            {
                now.set(Calendar.HOUR_OF_DAY, Integer.parseInt(before));
                now.set(Calendar.MINUTE, 0);
                givenDate = Calendar.getInstance();
                givenDate.setTime(date1);
                if(now.after(givenDate))
                {
                    AdminFrame.getRaport().append("Order id: " + i.getOrderId()+ "\nTotal: "+i.getTotal() +" lei\nDate: " +  i.getOrderDate() + " \n" );
                    int qtIdx=0;
                    for(BaseProduct j:i.getProducts())
                    {
                        AdminFrame.getRaport().append("Produs: " + j.getTitle() + "\nRating: "+j.getRating()+ "\nCalories: "+j.getCalories() + "\nProtein: "
                                +j.getProtein() + "\nFat: "+j.getFat() + "\nCarbs: " +
                                j.getCarbs() + "\nPrice: "+j.getPrice() + " lei\nCantitate: "+i.getCantitati().get(qtIdx)+" \n\n");
                        qtIdx++;
                    }
                    AdminFrame.getRaport().append("\n");
                    AdminFrame.getRaport().append("Detalii: " + i.getDetalii() + "\nNume client: "+i.getClient() +"\nNumar telefon: "+i.getNr_tel() +"\n------------------------------------------" +
                            "----------\n");
                }
            }

        }

     }
    public static void zi_Produse(String day)
    {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd/HH:mm");
        Date d1=null;
        AdminFrame.getRaport().setText("");
        for(Order o:OrdersData.getComenzi())
        {
            try {
                Date date=format.parse(o.getOrderDate());
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                String dayWeekText = new SimpleDateFormat("EEEE").format(date);
                List<BaseProduct> baseProducts=o.getProducts().stream().filter(d -> dayWeekText.contains(day)).collect(Collectors.toList());
                int qtIdx=0;
                for(BaseProduct i: baseProducts)
                {
                    AdminFrame.getRaport().append("Produs: " + i.getTitle() + "\nRating: "+i.getRating()+ "\nCalories: "+i.getCalories() + "\nProtein: "
                            +i.getProtein() + "\nFat: "+i.getFat() + "\nCarbs: " +
                            i.getCarbs() + "\nPrice: "+i.getPrice() + " lei\nCantitate: " + o.getCantitati().get(qtIdx) + " \n\n");
                    qtIdx++;
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

    }
    public static void confirmaProdus()
    {
        AdminFrame.getUpdateb().setVisible(false);
        try {
            if (AdminFrame.getTab2().getT().isEditing()) {
                AdminFrame.getTab2().getT().getCellEditor().stopCellEditing();
            }
            CallableStatement s = DBConnection.getC().prepareCall("{call insertProdus(?,?,?,?,?,?,?,?)}");

            s.setInt(1, Integer.parseInt(AdminFrame.getIdT().getText()));
            s.setString(2, AdminFrame.getDenumireT().getText());
            s.setDouble(3, Double.parseDouble(AdminFrame.getRatingT().getText()));
            s.setInt(4, Integer.parseInt(AdminFrame.getCaloriiT().getText()));
            s.setInt(5, Integer.parseInt(AdminFrame.getProteineT().getText()));
            s.setInt(6, Integer.parseInt(AdminFrame.getGrasimiT().getText()));
            s.setInt(7, Integer.parseInt(AdminFrame.getCarbohidratiT().getText()));
            s.setInt(8, Integer.parseInt(AdminFrame.getPretT().getText()));
            s.execute();

            PreparedStatement ps = DBConnection.getC().prepareStatement("select max(id) from products");
            ResultSet rs = ps.executeQuery();
            int nextId = 1;
            while (rs.next()) {
                nextId = rs.getInt(1) + 1;
            }
            AdminFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "products"));
            ((DefaultTableModel) AdminFrame.getTab2().getT().getModel()).setDataVector(AdminFrame.getDate1(), AdminFrame.getHeader1());
            AdminFrame.getIdT().setText(String.valueOf(nextId));
            AdminFrame.getDenumireT().setText("");
            AdminFrame.getRatingT().setText("");
            AdminFrame.getCaloriiT().setText("");
            AdminFrame.getProteineT().setText("");
            AdminFrame.getGrasimiT().setText("");
            AdminFrame.getCarbohidratiT().setText("");
            AdminFrame.getPretT().setText("");
            loadProducts();
            JOptionPane.showMessageDialog(null, "Produs inserat!");

        } catch (Exception sqe) {
            JOptionPane.showMessageDialog(null,"Eroare, introduceti date valide");
            AdminFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "products"));
            ((DefaultTableModel) AdminFrame.getTab2().getT().getModel()).setDataVector(AdminFrame.getDate1(), AdminFrame.getHeader1());
        }

    }
    public static void loadProducts()
    {
        Menu.setProduse(new HashSet<>());
        String[][] data=ExtragereDate.getData(DBConnection.getC(),"products");
        for(int i=0;i<data.length;i++)
        {
            Menu.getProduse().add(new BaseProduct(data[i][1],Double.parseDouble(data[i][2]),Integer.parseInt(data[i][3]),
                    Integer.parseInt(data[i][4]),Integer.parseInt(data[i][5]),Integer.parseInt(data[i][6]),Integer.parseInt(data[i][7])));

        }
    }
    public static void confirmaUser()
    {
        try {
            CallableStatement s = DBConnection.getC().prepareCall("call insertUser2(?,?,?,?,?,?,?,?)");
            s.setInt(1, Integer.parseInt(AdminFrame.getIdUT().getText()));
            s.setString(2, AdminFrame.getNumeT().getText());
            s.setString(3, AdminFrame.getPrenumeT().getText());
            s.setString(4, AdminFrame.getNrTelT().getText());
            s.setString(5, AdminFrame.getEmailT().getText());
            s.setString(6, AdminFrame.getParolaT().getText());
            s.setString(7, AdminFrame.getAdresaT().getText());
            s.setString(8, AdminFrame.getFunctieT().getText());
            s.execute();
            JOptionPane.showMessageDialog(null, "User adaugat!");
            PreparedStatement ps = DBConnection.getC().prepareStatement("select max(id) from users");
            ResultSet rs = ps.executeQuery();
            int nextId = 1;
            while (rs.next()) {
                nextId = rs.getInt(1) + 1;
            }
            AdminFrame.getIdUT().setText(String.valueOf(nextId));
            AdminFrame.getNumeT() .setText("");
            AdminFrame.getPrenumeT().setText("");
            AdminFrame.getNrTelT().setText("");
            AdminFrame.getEmailT().setText("");
            AdminFrame.getParolaT().setText("");
            AdminFrame.getAdresaT().setText("");
            AdminFrame.getFunctieT().setText("");
            AdminFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "users"));
            ((DefaultTableModel) AdminFrame.getTab1().getT().getModel()).setDataVector(AdminFrame.getDate(),AdminFrame.getHeader());
        } catch (Exception eq) {
            JOptionPane.showMessageDialog(null, "Eroare, reincercati");
            AdminFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "users"));
            ((DefaultTableModel) AdminFrame.getTab1().getT().getModel()).setDataVector(AdminFrame.getDate(),AdminFrame.getHeader());
        }
    }

    public static void modificaUser()
    {
        try{
        int selRow=AdminFrame.getTab1().getT().getSelectedRow();
        CallableStatement s = DBConnection.getC().prepareCall("call updateUser(?,?,?,?,?,?,?,?)");
        s.setInt(1, Integer.parseInt(AdminFrame.getTab1().getT().getValueAt(selRow,0).toString()));
        s.setString(2, AdminFrame.getTab1().getT().getValueAt(selRow,1).toString());
        s.setString(3, AdminFrame.getTab1().getT().getValueAt(selRow,2).toString());
        s.setString(4, AdminFrame.getTab1().getT().getValueAt(selRow,3).toString());
        s.setString(5, AdminFrame.getTab1().getT().getValueAt(selRow,4).toString());
        s.setString(6, AdminFrame.getTab1().getT().getValueAt(selRow,5).toString());
        s.setString(7, AdminFrame.getTab1().getT().getValueAt(selRow,6).toString());
        s.setString(8, AdminFrame.getTab1().getT().getValueAt(selRow,7).toString());
        s.execute();
        JOptionPane.showMessageDialog(null, "User modificat!");
        AdminFrame.getUpdateUser().setVisible(false);
        AdminFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "users"));
        ((DefaultTableModel) AdminFrame.getTab1().getT().getModel()).setDataVector(AdminFrame.getDate(),AdminFrame.getHeader());
    } catch (Exception eq) {
        JOptionPane.showMessageDialog(null, "Eroare, reincercati");
        AdminFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "users"));
        ((DefaultTableModel) AdminFrame.getTab1().getT().getModel()).setDataVector(AdminFrame.getDate(),AdminFrame.getHeader());
      }
    }
    public static void modificaProdus()
    {
        AdminFrame.getUpdateb().setVisible(false);
        try {
            if (AdminFrame.getTab2().getT().isEditing()) {
                AdminFrame.getTab2().getT().getCellEditor().stopCellEditing();
            }
            int selRow = AdminFrame.getTab2().getT().getSelectedRow();

            CallableStatement s = DBConnection.getC().prepareCall("{call updateProdus(?,?,?,?,?,?,?,?)}");
            s.setInt(1, Integer.parseInt(AdminFrame.getTab2().getT().getValueAt(selRow, 0).toString()));
            s.setString(2, AdminFrame.getTab2().getT().getValueAt(selRow, 1).toString());
            s.setDouble(3, Double.parseDouble(AdminFrame.getTab2().getT().getValueAt(selRow, 2).toString()));
            s.setInt(4, Integer.parseInt(AdminFrame.getTab2().getT().getValueAt(selRow, 3).toString()));
            s.setInt(5, Integer.parseInt(AdminFrame.getTab2().getT().getValueAt(selRow, 4).toString()));
            s.setInt(6, Integer.parseInt(AdminFrame.getTab2().getT().getValueAt(selRow, 5).toString()));
            s.setInt(7, Integer.parseInt(AdminFrame.getTab2().getT().getValueAt(selRow, 6).toString()));
            s.setInt(8, Integer.parseInt(AdminFrame.getTab2().getT().getValueAt(selRow, 7).toString()));

            s.execute();

            AdminFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "products"));
            ((DefaultTableModel) AdminFrame.getTab2().getT().getModel()).setDataVector(AdminFrame.getDate1(), AdminFrame.getHeader1());
            loadProducts();
            AdminFrame.getUpdateb().setVisible(false);
            JOptionPane.showMessageDialog(null, "Produs modificat!");

        } catch (Exception sqe) {
            AdminFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "products"));
            ((DefaultTableModel) AdminFrame.getTab2().getT().getModel()).setDataVector(AdminFrame.getDate1(), AdminFrame.getHeader1());
            JOptionPane.showMessageDialog(null,"Eroare, reincearca");
        }
    }
    public static void incarcaProduse()
    {
       Serializator.citireProduse();
    }

}
