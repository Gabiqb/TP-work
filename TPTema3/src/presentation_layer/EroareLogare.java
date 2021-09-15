package presentation_layer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EroareLogare extends JFrame {

    public EroareLogare() {
        this.setTitle("Invalid");
        this.setResizable(false);
        this.setLayout(null);
        this.pack();
        this.setSize(200,100 );
        this.setBounds(800,450,300,168);
        ImageIcon img = new ImageIcon("icon.png");
        this.setIconImage(img.getImage());

             Font font2 = new Font("Georgia", Font.BOLD, 30);
        try {
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("error.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
