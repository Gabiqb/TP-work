package model;

import controller.SimulationManager;

import java.sql.SQLOutput;
import java.util.Timer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    public Server(int numberOfClients)
    {
        waitingPeriod=new AtomicInteger(0);
        tasks=new ArrayBlockingQueue<>(numberOfClients);
    }
    public void addTask(Task newTask)
    {
        tasks.add(newTask);
        waitingPeriod.getAndAdd(newTask.getProcessingTime());
    }


    @Override
    public void run() {

        while(true) {
            try {
                Thread.sleep(1000);
                if(!tasks.isEmpty()) {
                    Task t = tasks.peek();
                    if (t.getProcessingTime() == 1) {
                        tasks.take();
                        SimulationManager.setServedClients(SimulationManager.getServedClients() + 1);
                    }
                    else {
                        t.setProcessingTime(t.getProcessingTime() - 1);
                    }
                }
                this.waitingPeriod.getAndDecrement();
            }
            catch (InterruptedException e) {
                    e.printStackTrace();
            }
        }
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }
    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }


}
