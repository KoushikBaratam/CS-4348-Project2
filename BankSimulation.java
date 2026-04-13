public class BankSimulation {

    public static void main(String[] args) {

        // create shared bank object
        Bank bank = new Bank();

        // arrays for thread objects
        Teller[] tellers = new Teller[3];
        Customer[] customers = new Customer[50];

        // create and start teller threads
        for (int i = 0; i < 3; i++) {
            tellers[i] = new Teller(i, bank);
            tellers[i].start();
        }

        // create and start customer threads
        for (int i = 0; i < 50; i++) {
            customers[i] = new Customer(i, bank);
            customers[i].start();
        }

        // wait for customers to finish
        for (int i = 0; i < 50; i++) {
            try {
                customers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // stop tellers
        for (int i = 0; i < 3; i++) {
            tellers[i].stopTeller();
        }

        // wait for tellers
        for (int i = 0; i < 3; i++) {
            try {
                tellers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Simulation finished.");

    }
}