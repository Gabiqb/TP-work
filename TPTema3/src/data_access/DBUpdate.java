package data_access;

import business_logic.ExtragereDate;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import presentation_layer.ClientsFrame;
import presentation_layer.OrdersFrame;
import presentation_layer.ProductsFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DBUpdate {
    public DBUpdate()
    {
        ProductsFrame.getSterge().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductsFrame.setSelectedRow(ProductsFrame.getTab1().getT().getSelectedRow());
                ProductsFrame.setDate(  ExtragereDate.getData(DBConnection.getC(), "products"));

                if (ProductsFrame.getSelectedRow() >= 0) {
                    if (ProductsFrame.getDate().length > ProductsFrame.getSelectedRow()) {
                        try {
                            ProductsFrame.setSelRow(Integer.parseInt(ProductsFrame.getDate()[ProductsFrame.getSelectedRow()][0])); //id pentru stergere
                            PreparedStatement s = DBConnection.getC().prepareCall("{call stergeProdus(?)}"); ///sterge un utilizator
                            s.setInt(1, ProductsFrame.getSelRow());
                            s.execute();
                            PreparedStatement ps = DBConnection.getC().prepareStatement("select max(id) from products");
                            ResultSet rs = ps.executeQuery();
                            int nextId = 1;
                            while (rs.next()) {
                                nextId = rs.getInt(1) + 1;
                            }
                            ProductsFrame.getIdT().setText(String.valueOf(nextId));
                            ProductsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));

                            ((DefaultTableModel) ProductsFrame.getTab1().getT().getModel()).setDataVector(ProductsFrame.getDate(), ProductsFrame.getHeader());
                            ProductsFrame.getUpdateb().setVisible(false);
                        } catch (Exception sqe) {
                            ProductsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
                        }
                    } else if (ProductsFrame.getTab1().getT().getSelectedRow() >= 0) {
                        ProductsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
                        ((DefaultTableModel) ProductsFrame.getTab1().getT().getModel()).setDataVector(ProductsFrame.getDate(), ProductsFrame.getHeader());
                    }
                }
            }


        });

        ProductsFrame.getUpdateb().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ProductsFrame.getUpdateb().setVisible(false);
                try {
                    if (ProductsFrame.getTab1().getT().isEditing()) {
                        ProductsFrame.getTab1().getT().getCellEditor().stopCellEditing();
                    }
                    ProductsFrame.setSelectedRow(ProductsFrame.getTab1().getT().getSelectedRow());
                    ProductsFrame.setId(Integer.parseInt((ProductsFrame.getTab1().getT().getValueAt(ProductsFrame.getSelectedRow(), 0)).toString()));
                    ProductsFrame.setDenumireS((ProductsFrame.getTab1().getT().getValueAt(ProductsFrame.getSelectedRow(), 1)).toString());
                    ProductsFrame.setCantitate(Integer.parseInt(ProductsFrame.getTab1().getT().getValueAt(ProductsFrame.getSelectedRow(), 2).toString()));

                    CallableStatement s = DBConnection.getC().prepareCall("{call updateProdus(?,?,?)}");
                    s.setInt(1, ProductsFrame.getId());
                    s.setString(2, ProductsFrame.getDenumireS());
                    s.setInt(3, ProductsFrame.getCantitate());

                    s.execute();

                    ProductsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
                    ((DefaultTableModel) ProductsFrame.getTab1().getT().getModel()).setDataVector(ProductsFrame.getDate(), ProductsFrame.getHeader());

                } catch (Exception sqe) {
                    ProductsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
                    ProductsFrame.getErr().setVisible(true);
                }
            }

        });

        ProductsFrame.getConfirma().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductsFrame.getUpdateb().setVisible(false);
                try {
                    if (ProductsFrame.getTab1().getT().isEditing()) {
                        ProductsFrame.getTab1().getT().getCellEditor().stopCellEditing();
                    }
                    ProductsFrame.setId(Integer.parseInt(ProductsFrame.getIdT().getText()));
                    ProductsFrame.setDenumireS( ProductsFrame.getDenumireT().getText());
                    ProductsFrame.setCantitate(Integer.parseInt(ProductsFrame.getCantitateT().getText()));

                    CallableStatement s = DBConnection.getC().prepareCall("{call insertProdus(?,?,?)}");

                    s.setInt(1, ProductsFrame.getId());
                    s.setString(2, ProductsFrame.getDenumireS());
                    s.setInt(3, ProductsFrame.getCantitate());

                    s.execute();
                    ProductsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
                    PreparedStatement ps = DBConnection.getC().prepareStatement("select max(id) from products");
                    ResultSet rs = ps.executeQuery();
                    int nextId = 1;
                    while (rs.next()) {
                        nextId = rs.getInt(1) + 1;
                    }
                    ProductsFrame.getIdT().setText(String.valueOf(nextId));
                    ((DefaultTableModel) ProductsFrame.getTab1().getT().getModel()).setDataVector(ProductsFrame.getDate(), ProductsFrame.getHeader());
                    ProductsFrame.getDenumireT().setText("");
                    ProductsFrame.getCantitateT().setText("");


                } catch (Exception sqe) {
                    ProductsFrame.getErr().setVisible(true);
                    ProductsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
                }

            }
        });

        ProductsFrame.getErr().getContinua().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductsFrame.getErr().setVisible(false);
                ProductsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "products"));
                ((DefaultTableModel) ProductsFrame.getTab1().getT().getModel()).setDataVector(ProductsFrame.getDate(), ProductsFrame.getHeader());

            }
        });

        OrdersFrame.getSterge().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrdersFrame.setSelectedRow(OrdersFrame.getTab1().getT().getSelectedRow());
                OrdersFrame.setDate(ExtragereDate.getData(DBConnection.getC(),"orders"));
                OrdersFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "clients"));
                if (OrdersFrame.getSelectedRow() >= 0) {
                    if (OrdersFrame.getDate().length > OrdersFrame.getSelectedRow()) {
                        try {
                            OrdersFrame.setSelRow(Integer.parseInt(OrdersFrame.getDate()[OrdersFrame.getSelectedRow()][0])); //id pentru stergere
                            PreparedStatement s = DBConnection.getC().prepareCall("{call stergeOrder(?)}"); ///sterge un utilizator
                            s.setInt(1, OrdersFrame.getSelRow());
                            s.execute();
                            PreparedStatement ps=DBConnection.getC().prepareStatement("select max(id) from orders");
                            ResultSet rs=ps.executeQuery();
                            int nextId=1;
                            while(rs.next())
                            {
                                nextId=rs.getInt(1)+1;
                            }
                            OrdersFrame.getIdT().setText(String.valueOf(nextId));
                            PreparedStatement ps1=DBConnection.getC().prepareStatement("select max(id_produs) from orders");
                            ResultSet rs1=ps1.executeQuery();
                            int nextIdP=1;
                            while(rs1.next())
                            {
                                nextIdP=rs1.getInt(1)+1;
                            }
                            OrdersFrame.getIdProdusT().setText(String.valueOf(nextIdP));
                            OrdersFrame.setDate(ExtragereDate.getData(DBConnection.getC(),"orders"));

                            ((DefaultTableModel) OrdersFrame.getTab1().getT().getModel()).setDataVector(OrdersFrame.getDate(), OrdersFrame.getHeader());
                            OrdersFrame.getUpdateb().setVisible(false);
                        } catch (Exception sqe) {

                        }
                    } else if (OrdersFrame.getTab1().getT().getSelectedRow() >= 0) {
                        OrdersFrame.setDate(ExtragereDate.getData(DBConnection.getC(),"orders"));
                        ((DefaultTableModel) OrdersFrame.getTab1().getT().getModel()).setDataVector(OrdersFrame.getDate(), OrdersFrame.getHeader());
                    }
                }
            }
        });

        OrdersFrame.getUpdateb().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                OrdersFrame.getUpdateb().setVisible(false);
                try {
                    if (OrdersFrame.getTab1().getT().isEditing()) {
                        OrdersFrame.getTab1().getT().getCellEditor().stopCellEditing();
                    }
                    OrdersFrame.setSelectedRow(OrdersFrame.getTab1().getT().getSelectedRow());
                    OrdersFrame.setId(Integer.parseInt((OrdersFrame.getTab1().getT().getValueAt(OrdersFrame.getSelectedRow() , 0)).toString()));
                    OrdersFrame.setNumeS((OrdersFrame.getTab1().getT().getValueAt(OrdersFrame.getSelectedRow(), 1)).toString());
                    OrdersFrame.setPrenumeS((OrdersFrame.getTab1().getT().getValueAt(OrdersFrame.getSelectedRow(), 2)).toString());
                    OrdersFrame.setNr_telS((OrdersFrame.getTab1().getT().getValueAt(OrdersFrame.getSelectedRow(), 3)).toString());
                    OrdersFrame.setNumeAgentS((OrdersFrame.getTab1().getT().getValueAt(OrdersFrame.getSelectedRow(), 4)).toString());
                    OrdersFrame.setPrenumeAgentS((OrdersFrame.getTab1().getT().getValueAt(OrdersFrame.getSelectedRow(), 5)).toString());
                    OrdersFrame.setIdProdusS( (OrdersFrame.getTab1().getT().getValueAt(OrdersFrame.getSelectedRow(), 6)).toString());
                    OrdersFrame.setCantitate(Integer.parseInt(OrdersFrame.getTab1().getT().getValueAt(OrdersFrame.getSelectedRow(),7).toString()));
                    OrdersFrame.setAdresaS((OrdersFrame.getTab1().getT().getValueAt(OrdersFrame.getSelectedRow(), 8)).toString());
                    OrdersFrame.setDataEliberareS((OrdersFrame.getTab1().getT().getValueAt(OrdersFrame.getSelectedRow(), 9)).toString());
                    OrdersFrame.setDetaliiS((OrdersFrame.getTab1().getT().getValueAt(OrdersFrame.getSelectedRow(), 10)).toString());


                    CallableStatement s = DBConnection.getC().prepareCall("{call updateOrder(?,?,?,?,?,?,?,?,?,?,?)}");
                    s.setInt(1, OrdersFrame.getId());
                    s.setString(2, OrdersFrame.getNumeS());
                    s.setString(3, OrdersFrame.getPrenumeS());
                    s.setString(4, OrdersFrame.getNumeAgentS());
                    s.setString(5, OrdersFrame.getPrenumeAgentS());
                    s.setString(6, OrdersFrame.getIdProdusS());
                    s.setInt(7, OrdersFrame.getCantitate());
                    s.setString(8, OrdersFrame.getAdresaS());
                    s.setString(9, OrdersFrame.getDataEliberareS());
                    s.setString(10, OrdersFrame.getDetaliiS());
                    s.setString(11, OrdersFrame.getNr_telS());
                    s.execute();

                    OrdersFrame.setDate(ExtragereDate.getData(DBConnection.getC(),"orders"));
                    ((DefaultTableModel) OrdersFrame.getTab1().getT().getModel()).setDataVector(OrdersFrame.getDate(), OrdersFrame.getHeader());

                } catch (Exception sqe) {
                    OrdersFrame.setDate(ExtragereDate.getData(DBConnection.getC(),"orders"));
                    OrdersFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "clients"));
                    OrdersFrame.getErr().setVisible(true);
                }
            }

        });

        OrdersFrame.getErr().getContinua().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdersFrame.getErr().setVisible(false);
                OrdersFrame.setDate(ExtragereDate.getData(DBConnection.getC(),"orders"));
                OrdersFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "clients"));
                ((DefaultTableModel)OrdersFrame.getTab1().getT().getModel()).setDataVector(OrdersFrame.getDate(),OrdersFrame.getHeader());

            }
        });

        OrdersFrame.getConfirma().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                OrdersFrame.getUpdateb().setVisible(false);
                try {

                    if(OrdersFrame.getTab1().getT().isEditing()) {
                        OrdersFrame.getTab1().getT().getCellEditor().stopCellEditing();
                    }
                    String local=OrdersFrame.getIdProdusT().getText().replace(" ","");
                    String qtl=OrdersFrame.getCantitateT().getText().replace(" ","");

                    String qt[]=qtl.split(",");
                    String pt[]=local.split(",");



                    PreparedStatement ps;
                    boolean good=true;
                    for(int i=0;i<qt.length;i++) {
                        ps = DBConnection.getC().prepareCall("select cantitate from products where id=" + pt[i]);
                        ResultSet rs = ps.executeQuery();
                        int out = 0;
                        while (rs.next()) {
                            out = rs.getInt(1);
                            if (out - Integer.parseInt(qt[i]) < 0) {
                                OrdersFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "orders"));
                                OrdersFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "clients"));
                                JOptionPane.showMessageDialog(null, "Out of stock on "+pt[i] + " with the quantity " + out);
                                good=false;
                                break;
                            }
                            else
                            {
                                CallableStatement s = DBConnection.getC().prepareCall("{call subProduct(?,?)}");
                                s.setInt(1, Integer.parseInt(pt[i]));
                                s.setInt(2, Integer.parseInt(qt[i]));
                                s.execute();
                            }
                        }


                    }

                    if(good==true)
                    {
                        File f=new File("receipt"+OrdersFrame.getIdT().getText()+".txt");
                        FileWriter wr=new FileWriter(f);

                        OrdersFrame.setId(Integer.parseInt(OrdersFrame.getIdT().getText()));
                        OrdersFrame.setNumeS(OrdersFrame.getNumeT().getText());
                        OrdersFrame.setPrenumeS(OrdersFrame.getPrenumeT().getText());
                        OrdersFrame.setNr_telS(OrdersFrame.getNr_telT().getText());
                        OrdersFrame.setNumeAgentS(OrdersFrame.getNumeAgent().getText());
                        OrdersFrame.setPrenumeAgentS(OrdersFrame.getPrenumeAgent().getText());
                        OrdersFrame.setIdProdusS(OrdersFrame.getIdProdusT().getText());
                        OrdersFrame.setAdresaS(OrdersFrame.getAdresaT().getText());
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(" HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        OrdersFrame.setDataEliberareS(formatter.format(OrdersFrame.getDataEliberare().getDate()) + dtf.format(now));
                        OrdersFrame.setDetaliiS(OrdersFrame.getDetaliiT().getText());


                        CallableStatement s = DBConnection.getC().prepareCall("{call insertOrder(?,?,?,?,?,?,?,?,?,?,?)}");

                        s.setInt(1, OrdersFrame.getId());
                        s.setString(2, OrdersFrame.getNumeS());
                        s.setString(3, OrdersFrame.getPrenumeS());
                        s.setString(4, OrdersFrame.getNumeAgentS());
                        s.setString(5, OrdersFrame.getPrenumeAgentS());
                        s.setString(6, OrdersFrame.getIdProdusS());
                        s.setString(7, OrdersFrame.getCantitateT().getText());
                        s.setString(8, OrdersFrame.getAdresaS());
                        s.setString(9, OrdersFrame.getDataEliberareS());
                        s.setString(10, OrdersFrame.getDetaliiS());
                        s.setString(11, OrdersFrame.getNr_telS());

                        s.execute();

                        OrdersFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "orders"));
                        ((DefaultTableModel) OrdersFrame.getTab1().getT().getModel()).setDataVector(OrdersFrame.getDate(), OrdersFrame.getHeader());
                        OrdersFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "clients"));


                        wr.write("Order "+OrdersFrame.getIdT().getText()+"\n");
                        wr.write("Nume client:"+OrdersFrame.getNumeT().getText()+"\n");
                        wr.write("Prenume client:"+OrdersFrame.getPrenumeT().getText()+"\n");
                        wr.write("Nume agent:"+OrdersFrame.getNumeAgent().getText()+"\n");
                        wr.write("Prenume agent:"+OrdersFrame.getPrenumeAgent().getText()+"\n");
                        wr.write("Data eliberare:"+OrdersFrame.getDataEliberare().getDate()+"\n");
                        wr.close();
                        OrdersFrame.getIdProdusT().setText("");
                        OrdersFrame.getNumeT().setText("");
                        OrdersFrame.getPrenumeT().setText("");
                        OrdersFrame.getNr_telT().setText("");
                        OrdersFrame.getNumeAgent().setText("");
                        OrdersFrame.getPrenumeAgent().setText("");
                        OrdersFrame.getAdresaT().setText("");
                        OrdersFrame.getDataEliberare().setDate(null);
                        OrdersFrame.getDetaliiT().setText("");
                        OrdersFrame.getIdT().setText(String.valueOf(Integer.parseInt(OrdersFrame.getIdT().getText())+1));
                    }



                } catch (Exception sqe) {
                    OrdersFrame.getErr().setVisible(true);
                    OrdersFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "orders"));
                    OrdersFrame.setDate1(ExtragereDate.getData(DBConnection.getC(), "clients"));
                }

            }
        });

        ClientsFrame.getSterge().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientsFrame.setSelectedRow(ClientsFrame.getTab1().getT().getSelectedRow());
                ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "clients"));

                if ( ClientsFrame.getSelectedRow() >= 0) {
                    if ( ClientsFrame.getDate().length >  ClientsFrame.getSelectedRow()) {
                        try {
                            ClientsFrame.setSelRow(Integer.parseInt( ClientsFrame.getDate()[ ClientsFrame.getSelectedRow()][0])); //id pentru stergere
                            PreparedStatement s = DBConnection.getC().prepareCall("{call stergeClient(?)}"); ///sterge un utilizator
                            s.setInt(1,  ClientsFrame.getSelRow());
                            s.execute();
                            PreparedStatement ps = DBConnection.getC().prepareStatement("select max(id) from clients");
                            ResultSet rs = ps.executeQuery();
                            int nextId = 1;
                            while (rs.next()) {
                                nextId = rs.getInt(1) + 1;
                            }
                            ClientsFrame.getIdT().setText(String.valueOf(nextId));
                            ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "clients"));

                            ((DefaultTableModel)  ClientsFrame.getTab1().getT().getModel()).setDataVector( ClientsFrame.getDate(),  ClientsFrame.getHeader());
                            ClientsFrame.getUpdateb().setVisible(false);
                        } catch (Exception sqe) {

                        }
                    } else if ( ClientsFrame.getTab1().getT().getSelectedRow() >= 0) {
                        ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "clients"));
                        ((DefaultTableModel)  ClientsFrame.getTab1().getT().getModel()).setDataVector( ClientsFrame.getDate(),  ClientsFrame.getHeader());
                    }
                }
            }
        });

        ClientsFrame.getUpdateb().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ClientsFrame.getUpdateb().setVisible(false);
                try {
                    if (ClientsFrame.getTab1().getT().isEditing()) {
                        ClientsFrame.getTab1().getT().getCellEditor().stopCellEditing();
                    }
                    ClientsFrame.setSelectedRow(ClientsFrame.getTab1().getT().getSelectedRow());
                    ClientsFrame.setId(Integer.parseInt((ClientsFrame.getTab1().getT().getValueAt(ClientsFrame.getSelectedRow(), 0)).toString()));
                    ClientsFrame.setNumeS((ClientsFrame.getTab1().getT().getValueAt(ClientsFrame.getSelectedRow(), 1)).toString());
                    ClientsFrame.setPrenumeS((ClientsFrame.getTab1().getT().getValueAt(ClientsFrame.getSelectedRow(), 2)).toString());
                    ClientsFrame.setNr_telS((ClientsFrame.getTab1().getT().getValueAt(ClientsFrame.getSelectedRow(), 3)).toString());
                    ClientsFrame.setEmailS((ClientsFrame.getTab1().getT().getValueAt(ClientsFrame.getSelectedRow(), 4)).toString());
                    ClientsFrame.setParolaS((ClientsFrame.getTab1().getT().getValueAt(ClientsFrame.getSelectedRow(), 6)).toString());
                    ClientsFrame.setAdresaS((ClientsFrame.getTab1().getT().getValueAt(ClientsFrame.getSelectedRow(), 5)).toString());


                    CallableStatement s = DBConnection.getC().prepareCall("{call updateClient(?,?,?,?,?,?,?)}");
                    s.setInt(1, ClientsFrame.getId());
                    s.setString(2, ClientsFrame.getNumeS());
                    s.setString(3, ClientsFrame.getPrenumeS());
                    s.setString(4, ClientsFrame.getNr_telS());
                    s.setString(5, ClientsFrame.getEmailS());
                    s.setString(6, ClientsFrame.getParolaS());
                    s.setString(7, ClientsFrame.getAdresaS());
                    s.execute();

                    ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "clients"));
                    ((DefaultTableModel)  ClientsFrame.getTab1().getT().getModel()).setDataVector( ClientsFrame.getDate(),  ClientsFrame.getHeader());

                } catch (Exception sqe) {
                    ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "clients"));
                    ((DefaultTableModel)  ClientsFrame.getTab1().getT().getModel()).setDataVector( ClientsFrame.getDate(),  ClientsFrame.getHeader());
                    ClientsFrame.getErr().setVisible(true);
                }
            }

        });

        ClientsFrame.getConfirma().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientsFrame.getUpdateb().setVisible(false);
                try {
                    if ( ClientsFrame.getTab1().getT().isEditing()) {
                        ClientsFrame.getTab1().getT().getCellEditor().stopCellEditing();
                    }
                    ClientsFrame.setId(Integer.parseInt( ClientsFrame.getIdT().getText()));
                    ClientsFrame.setNumeS(ClientsFrame.getNumeT().getText());
                    ClientsFrame.setPrenumeS( ClientsFrame.getPrenumeT().getText());
                    ClientsFrame.setNr_telS(ClientsFrame.getNr_telT().getText());
                    ClientsFrame.setEmailS(ClientsFrame.getEmailT().getText());
                    ClientsFrame.setParolaS(ClientsFrame.getParolaT().getText());
                    ClientsFrame.setAdresaS(ClientsFrame.getAdresaT().getText());

                    CallableStatement s = DBConnection.getC().prepareCall("{call insertClient(?,?,?,?,?,?,?)}");

                    s.setInt(1, ClientsFrame.getId());
                    s.setString(2, ClientsFrame.getNumeS());
                    s.setString(3, ClientsFrame.getPrenumeS());
                    s.setString(4, ClientsFrame.getNr_telS());
                    s.setString(5, ClientsFrame.getEmailS());
                    s.setString(6, ClientsFrame.getParolaS());
                    s.setString(7, ClientsFrame.getAdresaS());

                    s.execute();
                    ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "clients"));
                    PreparedStatement ps = DBConnection.getC().prepareStatement("select max(id) from clients");
                    ResultSet rs = ps.executeQuery();
                    int nextId = 1;
                    while (rs.next()) {
                        nextId = rs.getInt(1) + 1;
                    }
                    ClientsFrame.getIdT().setText(String.valueOf(nextId));
                    ((DefaultTableModel) ClientsFrame.getTab1().getT().getModel()).setDataVector(ClientsFrame.getDate(), ClientsFrame.getHeader());

                    ClientsFrame.getNumeT().setText("");
                    ClientsFrame.getPrenumeT().setText("");
                    ClientsFrame.getNr_telT().setText("");
                    ClientsFrame.getEmailT().setText("");
                    ClientsFrame.getParolaT().setText("");
                    ClientsFrame.getAdresaT().setText("");

                } catch (Exception sqe) {
                    ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "users"));
                    ClientsFrame.getErr().setVisible(true);
                }
            }
        });

        ClientsFrame.getErr().getContinua().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientsFrame.getErr().setVisible(false);
                ClientsFrame.setDate(ExtragereDate.getData(DBConnection.getC(), "clients"));
                ((DefaultTableModel) ClientsFrame.getTab1().getT().getModel()).setDataVector(ClientsFrame.getDate(), ClientsFrame.getHeader());

            }
        });


    }
}
