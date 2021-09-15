package data_layer;

import business_layer.DBMethods;
import presentation_layer.*;

import javax.swing.*;

import java.sql.CallableStatement;



public class DataUpdate implements DBMethods {
    public DataUpdate() {
        sterge();
        confirma();
        modifica();

        RegisterFrame.getButton1().addActionListener(e -> {
            try {
                CallableStatement s = DBConnection.getC().prepareCall("{call insertUser(?,?,?,?,?,?)}");
                s.setString(1, RegisterFrame.getNumeT().getText());
                s.setString(2, RegisterFrame.getPrenumeT().getText());
                s.setString(3, RegisterFrame.getNrTelT().getText());
                s.setString(4, RegisterFrame.getEmailT().getText());
                s.setString(5, String.valueOf(RegisterFrame.getPasswordField().getPassword()));
                s.setString(6, RegisterFrame.getAdresaT().getText());
                s.execute();

                RegisterFrame.getNumeT().setText("");
                RegisterFrame.getPrenumeT().setText("");
                RegisterFrame.getNrTelT().setText("");
                RegisterFrame.getEmailT().setText("");
                RegisterFrame.getPasswordField().setText("");
                RegisterFrame.getAdresaT().setText("");
                JOptionPane.showMessageDialog(null,"Inregistrarea a avut loc cu succes");

            } catch (Exception sqe) {
                JOptionPane.showMessageDialog(null,"Introduceti date valide");
            }
        });

    }

    @Override
    public void sterge() {
        AdminFrame.getStergeUser().addActionListener(e -> {
            AdminData.stergeUser();

        });

        AdminFrame.getSterge().addActionListener(e -> {
            AdminData.stergeProdus();

        });
        ClientsFrame.getSterge().addActionListener(e -> {
            ClientData.stergeProdus();
        });

    }

    @Override
    public void confirma() {
        AdminFrame.getConfirma().addActionListener(e -> {
            AdminData.confirmaProdus();
        });
        AdminFrame.getConfirmaUser().addActionListener(e -> {
            AdminData.confirmaUser();
        });
        ClientsFrame.getAdaugaProdus().addActionListener(e -> {
            ClientData.adaugaProdus();
        });
        ClientsFrame.getConfirma().addActionListener(e -> {
            ClientData.confirmaComanda();
        });

        AdminData.loadProducts();
        Serializator.deserializeOrder();
    }

    @Override
    public void modifica() {
        AdminFrame.getUpdateUser().addActionListener(e -> AdminData.modificaUser());

        AdminFrame.getUpdateb().addActionListener(e -> AdminData.modificaProdus());
        AdminFrame.getEmite().addActionListener(e->{
            try {
                if(AdminFrame.getCriteriu().getSelectedItem().toString().equals("interval_comenzi"))
                    AdminData.interval_Comenzi( AdminFrame.getCriteriuT().getText() );
                else if(AdminFrame.getCriteriu().getSelectedItem().toString().equals("zi_comanda"))
                    AdminData.zi_Produse(AdminFrame.getCriteriuT().getText());
            }
            catch(Exception eq)
            {
                JOptionPane.showMessageDialog(null,"Introduceti datele corect");
            }
        });
        AdminFrame.getIncarca().addActionListener(e -> AdminData.incarcaProduse());
        ClientsFrame.getUpdateb().addActionListener(e -> ClientData.modificaClient());
        OrdersFrame.getUpdateb().addActionListener(e -> OrdersData.modificaUser());
    }
}
