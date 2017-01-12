package saveStrategies;

import fpt.com.db.AbstractDatabaseStrategy;
import model.Product;
import jdbc.JDBCConnector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Henry on 30.12.2016.
 */
public class JDBCStrategy extends AbstractDatabaseStrategy {

    JDBCConnector jdbcConnector;
    Iterator<Product> productIterator;

    @Override
    public void open() throws IOException {
        jdbcConnector = new JDBCConnector();
    }

    @Override
    public Product readObject() throws IOException {
        if(jdbcConnector == null)
            open();
        if(productIterator == null){
            productIterator = jdbcConnector.readProducts(10);
        }
        if(productIterator.hasNext())
            return productIterator.next();

        productIterator = null;
        return null;
    }

    @Override
    public void writeObject(fpt.com.Product obj) throws IOException {
        if(jdbcConnector == null)
            open();
        Product p = (Product)obj;
        jdbcConnector.insert(p);
        System.out.println("Wrote in DB:" + p.getName() + " - " + p.getId());
    }

    @Override
    public void close() throws IOException {
        jdbcConnector = null;
    }
}
