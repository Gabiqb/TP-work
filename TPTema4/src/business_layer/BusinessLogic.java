package business_layer;

import data_layer.DBConnection;
import data_layer.DataUpdate;
import data_layer.Serializator;
import model.ExecProc;
import model.ExtragereAntet;
import model.ExtragereDate;
import model.Model;
import presentation_layer.*;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BusinessLogic {
    public BusinessLogic() {
        DBConnection d=new DBConnection();
        Model m=new Model();
        m.setmPFrame(new MeniuPrincipal());
        m.setoFrame(new OrdersFrame());
        m.setcFrame(new ClientsFrame(d.getC()));
        m.setrFrame(new RegisterFrame());
        m.setlFrame(new LoginFrame());
        m.setaFrame(new AdminFrame());
        m.setdService(new DeliveryService());
        new DataUpdate();
        //TODO clients frame
        Model.getcFrame().getLogout().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                AdminFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "users"));
                ((DefaultTableModel) AdminFrame.getTab1().getT().getModel()).setDataVector(AdminFrame.getDate(),AdminFrame.getHeader());

                AdminFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "products"));
                ((DefaultTableModel) AdminFrame.getTab2().getT().getModel()).setDataVector(AdminFrame.getDate1(), AdminFrame.getHeader1());



                 Model.getcFrame().setVisible(false);
                 Model.getmPFrame().setVisible(true);
            }
        });

        Model.getoFrame().getLogout().addActionListener(e -> {


            AdminFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "users"));
            ((DefaultTableModel) AdminFrame.getTab1().getT().getModel()).setDataVector(AdminFrame.getDate(),AdminFrame.getHeader());

            AdminFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "products"));
            ((DefaultTableModel) AdminFrame.getTab2().getT().getModel()).setDataVector(AdminFrame.getDate1(), AdminFrame.getHeader1());
            ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
            ((DefaultTableModel)ClientsFrame.getTab1().getT().getModel()).setDataVector(ClientsFrame.getDate(), ClientsFrame.getHeader());
            ClientsFrame.getProduseCombo().removeAllItems();
            String[] combo=ClientsFrame.comboFiller();
            for(int i=0;i<combo.length;i++)
                ClientsFrame.getProduseCombo().addItem(combo[i]);
            Model.getoFrame().setVisible(false);
            Model.getmPFrame().setVisible(true);
        });
        Model.getaFrame().getLogout().addActionListener(e -> {

            AdminFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "users"));
            ((DefaultTableModel) AdminFrame.getTab1().getT().getModel()).setDataVector(AdminFrame.getDate(),AdminFrame.getHeader());

            AdminFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "products"));
            ((DefaultTableModel) AdminFrame.getTab2().getT().getModel()).setDataVector(AdminFrame.getDate1(), AdminFrame.getHeader1());

            ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
            ((DefaultTableModel)ClientsFrame.getTab1().getT().getModel()).setDataVector(ClientsFrame.getDate(), ClientsFrame.getHeader());
            ClientsFrame.getProduseCombo().removeAllItems();
            String[] combo=ClientsFrame.comboFiller();
            for(int i=0;i<combo.length;i++)
                ClientsFrame.getProduseCombo().addItem(combo[i]);

            Model.getaFrame().setVisible(false);
            Model.getmPFrame().setVisible(true);
        });

        Model.getmPFrame().getButton1().addActionListener(e -> {


            AdminFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "users"));
            ((DefaultTableModel) AdminFrame.getTab1().getT().getModel()).setDataVector(AdminFrame.getDate(),AdminFrame.getHeader());

            AdminFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "products"));
            ((DefaultTableModel) AdminFrame.getTab2().getT().getModel()).setDataVector(AdminFrame.getDate1(), AdminFrame.getHeader1());


            ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
            ((DefaultTableModel)ClientsFrame.getTab1().getT().getModel()).setDataVector(ClientsFrame.getDate(), ClientsFrame.getHeader());
            ClientsFrame.getProduseCombo().removeAllItems();
            String[] combo=ClientsFrame.comboFiller();
            for(int i=0;i<combo.length;i++)
                ClientsFrame.getProduseCombo().addItem(combo[i]);

            Model.getlFrame().setVisible(true);
            Model.getmPFrame().setVisible(false);
        });

        Model.getmPFrame().getButton2().addActionListener(e -> {


            AdminFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "users"));
            ((DefaultTableModel) AdminFrame.getTab1().getT().getModel()).setDataVector(AdminFrame.getDate(),AdminFrame.getHeader());

            AdminFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "products"));
            ((DefaultTableModel) AdminFrame.getTab2().getT().getModel()).setDataVector(AdminFrame.getDate1(), AdminFrame.getHeader1());


            ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
            ((DefaultTableModel)ClientsFrame.getTab1().getT().getModel()).setDataVector(ClientsFrame.getDate(), ClientsFrame.getHeader());
            ClientsFrame.getProduseCombo().removeAllItems();
            String[] combo=ClientsFrame.comboFiller();
            for(int i=0;i<combo.length;i++)
                ClientsFrame.getProduseCombo().addItem(combo[i]);
            Model.getmPFrame().setVisible(false);
            Model.getrFrame().setVisible(true);
        });
        Model.getrFrame().getLogout().addActionListener(e -> {

             AdminFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "users"));
            ((DefaultTableModel) AdminFrame.getTab1().getT().getModel()).setDataVector(AdminFrame.getDate(),AdminFrame.getHeader());

            AdminFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "products"));
            ((DefaultTableModel) AdminFrame.getTab2().getT().getModel()).setDataVector(AdminFrame.getDate1(), AdminFrame.getHeader1());

            ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
            ((DefaultTableModel)ClientsFrame.getTab1().getT().getModel()).setDataVector(ClientsFrame.getDate(), ClientsFrame.getHeader());
            ClientsFrame.getProduseCombo().removeAllItems();
            String[] combo=ClientsFrame.comboFiller();
            for(int i=0;i<combo.length;i++)
                ClientsFrame.getProduseCombo().addItem(combo[i]);
            Model.getrFrame().setVisible(false);
            Model.getmPFrame().setVisible(true);
        });


        Model.getlFrame().getLogout().addActionListener(e -> {
            LoginFrame.getPasswordField().setText("");
            LoginFrame.getEmailT().setText("");

            AdminFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "users"));
            ((DefaultTableModel) AdminFrame.getTab1().getT().getModel()).setDataVector(AdminFrame.getDate(),AdminFrame.getHeader());

            AdminFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "products"));
            ((DefaultTableModel) AdminFrame.getTab2().getT().getModel()).setDataVector(AdminFrame.getDate1(), AdminFrame.getHeader1());

            ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
            ((DefaultTableModel)ClientsFrame.getTab1().getT().getModel()).setDataVector(ClientsFrame.getDate(), ClientsFrame.getHeader());
            ClientsFrame.getProduseCombo().removeAllItems();
            String[] combo=ClientsFrame.comboFiller();
            for(int i=0;i<combo.length;i++)
                ClientsFrame.getProduseCombo().addItem(combo[i]);
            Model.getlFrame().setVisible(false);
            Model.getmPFrame().setVisible(true);
        });


        Model.getlFrame().getButton1().addActionListener(e -> {
            try {
                String email,pass;
                email=LoginFrame.getEmailT().getText();
                pass=String.valueOf(LoginFrame.getPasswordField().getPassword());
                CallableStatement s = DBConnection.getC().prepareCall("{call getdata(?,?,?)}");
                s.setString(1, email);
                s.setString(2, pass);
                s.execute();
                int ok=s.getInt(3);
                if(ok==1)
                {
                    LoginFrame.getPasswordField().setText("");
                    LoginFrame.getEmailT().setText("");
                    PreparedStatement ps= DBConnection.getC().prepareStatement("select functie from users where email= '"+email+"' and parola= '"+pass+"'");
                    ResultSet rs=ps.executeQuery();
                    while(rs.next())
                    {
                        String f=rs.getString(1);
                        if(f.equals("client"))
                        {
                            String [][]data;
                            String []header= ExtragereAntet.getHeader(DBConnection.getC(),"users");
                            data= ExecProc.getData(DBConnection.getC(),"select * from users where email='"+email+"' and parola='"+pass+"';");
                            ((DefaultTableModel)ClientsFrame.getTab2().getT().getModel()).setDataVector(data,header);

                            ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
                            ((DefaultTableModel)ClientsFrame.getTab1().getT().getModel()).setDataVector(ClientsFrame.getDate(), ClientsFrame.getHeader());

                            AdminFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "users"));
                            ((DefaultTableModel) AdminFrame.getTab1().getT().getModel()).setDataVector(AdminFrame.getDate(),AdminFrame.getHeader());

                            AdminFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "products"));
                            ((DefaultTableModel) AdminFrame.getTab2().getT().getModel()).setDataVector(AdminFrame.getDate1(), AdminFrame.getHeader1());

                            Model.getlFrame().setVisible(false);
                            Model.getcFrame().setVisible(true);

                        }
                        else if(f.equals("agent"))
                        {
                            String [][]data;
                            String []header=ExtragereAntet.getHeader(DBConnection.getC(),"users");
                            data=ExecProc.getData(DBConnection.getC(),"select * from users where email='"+email+"' and parola='"+pass+"';");
                            ((DefaultTableModel)OrdersFrame.getTab2().getT().getModel()).setDataVector(data,header);

                            AdminFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "users"));
                            ((DefaultTableModel) AdminFrame.getTab1().getT().getModel()).setDataVector(AdminFrame.getDate(),AdminFrame.getHeader());

                            AdminFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "products"));
                            ((DefaultTableModel) AdminFrame.getTab2().getT().getModel()).setDataVector(AdminFrame.getDate1(), AdminFrame.getHeader1());
                            OrdersFrame.getQueue().setText("");
                            Serializator.deserializeOrder();
                            Model.getlFrame().setVisible(false);
                            Model.getoFrame().setVisible(true);
                        }
                        else
                        {

                            ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
                            ((DefaultTableModel)ClientsFrame.getTab1().getT().getModel()).setDataVector(ClientsFrame.getDate(), ClientsFrame.getHeader());

                            AdminFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "users"));
                            ((DefaultTableModel) AdminFrame.getTab1().getT().getModel()).setDataVector(AdminFrame.getDate(),AdminFrame.getHeader());

                            AdminFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "products"));
                            ((DefaultTableModel) AdminFrame.getTab2().getT().getModel()).setDataVector(AdminFrame.getDate1(), AdminFrame.getHeader1());
                            Model.getaFrame().setVisible(true);
                            Model.getlFrame().setVisible(false);
                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Credentiale invalide");
                }


            } catch (Exception sqe) {
                 JOptionPane.showMessageDialog(null,"Eroare, reporniti");
            }
        });
    }
}

