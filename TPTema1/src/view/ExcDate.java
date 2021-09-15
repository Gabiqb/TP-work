package view;

import javax.swing.*;
import java.awt.*;

public class ExcDate extends JFrame {
    private JTextArea l1;
    public ExcDate()
    {
        this.setLayout(null);
       this.setBounds(650,350,200,165);
        this.setResizable(false);
        l1=new JTextArea("Date introduse gresit: este necesara forma nrx^nr" +
                "\nex:2x^2, 3, -2x,-3x^7,+2x^3");
        l1.setLineWrap(true);
        l1.setWrapStyleWord(true);
        l1.setFont(new Font("Comic Sans",Font.BOLD,16));
        l1.setBounds(0,0,200,150);
        l1.setBackground(new Color(158, 17, 30));
        l1.setForeground(Color.WHITE);
        this.add(l1);
    }
}
