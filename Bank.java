import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Bank {

    // shared resources
    public Semaphore safe;
    public Semaphore manager;
    public Semaphore door;

    // teller availability
    public Semaphore availableTellers;

    // queue of ready tellers
    public Queue<Teller> readyTellers;

    // customer count
    public int served;
    public final int TOTAL_CUSTOMERS = 50;

    public Bank() {

        // initialize semaphores
        safe = new Semaphore(2);
        manager = new Semaphore(1);
        door = new Semaphore(2);
        availableTellers = new Semaphore(0);

        // initialize queue
        readyTellers = new LinkedList<>();

        served = 0;
    }

    // add ready teller to queue
    public synchronized void addReadyTeller(Teller t) {

    }

    // get next teller
    public synchronized Teller getReadyTeller() {
        return null;
    }

    // mark customer served
    public synchronized void customerServed() {

    }

    // check if all customers done
    public synchronized boolean allDone() {
        return false;
    }

    // logging method
    public synchronized void log(String msg) {

    }
}