package presentation_layer;

import business_logic.ExtragereAntet;
import business_logic.ExtragereDate;

import datechoosers.calendar.JDateChooser;
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
import java.text.SimpleDateFormat;
import java.util.EventObject;

public class OrdersFrame extends JFrame {
    private static String numeS, prenumeS, nr_telS, numeAgentS, prenumeAgentS, idProdusS, adresaS, dataEliberareS, detaliiS;
    private static int id,cantitate;
    private static String[][] date;
    private static String[] header;
    private static JButton sterge;
    private static JButton updateb;
    private static JButton confirma;

    public static String getNumeS() {
        return numeS;
    }

    public static void setNumeS(String numeS) {
        OrdersFrame.numeS = numeS;
    }

    public static String getPrenumeS() {
        return prenumeS;
    }

    public static void setPrenumeS(String prenumeS) {
        OrdersFrame.prenumeS = prenumeS;
    }

    public static String getNr_telS() {
        return nr_telS;
    }

    public static void setNr_telS(String nr_telS) {
        OrdersFrame.nr_telS = nr_telS;
    }

    public static String getNumeAgentS() {
        return numeAgentS;
    }

    public static void setNumeAgentS(String numeAgentS) {
        OrdersFrame.numeAgentS = numeAgentS;
    }

    public static String getPrenumeAgentS() {
        return prenumeAgentS;
    }

    public static void setPrenumeAgentS(String prenumeAgentS) {
        OrdersFrame.prenumeAgentS = prenumeAgentS;
    }

    public static String getIdProdusS() {
        return idProdusS;
    }

    public static void setIdProdusS(String idProdusS) {
        OrdersFrame.idProdusS = idProdusS;
    }

    public static String getAdresaS() {
        return adresaS;
    }

    public static void setAdresaS(String adresaS) {
        OrdersFrame.adresaS = adresaS;
    }

    public static String getDataEliberareS() {
        return dataEliberareS;
    }

    public static void setDataEliberareS(String dataEliberareS) {
        OrdersFrame.dataEliberareS = dataEliberareS;
    }

    public static String getDetaliiS() {
        return detaliiS;
    }

    public static void setDetaliiS(String detaliiS) {
        OrdersFrame.detaliiS = detaliiS;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        OrdersFrame.id = id;
    }

    public static int getCantitate() {
        return cantitate;
    }

    public static void setCantitate(int cantitate) {
        OrdersFrame.cantitate = cantitate;
    }

    public static String[][] getDate() {
        return date;
    }

    public static void setDate(String[][] date) {
        OrdersFrame.date = date;
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

    public static JTextField getNumeAgent() {
        return numeAgent;
    }

    public static JTextField getPrenumeAgent() {
        return prenumeAgent;
    }

    public static JTextField getNr_telT() {
        return nr_telT;
    }

    public static JTextField getIdProdusT() {
        return idProdusT;
    }

    public static JTextArea getAdresaT() {
        return adresaT;
    }

    public static JTextArea getDetaliiT() {
        return detaliiT;
    }

    public static JDateChooser getDataEliberare() {
        return dataEliberare;
    }

    public static JButton getLogout() {
        return logout;
    }

    public static JTextField getCantitateT() {
        return cantitateT;
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
        OrdersFrame.selectedRow = selectedRow;
    }

    public static int getSelRow() {
        return selRow;
    }

    public static void setSelRow(int selRow) {
        OrdersFrame.selRow = selRow;
    }

    private static JTextField idT;
    private static JTextField numeT;
    private static JTextField prenumeT;
    private static JTextField numeAgent;
    private static JTextField prenumeAgent;
    private static JTextField nr_telT;
    private static JTextField idProdusT;
    private static JTextArea adresaT;
    private static JTextArea detaliiT;
    private static JDateChooser dataEliberare;


    public static void setDate1(String[][] date1) {
        OrdersFrame.date1 = date1;
    }

    private static JLabel tabela;
    private static JLabel searchL;
    private static JTextField searchT;
    private static JButton logout;
    private static JTextField cantitateT;
    private static Table tab1;
    private static EroareInsert err;
    private static int selectedRow = 0;
    private static int selRow = 0;

    ///partea de clients
    private static JLabel tabela2;
    private static String[][] date1;
    private static String[] header1;
    private static JLabel searchL2;
    private static JTextField searchT2;
    private static Table tab2;

    public OrdersFrame(Connection c) {
        Font font2 = new Font("Georgia", Font.BOLD, 12);
        this.setTitle("Orders");
        JTabbedPane tabbedPane=new JTabbedPane();
        tabbedPane.setBounds(0,50,1280,760);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel1=new JPanel();
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

        err = new EroareInsert();
        tabela = new JLabel();
        tabela.setText("Tabela de comenzi");
        tabela.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        tabela.setOpaque(true);
        tabela.setBounds(580, 0, 145, 50);
        tabela.setBackground(new Color(252, 242, 206));
        tabela.setFont(font2);
        panel1.add(tabela);

        date= ExtragereDate.getData(c,"orders");
        header= ExtragereAntet.getHeader(c,"orders");
        tab1 = new Table(date,header);
        panel1.add(tab1);
        tab1.setBounds(50, 50, 890, 450);

        tab1.getT().setFont(font2);
        tab1.getT().getTableHeader().setFont(font2);
        tab1.getT().getTableHeader().setBackground(new Color(92, 143, 243));
        tab1.getT().getTableHeader().setReorderingAllowed(false);
        tab1.getT().setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tab1.getT().setModel(new DefaultTableModel(date, header) {
            @Override
            public boolean isCellEditable(int row, int col) {
                if(col==7 || col==0 || col==6) return false;
                return true;
            }
        });


        panel1.setForeground(new Color(153, 153, 153));

        ///textfields pentru inserare

        idT = new JTextField("");
        PreparedStatement ps;
        try {
            ps = c.prepareStatement("select max(id) from orders");
            ResultSet rs=ps.executeQuery();
            int nextId=1;
            while(rs.next())
            {
                nextId=rs.getInt(1)+1;
            }
            idT.setText(String.valueOf(nextId));
        } catch (SQLException throwables) {
            date = ExtragereDate.getData(c, "users");
            throwables.printStackTrace();
        }
        idT.setEditable(false);
        idT.setBounds(50, 540, 150, 30);
        idT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel idL = new JLabel("ID");
        idL.setOpaque(true);
        idL.setBackground(new Color(252, 242, 206));
        idL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        idL.setFont(new Font("Georgia", Font.BOLD, 16));
        idL.setBounds(100, 520, 48, 20);
        panel1.add(idL);
        panel1.add(idT);

        numeT = new JTextField("");
        numeT.setBounds(260, 540, 150, 30);
        numeT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel numeL = new JLabel("Nume client");
        numeL.setOpaque(true);
        numeL.setBackground(new Color(252, 242, 206));
        numeL.setFont(new Font("Georgia", Font.BOLD, 16));
        numeL.setBounds(250, 520, 110, 20);
        numeL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(numeL);
        panel1.add(numeT);

        prenumeT = new JTextField("");
        prenumeT.setBounds(470, 540, 150, 30);
        prenumeT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel prenumeL = new JLabel("Prenume client");
        prenumeL.setOpaque(true);
        prenumeL.setBackground(new Color(252, 242, 206));
        prenumeL.setFont(new Font("Georgia", Font.BOLD, 16));
        prenumeL.setBounds(490, 520, 138, 20);
        prenumeL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(prenumeL);
        panel1.add(prenumeT);


        nr_telT = new JTextField("");
        nr_telT.setBounds(470, 610, 150, 30);
        nr_telT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel nr_telL = new JLabel("Nr_tel");
        nr_telL.setOpaque(true);
        nr_telL.setBackground(new Color(252, 242, 206));
        nr_telL.setFont(new Font("Georgia", Font.BOLD, 16));
        nr_telL.setBounds(480, 590 , 130, 20);
        nr_telL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(nr_telL);
        panel1.add(nr_telT);

        numeAgent  = new JTextField("");
        numeAgent.setBounds(870, 540, 150, 30);
        numeAgent.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel numeAgentL = new JLabel("Nume agent");
        numeAgentL.setOpaque(true);
        numeAgentL.setBackground(new Color(252, 242, 206));
        numeAgentL.setFont(new Font("Georgia", Font.BOLD, 16));
        numeAgentL.setBounds(890, 520, 124, 20);
        numeAgentL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(numeAgentL);
        panel1.add(numeAgent);

        prenumeAgent = new JTextField("");
        prenumeAgent.setBounds(1070, 540, 150, 30);
        prenumeAgent.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel prenumeAgentL = new JLabel("Prenume agent");
        prenumeAgentL.setOpaque(true);
        prenumeAgentL.setBackground(new Color(252, 242, 206));
        prenumeAgentL.setFont(new Font("Georgia", Font.BOLD, 16));
        prenumeAgentL.setBounds(1110, 520, 134, 20);
        prenumeAgentL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(prenumeAgentL);
        panel1.add(prenumeAgent);


        JLabel adresaL = new JLabel("Adresa");
        adresaL.setOpaque(true);
        adresaL.setBackground(new Color(252, 242, 206));
        adresaL.setFont(new Font("Georgia", Font.BOLD, 16));
        adresaL.setBounds(60, 610, 114, 20);
        adresaL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(adresaL);
        adresaT = new JTextArea("");
        adresaT.setLineWrap(true);
        adresaT.setBounds(60, 630, 150, 100);
        adresaT.setFont(new Font("Georgia", Font.BOLD, 12));
        panel1.add(adresaT);


        JLabel idProdusL = new JLabel("Id produs");
        idProdusL.setOpaque(true);
        idProdusL.setBackground(new Color(252, 242, 206));
        idProdusL.setFont(new Font("Georgia", Font.BOLD, 16));
        idProdusL.setBounds(260, 610, 114, 20);
        idProdusL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(idProdusL);
        idProdusT = new JTextField("");

        idProdusT.setBounds(260, 630, 150, 30);
        idProdusT.setFont(new Font("Georgia", Font.BOLD, 12));
        panel1.add(idProdusT);

        dataEliberare = new JDateChooser();
        dataEliberare.setBounds(670, 540, 150, 30);
        dataEliberare.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel dataEliberareL = new JLabel("Data eliberarii");
        dataEliberareL.setOpaque(true);
        dataEliberareL.setBackground(new Color(252, 242, 206));
        dataEliberareL.setFont(new Font("Georgia", Font.BOLD, 16));
        dataEliberareL.setBounds(680, 510, 134, 20);
        dataEliberareL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(dataEliberareL);
        panel1.add(dataEliberare);

        detaliiT = new JTextArea("");
        detaliiT.setBounds(670, 630, 150, 100);
        detaliiT.setFont(new Font("Georgia", Font.BOLD, 12));
        detaliiT.setLineWrap(true);
        JLabel detaliiL = new JLabel("Detalii");
        detaliiL.setOpaque(true);
        detaliiL.setBackground(new Color(252, 242, 206));
        detaliiL.setFont(new Font("Georgia", Font.BOLD, 16));
        detaliiL.setBounds(710, 610, 72, 20);
        detaliiL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(detaliiL);
        panel1.add(detaliiT);

        cantitateT = new JTextField("");
        cantitateT.setBounds(870, 630, 150, 30);
        cantitateT.setFont(new Font("Georgia", Font.BOLD, 12));

        JLabel cantitateL = new JLabel("Cantitate");
        cantitateL.setOpaque(true);
        cantitateL.setBackground(new Color(252, 242, 206));
        cantitateL.setFont(new Font("Georgia", Font.BOLD, 16));
        cantitateL.setBounds(880, 610, 122, 20);
        cantitateL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 153, 204)));
        panel1.add(cantitateL);
        panel1.add(cantitateT);
        ///textfields pentru inserare

        sterge = new JButton("Șterge");
        sterge.setBounds(1050, 200, 100, 50);
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

        updateb = new JButton("Confirmi modificarea?");
        updateb.setBounds(1000, 50, 160, 50);
        updateb.setFont(new Font("Georgia", Font.BOLD, 12));
        updateb.setBackground(new Color(252, 242, 206));
        updateb.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        updateb.setFocusPainted(false);

        updateb.setVisible(false);
        panel1.add(updateb);

        confirma = new JButton("Confirma adaugarea");
        confirma.setBounds(1000, 310, 160, 50);
        confirma.setFont(new Font("Georgia", Font.BOLD, 12));
        confirma.setBackground(new Color(252, 242, 206));
        confirma.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        confirma.setFocusPainted(false);
        panel1.add(confirma);
        tab1.getT().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(tab1.getT().getSelectedRow()>=0)
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

        err.setBounds(960, 450, 300, 168);
        logout = new JButton("Logout");
        logout.setBounds(1150, 20, 100, 50);
        logout.setFont(new Font("Georgia", Font.BOLD, 13));
        logout.setBackground(new Color(216, 0, 55));
        logout.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        logout.setFocusPainted(false);
        panel1.add(logout);
        panel1.addMouseMotionListener(new MouseMotionListener() {
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

        JPanel panel2=new JPanel();
        tabela2 = new JLabel();
        tabela2.setText("Tabela de clienti");
        tabela2.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        tabela2.setOpaque(true);
        tabela2.setBounds(580, 50, 145, 50);
        tabela2.setBackground(new Color(252, 242, 206));
        tabela2.setFont(font2);
        panel2.add(tabela2);

        panel2.setBounds(0,50,1280,760);
        panel2.setLayout(null);
        panel2.setBackground(new Color(40, 97, 141));
        date1 = ExtragereDate.getData(c, "clients");
        header1 = ExtragereAntet.getHeader(c, "clients");
        tab2 = new Table(date1, header1);
        panel2.add(tab2);
        tab2.setBounds(50, 100, 890, 450);

        tab2.getT().setFont(font2);
        tab2.getT().getTableHeader().setFont(font2);
        tab2.getT().getTableHeader().setBackground(new Color(92, 143, 243));
        tab2.getT().getTableHeader().setReorderingAllowed(false);
        tab2.getT().setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

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


        tabbedPane.add("Orders tab",panel1);
        tabbedPane.add("Clients tab",panel2);
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

