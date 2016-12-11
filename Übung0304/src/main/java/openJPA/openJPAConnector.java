package openJPA;

import model.Product;
import org.apache.openjpa.persistence.OpenJPAPersistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NiklasM on 11.12.16.
 */
public class openJPAConnector {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Product p = new Product();
        p.setName("Apfl");
        p.setId(68945879);
        EntityManagerFactory fac = getWithoutConfig();
        //EntityManagerFactory fac = Persistence.createEntityManagerFactory(
        //		"openjpa", System.getProperties());

        EntityManager e = fac.createEntityManager();

        EntityTransaction t = e.getTransaction();
        t.begin();
        e.persist(p);
        t.commit(); // all ok commit
        // all Data is saved in database now
        t.begin();
        // QBE
        for (Object o : e.createQuery("SELECT * FROM Product")
                .getResultList()) {
            Product z = (Product) o;
            System.out.println(z.getId() + ": " + z.getName());
        }
        t.commit(); // all ok commit

        e.close();
        fac.close();

    }


    public static EntityManagerFactory getWithoutConfig() {

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

