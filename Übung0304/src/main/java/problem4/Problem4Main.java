package problem4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Leona on 18.12.2016.
 */
public class Problem4Main {
    public static void main(String[] args){
        Acquisition a1 = new Acquisition();
        Thread t1 = new Thread(a1);
        t1.start();
        Cashpoint c1 = new Cashpoint();
        Thread t2 = new Thread(c1);
        t2.start();
    }
}

