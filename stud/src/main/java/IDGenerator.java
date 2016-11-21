/**
 * Created by NiklasM on 21.11.16.
 */

public class IDGenerator {
    public static long id = 0;

    public long getId() throws IDOverflowException{
        if(id<999999) {
            id++;
            return id;
        }
        else{
            throw new IDOverflowException();
        }
    }
}

