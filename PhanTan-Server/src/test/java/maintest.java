import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class maintest {

        public static void main(String[] args) throws Exception {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=QLNT;encrypt=false";
            String user = "sa";
            String pass = "sapassword";

            try (Connection conn = DriverManager.getConnection(url, user, pass)) {
                System.out.println("✅ Connected successfully!");
            } catch (SQLException e) {
                System.err.println("❌ Connection failed: " + e.getMessage());
            }
        }


}
