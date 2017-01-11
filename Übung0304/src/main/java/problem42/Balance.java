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


    private List<CashSave> cashSaves = new ArrayList<>();

    public void addCashpoint(Cashpoint cashpoint){
        if(!cashpointContained(cashpoint))
             cashSaves.add(new CashSave(cashpoint.getNr(),0d));
    }

    public void addValue(Cashpoint cashpoint, double value){
        for(CashSave c : cashSaves){
            if(c.id == cashpoint.getNr()){
                c.add(value);
                break;
            }
        }
        Collections.sort(cashSaves);
        Collections.reverse(cashSaves);
    }

    public void printInfos(){
        System.out.println(getInfos());
    }

    private String getInfos(){
        String info = "";
        for(CashSave c : cashSaves){
            info += "Kasse " + c.id + ": " + c.value+ " €; ";
        }
        return info;
    }

    private boolean cashpointContained(Cashpoint cashpoint){
        boolean contained = false;
        for(CashSave c : cashSaves){
            if(c.id == cashpoint.getNr())
                contained = true;
        }
        return contained;
    }

}
