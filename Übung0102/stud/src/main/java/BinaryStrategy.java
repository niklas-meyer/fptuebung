import fpt.com.*;
import fpt.com.Product;
import sun.misc.IOUtils;

import java.io.*;
import java.nio.file.Path;

/**
 * Created by NiklasM on 21.11.16.
 */

public class BinaryStrategy implements fpt.com.SerializableStrategy {

    FileInputStream fileInputStream;
    ObjectInputStream objectInputStream;
    FileOutputStream fileOutputStream;
    ObjectOutputStream objectOutputStream;
    Product readObject;
    private  Path path;


    public fpt.com.Product readObject() throws IOException {

        readObject = null;
        if(fileOutputStream != null)
            fileOutputStream.close();
        if(objectOutputStream != null)
            objectOutputStream.close();
        try {
            readObject = (Product)objectInputStream.readObject();
        } catch (Exception e){
            e.printStackTrace();
        }
        return  readObject;
    }

    @Override
    public void writeObject(Product obj) throws IOException {
        objectOutputStream.writeObject (obj);
        objectOutputStream.flush();
    }

    @Override
    public void close() throws IOException {
        if(fileInputStream != null)
            fileInputStream.close();
        if(objectInputStream != null)
            objectInputStream.close();
        if(fileOutputStream != null)
             fileOutputStream.close();
        if(objectOutputStream != null)
            objectOutputStream.close();
    }


    public void open(InputStream input, OutputStream output) throws IOException {

    }

    @Override
    public void open(Path p) throws IOException{
        path = p;
        fileOutputStream = new FileOutputStream(path.getFileName().toString());
        objectOutputStream = new ObjectOutputStream(fileOutputStream);

        fileInputStream = new FileInputStream(path.getFileName().toString());
        objectInputStream = new ObjectInputStream(fileInputStream);
    }

    public  void setPath(Path p){
        path = p;
    }
}
