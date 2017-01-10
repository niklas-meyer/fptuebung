package problem42;

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

    public void add(double value){
        this.value += value;
    }

    @Override
    public int compareTo(CashSave cashSave) {
        return (int)(value - cashSave.value);
    }
}
