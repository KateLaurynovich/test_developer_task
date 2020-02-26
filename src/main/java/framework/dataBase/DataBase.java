package framework.dataBase;

import framework.logger.MyLogger;
import framework.utils.PropertiesManager;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;

public class DataBase {

    private static final MyLogger LOGGER = new MyLogger();

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs = null;

    private static Connection getConnection() {
        try {
            con = DriverManager.getConnection(PropertiesManager.getProperties("mySQL"),
                    PropertiesManager.getProperties("user"),
                    PropertiesManager.getProperties("password"));
            LOGGER.info("connection open");
        } catch (SQLException sqlEx) {
            LOGGER.error("SQLException is thrown during opening connection", sqlEx);
        }
        return con;
    }

    public static int sendQuery(String query) {
        int res = 0;
        try {
            stmt = getConnection().createStatement();
            res = stmt.executeUpdate(query);
        } catch (SQLException e) {
            LOGGER.error("SQLException is thrown during getting resultSet", e);
        } finally {
            closeConnection();
        }
        return res;
    }

    public static void sendQueryAtt(String query, byte[] byteArray) {
        try {
            stmt = getConnection().createStatement();
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
            preparedStatement.setBinaryStream(1, byteArrayInputStream);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("SQLException is thrown during getting resultSet", e);
        }
    }

    public static ArrayList sendQuerySelect(String query) {
        ArrayList res = new ArrayList();
        try {
            stmt = getConnection().createStatement();
            rs = stmt.executeQuery(query);
            try {
                while (rs.next()) {
                    ArrayList<String> arrayList = new ArrayList();
                    ResultSetMetaData rsmd = rs.getMetaData();
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        String elem = rs.getString(i);
                        arrayList.add(elem);
                    }
                    res.add(arrayList);
                }
            } catch (SQLException e) {
                LOGGER.error("SQLException is thrown during getting data from result set", e);
            }
            return res;
        } catch (SQLException e) {
            LOGGER.error("SQLException is thrown during getting resultSet", e);
        } finally {
            closeConnection();
        }
        return res;
    }

    private static void closeConnection() {
        try {
            con.close();
            stmt.close();
            if (rs != null) {
                rs.close();
            }
            LOGGER.info("connection closed");
        } catch (SQLException e) {
            LOGGER.error("SQLException is thrown during closing connection ", e);
        }
    }
}
