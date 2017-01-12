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

    public List<String> getWaitingQueue(){
        return waitingQueue;
    }

    public int getWaitingQueueSize(){
        return waitingQueue.size();
    }


    public int getNr(){
        return nr;
    }

    public int getTotalCustomers(){
        return totalCustomers;
    }


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
                int wait = (int) (Math.random() * 5) + 6; //Abarbeitung dauert zwischen 6 bis 10 Sekunden.
                sleep(wait * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            double sale = Math.random() *50;
            sale = Double.valueOf(cashFormat.format(sale));

            acquisition.lock.lock();
            balance.addValue(this, sale);
            System.out.println("Kunde abgearbeitet " + "(+" + sale +" €)" + "(Kasse " + nr + ") - in Schlange: " + (getWaitingQueueSize() - 1));
            balance.printInfos();
            acquisition.lock.unlock();
            waitingQueue.remove(waitingQueue.get(0));

        }
        System.out.println("Kasse Nr. " + nr + " schließt.");
        hasOpened = false;
        acquisition.closeCashpoint(this);
    }

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
