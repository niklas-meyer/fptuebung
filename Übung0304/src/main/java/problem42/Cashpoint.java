package problem42;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by Henry on 08.01.2016.
 */
public class Cashpoint implements Runnable {

    List<String> waitingQueue = new ArrayList<>();
    int nr;
    int totalCustomers = 0;
    boolean hasOpened = false;
    Balance balance;

    private DecimalFormat cashFormat = new DecimalFormat("#,##");

    public Cashpoint(int nr, List<String> waitingQueue, Balance balance ) {
        this.waitingQueue = waitingQueue;
        this.nr = nr;
        this.balance = balance;
        balance.addCashpoint(this);
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
        System.out.println(customer + " hinzugefügt (Kasse " + nr + ")" );
    }

    public boolean isCloseable(){
        if(hasOpened && getWaitingQueueSize() == 0)
            return  true;
        return false;
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
            balance.addValue(this, sale);
            System.out.println(waitingQueue.get(0) + " abgearbeitet " + "(+" + sale +" €)" + "(Kasse " + nr + ") - in Schlange: " + getWaitingQueueSize());



            System.out.println(balance.getInfos());

            waitingQueue.remove(waitingQueue.get(0));
        }
        System.out.println("Kasse Nr. " + nr + " schließt.");
    }

    private  void openCashpoint(){
        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Kasse Nr. " + nr + " offen.");
        hasOpened = true;
    }

}
