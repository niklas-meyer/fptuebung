import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.nio.file.Path;

/**
 * Created by NiklasM on 21.11.16.
 */
public class XMLStrategy implements fpt.com.SerializableStrategy {

        private FileInputStream fileInputStream;
        private FileOutputStream fileOutputStream;
        private XMLDecoder xmlDecoder;
        private XMLEncoder xmlEncoder;
        FileOutputStream fo;


        Product readObject = null;
        private boolean inputOpen = false;
        private boolean outputOpen = false;

        public Product readObject() throws IOException {
            try{
                readObject = (Product) xmlDecoder.readObject();
            }catch(ArrayIndexOutOfBoundsException e){
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
                if(inputOpen){
                    xmlDecoder.close();
                        inputOpen = false;
                }
                if(outputOpen) {
                        xmlEncoder.close();
                        outputOpen = false;
                }
        }

        @Override
        public void open(InputStream input, OutputStream output) throws IOException {

        }

    public void open(Path p) throws IOException{
        fileOutputStream = new FileOutputStream(p.getFileName().toString());
        xmlEncoder = new XMLEncoder(fileOutputStream);

        fileInputStream = new FileInputStream(p.getFileName().toString());
        xmlDecoder = new XMLDecoder(fileInputStream);
    }
}

