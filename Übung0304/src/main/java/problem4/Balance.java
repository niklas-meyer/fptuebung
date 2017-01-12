package problem4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Henry on 10.01.2017.
 */
public class Balance {

    private List<CashSave> cashSaves = new ArrayList<>();

    /**
     * Adds a cashpoint to the saved ones (if its not already contained)
     * @param cashpoint
     */
    public void addCashpoint(Cashpoint cashpoint){
        if(!cashpointContained(cashpoint))
             cashSaves.add(new CashSave(cashpoint.getNr(),0d));
    }

    /**
     * Adds a sale to the total value in the cashpoint
     * @param cashpoint
     * @param value
     */
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

    /**
     * Prints out information on all cashpoints with their values in
     */
    public void printInfos(){
        System.out.println(getInfos());
    }

    /**
     * Creates a String with all cashpoints + values sorted by their value
     * @return
     */
    private String getInfos(){
        String info = "";
        for(CashSave c : cashSaves){
            info += "Kasse " + c.id + ": " + c.value+ " €; ";
        }
        return info;
    }

    /**
     * Checks if a selected cashpoint is already contained in the saved list
     * @param cashpoint
     * @return
     */
    private boolean cashpointContained(Cashpoint cashpoint){
        boolean contained = false;
        for(CashSave c : cashSaves){
            if(c.id == cashpoint.getNr())
                contained = true;
        }
        return contained;
    }

}
