package problem4;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * Created by Leona on 18.12.2016.
 */
public class Cashpoint extends Thread implements Runnable  {
    public List<String> waitingQueue = new ArrayList<>();
    public boolean open = false;
    public boolean full = false;
    public int cashpointNr = 200;
    public boolean alreadyStarted = false;
    private Lock lock = new ReentrantLock();

  /*  public Cashpoint(){

    }
    public Cashpoint(ThreadGroup g,int nr){
        super(g,new Cashpoint(nr));
    }*/

    public Cashpoint(int nr){
        cashpointNr = nr;
    }

    public void AddToQueue(String s){
        waitingQueue.add(s);
    }
    @Override
    public void run() {
        while(open) {
            lock.lock();
            if(waitingQueue.size()!= 0) {

                try {
                    int wait = (int) (Math.random() * 3) + 4; //Abarbeitung dauert zwischen 6 bis 10 Sekunden.
                    sleep(wait * 1000);
                    //setPriority(cashpointNr);
                    String s = waitingQueue.get(0);
                    waitingQueue.remove(waitingQueue.get(0));
                    System.out.print(s + " an Kasse " + cashpointNr +" ... abgearbeitet (Wartend: " + waitingQueue + " ), Priority: "+this.getPriority()+"\n");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
            lock.unlock();
        }
        open = false;
        if(alreadyStarted)
            System.out.print("Kasse "+cashpointNr+" permanent geschlossen");
    }


}

