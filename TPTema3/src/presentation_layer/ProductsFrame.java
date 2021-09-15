package presentation_layer;

import business_logic.ExtragereAntet;
import business_logic.ExtragereDate;
import model.ClockPane;
import model.Table;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProductsFrame extends JFrame {
    private static String denumireS;
    private static int id,cantitate;
    private static String[][] date;
    private static String[] header;
    private static JButton sterge;
    private static JButton updateb;
    private static JButton confirma;

    private static JTextField idT;
    private static JTextField denumireT;
    private static JTextField cantitateT;
    private static JLabel tabela;
    private static JLabel searchL;
    private static JTextField searchT;
    private static JButton logout;
    private static  Table tab1;
    private static EroareInsert err;
    private static int selectedRow;
    private static int selRow ;


    public ProductsFrame(Connection c) {
        Font font2 = new Font("Georgia", Font.BOLD, 12);
        this.setTitle("Products");

        this.getContentPane().setBackground(new Color(119, 161, 160));
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setSize(800, 601);
        this.setBounds(160, 50, 1280, 760);
        ClockPane c1 = new ClockPane();
        c1.setBounds(0, 0, 320, 50);
        c1.setOpaque(false);
        this.add(c1);

        err = new EroareInsert();
        tabela = new JLabel();
        tabela.setText("Tabela de produse");
        tabela.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        tabela.setOpaque(true);
        tabela.setBounds(580, 50, 145, 50);
        tabela.setBackground(new Color(252, 242, 206));
        tabela.setFont(font2);
        this.add(tabela);

        date = ExtragereDate.getData(c, "products");
        header = ExtragereAntet.getHeader(c, "products");
        tab1 = new Table(date, header);
        this.add(tab1);
        tab1.setBounds(50, 100, 890, 450);

        tab1.getT().setFont(font2);
        tab1.getT().getTableHeader().setFont(font2);
        tab1.getT().getTableHeader().setBackground(new Color(92, 143, 243));
        tab1.getT().getTableHeader().setReorderingAllowed(false);
        tab1.getT().setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));


        this.setForeground(new Color(153, 153, 153));

        ///textfields pentru inserare

        idT = new JTextField("");
        PreparedStatement ps;
        try {
            ps = c.prepareStatement("select max(id) from products");

            ResultSet rs = ps.executeQuery();
            int nextId = 1;
            while (rs.next()) {
                nextId = rs.getInt(1) + 1;
            }
            idT.setText(String.valueOf(nextId));
        } catch (SQLException throwables) {
            date = ExtragereDate.getData(c, "products");
            throwables.printStackTrace();
        }
        idT.setEditable(false);
        idT.setBounds(50, 590, 150, 30);
        idT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel idL = new JLabel("ID");
        idL.setOpaque(true);
        idL.setBackground(new Color(252, 242, 206));
        idL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        idL.setFont(new Font("Georgia", Font.BOLD, 16));
        idL.setBounds(100, 560, 48, 20);
        this.add(idL);
        this.add(idT);

        denumireT = new JTextField("");
        denumireT.setBounds(260, 590, 150, 30);
        denumireT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel denumireL = new JLabel("Denumire produs");
        denumireL.setOpaque(true);
        denumireL.setBackground(new Color(252, 242, 206));
        denumireL.setFont(new Font("Georgia", Font.BOLD, 16));
        denumireL.setBounds(250, 560, 180, 20);
        denumireL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        this.add(denumireL);
        this.add(denumireT);

        cantitateT = new JTextField("");
        cantitateT.setBounds(470, 590, 150, 30);
        cantitateT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel cantitateL = new JLabel("Cantitate");
        cantitateL.setOpaque(true);
        cantitateL.setBackground(new Color(252, 242, 206));
        cantitateL.setFont(new Font("Georgia", Font.BOLD, 16));
        cantitateL.setBounds(490, 560, 138, 20);
        cantitateL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        this.add(cantitateL);
        this.add(cantitateT);


        sterge = new JButton("Șterge");
        sterge.setBounds(1050, 200, 100, 50);
        sterge.setFont(new Font("Georgia", Font.BOLD, 12));
        sterge.setBackground(new Color(252, 242, 206));
        sterge.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        sterge.setFocusPainted(false);
        this.add(sterge);

        searchL = new JLabel("Cautare ");
        searchL.setBounds(1040, 420, 80, 30);
        searchL.setFont(new Font("Georgia", Font.BOLD, 16));
        searchL.setBackground(new Color(252, 242, 206));
        searchL.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        this.add(searchL);
        searchT = new JTextField("");
        searchT.setBounds(1000, 450, 170, 30);
        searchT.setFont(new Font("Georgia", Font.BOLD, 12));
        searchT.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                tab1.filter(searchT.getText());
            }


            @Override
            public void removeUpdate(DocumentEvent e) {
                tab1.filter(searchT.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                tab1.filter(searchT.getText());
            }
        });
        this.add(searchT);

        updateb = new JButton("Confirmi modificarea?");
        updateb.setBounds(1000, 100, 160, 50);
        updateb.setFont(new Font("Georgia", Font.BOLD, 12));
        updateb.setBackground(new Color(252, 242, 206));
        updateb.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        updateb.setFocusPainted(false);

        updateb.setVisible(false);
        add(updateb);

        confirma = new JButton("Confirma adaugarea");
        confirma.setBounds(1000, 360, 160, 50);
        confirma.setFont(new Font("Georgia", Font.BOLD, 12));
        confirma.setBackground(new Color(252, 242, 206));
        confirma.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        confirma.setFocusPainted(false);

        add(confirma);
        tab1.getT().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tab1.getT().getSelectedRow() >= 0)
                    updateb.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        err.setBounds(960, 500, 300, 168);

        logout = new JButton("Logout");
        logout.setBounds(1150, 20, 100, 50);
        logout.setFont(new Font("Georgia", Font.BOLD, 13));
        logout.setBackground(new Color(216, 0, 55));
        logout.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        logout.setFocusPainted(false);
        this.add(logout);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

                if (tab1.getT().isEditing()) {
                    tab1.getT().getCellEditor().stopCellEditing();

                }
            }
        });



    }

    public static String getDenumireS() {
        return denumireS;
    }

    public static void setDenumireS(String denumireS) {
        ProductsFrame.denumireS = denumireS;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        ProductsFrame.id = id;
    }

    public static int getCantitate() {
        return cantitate;
    }

    public static void setCantitate(int cantitate) {
        ProductsFrame.cantitate = cantitate;
    }

    public static String[][] getDate() {
        return date;
    }

    public static void setDate(String[][] date) {
        ProductsFrame.date = date;
    }

    public static String[] getHeader() {
        return header;
    }

    public static JButton getSterge() {
        return sterge;
    }

    public static JButton getUpdateb() {
        return updateb;
    }

    public static JButton getConfirma() {
        return confirma;
    }

    public static JTextField getIdT() {
        return idT;
    }

    public static JTextField getDenumireT() {
        return denumireT;
    }

    public static JTextField getCantitateT() {
        return cantitateT;
    }


    public static JButton getLogout() {
        return logout;
    }

    public static Table getTab1() {
        return tab1;
    }

    public static EroareInsert getErr() {
        return err;
    }


    public static int getSelectedRow() {
        return selectedRow;
    }

    public static void setSelectedRow(int selectedRow) {
        ProductsFrame.selectedRow = selectedRow;
    }

    public static int getSelRow() {
        return selRow;
    }

    public static void setSelRow(int selRow) {
        ProductsFrame.selRow = selRow;
    }
}

