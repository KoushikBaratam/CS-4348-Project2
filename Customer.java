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

    }
}