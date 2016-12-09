package serialization;

import java.io.EOFException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import model.ProductConverter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import fpt.com.Product;
import fpt.com.SerializableStrategy;



/**
 * Created by Surya on 19.11.2016.
 */


public class XStreamStrategy implements SerializableStrategy {
	//neuer xstream, DomDriver nicht vergessen !
    XStream xstream = new XStream(new DomDriver());
    //Writer & Reader
    FileReader fr;
    FileWriter fw;

    //Streams 
    ObjectOutputStream out;
    ObjectInputStream in;

    @Override
    public Product readObject() throws IOException {
        xstream.alias("Produkt", model.Product.class);
        xstream.registerConverter(new ProductConverter());

        if (fr == null) {
            fr = new FileReader("XStreamSer.xml");
            in = xstream.createObjectInputStream(fr);
        }
        try {
            return (Product) in.readObject();
        } catch (ClassNotFoundException | EOFException e) {
            return null;
        }

    }

    @Override
    public void writeObject(Product obj) throws IOException {
        xstream.alias("Produkt", model.Product.class);
        xstream.registerConverter(new ProductConverter());
        if (fw == null) {
            fw = new FileWriter("XStreamSer.xml");
            out = xstream.createObjectOutputStream(fw, "Produkte");
        }

        out.writeObject(obj);

    }

    @Override
    public void close() throws IOException {
        if (fw != null) {
            out.close();
            out = null;
            fw.close();
            fw = null;
        }
        if (fr != null) {
            in.close();
            in = null;
            fr.close();
            fr = null;
        }

    }

    @Override
    public void open(InputStream input, OutputStream output) throws IOException {

    }

}
