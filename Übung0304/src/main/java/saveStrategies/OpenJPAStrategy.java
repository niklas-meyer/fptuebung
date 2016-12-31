package saveStrategies;


import fpt.com.db.AbstractDatabaseStrategy;
import model.Product;
import openJPA.OpenJPAConnector;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Henry on 30.12.2016.
 */
public class OpenJPAStrategy extends AbstractDatabaseStrategy {

    OpenJPAConnector openJPAConnector;
    Iterator<Product> productIterator;

    @Override
    public void open() throws IOException {
        openJPAConnector = new OpenJPAConnector();
    }

    @Override
    public Product readObject() throws IOException {
        if(openJPAConnector == null)
            open();
        if(productIterator == null){
            productIterator = openJPAConnector.read(10);
        }
        if(productIterator.hasNext())
            return productIterator.next();

        productIterator = null;
        return null;
    }

    @Override
    public void writeObject(fpt.com.Product obj) throws IOException {
        if(openJPAConnector == null)
            open();
        model.Product p = (Product)obj;
        openJPAConnector.insert(p);
        System.out.println("Wrote in DB:" + p.getName() + " - " + p.getId());
    }

    @Override
    public void close() throws IOException {
        openJPAConnector = null;
    }
}
