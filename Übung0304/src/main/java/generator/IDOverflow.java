package generator;

/**
 * Created by Surya on 19.11.2016.
 */

public class IDOverflow extends Exception{
    private static final long serialVersionUID = 21389129038129L;
    IDOverflow(){
        super("Die neue ID hat einen Wert über 999999");
    }


}

