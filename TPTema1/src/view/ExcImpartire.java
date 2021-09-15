package view;

import javax.swing.*;
import java.awt.*;

public class ExcImpartire extends JFrame {
    private JTextArea l1;
    public ExcImpartire()
    {
        this.setLayout(null);
        this.setBounds(650,350,200,165);
        this.setResizable(false);
        l1=new JTextArea("Gradele trebuie sa fie egale \n" +
                "sau coeficientii deimpartitului sa fie divizibili cu cei ai impartitorului");
        l1.setLineWrap(true);
        l1.setWrapStyleWord(true);
        l1.setFont(new Font("Comic Sans",Font.BOLD,16));
        l1.setForeground(Color.WHITE);
        l1.setBounds(0,0,200,150);
        l1.setBackground(new Color(158, 17, 30));
        this.add(l1);
    }
}
