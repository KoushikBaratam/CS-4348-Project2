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
        running = false;
        customerArrived.release();
    }

    public void run() {

        try {
            while (running) {

                // if all customers done, stop
                if (bank.allDone()) {
                    break;
                }

                // teller becomes available
                bank.log("Teller " + id + " []: ready to serve");
                bank.log("Teller " + id + " []: waiting for a customer");

                bank.addReadyTeller(this);

                // wait for customer assignment
                customerArrived.acquire();

                if (!running) {
                    break;
                }

                bank.log("Teller " + id + " [Customer " +
                        currentCustomer.id + "]: serving a customer");

                bank.log("Teller " + id + " [Customer " +
                        currentCustomer.id + "]: asks for transaction");

                // withdrawal needs manager first
                if (transaction.equals("Withdraw")) {

                    bank.log("Teller " + id + " [Customer " +
                            currentCustomer.id +
                            "]: handling withdrawal transaction");

                    bank.log("Teller " + id + " [Customer " +
                            currentCustomer.id +
                            "]: going to the manager");

                    bank.manager.acquire();

                    bank.log("Teller " + id + " [Customer " +
                            currentCustomer.id +
                            "]: getting manager's permission");

                    Thread.sleep(rand.nextInt(26) + 5);

                    bank.log("Teller " + id + " [Customer " +
                            currentCustomer.id +
                            "]: got manager's permission");

                    bank.manager.release();

                } else {

                    bank.log("Teller " + id + " [Customer " +
                            currentCustomer.id +
                            "]: handling deposit transaction");
                }

                // Use safe
                bank.log("Teller " + id + " [Customer " +
                        currentCustomer.id + "]: going to safe");

                bank.safe.acquire();

                bank.log("Teller " + id + " [Customer " +
                        currentCustomer.id + "]: enter safe");

                Thread.sleep(rand.nextInt(41) + 10);

                bank.log("Teller " + id + " [Customer " +
                        currentCustomer.id + "]: leaving safe");

                bank.safe.release();

                bank.log("Teller " + id + " [Customer " +
                        currentCustomer.id + "]: finishes " +
                        transaction.toLowerCase() + " transaction.");

                bank.log("Teller " + id + " [Customer " +
                        currentCustomer.id +
                        "]: wait for customer to leave.");

                finished.release();
            }

            bank.log("Teller " + id + " []: leaving for the day");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}