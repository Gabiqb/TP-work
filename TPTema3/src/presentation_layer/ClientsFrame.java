package presentation_layer;

import business_logic.ExtragereAntet;
import business_logic.ExtragereDate;
import model.ClockPane;
import model.Table;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ClientsFrame extends JFrame {
    private static String numeS, prenumeS, nr_telS, emailS, parolaS, adresaS;
    private static int id;
    private static String[][] date;
    private static String[] header;
    private static JButton sterge;
    private static JButton updateb;
    private static JButton confirma;

    private static JTextField idT;
    private static JTextField numeT;
    private static JTextField prenumeT;
    private static JTextField emailT;
    private static JTextField parolaT;
    private static JTextField nr_telT;
    private static JTextArea adresaT;
    private static JLabel tabela;
    private static JLabel searchL;
    private static JTextField searchT;
    private static JButton logout;
    private static Table tab1;
    private static EroareInsert err;
    private static int selectedRow = 0;

    public static String getNumeS() {
        return numeS;
    }

    public static void setNumeS(String numeS) {
        ClientsFrame.numeS = numeS;
    }

    public static String getPrenumeS() {
        return prenumeS;
    }

    public static void setPrenumeS(String prenumeS) {
        ClientsFrame.prenumeS = prenumeS;
    }

    public static String getNr_telS() {
        return nr_telS;
    }

    public static void setNr_telS(String nr_telS) {
        ClientsFrame.nr_telS = nr_telS;
    }

    public static String getEmailS() {
        return emailS;
    }

    public static void setEmailS(String emailS) {
        ClientsFrame.emailS = emailS;
    }

    public static String getParolaS() {
        return parolaS;
    }

    public static void setParolaS(String parolaS) {
        ClientsFrame.parolaS = parolaS;
    }

    public static String getAdresaS() {
        return adresaS;
    }

    public static void setAdresaS(String adresaS) {
        ClientsFrame.adresaS = adresaS;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        ClientsFrame.id = id;
    }

    public static String[][] getDate() {
        return date;
    }

    public static void setDate(String[][] date) {
        ClientsFrame.date = date;
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

    public static JTextField getNumeT() {
        return numeT;
    }

    public static JTextField getPrenumeT() {
        return prenumeT;
    }

    public static JTextField getEmailT() {
        return emailT;
    }

    public static JTextField getParolaT() {
        return parolaT;
    }

    public static JTextField getNr_telT() {
        return nr_telT;
    }

    public static JTextArea getAdresaT() {
        return adresaT;
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
        ClientsFrame.selectedRow = selectedRow;
    }

    public static int getSelRow() {
        return selRow;
    }

    public static void setSelRow(int selRow) {
        ClientsFrame.selRow = selRow;
    }

    private static int selRow = 0;


    public ClientsFrame(Connection c) {
        Font font2 = new Font("Georgia", Font.BOLD, 12);
        this.setTitle("Clients");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(119, 161, 160));
        this.setLayout(null);

        this.pack();
        this.setSize(800, 601);
        this.setBounds(160, 50, 1280, 760);
        ClockPane c1 = new ClockPane();
        c1.setBounds(0, 0, 320, 50);
        c1.setOpaque(false);
        this.add(c1);

        err = new EroareInsert();
        tabela = new JLabel();
        tabela.setText("Tabela de clienti");
        tabela.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        tabela.setOpaque(true);
        tabela.setBounds(580, 50, 145, 50);
        tabela.setBackground(new Color(252, 242, 206));
        tabela.setFont(font2);
        this.add(tabela);

        date = ExtragereDate.getData(c, "clients");
        header = ExtragereAntet.getHeader(c, "clients");
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
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("select max(id) from clients");

            ResultSet rs = ps.executeQuery();
            int nextId = 1;
            while (rs.next()) {
                nextId = rs.getInt(1) + 1;
            }
            idT.setText(String.valueOf(nextId));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            date = ExtragereDate.getData(c, "users");
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

        numeT = new JTextField("");
        numeT.setBounds(260, 590, 150, 30);
        numeT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel numeL = new JLabel("Nume client");
        numeL.setOpaque(true);
        numeL.setBackground(new Color(252, 242, 206));
        numeL.setFont(new Font("Georgia", Font.BOLD, 16));
        numeL.setBounds(250, 560, 110, 20);
        numeL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        this.add(numeL);
        this.add(numeT);

        prenumeT = new JTextField("");
        prenumeT.setBounds(470, 590, 150, 30);
        prenumeT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel prenumeL = new JLabel("Prenume client");
        prenumeL.setOpaque(true);
        prenumeL.setBackground(new Color(252, 242, 206));
        prenumeL.setFont(new Font("Georgia", Font.BOLD, 16));
        prenumeL.setBounds(490, 560, 138, 20);
        prenumeL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        this.add(prenumeL);
        this.add(prenumeT);


        nr_telT = new JTextField("");
        nr_telT.setBounds(670, 590, 150, 30);
        nr_telT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel nr_telL = new JLabel("Nr_tel");
        nr_telL.setOpaque(true);
        nr_telL.setBackground(new Color(252, 242, 206));
        nr_telL.setFont(new Font("Georgia", Font.BOLD, 16));
        nr_telL.setBounds(700, 560, 64, 20);
        nr_telL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        this.add(nr_telL);
        this.add(nr_telT);

        emailT = new JTextField("");
        emailT.setBounds(870, 590, 150, 30);
        emailT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel emailL = new JLabel("Email");
        emailL.setOpaque(true);
        emailL.setBackground(new Color(252, 242, 206));
        emailL.setFont(new Font("Georgia", Font.BOLD, 16));
        emailL.setBounds(890, 560, 124, 20);
        emailL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        this.add(emailL);
        this.add(emailT);

        parolaT = new JTextField("");
        parolaT.setBounds(60, 680, 150, 100);
        parolaT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel parolaL = new JLabel("Parola");
        parolaL.setOpaque(true);
        parolaL.setBackground(new Color(252, 242, 206));
        parolaL.setFont(new Font("Georgia", Font.BOLD, 16));
        parolaL.setBounds(1110, 570, 134, 20);
        parolaL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        this.add(parolaL);
        this.add(parolaT);


        JLabel adresaL = new JLabel("Adresa");
        adresaL.setOpaque(true);
        adresaL.setBackground(new Color(252, 242, 206));
        adresaL.setFont(new Font("Georgia", Font.BOLD, 16));
        adresaL.setBounds(60, 660, 114, 20);
        adresaL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        this.add(adresaL);
        adresaT = new JTextArea("");
        adresaT.setLineWrap(true);
        adresaT.setBounds(1070, 590, 150, 30);
        adresaT.setFont(new Font("Georgia", Font.BOLD, 12));
        this.add(adresaT);


        ///textfields pentru inserare

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


}

