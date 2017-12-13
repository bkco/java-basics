package co.bk.javabasics.main.dbconnections;

import java.sql.*;

public class ConnectHSQLDB {

    /*
     * SQUIRREL provides a good way to view data...
     */
    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            //String url = "jdbc:hsqldb:hsqldb\\demoDatabase";
            String url = "jdbc:hsqldb:/var/Davra/RuBAN/database/eemdb";
            connection = DriverManager.getConnection(url, "sa", "");
            connection.setAutoCommit(false);

            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select count(*) from eem_db.device");

            while (rs.next()) {
                Object o = rs.getInt("device_id");
                System.out.println("rs value is: " + o.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                } // nothing we can do
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                } // nothing we can do
            }
        }
    }

}