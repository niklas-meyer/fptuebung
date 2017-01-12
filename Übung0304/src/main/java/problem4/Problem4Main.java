package problem4;

/**
 * Created by Henry on 08.01.2016.
 */
public class Problem4Main {

    /*
        Total amount of cashpoints that can be opened
     */
    public static final int MAX_CASHPOINTS = 6;

    public static void main(String[] args){


        Acquisition acquisition = new Acquisition();
        Thread t1 = new Thread(acquisition);

        t1.start();



    }
}
