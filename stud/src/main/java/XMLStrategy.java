import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by NiklasM on 21.11.16.
 */
public class XMLStrategy implements fpt.com.SerializableStrategy {

        private FileInputStream fileInputStream;
        private FileOutputStream fileOutputStream;
        private XMLDecoder xmlDecoder;
        private XMLEncoder xmlEncoder;
        FileOutputStream fo;
        Path path = Paths.get("products.ser");

        Product readObject = null;
        private boolean inputOpen = false;
        private boolean outputOpen = false;

        public Product readObject() throws IOException {
            fileInputStream = new FileInputStream(path.getFileName().toString());
            xmlDecoder = new XMLDecoder(fileInputStream);

            try{
                readObject = (Product) xmlDecoder.readObject();
            }catch(ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
                readObject = null;
            }finally {
                return readObject;
            }
        }

            @Override
        public void writeObject(fpt.com.Product obj) throws IOException {

                xmlEncoder.writeObject (obj);
                xmlEncoder.flush();
        }

        @Override
        public void close() throws IOException {
            xmlEncoder.close();
        }

        @Override
        public void open(InputStream input, OutputStream output) throws IOException {

        }

    public void open(Path p) throws IOException{
        fileOutputStream = new FileOutputStream(p.getFileName().toString());
        xmlEncoder = new XMLEncoder(fileOutputStream);

    }
}

