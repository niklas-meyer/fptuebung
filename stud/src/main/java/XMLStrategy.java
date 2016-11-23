import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * Created by NiklasM on 21.11.16.
 */
public class XMLStrategy implements fpt.com.SerializableStrategy {

        public FileInputStream fi;
        public XMLDecoder  xDec;
        FileOutputStream fo;
        XMLEncoder xEnc;
        Product readObject = null;
        private boolean inputOpen = false;
        private boolean outputOpen = false;

        public Product readObject() throws IOException {
            try{
                readObject = (Product) xDec.readObject();
            }catch(ArrayIndexOutOfBoundsException e){
                readObject = null;
            }finally {
                return readObject;
            }
        }

            @Override
        public void writeObject(fpt.com.Product obj) throws IOException {

                xEnc.writeObject (obj);
                xEnc.flush();
                }

        @Override
        public void close() throws IOException {
                if(inputOpen){
                    xDec.close();
                        inputOpen = false;
                }
                if(outputOpen) {
                        xEnc.close();
                        outputOpen = false;
                }
        }

        @Override
        public void open(InputStream input, OutputStream output) throws IOException {
                if(output != null) {
                        fo = (FileOutputStream) output;
                        this.xEnc = new XMLEncoder(fo);
                        outputOpen = true;

                }
                if(input != null) {
                        fi = (FileInputStream) input;
                        xDec = new XMLDecoder(fi);
                        inputOpen = true;
                }


        }
}

