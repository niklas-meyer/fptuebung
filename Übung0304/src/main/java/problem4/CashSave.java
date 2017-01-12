package problem4;

/**
 * Created by Henry on 10.01.2017.
 */
public class CashSave implements Comparable<CashSave> {

    int id;
    double value;

    public CashSave(int id, double value){
        this.id = id;
        this.value = value;
    }

    /**
     * adds a value to the current amount in cashpoint
     * @param value
     */
    public void add(double value){
        this.value += value;
    }

    /**
     * Method to sort the cashpoints by their values
     * @param cashSave
     * @return
     */
    @Override
    public int compareTo(CashSave cashSave) {
        return (int)(value - cashSave.value);
    }
}
