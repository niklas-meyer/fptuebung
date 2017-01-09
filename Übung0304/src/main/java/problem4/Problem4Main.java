package problem4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Leona on 18.12.2016.
 */
public class Problem4Main {



    public static void main(String[] args)throws Exception{


        Acquisition a1 = new Acquisition();
        Cashpoint c1 = new Cashpoint(1);
        c1.open = true;
        Cashpoint c2 = new Cashpoint(2);
        Cashpoint c3 = new Cashpoint(3);
        Cashpoint c4 = new Cashpoint(4);
        Cashpoint c5 = new Cashpoint(5);
        Cashpoint c6 = new Cashpoint(6);

        Acquisition.cashpoints.add(c1);
        Acquisition.cashpoints.add(c2);
        Acquisition.cashpoints.add(c3);
        Acquisition.cashpoints.add(c4);
        Acquisition.cashpoints.add(c5);
        Acquisition.cashpoints.add(c6);


        a1.start();
        Acquisition.cashpoints.get(0).start();

    }


}

