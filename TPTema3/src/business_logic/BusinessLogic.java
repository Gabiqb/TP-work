package business_logic;

import data_access.DBConnection;
import data_access.DBUpdate;
import model.Model;
import presentation_layer.ClientsFrame;
import presentation_layer.MeniuPrincipal;
import presentation_layer.OrdersFrame;
import presentation_layer.ProductsFrame;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BusinessLogic {
    public BusinessLogic() {
        DBConnection d=new DBConnection();
        new Model();
        Model.setmPFrame(new MeniuPrincipal());
        Model.setoFrame(new OrdersFrame(d.getC()));
        Model.setpFrame(new ProductsFrame(d.getC()));
        Model.setcFrame(new ClientsFrame(d.getC()));
        new DBUpdate();

        Model.getcFrame().getLogout().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 OrdersFrame.setDate(ExtragereDate.getData(DBConnection.getC(),"orders"));
                 OrdersFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "clients"));
                 ProductsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
                 ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "clients"));
                 Model.getcFrame().setVisible(false);
                 Model.getmPFrame().setVisible(true);
            }
        });

        Model.getoFrame().getLogout().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdersFrame.setDate(ExtragereDate.getData(DBConnection.getC(),"orders"));
                OrdersFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "clients"));
                ProductsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
                ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "clients"));
                Model.getoFrame().setVisible(false);
                Model.getmPFrame().setVisible(true);
            }
        });
        Model.getpFrame().getLogout().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdersFrame.setDate(ExtragereDate.getData(DBConnection.getC(),"orders"));
                OrdersFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "clients"));
                ProductsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
                ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "clients"));
                Model.getpFrame().setVisible(false);
                Model.getmPFrame().setVisible(true);
            }
        });

        Model.getmPFrame().getButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdersFrame.setDate(ExtragereDate.getData(DBConnection.getC(),"orders"));
                OrdersFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "clients"));
                ProductsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
                ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "clients"));
                Model.getmPFrame().setVisible(false);
                Model.getcFrame().setVisible(true);
            }
        });

        Model.getmPFrame().getButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdersFrame.setDate(ExtragereDate.getData(DBConnection.getC(),"orders"));
                OrdersFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "clients"));
                ProductsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
                ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "clients"));
                Model.getmPFrame().setVisible(false);
                Model.getoFrame().setVisible(true);
            }
        });

        Model.getmPFrame().getButton3().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdersFrame.setDate(ExtragereDate.getData(DBConnection.getC(),"orders"));
                ProductsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
                ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "clients"));
                Model.getmPFrame().setVisible(false);
                Model.getpFrame().setVisible(true);
            }
        });
    }
}

