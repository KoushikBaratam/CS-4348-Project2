import java.util.Random;
import java.util.concurrent.Semaphore;

public class Teller extends Thread {

    // teller id
    public int id;

    // shared bank reference
    private Bank bank;

    // random delays
    private Random rand;

    // current customer data
    private Customer currentCustomer;
    private String transaction;

    // control flag
    private boolean running;

    // synchronization semaphores
    private Semaphore customerArrived;
    public Semaphore finished;

    public Teller(int id, Bank bank) {

        this.id = id;
        this.bank = bank;

        rand = new Random();

        running = true;

        customerArrived = new Semaphore(0);
        finished = new Semaphore(0);
    }

    // assign customer to teller
    public synchronized void assignCustomer(Customer c, String trans) {

    }

    // stop teller thread
    public void stopTeller() {

    }

    public void run() {

    }
}