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


public class OrdersFrame extends JFrame {


    public static JButton getLogout() {
        return logout;
    }
    public static Table getTab2() {
        return tab2;
    }
    private static String[][] date1;
    private static String[] header1;
    private static JButton updateb;
    public static JButton getUpdateb() {
        return updateb;
    }
    private static JLabel tabela;
    private static JButton logout;
    private static JTextArea queue;


    ///partea de clients
    private static JLabel tabela2;
    private static JLabel searchL2;
    private static JTextField searchT2;
    private static Table tab2;

    public static JTextArea getQueue() {
        return queue;
    }

    public OrdersFrame() {
        Font font2 = new Font("Georgia", Font.BOLD, 12);
        this.setTitle("Orders");
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
        tabela.setText("Orders");
        tabela.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        tabela.setOpaque(true);
        tabela.setBounds(580, 0, 145, 50);
        tabela.setBackground(new Color(252, 242, 206));
        tabela.setFont(font2);
        panel1.add(tabela);
        queue = new JTextArea("");
        queue.setBounds(100, 100, 800, 500);
        queue.setFont(new Font("Georgia", Font.BOLD, 12));
        queue.setBackground(new Color(232, 136, 136));
        queue.setEditable(false);
        queue.setLineWrap(true);
        JScrollPane scrollV=new JScrollPane(queue);
        scrollV.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollV.setBounds(100,100,800,500);
        panel1.add(scrollV);
        panel1.setForeground(new Color(153, 153, 153));

        ///textfields pentru inserare

        logout = new JButton("Logout");
        logout.setBounds(1150, 20, 100, 50);
        logout.setFont(new Font("Georgia", Font.BOLD, 13));
        logout.setBackground(new Color(216, 0, 55));
        logout.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        logout.setFocusPainted(false);
        panel1.add(logout);

        JPanel panel2=new JPanel();
        tabela2 = new JLabel();
        tabela2.setText("Date agent");
        tabela2.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        tabela2.setOpaque(true);
        tabela2.setBounds(580, 50, 145, 50);
        tabela2.setBackground(new Color(252, 242, 206));
        tabela2.setFont(font2);
        panel2.add(tabela2);

        panel2.setBounds(0,50,1280,760);
        panel2.setLayout(null);
        panel2.setBackground(new Color(40, 97, 141));

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
        tab2.getT().getTableHeader().setFont(font2);
        tab2.getT().getTableHeader().setBackground(new Color(92, 143, 243));
        tab2.getT().getTableHeader().setReorderingAllowed(false);
        tab2.getT().setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tab2.getT().setModel(new DefaultTableModel(date1, header1) {
            @Override
            public boolean isCellEditable(int row, int col) {
                if(col==0 || col==7)
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


        tabbedPane.add("Orders tab",panel1);
        tabbedPane.add("Agent tab",panel2);
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

