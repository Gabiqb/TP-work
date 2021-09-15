package presentation_layer;

import model.ClockPane;

import javax.swing.*;
import java.awt.*;


public class RegisterFrame extends JFrame {


    private static JButton button1;
    private static JButton logout;
    private static JPasswordField passwordField;

    public static JButton getLogout() {
        return logout;
    }

    private static JTextField emailT;
    private static JTextField numeT;
    private static JTextField prenumeT;
    private static JTextField nrTelT;
    private static JTextField adresaT;

    public static JTextField getNrTelT() {
        return nrTelT;
    }
    public static JTextField getAdresaT() {
        return adresaT;
    }
    public static JButton getButton1() {
        return button1;
    }
    public static JPasswordField getPasswordField() {
        return passwordField;
    }


    public static JTextField getEmailT() {
        return emailT;
    }

    public static JTextField getNumeT() {
        return numeT;
    }

    public static JTextField getPrenumeT() {
        return prenumeT;
    }

    public RegisterFrame()
    {
        this.setTitle("Register Frame");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.pack();
        this.setSize(800, 601);
        this.setBounds(560,200,800,601);
        this.getContentPane().setBackground(new Color(121, 79, 79));
        Font font2 = new Font("Georgia", Font.BOLD, 30);

        logout = new JButton("Back");
        logout.setBounds(510, 50, 100, 50);
        logout.setFont(new Font("Georgia", Font.BOLD, 13));
        logout.setBackground(new Color(216, 0, 55));
        logout.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        logout.setFocusPainted(false);
        this.add(logout);
        emailT=new JTextField();
        emailT.setBounds(250, 90, 150, 30);
        emailT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel emailL = new JLabel("Email");
        emailL.setOpaque(true);
        emailL.setBackground(new Color(252, 242, 206));
        emailL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        emailL.setFont(new Font("Georgia", Font.BOLD, 16));
        emailL.setBounds(270, 60, 108, 20);
        this.add(emailL);
        this.add(emailT);

        passwordField=new JPasswordField();
        passwordField.setBounds(250, 190, 150, 30);
        passwordField.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel passL = new JLabel("Password");
        passL.setOpaque(true);
        passL.setBackground(new Color(252, 242, 206));
        passL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        passL.setFont(new Font("Georgia", Font.BOLD, 16));
        passL.setBounds(270,  160, 88, 20);
        this.add(passL);
        this.add(passwordField);


        numeT=new JTextField();
        numeT.setBounds(250, 290, 150, 30);
        numeT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel numeL = new JLabel("Nume");
        numeL.setOpaque(true);
        numeL.setBackground(new Color(252, 242, 206));
        numeL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        numeL.setFont(new Font("Georgia", Font.BOLD, 16));
        numeL.setBounds(270, 260, 108, 20);
        this.add(numeL);
        this.add(numeT);

        prenumeT=new JTextField();
        prenumeT.setBounds(250, 390, 150, 30);
        prenumeT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel prenumeL = new JLabel("Prenume");
        prenumeL.setOpaque(true);
        prenumeL.setBackground(new Color(252, 242, 206));
        prenumeL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        prenumeL.setFont(new Font("Georgia", Font.BOLD, 16));
        prenumeL.setBounds(270, 360, 108, 20);
        this.add(prenumeL);
        this.add(prenumeT);

        nrTelT=new JTextField();
        nrTelT.setBounds(250, 490, 150, 30);
        nrTelT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel nrTelL = new JLabel("Nr_tel");
        nrTelL.setOpaque(true);
        nrTelL.setBackground(new Color(252, 242, 206));
        nrTelL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        nrTelL.setFont(new Font("Georgia", Font.BOLD, 16));
        nrTelL.setBounds(270, 460, 108, 20);
        this.add(nrTelL);
        this.add(nrTelT);

        adresaT=new JTextField();
        adresaT.setBounds(500, 490, 200, 30);
        adresaT.setFont(new Font("Georgia", Font.BOLD, 12));
        JLabel  adresaL = new JLabel("Adresa");
        adresaL.setOpaque(true);
        adresaL.setBackground(new Color(252, 242, 206));
        adresaL.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 2, 3, new java.awt.Color(0, 153, 204)));
        adresaL.setFont(new Font("Georgia", Font.BOLD, 16));
        adresaL.setBounds(520, 460, 108, 20);
        this.add(adresaL);
        this.add(adresaT);

        button1 = new JButton("Register");
        button1.setFont(font2);
        button1.setBounds(510, 235, 250, 50);
        button1.setBackground(new java.awt.Color(0, 26, 6));
        button1.setForeground(new java.awt.Color(82, 10, 51));
        button1.setOpaque(false);
        button1.setBorderPainted(false);
        button1.setFocusPainted(false);
        this.add(button1);


        ClockPane c1 = new ClockPane();
        c1.setBounds(0, 0, 300, 50);
        c1.setOpaque(false);
        this.add(c1);


    }
}
