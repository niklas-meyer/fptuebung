
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.binary.Token;

import java.text.DecimalFormat;
import java.util.Locale;

public class ProductConverter implements Converter {

    public boolean canConvert(Class clazz) {
        return clazz.equals(Product.class);
}

    public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        Product p = (Product) value;
        String IDString = p.getId()+"";
        String priceString = p.getPrice()+"";
        StringBuilder price = new StringBuilder(priceString);
        if(price.substring(price.indexOf(".")).length() <3) {
            price.append("0");
        }

        priceString = price.toString();

        StringBuilder IDStringBuilder = new StringBuilder(IDString);
        while(IDStringBuilder.length() < 6) {
            IDStringBuilder.insert(0,"0");
        }



        IDString = IDStringBuilder.toString();


        writer.startNode("Ware");
        writer.addAttribute("ID",IDString);

            writer.startNode("name");
            writer.setValue(p.getName());
            writer.endNode();
            writer.startNode("preis");
            writer.setValue(priceString);
            writer.endNode();
            writer.startNode("anzahl");
            writer.setValue(p.getQuantity()+"");
            writer.endNode();
        writer.endNode();
    }

    public Object unmarshal(HierarchicalStreamReader reader,
                            UnmarshallingContext context) {
        Product p = new Product();
        reader.moveDown();
        p.setId((Long.parseLong(reader.getAttribute(0))));
        reader.moveUp();
        return p;

    }

}