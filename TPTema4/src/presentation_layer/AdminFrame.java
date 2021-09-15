package presentation_layer;

import model.ExtragereAntet;
import model.ExtragereDate;

import data_layer.DBConnection;
import model.ClockPane;
import model.Table;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminFrame extends JFrame {

    public static String[][] getDate1() {
        return date1;
    }

    public static String[] getHeader1() {
        return header1;
    }


    public static JButton getLogout() {
        return logout;
    }

    public static Table getTab1() {
        return tab1;
    }

    public static Table getTab2() {
        return tab2;
    }

    private static String[][] date;
    private static String[] header;
    private static String[][] date1;
    private static String[] header1;

    private static JButton stergeUser;
    private static JButton updateUser;
    private static JButton confirmaUser;
    private static JTextField idUT;
    private static JTextField numeT;
    private static JTextField prenumeT;
    private static JTextField nrTelT;
    private static JTextField emailT;
    private static JTextField parolaT;
    private static JTextField adresaT;
    private static JTextField functieT;
    public static String[] getHeader() {
        return header;
    }
    public static JButton getSterge() {
        return sterge;
    }
    public static JButton getUpdateb() {
        return updateb;
    }

    private static JLabel tabela;
    private static JLabel searchL;
    private static JTextField searchT;
    private static JButton logout;
    private static Table tab1;


    ///partea de products
    private static JLabel tabela2;
    private static JLabel searchL2;
    private static JTextField searchT2;
    private static Table tab2;
    private static JTextField idT;
    private static JTextField denumireT;
    private static JTextField ratingT;
    private static JTextField caloriiT;
    private static JTextField proteineT;
    private static JTextField grasimiT;
    private static JTextField carbohidratiT;
    private static JTextField pretT;
    private static JTextField fisierT;
    private static JButton incarca;

    public static JTextField getFisierT() {
        return fisierT;
    }

    public static JButton getIncarca() {
        return incarca;
    }

    private static JButton sterge;
    private static JButton updateb;
    private static JButton confirma;

    private static JTextArea raport;
    private static JButton emite;
    private static JComboBox criteriu;
    private static JTextField criteriuT;

    public static String[][] getDate() {
        return date;
    }

    public static void setDate(String[][] date) {
        AdminFrame.date = date;
    }

    public static void setDate1(String[][] date1) {
        AdminFrame.date1 = date1;
    }

    public static JButton getStergeUser() {
        return stergeUser;
    }

    public static JButton getUpdateUser() {
        return updateUser;
    }

    public static JButton getConfirmaUser() {
        return confirmaUser;
    }

    public static JTextField getIdUT() {
        return idUT;
    }

    public static JTextField getNumeT() {
        return numeT;
    }

    public static JTextField getPrenumeT() {
        return prenumeT;
    }

    public static JTextField getNrTelT() {
        return nrTelT;
    }

    public static JTextField getEmailT() {
        return emailT;
    }

    public static JTextField getParolaT() {
        return parolaT;
    }

    public static JTextField getAdresaT() {
        return adresaT;
    }

    public static JTextField getFunctieT() {
        return functieT;
    }
    public static JTextField getIdT() {
        return idT;
    }

    public static JTextField getDenumireT() {
        return denumireT;
    }

    public static JTextField getRatingT() {
        return ratingT;
    }

    public static JTextField getCaloriiT() {
        return caloriiT;
    }

    public static JTextField getProteineT() {
        return proteineT;
    }

    public static JTextField getGrasimiT() {
        return grasimiT;
    }

    public static JTextField getCarbohidratiT() {
        return carbohidratiT;
    }

    public static JTextField getPretT() {
        return pretT;
    }

    public static JButton getConfirma() {
        return confirma;
    }


    public static JTextArea getRaport() {
        return raport;
    }

    public static JButton getEmite() {
        return emite;
    }

    public static JComboBox getCriteriu() {
        return criteriu;
    }

    public static JTextField getCriteriuT() {
        return criteriuT;
    }

    public AdminFrame() {
        Font font2 = new Font("Georgia", Font.BOLD, 12);
        this.setTitle("Admin frame");
        JTabbedPane tabbedPane=new JTabbedPane();
        tabbedPane.setBounds(0,50,1280,760);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel1=new JPanel();
        panel1.setBackground(new Color(119, 161, 160));

        panel1.setLayout(null);
        this.getContentPane().setBackground(new Color(119, 161, 160));
        this.setLayout(null);

        this.pack();
        this.setSize(800, 601);
        this.setBounds(160, 50, 1280, 760);
        ClockPane c1 = new ClockPane();
        c1.setBounds(0, 0, 320, 50);
        c1.setOpaque(false);
        this.add(c1);


        tabela = new JLabel();
        tabela.setText("Users");
        tabela.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        tabela.setOpaque(true);
        tabela.setBounds(580, 0, 145, 50);
        tabela.setBackground(new Color(252, 242, 206));
        tabela.setFont(font2);
        panel1.add(tabela);


        idUT=new JTextField("");
        PreparedStatement ps;
        try {
            ps = DBConnection.getC().prepareStatement("select max(id) from users");

            ResultSet rs = ps.executeQuery();
            int nextId = 1;
            while (rs.next()) {
                nextId = rs.getInt(1) + 1;
            }
            idUT.setText(String.valueOf(nextId));
        } catch (SQLException throwables) {
            date = ExtragereDate.getData(DBConnection.getC(), "users");

        }
        idUT.setEditable(false);
        idUT.setBounds(50, 590, 150, 30);
        idUT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel idUL = new JLabel("ID");
        idUL.setOpaque(true);
        idUL.setBackground(new Color(252, 242, 206));
        idUL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        idUL.setFont(new Font("Georgia", Font.BOLD, 16));
        idUL.setBounds(100, 560, 48, 20);
        panel1.add( idUL);
        panel1.add(idUT);

        numeT = new JTextField("");
        numeT .setBounds(260, 590, 150, 30);
        numeT .setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel numeL = new JLabel("Nume");
        numeL.setOpaque(true);
        numeL.setBackground(new Color(252, 242, 206));
        numeL.setFont(new Font("Georgia", Font.BOLD, 16));
        numeL.setBounds(250, 560, 100, 20);
        numeL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel1.add( numeL);
        panel1.add( numeT);

        prenumeT = new JTextField("");
        prenumeT.setBounds(470, 590, 150, 30);
        prenumeT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel prenumeL= new JLabel("Prenume");
        prenumeL.setOpaque(true);
        prenumeL.setBackground(new Color(252, 242, 206));
        prenumeL.setFont(new Font("Georgia", Font.BOLD, 16));
        prenumeL.setBounds(490, 560, 100, 20);
        prenumeL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(prenumeL);
        panel1.add(prenumeT);

        nrTelT = new JTextField("");
        nrTelT.setBounds(670, 590, 150, 30);
        nrTelT .setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel nrTelL = new JLabel("Nr tel");
        nrTelL.setOpaque(true);
        nrTelL.setBackground(new Color(252, 242, 206));
        nrTelL.setFont(new Font("Georgia", Font.BOLD, 16));
        nrTelL.setBounds(690, 560, 100, 20);
        nrTelL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(nrTelL);
        panel1.add(nrTelT);

        emailT = new JTextField("");
        emailT .setBounds(870, 590, 150, 30);
        emailT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel emailL = new JLabel("Email");
        emailL.setOpaque(true);
        emailL.setBackground(new Color(252, 242, 206));
        emailL.setFont(new Font("Georgia", Font.BOLD, 16));
        emailL.setBounds(890, 560, 100, 20);
        emailL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(emailL);
        panel1.add(emailT);

        parolaT = new JTextField("");
        parolaT.setBounds(1070, 590, 150, 30);
        parolaT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel parolaL = new JLabel("Parola");
        parolaL .setOpaque(true);
        parolaL .setBackground(new Color(252, 242, 206));
        parolaL .setFont(new Font("Georgia", Font.BOLD, 16));
        parolaL .setBounds(1090, 560, 100, 20);
        parolaL .setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(parolaL );
        panel1.add(parolaT);

        adresaT = new JTextField("");
        adresaT.setBounds(470, 690, 150, 30);
        adresaT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel adresaL = new JLabel("Adresa");
        adresaL.setOpaque(true);
        adresaL.setBackground(new Color(252, 242, 206));
        adresaL.setFont(new Font("Georgia", Font.BOLD, 16));
        adresaL.setBounds(490, 660, 120, 20);
        adresaL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(adresaL);
        panel1.add(adresaT);

        functieT = new JTextField("");
        functieT .setBounds(670, 690, 150, 30);
        functieT .setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel  functieL = new JLabel("Functie");
        functieL.setOpaque(true);
        functieL.setBackground(new Color(252, 242, 206));
        functieL.setFont(new Font("Georgia", Font.BOLD, 16));
        functieL.setBounds(690, 660, 120, 20);
        functieL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(functieL);
        panel1.add(functieT);

        date= ExtragereDate.getData(DBConnection.getC(),"users");
        header= ExtragereAntet.getHeader(DBConnection.getC(),"users");
        tab1 = new Table(date,header);
        panel1.add(tab1);
        tab1.setBounds(50, 50, 890, 450);

        tab1.getT().setFont(font2);
        tab1.getT().setPreferredScrollableViewportSize(new Dimension(850,420));
        tab1.getT().getTableHeader().setFont(font2);
        tab1.getT().getTableHeader().setBackground(new Color(92, 143, 243));
        tab1.getT().getTableHeader().setReorderingAllowed(false);
        tab1.getT().setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tab1.getT().setModel(new DefaultTableModel(date, header) {
            @Override
            public boolean isCellEditable(int row, int col) {
                if(col==0)
                    return false;
                return true;
            }
        });

        panel1.setForeground(new Color(153, 153, 153));

        ///textfields pentru inserare

        stergeUser = new JButton("Șterge");
        stergeUser.setBounds(1050, 200, 100, 50);
        stergeUser.setFont(new Font("Georgia", Font.BOLD, 12));
        stergeUser.setBackground(new Color(252, 242, 206));
        stergeUser.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        stergeUser.setFocusPainted(false);
        panel1.add(stergeUser);

        confirmaUser = new JButton("Confirma adaugarea");
        confirmaUser.setBounds(1000, 300, 160, 50);
        confirmaUser.setFont(new Font("Georgia", Font.BOLD, 12));
        confirmaUser.setBackground(new Color(252, 242, 206));
        confirmaUser.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        confirmaUser.setFocusPainted(false);

        panel1.add(confirmaUser);

        updateUser = new JButton("Confirma modificarea");
        updateUser .setBounds(1000, 50, 160, 50);
        updateUser .setFont(new Font("Georgia", Font.BOLD, 12));
        updateUser .setBackground(new Color(252, 242, 206));
        updateUser .setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        updateUser .setFocusPainted(false);
        updateUser .setVisible(false);
        panel1.add(updateUser);
        panel1.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                if (tab1.getT().isEditing()) {
                    tab1.getT().getCellEditor().stopCellEditing();
                    updateUser.setVisible(true);

                }
            }
        });

        searchL = new JLabel("Cautare ");
        searchL.setBounds(1040, 370, 80, 30);
        searchL.setFont(new Font("Georgia", Font.BOLD, 16));
        searchL.setBackground(new Color(252, 242, 206));
        searchL.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        panel1.add(searchL);

        searchT = new JTextField("");
        searchT.setBounds(1000, 400, 170, 30);
        searchT.setFont(new Font("Georgia", Font.BOLD, 12));
        searchT.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                tab1.filter(searchT.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                tab1.filter(searchT.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                tab1.filter(searchT.getText());
            }
        });
        panel1.add(searchT);

        logout = new JButton("Logout");
        logout.setBounds(1150, 20, 100, 50);
        logout.setFont(new Font("Georgia", Font.BOLD, 13));
        logout.setBackground(new Color(216, 0, 55));
        logout.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        logout.setFocusPainted(false);
        panel1.add(logout);

        JPanel panel2=new JPanel();
        tabela2 = new JLabel();
        tabela2.setText("Products");
        tabela2.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        tabela2.setOpaque(true);
        tabela2.setBounds(580, 50, 145, 50);
        tabela2.setBackground(new Color(252, 242, 206));
        tabela2.setFont(font2);
        panel2.add(tabela2);

        panel2.setBounds(0,50,1280,760);
        panel2.setLayout(null);
        panel2.setBackground(new Color(40, 97, 141));

        date1=ExtragereDate.getData(DBConnection.getC(),"products");
        header1=ExtragereAntet.getHeader(DBConnection.getC(),"products");
        tab2 = new Table(date1, header1);
        panel2.add(tab2);
        tab2.setBounds(50, 100, 890, 450);
        panel2.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                if (tab2.getT().isEditing()) {
                    tab2.getT().getCellEditor().stopCellEditing();
                    updateb.setVisible(true);

                }
            }
        });
        tab2.getT().setFont(font2);
        tab2.getT().setPreferredScrollableViewportSize(new Dimension(850,420));
        tab2.getT().getTableHeader().setFont(font2);
        tab2.getT().getTableHeader().setBackground(new Color(92, 143, 243));
        tab2.getT().getTableHeader().setReorderingAllowed(false);
        tab2.getT().setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tab2.getT().setModel(new DefaultTableModel(date1, header1) {
            @Override
            public boolean isCellEditable(int row, int col) {
                if(col==0)
                    return false;
                return true;
            }
        });
        searchL2 = new JLabel("Cautare ");
        searchL2.setBounds(1040, 420, 80, 30);
        searchL2.setFont(new Font("Georgia", Font.BOLD, 16));
        searchL2.setBackground(new Color(252, 242, 206));
        searchL2.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        panel2.add(searchL2);

        searchT2 = new JTextField("");
        searchT2.setBounds(1000, 450, 170, 30);
        searchT2.setFont(new Font("Georgia", Font.BOLD, 12));
        searchT2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                tab2.filter(searchT2.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e)
            {
                tab2.filter(searchT2.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                tab2.filter(searchT2.getText());
            }
        });
        panel2.add(searchT2);

        updateb = new JButton("Confirma modificarea");
        updateb.setBounds(1000, 50, 160, 50);
        updateb.setFont(new Font("Georgia", Font.BOLD, 12));
        updateb.setBackground(new Color(252, 242, 206));
        updateb.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        updateb.setFocusPainted(false);
        updateb.setVisible(false);
        panel2.add(updateb);
        tab1.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                if (tab1.getT().isRowSelected(tab1.getT().getSelectedRow())) {
                    updateb.setVisible(true);

                }
            }
        });
        sterge = new JButton("Șterge");
        sterge.setBounds(1050, 200, 100, 50);
        sterge.setFont(new Font("Georgia", Font.BOLD, 12));
        sterge.setBackground(new Color(252, 242, 206));
        sterge.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        sterge.setFocusPainted(false);
        panel2.add(sterge);

        confirma = new JButton("Confirma adaugarea");
        confirma.setBounds(1000, 360, 160, 50);
        confirma.setFont(new Font("Georgia", Font.BOLD, 12));
        confirma.setBackground(new Color(252, 242, 206));
        confirma.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        confirma.setFocusPainted(false);

        panel2.add(confirma);

        idT = new JTextField("");

        try {
            ps = DBConnection.getC().prepareStatement("select max(id) from products");

            ResultSet rs = ps.executeQuery();
            int nextId = 1;
            while (rs.next()) {
                nextId = rs.getInt(1) + 1;
            }
            idT.setText(String.valueOf(nextId));
        } catch (SQLException throwables) {
            date = ExtragereDate.getData(DBConnection.getC(), "products");
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
        panel2.add(idL);
        panel2.add(idT);

        denumireT = new JTextField("");
        denumireT.setBounds(260, 590, 150, 30);
        denumireT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel denumireL = new JLabel("Denumire produs");
        denumireL.setOpaque(true);
        denumireL.setBackground(new Color(252, 242, 206));
        denumireL.setFont(new Font("Georgia", Font.BOLD, 16));
        denumireL.setBounds(250, 560, 180, 20);
        denumireL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel2.add(denumireL);
        panel2.add(denumireT);

        ratingT = new JTextField("");
        ratingT.setBounds(470, 590, 150, 30);
        ratingT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel ratingL = new JLabel("Rating");
        ratingL.setOpaque(true);
        ratingL.setBackground(new Color(252, 242, 206));
        ratingL.setFont(new Font("Georgia", Font.BOLD, 16));
        ratingL.setBounds(490, 560, 100, 20);
        ratingL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel2.add(ratingL);
        panel2.add(ratingT);

        caloriiT = new JTextField("");
        caloriiT.setBounds(670, 590, 150, 30);
        caloriiT .setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel caloriiL = new JLabel("Calorii");
        caloriiL.setOpaque(true);
        caloriiL.setBackground(new Color(252, 242, 206));
        caloriiL.setFont(new Font("Georgia", Font.BOLD, 16));
        caloriiL.setBounds(690, 560, 100, 20);
        caloriiL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel2.add(caloriiL);
        panel2.add(caloriiT);

        proteineT = new JTextField("");
        proteineT.setBounds(870, 590, 150, 30);
        proteineT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel proteineL = new JLabel("Proteine");
        proteineL.setOpaque(true);
        proteineL.setBackground(new Color(252, 242, 206));
        proteineL.setFont(new Font("Georgia", Font.BOLD, 16));
        proteineL.setBounds(890, 560, 100, 20);
        proteineL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel2.add(proteineL);
        panel2.add(proteineT);

        grasimiT = new JTextField("");
        grasimiT.setBounds(1070, 590, 150, 30);
        grasimiT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel grasimiL = new JLabel("Grasimi");
        grasimiL.setOpaque(true);
        grasimiL.setBackground(new Color(252, 242, 206));
        grasimiL.setFont(new Font("Georgia", Font.BOLD, 16));
        grasimiL.setBounds(1090, 560, 100, 20);
        grasimiL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel2.add(grasimiL);
        panel2.add(grasimiT);

        carbohidratiT = new JTextField("");
        carbohidratiT.setBounds(470, 690, 150, 30);
        carbohidratiT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel carbohidratiL = new JLabel("Carbohidrati");
        carbohidratiL.setOpaque(true);
        carbohidratiL.setBackground(new Color(252, 242, 206));
        carbohidratiL.setFont(new Font("Georgia", Font.BOLD, 16));
        carbohidratiL.setBounds(490, 660, 120, 20);
        carbohidratiL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel2.add(carbohidratiL);
        panel2.add(carbohidratiT);

        pretT = new JTextField("");
        pretT.setBounds(670, 690, 150, 30);
        pretT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel pretL = new JLabel("Pret");
        pretL.setOpaque(true);
        pretL.setBackground(new Color(252, 242, 206));
        pretL.setFont(new Font("Georgia", Font.BOLD, 16));
        pretL.setBounds(690, 660, 90, 20);
        pretL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel2.add(pretL);
        panel2.add(pretT);


        fisierT = new JTextField("");
        fisierT.setBounds(970, 690, 150, 30);
        fisierT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel fisierL = new JLabel("Numer fisier");
        fisierL.setOpaque(true);
        fisierL.setBackground(new Color(252, 242, 206));
        fisierL.setFont(new Font("Georgia", Font.BOLD, 16));
        fisierL.setBounds(970, 660, 170, 20);
        fisierL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel2.add(fisierL);
        panel2.add(fisierT);

        incarca = new JButton("Incarca");
        incarca.setBounds(1170, 650, 100, 50);
        incarca.setFont(new Font("Georgia", Font.BOLD, 12));
        incarca.setBackground(new Color(252, 242, 206));
        incarca.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        incarca.setFocusPainted(false);
        panel2.add(incarca);

        JPanel panel3=new JPanel();
        panel3.setBounds(0,50,1280,760);
        panel3.setLayout(null);
        panel3.setBackground(new Color(40, 97, 141));


        criteriu=new JComboBox(new String[]{"interval_comenzi","zi_comanda"});
        criteriu.setBounds(1000,100,100,30);
        panel3.add(criteriu);
        emite  = new JButton("Confirma emiterea");
        emite.setBounds(1000, 360, 160, 50);
        emite.setFont(new Font("Georgia", Font.BOLD, 12));
        emite.setBackground(new Color(252, 242, 206));
        emite.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        emite.setFocusPainted(false);
        panel3.add(emite);

        raport = new JTextArea("");
        raport.setBounds(100, 100, 800, 500);
        raport.setFont(new Font("Georgia", Font.BOLD, 12));
        raport.setBackground(new Color(232, 136, 136));
        raport.setEditable(false);
        raport.setLineWrap(true);
        JScrollPane scrollV=new JScrollPane(raport);
        scrollV.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollV.setBounds(100,100,800,500);
        panel3.add(scrollV);
        criteriuT = new JTextField("");
        criteriuT .setBounds(1000, 490, 150, 30);
        criteriuT .setFont(new Font("Georgia", Font.BOLD, 12));
        panel3.add(criteriuT);

        tabbedPane.add("Users tab",panel1);
        tabbedPane.add("Products tab",panel2);
        tabbedPane.add("Reports tab",panel3);
        UIManager.put("TabbedPane.contentBorderInsets", new InsetsUIResource(2, 0, 0, 0));
        UIManager.put("TabbedPane.focus", new ColorUIResource(new Color(252, 242, 206)));
        UIManager.put("TabbedPane.selected", new ColorUIResource(new Color(252, 242, 206)));
        UIManager.put("TabbedPane.darkShadow", new ColorUIResource(Color.BLACK));
        UIManager.put("TabbedPane.borderHightlightColor", new ColorUIResource(Color.BLACK));
        UIManager.put("TabbedPane.light", new ColorUIResource(Color.WHITE));
        UIManager.put("TabbedPane.tabAreaBackground", new ColorUIResource(Color.BLUE));
        UIManager.put("ToolTip.background", Color.BLUE);
        UIManager.put("ToolTip.border", new BorderUIResource(new LineBorder(Color.BLACK)));
        tabbedPane.updateUI();
        this.add(tabbedPane);




    }

}

