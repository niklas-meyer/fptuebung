package problem42;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

/**
 * Created by Henry on 10.01.2017.
 */
public class Balance {

    Lock lock;

    private List<CashSave> cashSaves = new ArrayList<>();

    public Balance (Lock lock){
        this.lock = lock;
    }

    public void addCashpoint(Cashpoint cashpoint){
        lock.lock();
        cashSaves.add(new CashSave(cashpoint.getNr(),0d));
        lock.unlock();
        /*
        if(!cashpointMap.containsKey(cashpoint.getNr())){
            lock.lock();
            cashpointMap.put(cashpoint.getNr(),0d);
            lock.unlock();
        }
        */
    }

    public void addValue(Cashpoint cashpoint, double value){
        lock.lock();
        for(CashSave c : cashSaves){
            if(c.id == cashpoint.getNr()){
                c.add(value);
                break;
            }
        }
        Collections.reverse(cashSaves);
        System.out.println(getInfos());
        lock.unlock();
    }


    private String getInfos(){
        String info = "";
        for(CashSave c : cashSaves){
            info += "Kasse " + c.id + ": " + c.value+ " €; ";
        }
        return info;
    }

}
