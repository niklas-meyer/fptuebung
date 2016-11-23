import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * Created by NiklasM on 21.11.16.
 */
public class XMLStrategy implements fpt.com.SerializableStrategy {

        FileInputStream fi;
        XMLDecoder  xDec;
        FileOutputStream fo;
        XMLEncoder xEnc;
        Product readObject;

public fpt.com.Product readObject() throws IOException {
    return readObject = (Product) xDec.readObject();
    }

    @Override
public void writeObject(fpt.com.Product obj) throws IOException {
        xEnc.writeObject (obj);
        xEnc.flush();
        }

@Override
public void close() throws IOException {
        xEnc.close();
        }

@Override
public void open(InputStream input, OutputStream output) throws IOException {
        fo = (FileOutputStream) output;
        xEnc = new XMLEncoder(fo);
        fi = (FileInputStream) input;
        xDec = new XMLDecoder(fi);
        }
}

