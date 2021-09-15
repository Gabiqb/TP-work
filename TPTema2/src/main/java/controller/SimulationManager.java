package controller;

import model.SelectionPolicy;
import model.Server;
import model.Task;
import view.SimulationFrame;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SimulationManager implements Runnable {
    private SimulationFrame frame;
    private static int servedClients;

    public static int getServedClients() {
        return servedClients;
    }

    public static void setServedClients(int servedClients) {
        SimulationManager.servedClients = servedClients;
    }

    private double averageWTime;
    private double averagePTime;
    private int peakTime = -1;
    private int timeLimit;
    private int maxProcessingTime;
    private int minProcessingTime;
    private int minArrivalTime;
    private int maxArrivalTime;
    private int numberOfServers;
    private int numberOfClients;
    private SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;
    private File f;
    private FileWriter w;
    private Scheduler scheduler;
    private List<Task> generatedTasks;

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public void setMaxProcessingTime(int maxProcessingTime) {
        this.maxProcessingTime = maxProcessingTime;
    }

    public void setMinProcessingTime(int minProcessingTime) {
        this.minProcessingTime = minProcessingTime;
    }

    public void setMinArrivalTime(int minArrivalTime) {
        this.minArrivalTime = minArrivalTime;
    }

    public void setMaxArrivalTime(int maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
    }

    public void setNumberOfServers(int numberOfServers) {
        this.numberOfServers = numberOfServers;
    }

    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }

    public int getNumberOfServers() {
        return numberOfServers;
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public SelectionPolicy getSelectionPolicy() {
        return selectionPolicy;
    }

    public void setSelectionPolicy(SelectionPolicy selectionPolicy) {
        this.selectionPolicy = selectionPolicy;
    }

    public SimulationManager() {
        frame = new SimulationFrame(this);
        f = new File("logs.txt");
        try {
            w = new FileWriter(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printTasks() {
        try {
            int serverNo = 0;
            for (Thread i : scheduler.getThreads()) {
                if (!scheduler.getServers().get(serverNo).getTasks().isEmpty()) {
                    frame.getLog().append(i.getName() + " : ");
                    w.write(i.getName() + " : ");
                    for (Task t : scheduler.getServers().get(serverNo).getTasks()) {
                        frame.getLog().append("(" + t.getId() + "," + t.getArrivalTime() + "," + t.getProcessingTime() + ") ");
                        w.write("(" + t.getId() + "," + t.getArrivalTime() + "," + t.getProcessingTime() + ") ");
                    }
                    frame.getLog().append("\n");
                    w.write("\n");
                } else {
                    frame.getLog().append(i.getName() + " is closed\n");
                    w.write(i.getName() + " is closed\n");
                }
                serverNo++;
            }
            frame.getLog().append("\n");
            w.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public int updateTimes() //peak time
    {
        int peak = 0;
        for (Server s : scheduler.getServers()) {
            for (Task i : s.getTasks()) {
                peak++;
            }
        }
        return peak;
    }

    @Override
    public void run() {
        while (true) {
            try {

                int currentTime = 0;
                if (scheduler != null) {
                    generateNRandomTasks();
                    frame.getLog().setText("");
                    w = new FileWriter(f);
                    int peakData = -1;
                    for (Task i : generatedTasks) {
                        averageWTime += i.getProcessingTime();
                    }
                    averageWTime = averageWTime / timeLimit;
                    while (currentTime < timeLimit) {
                        frame.getLog().append("Time: " + currentTime + "\n");
                        w.write("Time: " + currentTime + "\n");

                        frame.getLog().append("\n");
                        w.write("\n");

                        frame.getLog().append("Waiting clients : ");
                        w.write("Waiting clients : ");

                        for (Task i : generatedTasks) {
                            frame.getLog().append("(" + i.getId() + "," + i.getArrivalTime() + "," + i.getProcessingTime() + ") ");
                            w.write("(" + i.getId() + "," + i.getArrivalTime() + "," + i.getProcessingTime() + ") ");
                        }
                        frame.getLog().append("\n");
                        w.write("\n");

                        for (Task i : generatedTasks) {
                            if (i.getArrivalTime() == currentTime) {
                                averagePTime += i.getProcessingTime();
                                scheduler.dispatchTask(new Task(i.getId(), i.getProcessingTime(), i.getArrivalTime()));
                                i.setProcessingTime(-1);
                                i.setArrivalTime(-1);
                            }
                        }
                        int peak = updateTimes();
                        if (peak > peakData) {
                            peakData = peak;
                            peakTime = currentTime;
                        }
                        printTasks();
                        Thread.sleep(1000);
                        currentTime++;
                        Iterator<Task> it = generatedTasks.iterator();
                        while (it.hasNext()) {
                            Task t = it.next();
                            if (t.getArrivalTime() == -1 && t.getProcessingTime() == -1)
                                it.remove();
                        }
                    }
                    averagePTime = averagePTime / servedClients;
                    frame.getLog().append("Average waiting time: " + averageWTime + "\nAverage processing time: " + averagePTime + "\nPeak hour: " + peakTime + "\n");
                    for (int i = 0; i < scheduler.getThreads().length; i++)
                        frame.getLog().append(scheduler.getThreads()[i].getName() + " is terminated \n");
                    scheduler = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                w.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void generateNRandomTasks() {
        int pTime;
        int aTime;
        generatedTasks = new ArrayList<>();
        for (int i = 0; i < numberOfClients; i++) {
            pTime = (int) (Math.random() * (maxProcessingTime - minProcessingTime) + minProcessingTime);
            aTime = (int) (Math.random() * (maxArrivalTime - minArrivalTime) + minArrivalTime);
            generatedTasks.add(new Task(i, pTime, aTime));
        }
        Collections.sort(generatedTasks);
    }

    public SimulationFrame getFrame() {
        return frame;
    }
}
