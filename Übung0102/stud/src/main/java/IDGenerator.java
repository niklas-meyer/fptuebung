/**
 * Created by NiklasM on 21.11.16.
 */

public abstract class IDGenerator {
    private static long id = 0;

    public static long getId() throws IDOverflowException{
        if(id<999999) {
            id++;
            return id;
        }
        else{
            throw new IDOverflowException();
        }
    }
}

