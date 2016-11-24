/**
 * Created by Leona on 23.11.2016.
 */
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import fpt.com.*;
import fpt.com.Product;


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XStreamStrategy implements SerializableStrategy{
    FileWriter fileWriter;
    FileReader fileReader;
    Product readObject;
    Path path = Paths.get("xproducts.xml");

    @Override
    public Product readObject() throws IOException {
        fileReader = new FileReader(path.getFileName().toString());
        return readObject = (Product) createXStream(Product.class).fromXML(fileReader) ;
    }

    @Override
    public void writeObject(Product obj) throws IOException {

        createXStream(Product.class).toXML (obj , fileWriter ) ;
    }

    @Override
    public void close() throws IOException {
        fileWriter.close();
    }

    @Override
    public void open(InputStream input, OutputStream output) throws IOException {
    }

    public void open(Path p) throws IOException{
        fileWriter = new FileWriter(p.getFileName().toString());



    }

}
