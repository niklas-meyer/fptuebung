package problem4;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * Created by Leona on 18.12.2016.
 */
public class Acquisition extends Thread implements Runnable {
public static Stack<String> wQuery = new Stack<>();
    public static List<Cashpoint> cashpoints = new ArrayList<>();
    public static int currentCP = 0;
    public static int currentCustomer = 1;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (!CheckIfFull() && wQuery.size()<7) {
            try {
                lock.lock();

                String c = "Kunde " + currentCustomer;

                //Warten bis nächster Kunde akquiriert wird
                int wait = (int) (Math.random() * 3); //Akquise dauert von 0 bis 2 Sekunden.
                sleep(wait * 1000);
                wQuery.add(c);
                System.out.print("Neuen Kunde akquiriert (KdNr = "+currentCustomer+")");

                lock.unlock();

                //Leere Kassen schließen
                CheckIfEmpty();
                //Checken, ob eine neue Kasse geöffnet werden muss
                CheckToOpen();
                // Kasse für den nächsten Kunden auswählen und hinzufügen
                if (EmptiestCP() <0) {
                    System.out.print("Keine Kasse verfügbar\n");
                    break;
                } else {
                    currentCP = EmptiestCP()-1;
                    cashpoints.get(currentCP).AddToQueue(wQuery.pop());
                    cashpoints.forEach((Cashpoint cashpoint) -> System.out.print(cashpoint.cashpointNr+" : "+cashpoint.waitingQueue.size()+"...open"+cashpoint.open+ "\n"));
                    System.out.print("Kunde " + currentCustomer + " wurde Kasse " + cashpoints.get(currentCP).cashpointNr+ " hinzugefügt....TotaleWarteschlange: "+ wQuery.size()+"\n");//cashpoints.get(currentCP).waitingQueue + \" )\\n\");
                    currentCustomer++;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("Keine neuen Kunden werden mehr akquiriert");

    }

    public boolean CheckIfFull() {
        for (Cashpoint c : cashpoints) {
            if (c.waitingQueue.size() > 7) {
                return true;
            }
        }
        return false;
    }

    public void CheckIfEmpty(){
        for (Cashpoint c : cashpoints) {
            if (c.waitingQueue.isEmpty() && c.alreadyStarted) {
                c.open = false;
            }
        }
    }

    public void CheckToOpen() {
        boolean oneIsFull = false;
        for (Cashpoint c : cashpoints) {
            if (c.waitingQueue.size() > 5) {
                oneIsFull = true;
            }
        }
        if(oneIsFull) {
            for (Cashpoint c : cashpoints) {
                if (!c.open && !c.alreadyStarted) {
                    System.out.print("Kasse " + c.cashpointNr + " wurde geöffnet\n");
                    c.open = true;
                    c.start();
                    c.alreadyStarted = true;
                    try {
                        sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }

            }
        }
    }

    public int EmptiestCP(){
        int ecp = -1;
        int size =100;
        for(Cashpoint c :cashpoints){
            if(!c.open){
                continue;
            }
            if(c.waitingQueue.size() < size) {
                ecp = c.cashpointNr;
                size = c.waitingQueue.size();
            }
        }
        return ecp;
    }

}
