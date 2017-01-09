package problem42;

import java.util.*;

import static java.lang.Thread.sleep;

/**
 * Created by Leona on 18.12.2016.
 */
public class Acquisition implements Runnable {

    Cashpoint[] cashpoints;

    @Override
    public void run() {

        cashpoints = new Cashpoint[Problem4Main.MAX_CASHPOINTS];

        Cashpoint c = new Cashpoint(0, new ArrayList<>());
        cashpoints[0] = c;
        Thread thread = new Thread(c);
        thread.start();

        while (!stopAcquisition()){

            Cashpoint current = getLeastCustomersCashpoint();

            String customer = "Customer"+ (current.getTotalCustomers() + 1);
            current.addToWaitingQueue(customer);

            try {
                int wait = (int) (Math.random()*3); //Akquise dauert von 0 bis 2 Sekunden.
                sleep(wait*1000);
            }catch ( InterruptedException e ) {
                e.printStackTrace();
            }

            openNewCashpoints();
            closeCashpoints();
        }



    }

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

    private boolean stopAcquisition(){
        for(int i = 0; i < cashpoints.length; i++){
            if(cashpoints[i] != null && cashpoints[i].getWaitingQueueSize() == 8){
                System.out.println("Keine Kunden werden mehr aufgenommen");
                return true;
            }
        }
        return false;
    }

    private void openNewCashpoints(){
        for(int i = 0; i < cashpoints.length; i++){
            if(cashpoints[i] != null && cashpoints[i].getWaitingQueue().size() == 6){
                //Open new Cashpoint if possible:
                for(int j = 0; j < cashpoints.length; j++) {
                    if (cashpoints[j] == null ) {
                        Cashpoint c = new Cashpoint(j, new ArrayList<>());
                        cashpoints[j] = c;
                        Thread thread = new Thread(c);
                        thread.start();
                        break;
                    }
                }

            }
        }
    }

    private void closeCashpoints(){
        for(int i = 0; i < cashpoints.length; i++){
            if(cashpoints[i] != null && cashpoints[i].isCloseable()){
                System.out.println("Kasse " + cashpoints[i].getNr() + " geschlossen.");
                cashpoints[i] = null;
            }
        }
    }


}
