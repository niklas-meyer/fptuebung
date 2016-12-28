package openJPA;

import model.Product;
import org.apache.openjpa.persistence.OpenJPAPersistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NiklasM on 11.12.16.
 */
public class OpenJPAConnector {

    /**
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * Inserts a product into the database
     * @param product Product
     * @return ID of the product
     */
    public static long insert(Product product){
        EntityManagerFactory fac = getFactoryWithoutConfig();
        EntityManager e = fac.createEntityManager();
        EntityTransaction t = e.getTransaction();

        t.begin();
        e.persist(product);
        t.commit();

        e.close();
        fac.close();
        return product.getId();
    }

    /**
     * Returns a Product in DB by its id
     * @param id ID
     * @return Product
     */
    public static Product read(long id){
        EntityManagerFactory fac = getFactoryWithoutConfig();
        EntityManager e = fac.createEntityManager();
        EntityTransaction t = e.getTransaction();

        Product product = null;
        t.begin();
        List resultList = e.createQuery("SELECT x FROM Product x WHERE x.id =" + id).getResultList();
        for(Object o : resultList){
            product = (Product) o;
        }
        t.commit();

        e.close();
        fac.close();
        return product;
    }

    /**
     * Creates the Factory using the persistence.xml
     * @return EntityManagerFactory
     */
    public static EntityManagerFactory getFactoryWithConfig(){
        return Persistence.createEntityManagerFactory("openjpa", System.getProperties());
    }

    /**
     * Creates the Factory without using the persistence.xml
     * @return EntityManagerFactory
     */
    public static EntityManagerFactory getFactoryWithoutConfig() {
        Map<String, String> map = new HashMap<String, String>();

        map.put("openjpa.ConnectionURL",
                "jdbc:postgresql://java.is.uni-due.de/ws1011");
        map.put("openjpa.ConnectionDriverName", "org.postgresql.Driver");
        map.put("openjpa.ConnectionUserName", "ws1011");
        map.put("openjpa.ConnectionPassword", "ftpw10");
        map.put("openjpa.RuntimeUnenhancedClasses", "supported");
        map.put("openjpa.jdbc.SynchronizeMappings", "false");

        // find all classes to registrate them
        List<Class<?>> types = new ArrayList<Class<?>>();
        types.add(Product.class);

        if (!types.isEmpty()) {
            StringBuffer buf = new StringBuffer();
            for (Class<?> c : types) {
                if (buf.length() > 0)
                    buf.append(";");
                buf.append(c.getName());
            }
            // <class>Producer</class>
            map.put("openjpa.MetaDataFactory", "jpa(Types=" + buf.toString()
                    + ")");
        }

        return OpenJPAPersistence.getEntityManagerFactory(map);

    }

}

