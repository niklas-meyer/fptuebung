import fpt.com.*;
import fpt.com.Product;

import java.io.*;

/**
 * Created by NiklasM on 21.11.16.
 */

public class BinaryStrategy implements fpt.com.SerializableStrategy {

    FileInputStream fi;
    ObjectInputStream is;
    FileOutputStream fo;
    ObjectOutputStream os;
    Product readObject;

    public fpt.com.Product readObject() throws IOException {
        try  {
            readObject = (Product) is.readObject();
        } catch (ClassNotFoundException | IOException e) { e . printStackTrace () ;
        }
        return null;
    }

    @Override
    public void writeObject(Product obj) throws IOException {
        try
        {
            os.writeObject (obj);
            os.flush();
        }
        catch (IOException e) { e . printStackTrace () ;
        }
    }

    @Override
    public void close() throws IOException {
        os.close();
    }

    @Override
    public void open(InputStream input, OutputStream output) throws IOException {
            fo = (FileOutputStream) output;
            os = new ObjectOutputStream(fo);

    }
}
