package EasyShopperMart;

import com.sun.org.apache.xpath.internal.objects.XString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBMapper {

    static final String sql1 = "SELECT user_name FROM owner";
    static final String sql2 = "SELECT user_password FROM owner";

    static final String sql3 = "SELECT user_name FROM customers";
    static final String sql4 = "SELECT user_password FROM customers";

    static final String sql5 = "SELECT user_name FROM sales_representatives";
    static final String sql6 = "SELECT user_password FROM sales_representatives";


    public static String checkOwnerName() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.DBConnection();
        String checkOwnerName = "";

        if (connection != null){
            try{
                PreparedStatement preparedStatement = DBConnection.DBConnection().prepareStatement(sql1);
                ResultSet resultSet = preparedStatement.executeQuery(sql1);
                StringBuilder stringBuilder = new StringBuilder();

                while (resultSet.next()){
                    stringBuilder.append(resultSet.getString("user_name"));
                }
                checkOwnerName = stringBuilder.toString();
                connection.close();
            }catch (Exception exc){
                exc.printStackTrace();
            }
        }
        return checkOwnerName;
    }

    public static String checkOwnerPass() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.DBConnection();
        String checkOwnerPass = "";
        if (connection != null){
            try{
                PreparedStatement preparedStatement = DBConnection.DBConnection().prepareStatement(sql2);
                ResultSet resultSet = preparedStatement.executeQuery(sql2);
                StringBuilder stringBuilder = new StringBuilder();
                while (resultSet.next()){
                    stringBuilder.append(resultSet.getString("user_password"));
                }
                checkOwnerPass = stringBuilder.toString();
                connection.close();
            }catch (Exception exc){
                exc.printStackTrace();
            }
        }
        return checkOwnerPass;
    }

    public static String checkCustomerName() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.DBConnection();
        String checkCustomerName = "";
        if (connection != null){
            try{
                PreparedStatement preparedStatement = DBConnection.DBConnection().prepareStatement(sql3);
                ResultSet resultSet = preparedStatement.executeQuery(sql3);
                StringBuilder stringBuilder = new StringBuilder();

                while (resultSet.next()){
                    stringBuilder.append(resultSet.getString("user_name"));
                }
                checkCustomerName = stringBuilder.toString();
                connection.close();
            }catch (Exception exc){
                exc.printStackTrace();
            }
        }
        return checkCustomerName;
    }

    public static String checkCustomerPass() throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.DBConnection();
        String checkCustomerPass = "";
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.DBConnection().prepareStatement(sql4);
                ResultSet resultSet = preparedStatement.executeQuery(sql4);
                StringBuilder stringBuilder = new StringBuilder();
                while (resultSet.next()) {
                    stringBuilder.append(resultSet.getString("user_password"));
                }
                checkCustomerPass = stringBuilder.toString();
                connection.close();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        return checkCustomerPass;

    }
}
