import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class employee extends security {
    @Override
    public int signin() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username: ");
        username = sc.nextLine();
        System.out.print("Enter password: ");
        password = sc.nextLine();
        try {
            Connection con=coneect.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from employee where username=? && password=?");
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                System.out.println("Welcome "+username);
return 1;
            }else {
                System.out.println("Invalid username or password");
                return 0;
            }
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }

    }

    public void register() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = sc.nextLine(); // Define username as String
        System.out.print("Enter password: ");
        String password = sc.nextLine(); // Define password as String

        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = coneect.getConnection(); // Assuming DatabaseConnector is a valid class with a static getConnection method
            if (con != null) { // Check if connection is successful
                preparedStatement = con.prepareStatement("INSERT INTO employee (username,password) VALUES (?, ?)");
                preparedStatement.setString(1, username); // Set username as String
                preparedStatement.setString(2, password); // Set password as String

                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    System.out.println("Data Inserted successful");
                } else {
                    System.out.println("Insertion failed");
                }
            } else {
                System.out.println("No connection");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close(); // Close PreparedStatement
            }
            if (con != null) {
                con.close(); // Close Connection
            }
        }
    }
}
