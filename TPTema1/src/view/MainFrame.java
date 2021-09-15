package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JButton confirma1;
    private JButton confirma2;
    private JButton aduna;
    private JButton scade;
    private JButton imparte;
    private JButton inmulteste;

    public JButton getConfirma1() {
        return confirma1;
    }

    public JButton getConfirma2() {
        return confirma2;
    }

    public JButton getAduna() {
        return aduna;
    }

    public JButton getScade() {
        return scade;
    }

    public JButton getImparte() {
        return imparte;
    }

    public JButton getInmulteste() {
        return inmulteste;
    }

    public JButton getDeriveaza() {
        return deriveaza;
    }

    public JButton getIntegreaza() {
        return integreaza;
    }

    public JTextArea getP1() {
        return p1;
    }

    public JTextArea getP2() {
        return p2;
    }

    public JTextArea getP3() {
        return p3;
    }

    public JTextArea getRest() {
        return rest;
    }

    public JLabel getRestlabel() {
        return restlabel;
    }

    private JButton deriveaza;
    private JButton integreaza;
    private JTextArea p1;
    private JTextArea p2;
    private JTextArea p3;
    private JTextArea rest;
    private JLabel restlabel;
    private JLabel rezultat;
    public MainFrame()
    {
        Font font = new Font("Arial", Font.BOLD, 20);

        this.setTitle("Calculator polinoame");
        this.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(1280, 900);
        this.setBounds(200,100,1000,600);
        this.getContentPane().setBackground(new Color(57, 61, 57));

        confirma1 = new JButton("Confirma");
        confirma1.setBounds(100,250,150,50);
        confirma1.setFont(font);
        confirma1.setFocusPainted(false);
        confirma1.setBackground(Color.YELLOW);
        confirma1.setForeground(Color.BLACK);
        this.add(confirma1);

        confirma2 = new JButton("Confirma");
        confirma2.setBounds(450,250,150,50);
        confirma2.setFont(font);
        confirma2.setFocusPainted(false);
        confirma2.setBackground(Color.RED);
        confirma2.setForeground(Color.BLACK);
        this.add(confirma2);

        aduna= new JButton("Aduna");
        aduna.setBounds(750,20,150,50);
        aduna.setFont(font);
        aduna.setFocusPainted(false);
        aduna.setBackground(Color.BLUE);
        aduna.setForeground(Color.WHITE);
        this.add(aduna);

        scade= new JButton("Scade");
        scade.setBounds(750, 100,150,50);
        scade.setFont(font);
        scade.setFocusPainted(false);
        scade.setBackground(Color.BLUE);
        scade.setForeground(Color.WHITE);
        this.add(scade);

        inmulteste= new JButton("Inmulteste");
        inmulteste.setBounds(750, 180,150,50);
        inmulteste.setFont(font);
        inmulteste.setFocusPainted(false);
        inmulteste.setBackground(Color.BLUE);
        inmulteste.setForeground(Color.WHITE);
        this.add(inmulteste);

        imparte= new JButton("Imparte");
        imparte.setBounds(750, 260,150,50);
        imparte.setFont(font);
        imparte.setFocusPainted(false);
        imparte.setBackground(Color.BLUE);
        imparte.setForeground(Color.WHITE);
        this.add(imparte);

        deriveaza= new JButton("Deriveaza");
        deriveaza.setBounds(750, 340,150,50);
        deriveaza.setFont(font);
        deriveaza.setFocusPainted(false);
        deriveaza.setBackground(Color.BLUE);
        deriveaza.setForeground(Color.WHITE);
        this.add(deriveaza);

        integreaza= new JButton("Integreaza");
        integreaza.setBounds(750, 420,150,50);
        integreaza.setFont(font);
        integreaza.setFocusPainted(false);
        integreaza.setBackground(Color.BLUE);
        integreaza.setForeground(Color.WHITE);
        this.add(integreaza);

        p1= new JTextArea();
        p1.setBounds(50, 320,300,220);
        p1.setFont(font);
        p1.setBackground(new Color(115, 123, 115));
        p1.setLineWrap(true);
        p1.setWrapStyleWord(true);
        this.add(p1);
        p2= new JTextArea();
        p2.setBackground(new Color(115, 123, 115));
        p2.setLineWrap(true);
        p2.setWrapStyleWord(true);
        p2.setFont(font);
        p2.setBounds(400, 320,300,220);
        this.add(p2);
        rezultat=new JLabel("Rezultat");
        rezultat.setBounds(250, 70,200,30);
        rezultat.setFont(font);
        rezultat.setBackground(new Color(55, 90, 158));
        rezultat.setForeground(new Color(0, 79, 238));
        this.add(rezultat);

        p3=new JTextArea();
        p3.setBounds(80, 100,500,100);
        p3.setFont(font);
        p3.setLineWrap(true);
        p3.setWrapStyleWord(true);
        p3.setBackground(new Color(132, 162, 217));
        p3.setEditable(false);
        this.add(p3);

        restlabel=new JLabel("Rest");
        restlabel.setBounds(100, 200,100,30);
        restlabel.setFont(font);
        restlabel.setBackground(new Color(55, 90, 158));
        restlabel.setForeground(new Color(0, 79, 238));
        this.add(restlabel);
        restlabel.setVisible(false);
        rest=new JTextArea();
        rest.setBounds(150, 210,300,30);
        rest.setFont(font);
        rest.setLineWrap(true);
        rest.setWrapStyleWord(true);
        rest.setBackground(new Color(132, 162, 217));
        rest.setEditable(false);
        this.add(rest);
        rest.setVisible(false);

        this.setVisible(true);
    }
}
