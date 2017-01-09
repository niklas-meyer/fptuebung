package problem42;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Leona on 18.12.2016.
 */
public class Problem4Main {

    public static final int MAX_CASHPOINTS = 6;

    public static void main(String[] args){


        Acquisition acquisition = new Acquisition();
        Thread t1 = new Thread(acquisition);

        t1.start();



    }
}
