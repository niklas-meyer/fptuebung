package problem4;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * Created by Henry on 08.01.2016.
 */
public class Acquisition implements Runnable {

    Cashpoint[] cashpoints;
    Balance balance;
    public Lock lock;
    private boolean stopAcquisition = false;

    @Override
    public void run() {

        initiate();

        while (!stopAcquisition){

            Cashpoint current = getLeastCustomersCashpoint();

            String customer = "Customer"+ (current.getTotalCustomers() + 1);
            current.addToWaitingQueue(customer);

            try {
                int wait = (int) (Math.random()*3); //Akquise dauert von 0 bis 2 Sekunden.
                sleep(wait*1000);
            }catch ( InterruptedException e ) {
                e.printStackTrace();
            }

        }

    }


    /**
     * Returns the Cashpoint with least customers in queue
     * @return
     */
    private Cashpoint getLeastCustomersCashpoint(){
        Cashpoint cashpoint = null;
        //get the first open cashpoint:
        for(int i = 0; i < cashpoints.length; i++){
            if(cashpoints[i] != null){
                cashpoint = cashpoints[i];
                break;
            }
        }
        //iterate to get the one with least customers:
        for(int i = 0; i < cashpoints.length; i++){
            if(cashpoints[i] != null && cashpoints[i].getWaitingQueueSize() < cashpoint.getWaitingQueueSize()){
                cashpoint = cashpoints[i];
                break;
            }
        }
        return cashpoint;
    }

    /**
     * Stops the acquisition of new customers
     */
    public void stopAcquisition(){
        if(!stopAcquisition){
            System.out.println("Es werden keine neuen Kunden mehr aufgenommen.");
            stopAcquisition = true;
        }

    }

    /**
     * Searches for the first not opened/created Cashpoint and opens it
     */
    public void openNewCashpoint(){
        for(int i = 0; i < cashpoints.length; i++) {
            if (cashpoints[i] == null  ) {
                Cashpoint c = new Cashpoint(i, new ArrayList<>(), balance, this);
                cashpoints[i] = c;
                Thread thread = new Thread(c);
                thread.start();
                System.out.println("Kasse " + i + " wird geöffnet");
                break;
            }
        }
    }

    /**
     * Removes the cashpoint from the list
     * @param cashpoint
     */
    public void closeCashpoint(Cashpoint cashpoint){
        cashpoints[cashpoint.getNr()] = null;
    }

    /**
     * Sets up variables and creates the first cashpoint
     */
    private  void initiate(){
        balance = new Balance();
        cashpoints = new Cashpoint[Problem4Main.MAX_CASHPOINTS];
        lock = new ReentrantLock();

        Cashpoint c = new Cashpoint(0, new ArrayList<>(), balance, this);
        cashpoints[0] = c;
        System.out.println("Kasse 0 wird geöffnet");
        Thread thread = new Thread(c);
        thread.start();
    }

}
