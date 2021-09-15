package controller;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;
    private Thread[] threads;

    public Thread[] getThreads() {
        return threads;
    }

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.strategy = new ConcreteStrategyQueue();
        threads = new Thread[maxNoServers];
        servers = new ArrayList<>();
        for (int i = 0; i < this.maxNoServers; i++) {
            servers.add(new Server(this.maxTasksPerServer));
            threads[i] = new Thread(servers.get(i), "Queue-" + i);
            threads[i].start();
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_QUEUE)
            strategy = new ConcreteStrategyQueue();
        else
            strategy = new ConcreteStrategyTime();
    }

    public void dispatchTask(Task t) {
        strategy.addTask(servers, t);
    }

    public List<Server> getServers() {
        return servers;
    }
}
