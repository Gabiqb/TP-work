package presentation_layer;

import model.ClockPane;

import javax.swing.*;
import java.awt.*;


public class MeniuPrincipal extends JFrame {


    private JButton button1;
    private JButton button2;

    public JButton getButton2() {
        return button2;
    }
    public JButton getButton1() {
        return button1;
    }

    public MeniuPrincipal() {
        this.setTitle("Meniu");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.pack();
        this.setSize(800, 601);
        this.setBounds(560,200,800,601);
        this.getContentPane().setBackground(new Color(121, 79, 79));
        Font font2 = new Font("Georgia", Font.BOLD, 30);

        button1 = new JButton("Login");
        button1.setFont(font2);
        button1.setBounds(310, 235, 250, 50);
        button1.setBackground(new java.awt.Color(0, 26, 6));
        button1.setForeground(new java.awt.Color(82, 10, 51));
        button1.setOpaque(false);
        button1.setBorderPainted(false);
        button1.setFocusPainted(false);
        this.add(button1);

        button2 = new JButton("Register");
        button2.setFont(font2);
        button2.setBounds(310, 235, 250, 50);
        button2.setBackground(new java.awt.Color(0, 26, 6));
        button2.setForeground(new java.awt.Color(82, 10, 51));
        button2.setOpaque(false);
        button2.setBorderPainted(false);
        button2.setFocusPainted(false);
        this.add(button2);



        ClockPane c1 = new ClockPane();
        c1.setBounds(0, 0, 300, 50);
        c1.setOpaque(false);
        this.add(c1);

        this.setVisible(true);
    }
}
