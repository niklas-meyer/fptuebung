package jdbc;

import java.sql.*;

/**
 * Created by Henry on 02.12.2016.
 */
public class JDBCConnector {


    public  static  void main(String args[]){

        JDBCConnector jdbcConnector = new JDBCConnector();
        jdbcConnector.execute();
    }


    private void execute(){
        try (Connection con =
                     DriverManager.getConnection( "jdbc:postgresql://java.is.uni-due.de/ws1011" , "ws1011" , "ftpw10"))
        {
           // doSomething(con);
            DatabaseMetaData databaseMetaData = con.getMetaData();


             System.out.println(databaseMetaData.getURL());
             System.out.println(databaseMetaData.getUserName());

            String   catalog          = null;
            String   schemaPattern    = null;
            String   tableNamePattern = "%";
            String[] types            = null;

            ResultSet result = databaseMetaData.getTables(
                    catalog, schemaPattern, tableNamePattern, types );

            while(result.next()) {
                String tableName = result.getString(3);
                System.out.println(tableName);
            }

        } catch (SQLException e)
        { e.printStackTrace() ; }
    }

    private void doSomething(Connection con) throws SQLDataException {
        try (Statement stmt = con.createStatement() ;
             ResultSet rs = stmt.executeQuery("SELECT id , name, wohnort FROM lieferanten")) {

            while (rs.next()) {
                int idVal = rs.getInt("id");
                String nameVal = rs.getString("name");
                String wohnortVal = rs.getString("wohnort");
                System.out.println("id = " + idVal + " , name = " + nameVal + " , wohnort = " + wohnortVal);
            }
        } catch (SQLException e)
        { e.printStackTrace() ; }




    }
}
