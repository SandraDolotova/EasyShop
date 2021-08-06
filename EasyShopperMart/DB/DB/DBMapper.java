package EasyShopperMart.DB;

import EasyShopperMart.Panels.PanelStore;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class DBMapper {

    static final String sql1 = "SELECT user_name, user_password FROM owner";
    static final String sql2 = "SELECT user_name, user_password FROM customers";
    static final String sql3 = "SELECT user_name, user_password FROM sales_representatives";

    static final String sql4 = "SELECT product_id, product_type, product_name, product_quantity_stock FROM sales_products";
    static final String sql5 = "SELECT product_id, product_name, product_price_vat FROM sales_products";

    static final int productID = PanelStore.productID;
    static final String sql6 = "SELECT product_name FROM sales_products WHERE product_id = ? ";
    static final String sql7 = "SELECT product_price_vat FROM sales_products WHERE product_id = ? ";
    static final String sql8 = "SELECT total_sum FROM pay";
    static final String sql9 = "SELECT difference FROM pay";
    static final String sql10 = "SELECT product_name, product_quantity, total_price FROM shopping_cart";


    public static Map<String, String> checkOwner() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.DBConnection();
        Map<String, String> mapResult = new HashMap<>();
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.DBConnection().prepareStatement(sql1);
                ResultSet resultSet = preparedStatement.executeQuery(sql1);
                while (resultSet.next()) {
                    String nameKey = resultSet.getString("user_name");
                    String passValue = resultSet.getString("user_password");
                    mapResult.put(nameKey, passValue);
                }
                connection.close();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        return mapResult;
    }

    public static Map<String, String> checkCustomer() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.DBConnection();
        Map<String, String> mapResult = new HashMap<>();
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.DBConnection().prepareStatement(sql2);
                ResultSet resultSet = preparedStatement.executeQuery(sql2);
                while (resultSet.next()) {
                    String nameKey = resultSet.getString("user_name");
                    String passValue = resultSet.getString("user_password");
                    mapResult.put(nameKey, passValue);
                }
                connection.close();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        return mapResult;
    }

    public static Map<String, String> checkSalesRepresentative() throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.DBConnection();
        Map<String, String> mapResult = new HashMap<>();
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.DBConnection().prepareStatement(sql3);
                ResultSet resultSet = preparedStatement.executeQuery(sql3);

                while (resultSet.next()) {
                    String nameKey = resultSet.getString("user_name");
                    String passValue = resultSet.getString("user_password");
                    mapResult.put(nameKey, passValue);
                }
                connection.close();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        return mapResult;

    }

    public static String showWarehouse() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.DBConnection();
        String status = "";
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = Objects.requireNonNull(DBConnection.DBConnection()).prepareStatement(sql4);
                ResultSet resultSet = preparedStatement.executeQuery(sql4);
                StringBuilder stringBuilder = new StringBuilder();

                while (resultSet.next()) {
                    stringBuilder.append(resultSet.getString("product_id"));
                    stringBuilder.append(") ");
                    stringBuilder.append(resultSet.getString("product_type"));
                    stringBuilder.append(": ");
                    stringBuilder.append(resultSet.getString("product_name"));
                    stringBuilder.append(" - ");
                    stringBuilder.append(resultSet.getString("product_quantity_stock"));
                    stringBuilder.append("; " + "\n");
                }
                status = stringBuilder.toString();
                connection.close();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        return status;
    }

    public static String showProductList() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.DBConnection();
        String productList = "";
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.DBConnection().prepareStatement(sql5);
                ResultSet resultSet = preparedStatement.executeQuery(sql5);
                StringBuilder stringBuilder = new StringBuilder();
                DecimalFormat decimalFormat = new DecimalFormat("0.00");  // ROUN money from sql
                //df.setRoundingMode(RoundingMode.HALF_UP);

                while (resultSet.next()) {
                    stringBuilder.append(resultSet.getInt("product_id"));
                    stringBuilder.append(")  ");
                    stringBuilder.append(resultSet.getString("product_name"));
                    stringBuilder.append(":         ");
                    stringBuilder.append(decimalFormat.format(resultSet.getDouble("product_price_vat")));
                   // stringBuilder.append(resultSet.getDouble("product_price_vat"));
                    stringBuilder.append(";" + "\n");
                }
                productList = stringBuilder.toString();
                connection.close();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        return productList;
    }

    public static String showProductNameByID(int productID) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.DBConnection();
        String productName = "";
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = Objects.requireNonNull(DBConnection.DBConnection()).prepareStatement(sql6);
                preparedStatement.setInt(1, productID);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    productName = resultSet.getString("product_name");
                }

                connection.close();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        return productName;
    }

    public static Double showProductPriceByID(Integer productID) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.DBConnection();
        Double productPrice = null;
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.DBConnection().prepareStatement(sql7);
                preparedStatement.setInt(1, productID);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    productPrice = resultSet.getDouble("product_price_vat");
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        return productPrice;
    }

    public  static Double showTotalPaySum() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.DBConnection();
        Double totalSum = null;
        if (connection != null){
            try{
                PreparedStatement preparedStatement = DBConnection.DBConnection().prepareStatement(sql8);
                ResultSet resultSet = preparedStatement.executeQuery();
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                while (resultSet.next()){
                    totalSum = resultSet.getDouble("total_sum");
                }
            }catch (Exception exc){
                exc.printStackTrace();
            }
        } return totalSum;
    }

    public static Double showChange() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.DBConnection();
        Double change = null;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = DBConnection.DBConnection().prepareStatement(sql9);
                ResultSet resultSet = preparedStatement.executeQuery();
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                while (resultSet.next()){
                   change = resultSet.getDouble("difference");
                }
            }catch (Exception exc){
                exc.printStackTrace();
            }
        }return change;
    }

    public static String showShoppingCart() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.DBConnection();
        String details = "";
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.DBConnection().prepareStatement(sql10);
                ResultSet resultSet = preparedStatement.executeQuery(sql10);
                StringBuilder stringBuilder = new StringBuilder();
                DecimalFormat decimalFormat = new DecimalFormat("0.00");

                while (resultSet.next()) {
                    stringBuilder.append(resultSet.getString("product_name"));
                    stringBuilder.append(": ");
                    stringBuilder.append(resultSet.getInt("product_quantity"));
                    stringBuilder.append(" = ");
                    stringBuilder.append(decimalFormat.format(resultSet.getDouble("total_price")));
                    stringBuilder.append("; " + "\n");
                }
                details = stringBuilder.toString();
                connection.close();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        return details;
    }
}















