import java.util.Random;

public class Customer extends Thread {

    // customer id
    public int id;

    // shared bank reference
    private Bank bank;

    // random delays
    private Random rand;

    public Customer(int id, Bank bank) {

        this.id = id;
        this.bank = bank;

        rand = new Random();
    }

    public void run() {

        try {
            // random transaction choice
            String transaction =
                    rand.nextBoolean() ? "Deposit" : "Withdraw";

            bank.log("Customer " + id + " []: wants to perform a " +
                    transaction.toLowerCase() + " transaction");

            // wait before arriving
            Thread.sleep(rand.nextInt(101));

            bank.log("Customer " + id + " []: going to bank.");

            // door allows only two at a time
            bank.door.acquire();

            bank.log("Customer " + id + " []: entering bank.");

            bank.door.release();

            bank.log("Customer " + id + " []: getting in line.");

            // wait for teller
            bank.availableTellers.acquire();

            Teller teller;

            synchronized (bank) {
                teller = bank.getReadyTeller();
            }

            bank.log("Customer " + id + " []: selecting a teller.");

            bank.log("Customer " + id + " [Teller " +
                    teller.id + "]: selects teller");

            bank.log("Customer " + id + " [Teller " +
                    teller.id + "]: introduces itself");

            // assign to teller
            teller.assignCustomer(this, transaction);

            bank.log("Customer " + id + " [Teller " +
                    teller.id + "]: asks for " +
                    transaction.toLowerCase() + " transaction");

            // wait for teller to finish
            teller.finished.acquire();

            bank.log("Customer " + id + " [Teller " +
                    teller.id + "]: leaves teller");

            bank.log("Customer " + id + " []: goes to door");

            bank.door.acquire();

            bank.log("Customer " + id + " []: leaves the bank");

            bank.door.release();

            bank.customerServed();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}