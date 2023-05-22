import java.sql.*;

public class gaussDB {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String url = "jdbc:postgresql://116.205.157.173:8000/db_2020_01";
    static final String user = "db_user2020_77";
    static final String password = "db_user@123";

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection conn;
        {
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql;
        sql = "SELECT * FROM websites";
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                String id = rs.getString("url");
                System.out.println(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                String name = rs.getString("name");
                System.out.println(name);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            // process the results
        }
        conn.close();

    }


}
