package presentation_layer;

import model.ClockPane;

import javax.swing.*;
import java.awt.*;


public class LoginFrame extends JFrame {

    public static JPasswordField getPasswordField() {
        return passwordField;
    }
    public static JTextField getEmailT() {
        return emailT;
    }
    private static JButton button1;
    private static JButton logout;
    private static JPasswordField passwordField;
    private static JTextField emailT;
    public static JButton getButton1() {
        return button1;
    }
    public static JButton getLogout() {
        return logout;
    }

    public LoginFrame() {
        this.setTitle("Login Frame");
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setSize(800, 601);
        this.setBounds(560,200,800,601);
        this.getContentPane().setBackground(new Color(121, 79, 79));
        Font font2 = new Font("Georgia", Font.BOLD, 30);

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


        button1 = new JButton("Login");
        button1.setFont(font2);
        button1.setBounds(510, 235, 250, 50);
        button1.setBackground(new java.awt.Color(0, 26, 6));
        button1.setForeground(new java.awt.Color(82, 10, 51));
        button1.setOpaque(false);
        button1.setBorderPainted(false);
        button1.setFocusPainted(false);
        this.add(button1);
        logout = new JButton("Back");
        logout.setBounds(510, 50, 100, 50);
        logout.setFont(new Font("Georgia", Font.BOLD, 13));
        logout.setBackground(new Color(216, 0, 55));
        logout.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 204)));
        logout.setFocusPainted(false);
        this.add(logout);

        ClockPane c1 = new ClockPane();
        c1.setBounds(0, 0, 300, 50);
        c1.setOpaque(false);
        this.add(c1);

    }
}
