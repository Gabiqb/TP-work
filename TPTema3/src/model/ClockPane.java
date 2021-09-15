package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

public class ClockPane extends JPanel {

    private JLabel clock;


    public ClockPane() {
        setLayout(new BorderLayout());
        clock = new JLabel();
        clock.setHorizontalAlignment(JLabel.CENTER);
        clock.setFont(UIManager.getFont("Label.font").deriveFont(Font.BOLD, 25));
        tickTock();
        clock.setForeground(new java.awt.Color(0, 26, 6));
        add(clock);

        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tickTock();
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
    }

    public void tickTock() {
        clock.setText(DateFormat.getDateTimeInstance().format(new Date()));
    }
}
