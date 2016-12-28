package jdbc;

import model.Product;

import java.sql.*;

/**
 * Created by Henry on 02.12.2016.
 */
public class JDBCConnector {


    private String url =  "jdbc:postgresql://java.is.uni-due.de/ws1011";
    private String user =  "ws1011";
    private String password = "ftpw10";


    public  static  void main(String args[]){

        JDBCConnector jdbcConnector = new JDBCConnector();
        jdbcConnector.printInfos();

       // System.out.println(jdbcConnector.insert("wudrst",10,2));

        /*
        Product p = jdbcConnector.read(11453);
        if(p != null){
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.getPrice());
            System.out.println(p.getQuantity());
        }
        */
    }

    private void printInfos(){
        try (Connection con = DriverManager.getConnection(url, user, password))
        {
            DatabaseMetaData databaseMetaData = con.getMetaData();

            System.out.println("URL : " + databaseMetaData.getURL());
            System.out.println("Username: " + databaseMetaData.getUserName());

            String   catalog          = null;
            String   schemaPattern    = null;
            String   tableNamePattern = "%";
            String[] types            = null;

            ResultSet result = databaseMetaData.getTables(
                    catalog, schemaPattern, tableNamePattern, types );

            System.out.println("Tables:");
            while(result.next()) {
                String tableName = result.getString(3);
                System.out.println(tableName);
            }

        } catch (SQLException e)
        { e.printStackTrace() ; }
    }

    private long insert(String name, double price, int quantity){
        try (Connection con = DriverManager.getConnection(url, user, password);
              PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO products (name, price, quantity) VALUES(?,?,?)",
                                                                            Statement.RETURN_GENERATED_KEYS);
        )
        {
            preparedStatement.setString(1 ,name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setInt(3,quantity);
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs != null && rs.next()) {
                return rs.getLong(1);
            }
        }catch (SQLException e)
        {
            e.printStackTrace() ;
        }

        return  -1;
    }

    private void insert(Product product){
        long id = insert(product.getName(), product.getPrice(), product.getQuantity());
        product.setId(id);
    }

    private Product read(long productID){
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = con.prepareStatement("SELECT id, name, price, quantity FROM products WHERE id=? ");
        )
        {
            preparedStatement.setLong(1, productID);


            ResultSet rs = preparedStatement.executeQuery();
            Product p = new Product();


            while (rs.next()){
                p.setId(rs.getLong(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getDouble(3));
                p.setQuantity(rs.getInt(4));
            }

            return p;
        }catch (SQLException e)
        {
            e.printStackTrace() ;
        }

        return  null;
    }
}
