package zadanie1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class App {
    private ArrayList<Process> processQue = new ArrayList<>();
    private final ArrayList<Process> startedProcessQue = new ArrayList<>();

    public static void run() {
        App app = new App();
        app.generateRandomProcessQue(100);
        app.useFcfsAlgorithm();
        app.useSjfAlgorithm();
        app.useRrsfAlgorithm(3);
    }

    private void generateRandomProcessQue(int processQuantity) {
        final int minDurationTime = 1;
        final int maxDurationTime = 10;

        this.processQue.clear();
        this.startedProcessQue.clear();
        for (int i = 0; i < processQuantity; i++) {
            Random rand = new Random();
            int durationTime = rand.nextInt((maxDurationTime - minDurationTime) + 1) + minDurationTime;
            this.processQue.add(new Process(durationTime, "Process no. " + i, i));
        }
    }

    private void useFcfsAlgorithm() {
        System.out.println("===== FCFS =====");
        this.startedProcessQue.clear();
        this.applyAlgorithm();
    }

    private void useSjfAlgorithm() {
        System.out.println("===== SJF =====");
        ArrayList<Process> tmpProcessQue = new ArrayList<Process>(this.processQue);
        Collections.sort(this.processQue);
        this.startedProcessQue.clear();
        this.applyAlgorithm();
        this.processQue = tmpProcessQue;
    }

    private void applyAlgorithm() {
        int durationTimeSum = 0, serviceTime = 0;

        for (Process process : this.processQue) {
            durationTimeSum += (serviceTime - process.getArivalTime());
            serviceTime += process.getDurationTime();

            this.startedProcessQue.add(process);
        }

        System.out.println("Average time: " + durationTimeSum/this.startedProcessQue.size());
    }

    private void useRrsfAlgorithm(int quantumTime) {
        System.out.println("===== RRS =====");
        this.startedProcessQue.clear();

        int durationTimeSum = 0, serviceTime = 0;
        boolean containsActiveProcesses = false;

        for (Process process : this.processQue) {
            int processRealDurationTime = Math.min(quantumTime, process.getDurationTime());
            durationTimeSum += (serviceTime - process.getArivalTime());
            serviceTime += processRealDurationTime;

            if (process.getDurationTime() > quantumTime) {
                containsActiveProcesses = true;
            }

            this.startedProcessQue.add(process);
        }

        while (containsActiveProcesses) {
            containsActiveProcesses = false;
            for (Process process : this.startedProcessQue) {
                if (process.getDurationTime() > quantumTime) {
                    process.setArivalTime(Math.max(0, process.getArivalTime() - quantumTime));
                    process.setDurationTime(Math.max(0, process.getDurationTime() - quantumTime));
                    containsActiveProcesses = true;

                    int processRealDurationTime = Math.min(quantumTime, process.getDurationTime());
                    durationTimeSum += (serviceTime - process.getArivalTime());
                    serviceTime += processRealDurationTime;
                }
            }
        }

        System.out.println("Average time: " + durationTimeSum/this.startedProcessQue.size());
    }
}
