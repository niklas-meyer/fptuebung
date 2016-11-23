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
    private boolean inputOpen = false;
    private boolean outputOpen = false;

    public fpt.com.Product readObject() throws IOException {
        try {
            readObject = ((Product) is.readObject());
        } catch (ClassNotFoundException | IOException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            readObject= null;
        } finally {
            return readObject;

        }
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
        if(inputOpen){
            is.close();
            inputOpen = false;
        }
        if(outputOpen) {
            os.close();
            outputOpen = false;
        }
    }

    @Override
    public void open(InputStream input, OutputStream output) throws IOException {
        if(output != null) {
            fo = (FileOutputStream) output;
            os = new ObjectOutputStream(fo);
            outputOpen = true;

        }
        if(input != null) {
            fi = (FileInputStream) input;
            is = new ObjectInputStream(fi);
            inputOpen = true;
        }


    }
}
