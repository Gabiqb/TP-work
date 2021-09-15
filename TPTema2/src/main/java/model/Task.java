package model;

public class Task implements Comparable<Task> {
    private int id;
    private int arrivalTime;
    private int processingTime;
    public int getId() {
        return id;
    }
    public int getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public int getProcessingTime() {
        return processingTime;
    }
    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }
    public Task(int id,int ptime,int atime)
    {
        this.id=id;
        this.processingTime=ptime;
        this.arrivalTime=atime;
    }
    @Override
    public int compareTo(Task o) {
        if(this.getArrivalTime()<o.getArrivalTime())
            return -1;
        else if(this.getArrivalTime()>o.getArrivalTime())
            return 1;
        else
            return 0;
    }
}
