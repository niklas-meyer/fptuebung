/**
 * Created by Leona on 23.11.2016.
 */
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import fpt.com.*;
import fpt.com.Product;


import java.io.*;
import java.nio.file.Path;

public class XStreamStrategy implements SerializableStrategy{
    FileWriter fileWriter;
    FileReader fileReader;
    Product readObject;

    @Override
    public Product readObject() throws IOException {
        return readObject = (Product) createXStream(Product.class).fromXML(fileReader) ;
    }

    @Override
    public void writeObject(Product obj) throws IOException {
        createXStream(Product.class).toXML (obj , fileWriter ) ;
    }

    @Override
    public void close() throws IOException {
        fileReader.close();
        fileWriter.close();
    }

    @Override
    public void open(InputStream input, OutputStream output) throws IOException {
    }

    public void open(Path p) throws IOException{
        fileWriter = new FileWriter(p.getFileName().toString());

        fileReader = new FileReader(p.getFileName().toString());

    }

}
