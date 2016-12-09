/**
 * Created by NiklasM on 21.11.16.
 */
public class IDOverflowException extends Exception {
    IDOverflowException()
    {
        super("All Ids are taken");
    }
}
