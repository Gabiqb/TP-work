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

public class UsersFrame extends JFrame {
    private String numeS, prenumeS, nr_telS, emailS, parolaS, adresaS;
    private int id;
    private String[][] date;
    private String[] header;
    private JButton sterge;
    private JButton updateb;
    private JButton confirma;

    private JTextField idT;
    private JTextField numeT;
    private JTextField prenumeT;
    private JTextField emailT;
    private JTextField parolaT;
    private JTextField nr_telT;
    private JLabel tabela;
    private JLabel searchL;
    private JTextField searchT;
    private JButton logout;
    private Table tab1;
    private EroareInsert err;
    private int selectedRow;
    private int selRow ;


    public UsersFrame(Connection c) {
        Font font2 = new Font("Georgia", Font.BOLD, 12);
        this.setTitle("Users");

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
        tabela.setText("Tabela de utilizatori");
        tabela.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        tabela.setOpaque(true);
        tabela.setBounds(580, 50, 145, 50);
        tabela.setBackground(new Color(252, 242, 206));
        tabela.setFont(font2);
        this.add(tabela);

        date = ExtragereDate.getData(c, "users");
        header = ExtragereAntet.getHeader(c, "users");
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
            ps = c.prepareStatement("select max(id) from users");

            ResultSet rs = ps.executeQuery();
            int nextId = 1;
            while (rs.next()) {
                nextId = rs.getInt(1) + 1;
            }
            idT.setText(String.valueOf(nextId));
        } catch (SQLException throwables) {
            date = ExtragereDate.getData(c, "users");
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
        parolaT.setBounds(1070, 590, 150, 30);
        parolaT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel parolaL = new JLabel("Parola");
        parolaL.setOpaque(true);
        parolaL.setBackground(new Color(252, 242, 206));
        parolaL.setFont(new Font("Georgia", Font.BOLD, 16));
        parolaL.setBounds(1110, 570, 134, 20);
        parolaL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        this.add(parolaL);
        this.add(parolaT);

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

        sterge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedRow = tab1.getT().getSelectedRow();
                date = ExtragereDate.getData(c, "users");

                if (selectedRow >= 0) {
                    if (date.length > selectedRow) {
                        try {
                            selRow = Integer.parseInt(date[selectedRow][0]); //id pentru stergere
                            PreparedStatement s = c.prepareCall("{call stergeUtilizator(?)}"); ///sterge un utilizator
                            s.setInt(1, selRow);
                            s.execute();
                            PreparedStatement ps = c.prepareStatement("select max(id) from users");
                            ResultSet rs = ps.executeQuery();
                            int nextId = 1;
                            while (rs.next()) {
                                nextId = rs.getInt(1) + 1;
                            }
                            idT.setText(String.valueOf(nextId));
                            date = ExtragereDate.getData(c, "users");

                            ((DefaultTableModel) tab1.getT().getModel()).setDataVector(date, header);
                            updateb.setVisible(false);
                        } catch (Exception sqe) {
                            date = ExtragereDate.getData(c, "users");
                        }
                    } else if (tab1.getT().getSelectedRow() >= 0) {
                        date = ExtragereDate.getData(c, "users");
                        ((DefaultTableModel) tab1.getT().getModel()).setDataVector(date, header);
                    }
                }
            }


        });
        updateb = new JButton("Confirmi modificarea?");
        updateb.setBounds(1000, 100, 160, 50);
        updateb.setFont(new Font("Georgia", Font.BOLD, 12));
        updateb.setBackground(new Color(252, 242, 206));
        updateb.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        updateb.setFocusPainted(false);
        updateb.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                updateb.setVisible(false);
                try {
                    if (tab1.getT().isEditing()) {
                        tab1.getT().getCellEditor().stopCellEditing();
                    }
                    selectedRow = tab1.getT().getSelectedRow();
                    id = Integer.parseInt((tab1.getT().getValueAt(selectedRow, 0)).toString());
                    numeS = (tab1.getT().getValueAt(selectedRow, 1)).toString();
                    prenumeS = (tab1.getT().getValueAt(selectedRow, 2)).toString();
                    nr_telS = (tab1.getT().getValueAt(selectedRow, 3)).toString();
                    emailS = (tab1.getT().getValueAt(selectedRow, 4)).toString();
                    parolaS = (tab1.getT().getValueAt(selectedRow, 5)).toString();


                    CallableStatement s = c.prepareCall("{call updateUser(?,?,?,?,?,?)}");
                    s.setInt(1, id);
                    s.setString(2, numeS);
                    s.setString(3, prenumeS);
                    s.setString(4, nr_telS);
                    s.setString(5, emailS);
                    s.setString(6, parolaS);
                    s.execute();

                    date = ExtragereDate.getData(c, "users");
                    ((DefaultTableModel) tab1.getT().getModel()).setDataVector(date, header);

                } catch (Exception sqe) {
                    date = ExtragereDate.getData(c, "users");
                    err.setVisible(true);
                }
            }

        });
        updateb.setVisible(false);
        add(updateb);

        confirma = new JButton("Confirma adaugarea");
        confirma.setBounds(1000, 360, 160, 50);
        confirma.setFont(new Font("Georgia", Font.BOLD, 12));
        confirma.setBackground(new Color(252, 242, 206));
        confirma.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        confirma.setFocusPainted(false);
        confirma.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                updateb.setVisible(false);
                try {
                    if (tab1.getT().isEditing()) {
                        tab1.getT().getCellEditor().stopCellEditing();
                    }
                    id = Integer.parseInt(idT.getText());
                    numeS = numeT.getText();
                    prenumeS = prenumeT.getText();
                    nr_telS = nr_telT.getText();
                    emailS = emailT.getText();
                    parolaS = parolaT.getText();

                    CallableStatement s = c.prepareCall("{call insertUser(?,?,?,?,?,?)}");

                    s.setInt(1, id);
                    s.setString(2, numeS);
                    s.setString(3, prenumeS);
                    s.setString(4, nr_telS);
                    s.setString(5, emailS);
                    s.setString(6, parolaS);


                    s.execute();
                    date = ExtragereDate.getData(c, "users");
                    PreparedStatement ps = c.prepareStatement("select max(id) from users");
                    ResultSet rs = ps.executeQuery();
                    int nextId = 1;
                    while (rs.next()) {
                        nextId = rs.getInt(1) + 1;
                    }
                    idT.setText(String.valueOf(nextId));
                    ((DefaultTableModel) tab1.getT().getModel()).setDataVector(date, header);

                    numeT.setText("");
                    prenumeT.setText("");
                    nr_telT.setText("");
                    emailT.setText("");
                    parolaT.setText("");



                } catch (Exception sqe) {
                    err.setVisible(true);
                    date = ExtragereDate.getData(c, "users");
                }

            }
        });
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
        err.getContinua().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                err.setVisible(false);
                date = ExtragereDate.getData(c, "users");
                ((DefaultTableModel) tab1.getT().getModel()).setDataVector(date, header);

            }
        });

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

