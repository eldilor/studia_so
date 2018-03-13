package zadanie1;

public class Process implements Comparable<Process> {
    private int durationTime;
    private final String id;
    private int arivalTime;

    public Process(int durationTime, String id, int arivalTime) {
        this.durationTime = durationTime;
        this.id = id;
        this.arivalTime = arivalTime;
    }

    public int getDurationTime() {
        return durationTime;
    }

    public String getId() {
        return id;
    }

    public int getArivalTime() {
        return arivalTime;
    }

    public void setArivalTime(int arivalTime) {
        this.arivalTime = arivalTime;
    }

    public void setDurationTime(int durationTime) {
        this.durationTime = durationTime;
    }

    @Override
    public int compareTo(Process compareProcess) {
        return this.durationTime - compareProcess.getDurationTime();
    }
}
