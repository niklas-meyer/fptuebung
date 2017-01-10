package problem42;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;

/**
 * Created by Henry on 10.01.2017.
 */
public class Balance {

    Lock lock;
    private HashMap<Integer, Double> cashpointMap = new HashMap<Integer, Double>();

    public Balance (Lock lock){
        this.lock = lock;
    }

    public void addCashpoint(Cashpoint cashpoint){
        if(!cashpointMap.containsKey(cashpoint.getNr())){
            lock.lock();
            cashpointMap.put(cashpoint.getNr(),0d);
            lock.unlock();
        }
    }

    public void addValue(Cashpoint cashpoint, double value){
        if(!cashpointMap.containsKey(cashpoint.getNr())){
            addCashpoint(cashpoint);
        }
        lock.lock();
        double newValue = cashpointMap.get(cashpoint.getNr()) + value;
        cashpointMap.replace(cashpoint.getNr(),newValue);
        lock.unlock();
    }


    public String getInfos(){
        String info = "";
        for(Integer i :cashpointMap.keySet()){
            info += "Kasse " + i + ": " + cashpointMap.get(i) + "€;";
        }

        return info;
    }

}
