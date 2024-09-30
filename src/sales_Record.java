import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public   class sales_Record {

     void record() {
         Scanner sc = new Scanner(System.in);
         Connection con = null;
         con = coneect.getConnection();
         if (con != null) {
             try {
                 Date today = new Date(Calendar.getInstance().getTimeInMillis());
                 PreparedStatement pst = con.prepareStatement("SELECT * FROM bill WHERE DATE(date) = CURDATE() ORDER BY id ASC;");
                 ResultSet rs = pst.executeQuery();
                 boolean found = false;
                 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                 System.out.println("id          Bill          date       time");
                 while (rs.next()) {
                     found = true;
                     String dateTime = dateFormat.format(rs.getTimestamp("date"));
                     System.out.printf("%-12s%-14.2f%-25s%n", rs.getString("id"), rs.getDouble("amount"), dateTime);
                 }
                 if (!found) {
                     System.out.println("No records found for the specified days.");
                 }
             } catch (SQLException e) {
                 e.printStackTrace();
             }
             try {
                 PreparedStatement pst = con.prepareStatement("SELECT sum(amount) AS total_sales FROM bill WHERE DATE(date) = CURDATE()");
                 ResultSet rs3 = pst.executeQuery();

                 // Retrieve result
                 if (rs3.next()) {
                     double totalBill = rs3.getDouble("total_sales");
                     System.out.println("Total sales of day is " + totalBill);
                 } else {
                     System.out.println("No records found for the specified month.");
                 }
             }catch (Exception e){
                 e.printStackTrace();
             } finally {

             }
         }
     }

      void month() {
         Scanner sc = new Scanner(System.in);
         int flag = 1; // Start with flag set to 1 to enter the loop
         do {
             try {
                 System.out.println("Enter month number:");
                 int month = sc.nextInt();
                 if (month >= 1 && month <= 12) { // Adjusted condition to ensure month is within valid range
                     flag = 0; // Set flag to 0 to exit the loop after valid input is provided

                     // Establish connection
                     Connection con = null;
                     con = coneect.getConnection();
                     if (con != null) {
                         // Prepare and execute query
                         boolean found = false;
                         try {
                         PreparedStatement ps=con.prepareStatement("SELECT * FROM bill WHERE MONTH(date) = ? ORDER BY id ASC;");
                         ps.setInt(1, month);
                         ResultSet rs1 =ps.executeQuery();
                             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                             System.out.println("id          Bill          date       time");
                             while (rs1.next()) {
                                 found = true;
                                 String dateTime = dateFormat.format(rs1.getTimestamp("date"));
                                 System.out.printf("%-12s%-14.2f%-25s%n", rs1.getString("id"), rs1.getDouble("amount"), dateTime);
                             }
                             if (!found) {
                                 System.out.println("No records found for the specified month.");
                             }
                         }catch (Exception e) {
                             System.out.println(e);
                         }
                         PreparedStatement pst = con.prepareStatement("SELECT SUM(amount) AS total_bill FROM bill WHERE MONTH(date) = ?");
                         pst.setInt(1, month);
                         ResultSet rs = pst.executeQuery();

                         // Retrieve result
                         if (rs.next()) {
                             double totalBill = rs.getDouble("total_bill");
                             System.out.println("Total sales of month " + month + ": " + totalBill);
                         } else {
                             System.out.println("No records found for the specified month.");
                         }

                         // Close resources
                         rs.close();
                         pst.close();
                         con.close();
                     } else {
                         System.out.println("Connection failed.");
                     }
                 } else {
                     System.out.println("Invalid month number. Please enter a number between 1 and 12.");
                 }
             } catch (SQLException e) {
                 throw new RuntimeException(e);
             } catch (InputMismatchException e) {
                 System.out.println("Invalid input. Please enter a valid month number.");
                 sc.next(); // Clear the input buffer
             }
         } while (flag == 1); // Continue loop while flag is equal to 1
     }
 }


