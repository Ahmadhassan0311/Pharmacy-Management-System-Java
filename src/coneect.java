import java.sql.Connection;
import java.sql.DriverManager;

public class coneect {
   public static  Connection   getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "Enter username", "Enter your db password");
        /*    System.out.println("Connected to database" + con);*/
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
}
