package saveStrategies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import fpt.com.Product;

public class BinaryStrategy implements fpt.com.SerializableStrategy {

	ObjectInputStream is;
	ObjectOutputStream os;
	
	FileOutputStream fo;
    FileInputStream fi;
    

    @Override
    public Product readObject() throws IOException {
        if (fi == null) {
            try {
                fi = new FileInputStream("products.ser");
            } catch (FileNotFoundException e) {
               System.out.println("Die Datei konnte nicht gefunden werden"); 
            	e.printStackTrace();
            }
            try {
                is = new ObjectInputStream(fi);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            return (Product) is.readObject();
        } catch (ClassNotFoundException | IOException e) {

            return null;
        }

    }

    @Override
    public void writeObject(Product obj) throws IOException {
        if (fo == null) {
            try {
                fo = new FileOutputStream("products.ser");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                os = new ObjectOutputStream(fo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            os.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() throws IOException {
        if (fi != null) {
            try {
                fi.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            fi = null;
            try {
                is.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            is = null;
        }
        if (os != null) {

            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            os = null;
            try {
                fo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fo = null;
        }

    }



    @Override
    public void open(InputStream input, OutputStream output) throws IOException {
        if(output == null){
            fo = new FileOutputStream("products.ser");
            os = new ObjectOutputStream(fo);
        }
        else
            fo = (FileOutputStream) output;
        if(input == null){
            fi = new FileInputStream("products.ser");
            is = new ObjectInputStream(fi);
        }
        else
            fi = (FileInputStream) input;

        }
    }
