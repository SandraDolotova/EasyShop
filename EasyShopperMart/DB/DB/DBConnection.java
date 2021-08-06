package EasyShopperMart.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection DBConnection() throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/easy_shop", "root", "root");
            //if (!connection.isClosed()){ System.out.println("Connected to SQL EasyShopperMart.DB!");}
            return connection;

        } catch (ClassNotFoundException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }
        return null;
    }





}
