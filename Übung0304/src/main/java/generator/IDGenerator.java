package generator;

import fpt.com.Product;
import view.ViewShop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Surya on 19.11.2016.
 */
public class IDGenerator {

    ArrayList<Long> idListe = new ArrayList<Long>();
    private long counter = 1;

    public long generateId() throws Exception {
        while (idListe.contains(counter))
            counter++;
        if (counter > 999999)
            throw new IDOverflow();
        idListe.add(counter);
        return counter++;
    }

    public void clear() {counter = 1;}

    public void addId(long id) {
        idListe.add(id);
    }

}
