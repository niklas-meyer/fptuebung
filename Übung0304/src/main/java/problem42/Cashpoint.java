package problem42;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by Henry on 08.01.2016.
 */
public class Cashpoint implements Runnable {

    Acquisition acquisition;

    List<String> waitingQueue = new ArrayList<>();
    int nr;
    int totalCustomers = 0;
    public boolean hasOpened = false;
    Balance balance;

    /*
        Converts the sale into a monetary format
     */
    private DecimalFormat cashFormat = new DecimalFormat("#,##");

    public Cashpoint(int nr, List<String> waitingQueue, Balance balance, Acquisition acquisition ) {
        this.waitingQueue = waitingQueue;
        this.nr = nr;
        this.balance = balance;
        this.acquisition = acquisition;
        acquisition.lock.lock();
        balance.addCashpoint(this);
        acquisition.lock.unlock();
    }

    /**
     * Returns the amount of customers in the waitingqueue of the cashpoint
     * @return
     */
    public int getWaitingQueueSize(){
        return waitingQueue.size();
    }

    /**
     * The ID of the cashpoint
     * @return
     */
    public int getNr(){
        return nr;
    }

    /**
     * Returns the amount of all considered customers since the cashpoint opened
     * @return
     */
    public int getTotalCustomers(){
        return totalCustomers;
    }

    /**
     * Adds a customer to the waitingqueue
     * @param customer name of the customer
     */
    public void addToWaitingQueue(String customer){
        totalCustomers++;
        waitingQueue.add(customer);
        System.out.println("Kunde hinzugefügt (Kasse " + nr + ") - Schlange: " + getWaitingQueueSize());
        if(waitingQueue.size() == 6)
            acquisition.openNewCashpoint();
        if(waitingQueue.size() == 8){
            acquisition.stopAcquisition();
        }
    }


    @Override
    public void run() {
        openCashpoint();

        while (!waitingQueue.isEmpty()) {
            try {
                // Processing a customer lasts 6 to 10 seconds
                int wait = (int) (Math.random() * 5) + 6;
                sleep(wait * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            double sale = Math.random() *50;
            sale = Double.valueOf(cashFormat.format(sale));

            /*
                Critical area
             */
            acquisition.lock.lock();
            balance.addValue(this, sale);
            System.out.println("Kunde abgearbeitet " + "(+" + sale +" €)" + "(Kasse " + nr + ") - in Schlange: " + (getWaitingQueueSize() - 1));
            balance.printInfos();
            waitingQueue.remove(waitingQueue.get(0));
            acquisition.lock.unlock();

        }
        System.out.println("Kasse Nr. " + nr + " schließt.");
        hasOpened = false;
        acquisition.closeCashpoint(this);
    }

    /**
     * Waits 6 seconds until customers are processed
     */
    private  void openCashpoint(){
        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Kasse " + nr + " offen.");
        hasOpened = true;
    }

}
