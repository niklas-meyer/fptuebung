package model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/**
 * Created by Tobis on 23.11.2016.
 */
public class ProductConverter implements Converter {

    public boolean canConvert(Class clazz) {
        return clazz.equals(Product.class);
    }

    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        Product product = (Product) value;

        writer.addAttribute("id", String.format("%06d", product.getId()));
        writer.startNode("name");
        writer.setValue(product.getName());
        writer.endNode();
        writer.startNode("preis");
        DecimalFormat f = new DecimalFormat("#0.00");
        DecimalFormatSymbols custom = new DecimalFormatSymbols();
        custom.setDecimalSeparator('.');
        f.setDecimalFormatSymbols(custom);
        writer.setValue(f.format(product.getPrice()));
        writer.endNode();
        if (product.getQuantity() > 1) {
            writer.startNode("anzahl");
            writer.setValue(String.valueOf(product.getQuantity()));
            writer.endNode();
        }

    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Product product = new Product();
        product.setId(Long.valueOf(reader.getAttribute("id")));
        reader.moveDown();
        product.setName(reader.getValue());
        reader.moveUp();
        reader.moveDown();
        product.setPrice(Double.valueOf(reader.getValue()));
        reader.moveUp();
        if (!reader.hasMoreChildren())
            product.setQuantity(1);
        else {
            reader.moveDown();
            product.setQuantity(Integer.valueOf(reader.getValue()));
            reader.moveUp();
        }
        return product;
    }

}