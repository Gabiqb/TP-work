package presentation_layer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EroareInsert extends JFrame{
    private JLabel ok;

    public JLabel getOk() {
        return ok;
    }

    public JButton getContinua() {
        return continua;
    }

    private JButton continua;
    public EroareInsert() {
        this.setTitle("Eroare");
        this.setResizable(false);
        this.setLayout(null);
        this.pack();
        this.setSize(200,100 );
        this.setBounds(800,450,300,168);
        ImageIcon img = new ImageIcon("icon.png");
        this.setIconImage(img.getImage());

        Font font2 = new Font("Georgia", Font.BOLD, 30);
        try {
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("invalid.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ok=new JLabel("Introduceti date valide");
        ok.setBounds(50,30,180,50);
        ok.setFont(new Font("Georgia",Font.BOLD,14));
        ok.setOpaque(true);
        ok.setForeground(Color.WHITE);
        ok.setBackground(new Color(160, 25, 68));



        continua=new JButton("Continua");
        continua.setBounds(80,90,120,30);
        continua.setFont(new Font("Georgia",Font.BOLD,14));
        continua.setForeground(Color.BLACK);
        continua.setBackground(new Color(			159,	176,	168));
        continua.setBorderPainted(false);
        continua.setFocusPainted(false);
        add(ok);
        add(continua);

    }
}
