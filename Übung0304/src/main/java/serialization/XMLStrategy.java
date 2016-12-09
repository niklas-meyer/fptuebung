package serialization;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import fpt.com.Product;
import fpt.com.SerializableStrategy;

/**
 * Created by Surya on 19.11.2016.
 */

public class XMLStrategy implements SerializableStrategy {
	
	//Streams
	FileInputStream fi;
	FileOutputStream fo;    
  
    //Encoder & Decoder 
    XMLEncoder encoder;
    XMLDecoder decoder;

    
    //Deserialize
    @Override
    public Product readObject() throws IOException {
        if (fi == null) {
            try {
              fi = new FileInputStream("products.xml");
            } catch (FileNotFoundException ex) {
              ex.printStackTrace();
            }
             decoder = new XMLDecoder(fi);
        }
        try {return (Product) decoder.readObject();
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }

    @Override
    public void writeObject(Product obj) throws IOException {
        if (fo == null) {
            try {
                fo = new FileOutputStream("products.xml");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            encoder = new XMLEncoder(fo);
        }
        encoder.writeObject(obj);
        encoder.flush();

    }

    @Override
    public void close() throws IOException {
    	if (fo != null) {
            encoder.close();
            fo.close();

            fo = null;
            encoder = null;
        }
        if (fi != null) {
            fi.close();

            decoder.close();
            fi = null;
            decoder = null;
        }

    }

    @Override
    public void open(InputStream input, OutputStream output) throws IOException {

    }

}
