package model;

import model.Server;
import model.Strategy;
import model.Task;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t) {
        int min=Integer.MAX_VALUE;
        int serverIndex=0;
        for(int i=0;i<servers.size();i++)
          {
              if(servers.get(i).getTasks().size()<min)
              {
                  min=servers.get(i).getTasks().size();
                  serverIndex=i;
              }
          }
        servers.get(serverIndex).addTask(t);
    }
}
