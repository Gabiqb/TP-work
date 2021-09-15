import javax.swing.*;
import java.awt.*;

public class main {
    public static void main(String[] args)
    {
        JFrame f1=new JFrame("frame");
        f1.setLayout(null);
        f1.setBounds(0,0,640,480);
        JButton b1=new JButton("hey");
        b1.setBackground(new Color(0,255,0));
        b1.setBounds(100,100,100,20);
        f1.add(b1);
        
        f1.setVisible(true);
        System.out.println("hello Los Angeles");
    }
}
