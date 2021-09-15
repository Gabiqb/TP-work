package view;

import controller.Scheduler;
import controller.SimulationManager;
import model.SelectionPolicy;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationFrame extends JFrame implements Runnable {

    private SimulationManager m;
    private JButton simulate;
    private JTextArea log;
    private JLabel maxPl;
    private JLabel minPl;
    private JLabel maxAl;
    private JLabel minAl;
    private JLabel nrOfClientsL;
    private JLabel nrOfServL;
    private JLabel timeLimitL;
    private JComboBox strat;
    private JTextField maxP;
    private JTextField minP;
    private JTextField maxA;
    private JTextField minA;
    private JTextField nrOfClients;
    private JTextField nrOfServ;
    private JTextField timeLimit;
    public JTextArea getLog() {
        return log;
    }

    public JTextField getMaxP() {
        return maxP;
    }

    public JTextField getMinP() {
        return minP;
    }

    public JTextField getMaxA() {
        return maxA;
    }

    public JTextField getMinA() {
        return minA;
    }

    public JTextField getNrOfClients() {
        return nrOfClients;
    }

    public JTextField getNrOfServ() {
        return nrOfServ;
    }

    public JTextField getTimeLimit() {
        return timeLimit;
    }

    public JButton getSimulate() {
        return simulate;
    }

    public SimulationFrame(SimulationManager man)
    {
        Thread t=new Thread(this);
        t.start();

        this.m=man;
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(250,50,900,600);
        this.getContentPane().setBackground(new Color(17, 17, 17, 216));
        simulate=new JButton("Simulate");
        simulate.setBounds(700,50,100,50);
        this.add(simulate);

        timeLimitL=new JLabel("Time limit");
        timeLimitL.setForeground(new Color(196, 56, 56));
        timeLimitL.setFont(new Font("Georgia",Font.BOLD,14));
        timeLimitL.setBounds(60,20,100,20);
        this.add(timeLimitL);

        timeLimit=new JTextField( );
        timeLimit.setBounds(50,50,100,30);
        this.add(timeLimit);

        nrOfClients=new JTextField( );
        nrOfClients.setBounds(200,50,100,30);
        this.add(nrOfClients);

        nrOfClientsL=new JLabel("Number of clients");
        nrOfClientsL.setForeground(new Color(196, 56, 56));
        nrOfClientsL.setFont(new Font("Georgia",Font.BOLD,14));
        nrOfClientsL.setBounds(185,20,130,20);
        this.add(nrOfClientsL);

        nrOfServ=new JTextField( );
        nrOfServ.setBounds(350,50,100,30);
        this.add(nrOfServ);

        nrOfServL=new JLabel("Number of queues");
        nrOfServL.setForeground(new Color(196, 56, 56));
        nrOfServL.setFont(new Font("Georgia",Font.BOLD,14));
        nrOfServL.setBounds(335,20,140,20);
        this.add(nrOfServL);

        minP=new JTextField( );
        minP.setBounds(500,50,100,30);
        this.add( minP);

        minPl=new JLabel("Min processing time");
        minPl.setForeground(new Color(196, 56, 56));
        minPl.setFont(new Font("Georgia",Font.BOLD,14));
        minPl.setBounds(500,20,160,20);
        this.add(minPl);

        nrOfServL=new JLabel("Number of queues");
        nrOfServL.setForeground(new Color(196, 56, 56));
        nrOfServL.setFont(new Font("Georgia",Font.BOLD,14));
        nrOfServL.setBounds(335,20,140,20);
        this.add(nrOfServL);


        maxP=new JTextField( );
        maxP.setBounds(480,150,100,30);
        this.add( maxP);

        maxPl=new JLabel("Max processing time");
        maxPl.setForeground(new Color(196, 56, 56));
        maxPl.setFont(new Font("Georgia",Font.BOLD,14));
        maxPl.setBounds(465,120,160,20);
        this.add(maxPl);

        minA=new JTextField( );
        minA.setBounds(130,150,100,30);
        this.add( minA);

        minAl=new JLabel("Max arrival time");
        minAl.setForeground(new Color(196, 56, 56));
        minAl.setFont(new Font("Georgia",Font.BOLD,14));
        minAl.setBounds(295,120,140,20);
        this.add(minAl);

        maxA=new JTextField( );
        maxA.setBounds(300,150,100,30);
        this.add( maxA);

        maxAl=new JLabel("Min arrival time");
        maxAl.setForeground(new Color(196, 56, 56));
        maxAl.setFont(new Font("Georgia",Font.BOLD,14));
        maxAl.setBounds(120,120,140,20);
        this.add(maxAl);



        log=new JTextArea();
        log.setLineWrap(true);
        DefaultCaret caret = (DefaultCaret)log.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        JScrollPane scroll=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        log.setEditable(false);
        log.setForeground(new Color(255, 255, 255, 238));
        log.setBackground(new Color(75, 28, 66));
        log.setFont(new Font("Georgia",Font.BOLD,14));
        scroll.add(log);
        scroll.setForeground(new Color(0, 0, 0));
        scroll.setBounds(50,200,750,350);
        scroll.setViewportView(log);
        this.add(scroll);

        strat=new JComboBox(new String[]{"Shortest queue", "Shortest time"});
        strat.setForeground(new Color(196, 56, 56));
        strat.setFont(new Font("Georgia",Font.BOLD,14));
        strat.setBounds(640,150,150,20);
        this.add(strat);
        this.simulate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    m.setScheduler(null);
                    m.setMaxArrivalTime(Integer.parseInt(m.getFrame().getMaxA().getText()));
                    m.setMinArrivalTime(Integer.parseInt(m.getFrame().getMinA().getText()));
                    m.setMaxProcessingTime(Integer.parseInt(m.getFrame().getMaxP().getText()));
                    m.setMinProcessingTime(Integer.parseInt(m.getFrame().getMinP().getText()));
                    m.setNumberOfClients(Integer.parseInt(m.getFrame().getNrOfClients().getText()));
                    m.setNumberOfServers(Integer.parseInt(m.getFrame().getNrOfServ().getText()));
                    m.setTimeLimit(Integer.parseInt(m.getFrame().getTimeLimit().getText()));
                    m.generateNRandomTasks();
                    m.getFrame().getLog().setText("");
                    m.setScheduler(new Scheduler(m.getNumberOfServers(), m.getNumberOfClients()));
                    if(strat.getSelectedIndex()==0)
                    {
                        m.setSelectionPolicy(SelectionPolicy.SHORTEST_QUEUE);
                    }
                    else
                        m.setSelectionPolicy(SelectionPolicy.SHORTEST_QUEUE);
                    m.getScheduler().changeStrategy(m.getSelectionPolicy());
                }
                catch(Exception eq)
                {
                    JOptionPane.showMessageDialog(null,"Intrduceti date numerice");
                }
                //TODO controller.SimulationManager.getScheduler().changeStrategy(controller.SimulationManager.getSelectionPolicy());
            }
        });

        this.setVisible(true);

    }

    @Override
    public void run() {

    }
}
