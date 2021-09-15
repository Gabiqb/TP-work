package presentation_layer;


import model.ExtragereAntet;
import model.ExtragereDate;


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
import java.util.Locale;


public class ClientsFrame extends JFrame {

    private static String[][] date;
    private static String[] header;

    private static JButton sterge;
    private static JButton confirma;
    private static JButton adaugaProdus;
    private static JButton updateb;

    private static JComboBox produseCombo;
    private static JComboBox cosProduse;
    private static JTextField totalT;
    private static JTextField detaliiT;

    public static JTextField getDetaliiT() {
        return detaliiT;
    }

    private static JLabel tabela;
    private static JLabel searchL;
    private static JTextField searchT;
    private static JButton logout;
    private static JTextField cantitateT;
    private static Table tab1;

    ///partea de clients
    private static JLabel tabela2;
    private static String[][] date1;
    private static String[] header1;
    private static JLabel searchL2;
    private static JTextField searchT2;
    private static Table tab2;

    public static String[][] getDate() {
        return date;
    }

    public static void setDate(String[][] date) {
        ClientsFrame.date = date;
    }


    public static JButton getSterge() {
        return sterge;
    }

    public static JButton getConfirma() {
        return confirma;
    }

    public static JButton getAdaugaProdus() {
        return adaugaProdus;
    }

    public static JComboBox getProduseCombo() {
        return produseCombo;
    }


    public static JComboBox getCosProduse() {
        return cosProduse;
    }

    public static JButton getLogout() {
        return logout;
    }

    public static JTextField getCantitateT() {
        return cantitateT;
    }


    public static JTextField getTotalT() {
        return totalT;
    }

    public static Table getTab1() {
        return tab1;
    }

    public static Table getTab2() {
        return tab2;
    }

    public static JButton getUpdateb() {
        return updateb;
    }


    public ClientsFrame(Connection c) {
        Font font2 = new Font("Georgia", Font.BOLD, 12);
        this.setTitle("Client");
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0, 50, 1280, 760);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(119, 161, 160));
        //  panel1.setBounds(0,0,1280,760);
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
        tabela.setText("Produse");
        tabela.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        tabela.setOpaque(true);
        tabela.setBounds(580, 0, 145, 50);
        tabela.setBackground(new Color(252, 242, 206));
        tabela.setFont(font2);
        panel1.add(tabela);

        date = ExtragereDate.getData(c, "products");
        header = ExtragereAntet.getHeader(c, "products");
        tab1 = new Table(date, header);

        tab1.setBounds(50, 50, 890, 450);
        tab1.getT().setPreferredScrollableViewportSize(new Dimension(850,420));
        panel1.add(tab1);
        tab1.getT().setFont(font2);
        tab1.getT().getTableHeader().setFont(font2);
        tab1.getT().getTableHeader().setBackground(new Color(92, 143, 243));
        tab1.getT().getTableHeader().setReorderingAllowed(false);
        tab1.getT().setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tab1.getT().setModel(new DefaultTableModel(date, header) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;

            }
        });
        panel1.setForeground(new Color(153, 153, 153));

        ///textfields pentru inserare

        cantitateT = new JTextField("");
        cantitateT.setBounds(870, 530, 150, 30);
        cantitateT.setFont(new Font("Georgia", Font.BOLD, 12));

        JLabel cantitateL = new JLabel("Cantitate");
        cantitateL.setOpaque(true);
        cantitateL.setBackground(new Color(252, 242, 206));
        cantitateL.setFont(new Font("Georgia", Font.BOLD, 16));
        cantitateL.setBounds(880, 510, 122, 20);
        cantitateL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(cantitateL);
        panel1.add(cantitateT);

        totalT = new JTextField("0");
        totalT.setBounds(30, 530, 100, 30);
        totalT.setFont(new Font("Georgia", Font.BOLD, 12));
        totalT.setEditable(false);

        JLabel totalL = new JLabel("Total: LEI");
        totalL.setOpaque(true);
        totalL.setBackground(new Color(252, 242, 206));
        totalL.setFont(new Font("Georgia", Font.BOLD, 16));
        totalL.setBounds(10, 510, 122, 20);
        totalL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(totalL);
        panel1.add(totalT);

        produseCombo = new JComboBox(comboFiller());
        produseCombo.setBounds(450, 530, 350, 30);
        produseCombo.setFont(new Font("Georgia", Font.BOLD, 12));

        JLabel produseComboL = new JLabel("Produse");
        produseComboL.setOpaque(true);
        produseComboL.setBackground(new Color(252, 242, 206));
        produseComboL.setFont(new Font("Georgia", Font.BOLD, 16));
        produseComboL.setBounds(480, 510, 122, 20);
        produseComboL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(produseComboL);
        panel1.add(produseCombo,BorderLayout.NORTH);


        cosProduse = new JComboBox();
        cosProduse.setBounds(170, 530, 250, 30);
        cosProduse.setFont(new Font("Georgia", Font.BOLD, 12));

        JLabel cosProduseL = new JLabel("Cos produse");
        cosProduseL.setOpaque(true);
        cosProduseL.setBackground(new Color(252, 242, 206));
        cosProduseL.setFont(new Font("Georgia", Font.BOLD, 16));
        cosProduseL.setBounds(180, 510, 122, 20);
        cosProduseL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(cosProduseL);
        panel1.add(cosProduse);

        detaliiT = new JTextField("");
        detaliiT.setBounds(670, 600, 200, 30);
        detaliiT.setFont(new Font("Georgia", Font.BOLD, 12));


        JLabel detaliiL = new JLabel("Detalii");
        detaliiL.setOpaque(true);
        detaliiL.setBackground(new Color(252, 242, 206));
        detaliiL.setFont(new Font("Georgia", Font.BOLD, 16));
        detaliiL.setBounds(680, 580, 122, 20);
        detaliiL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(detaliiL);
        panel1.add(detaliiT);

        sterge = new JButton("Șterge");
        sterge.setBounds(550, 570, 100, 50);
        sterge.setFont(new Font("Georgia", Font.BOLD, 12));
        sterge.setBackground(new Color(252, 242, 206));
        sterge.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        sterge.setFocusPainted(false);
        panel1.add(sterge);

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
            public void removeUpdate(DocumentEvent e) {
                tab1.filter(searchT.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                tab1.filter(searchT.getText());
            }
        });
        panel1.add(searchT);


        adaugaProdus = new JButton("Adauga");
        adaugaProdus.setBounds(1050, 500, 160, 50);
        adaugaProdus.setFont(new Font("Georgia", Font.BOLD, 12));
        adaugaProdus.setBackground(new Color(252, 242, 206));
        adaugaProdus.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        adaugaProdus.setFocusPainted(false);
        panel1.add(adaugaProdus);

        confirma = new JButton("Confirma comanda");
        confirma.setBounds(1050, 560, 160, 50);
        confirma.setFont(new Font("Georgia", Font.BOLD, 12));
        confirma.setBackground(new Color(252, 242, 206));
        confirma.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        confirma.setFocusPainted(false);
        panel1.add(confirma);


        logout = new JButton("Logout");
        logout.setBounds(1150, 20, 100, 50);
        logout.setFont(new Font("Georgia", Font.BOLD, 13));
        logout.setBackground(new Color(216, 0, 55));
        logout.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        logout.setFocusPainted(false);
        panel1.add(logout);

        JPanel panel2 = new JPanel();
        tabela2 = new JLabel();
        tabela2.setText("Date client");
        tabela2.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        tabela2.setOpaque(true);
        tabela2.setBounds(580, 50, 145, 50);
        tabela2.setBackground(new Color(252, 242, 206));
        tabela2.setFont(font2);
        panel2.add(tabela2);

        panel2.setBounds(0, 50, 1280, 760);
        panel2.setLayout(null);
        panel2.setBackground(new Color(40, 97, 141));

        tab2 = new Table(date1, header1);
        panel2.add(tab2);
        tab2.setBounds(50, 100, 890, 450);

        tab2.getT().setFont(font2);
        tab2.getT().getTableHeader().setFont(font2);
        tab2.getT().getTableHeader().setBackground(new Color(92, 143, 243));
        tab2.getT().getTableHeader().setReorderingAllowed(false);
        tab2.getT().setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tab2.getT().setModel(new DefaultTableModel(date1, header1) {
            @Override
            public boolean isCellEditable(int row, int col) {
                if (col == 0 || col == 7)
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
                tab2.filter(searchT2.getText().toLowerCase(Locale.ROOT));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                tab2.filter(searchT2.getText().toLowerCase(Locale.ROOT));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                tab2.filter(searchT2.getText().toLowerCase(Locale.ROOT));
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
        panel2.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (tab2.getT().isEditing()) {
                    updateb.setVisible(true);
                    tab2.getT().getCellEditor().stopCellEditing();
                }
            }
        });

        tabbedPane.add("Orders tab", panel1);
        tabbedPane.add("Clients tab", panel2);
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

    public static String[] comboFiller() {
        String[] prod = new String[date.length];
        for (int i = 0; i < date.length; i++) {
            prod[i] = date[i][1] + ": " + date[i][7] + " lei";
        }
        return prod;
    }

    public static String[] getHeader() {
        return header;
    }
}

