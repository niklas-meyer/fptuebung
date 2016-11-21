import fpt.com.*;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * Created by NiklasM on 21.11.16.
 */
public class XMLStrategy implements fpt.com.SerializableStrategy {

        FileInputStream fi;
        XMLDecoder  is;
        FileOutputStream fo;
        XMLEncoder os;
        Product readObject;

public fpt.com.Product readObject() throws IOException {
    return readObject = (Product) is.readObject();
    }

    @Override
public void writeObject(fpt.com.Product obj) throws IOException {
        os.writeObject (obj);
        os.flush();
        }

@Override
public void close() throws IOException {
        os.close();
        }

@Override
public void open(InputStream input, OutputStream output) throws IOException {
        fo = (FileOutputStream) output;
        os = new XMLEncoder(fo);

        }
        }

