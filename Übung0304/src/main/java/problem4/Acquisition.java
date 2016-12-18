package problem4;

import java.util.*;

import static java.lang.Thread.sleep;

/**
 * Created by Leona on 18.12.2016.
 */
public class Acquisition implements Runnable {
    public static List<String> waitingQueue = new ArrayList<>();

    @Override
    public void run() {
        while(waitingQueue.size() < 8){
            String c = "Customer"+waitingQueue.size();
            this.waitingQueue.add(c);
            System.out.print(c+" zu Warteliste hinzugefügt\n");
            try {
                int wait = (int) (Math.random()*2); //Akquise dauert von 0 bis 2 Sekunden.
                sleep(wait*1000);
            }catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }

    }
}
