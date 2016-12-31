package saveStrategies;

import fpt.com.Product;
import fpt.com.db.AbstractDatabaseStrategy;

import java.io.IOException;

/**
 * Created by Henry on 30.12.2016.
 */
public class OpenJPAStrategy extends AbstractDatabaseStrategy {
    @Override
    public void open() throws IOException {

    }

    @Override
    public Product readObject() throws IOException {
        return null;
    }

    @Override
    public void writeObject(Product obj) throws IOException {

    }

    @Override
    public void close() throws IOException {

    }
}
