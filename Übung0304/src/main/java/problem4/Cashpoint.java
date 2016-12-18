package problem4;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by Leona on 18.12.2016.
 */
public class Cashpoint implements Runnable {
    List<String> waitingQueue = new ArrayList<>();

    public Cashpoint(){
        this.waitingQueue = Acquisition.waitingQueue;
    }

    @Override
    public void run() {
            while(!waitingQueue.isEmpty()) {
                System.out.print(waitingQueue.get(0) + " abgearbeitet\n");
                waitingQueue.remove(waitingQueue.get(0));
                try {
                    int wait = (int) (Math.random() * 5) + 6; //Abarbeitung dauert zwischen 6 bis 10 Sekunden.
                    sleep(wait * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("Alles abgearbeitet");
        }
    }

