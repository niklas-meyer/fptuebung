package openJPA;

import model.Product;
import org.apache.openjpa.persistence.OpenJPAPersistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.*;

/**
 * Created by NiklasM on 11.12.16.
 */
public class OpenJPAConnector {

    /**
     * @param args
     */
    public static void main(String[] args) {
        OpenJPAConnector openJPAConnector = new OpenJPAConnector();
        Iterator<Product> p = openJPAConnector.read(10);
        while (p.hasNext()){
            Product product = p.next();
            System.out.println(product.getName() + "  " + product.getId());
        }
    }

    /**
     * Inserts a product into the database
     * @param product Product
     * @return ID of the product
     */
    public long insert(Product product){
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
    public Product read(long id){
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
     * Returns the last n entries in db
     * @param count amount of elements
     * @return
     */
    public Iterator<Product> read(int count){
        ArrayList<Product> productArrayList = new ArrayList<Product>();
        EntityManagerFactory fac = getFactoryWithoutConfig();
        EntityManager e = fac.createEntityManager();
        EntityTransaction t = e.getTransaction();

        t.begin();
        List resultList = e.createQuery("SELECT x FROM Product x ORDER BY x.id DESC")
                            .setMaxResults(count)
                            .getResultList();
        for(Object object : resultList){
            productArrayList.add((Product) object);
        }
        t.commit();

        e.close();
        fac.close();
        return productArrayList.iterator();
    }

    /**
     * Creates the Factory using the persistence.xml
     * @return EntityManagerFactory
     */
    public EntityManagerFactory getFactoryWithConfig(){
        return Persistence.createEntityManagerFactory("openjpa", System.getProperties());
    }

    /**
     * Creates the Factory without using the persistence.xml
     * @return EntityManagerFactory
     */
    public EntityManagerFactory getFactoryWithoutConfig() {
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

