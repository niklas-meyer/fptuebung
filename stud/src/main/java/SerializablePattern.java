import fpt.com.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Henry on 24.11.2016.
 */
public class SerializablePattern {
    private SerializableStrategy strategy;

    public SerializablePattern(SerializableStrategy strategy){
        this.strategy = strategy;
    }

    public void setStrategy(SerializableStrategy strategy){
        this.strategy = strategy;
    }

    public void write(List<Product> products, Path path){
        if(strategy == null)
            return;

        try {
            strategy.open(path);
            for (Product product : products){
                strategy.writeObject(product);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                strategy.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public  void open(Path path){
        try {
          strategy.open(path);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Product readObject(){
        Product product = null;
        try{
            product = (Product)strategy.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  product;

    }


}
