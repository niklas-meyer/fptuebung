/**
 * Created by Leona on 23.11.2016.
 */
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import fpt.com.*;
import fpt.com.Product;

import java.io.*;

public class XStreamStrategy extends XStream implements SerializableStrategy{
    XStream xstream;
    FileWriter fw;
    FileReader fr;
    Product readObject;
    private boolean outputOpen = false;
    private boolean inputOpen = false;

    public XStreamStrategy(){
         xstream = new XStream ( new DomDriver() );
    }
    @Override
    public fpt.com.Product readObject() throws IOException {
        return readObject = (Product) xstream.fromXML(fr) ;
    }

    @Override
    public void writeObject(Product obj) throws IOException {
        if(outputOpen)
        xstream.toXML (obj , fw ) ;
        else
            System.out.print("KEIN OUTPUT OFFEN");
    }

    @Override
    public void close() throws IOException {
        if(inputOpen){
            fr.close();
            inputOpen = false;
        }
        if(outputOpen) {
            fw.close();
            outputOpen = false;
        }
    }

    @Override
    public void open(InputStream input, OutputStream output) throws IOException {
        if (output != null) {
            fw = new FileWriter(output.toString());
            outputOpen = true;
        }
        if (input != null) {
            fr = new FileReader(input.toString());
            inputOpen = true;
        }
    }

}
