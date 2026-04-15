import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Bank {

    // shared resources
    public Semaphore safe;
    public Semaphore manager;
    public Semaphore door;

    // available teller counter
    public Semaphore availableTellers;

    // queue of tellers ready to serve
    public Queue<Teller> readyTellers;

    // number of completed customers
    public int served;
    public final int TOTAL_CUSTOMERS = 50;

    // output file writer
    private PrintWriter writer;

    public Bank() {

        // initialize semaphores
        safe = new Semaphore(2);
        manager = new Semaphore(1);
        door = new Semaphore(2);
        availableTellers = new Semaphore(0);

        // queue
        readyTellers = new LinkedList<>();

        served = 0;

        // create log file
        try {
            writer = new PrintWriter(new FileWriter("bank_log.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // add teller to ready queue
    public synchronized void addReadyTeller(Teller t) {
        readyTellers.add(t);
        availableTellers.release();
    }

    // get next teller
    public synchronized Teller getReadyTeller() {
        return readyTellers.poll();
    }

    // count completed customer
    public synchronized void customerServed() {
        served++;
    }

    // check if simulation is complete
    public synchronized boolean allDone() {
        return served >= TOTAL_CUSTOMERS;
    }

    // print and write to file
    public synchronized void log(String msg) {
        System.out.println(msg);

        if (writer != null) {
            writer.println(msg);
            writer.flush();
        }
    }

    // close file
    public synchronized void closeLog() {
        if (writer != null) {
            writer.close();
        }
    }
}