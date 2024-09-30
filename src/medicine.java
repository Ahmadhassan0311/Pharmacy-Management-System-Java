import java.nio.channels.ScatteringByteChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class medicine {
void add (){
    int again=0;
    while(again==0) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter medicine id: ");
        String id = in.nextLine();
        System.out.println("Enter medicine name: ");
        String name = in.nextLine();
        System.out.println("Enter medicine description: ");
        String desc = in.nextLine();
        System.out.println("Enter medicine price: ");
        double price = in.nextDouble();
        int quantity = 0;
        while (true) {
            try {
                System.out.println("Enter medicine quantity: ");
                quantity = in.nextInt();
                in.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("invalid input Enter again medicine quantity must be integer: ");
                in.nextLine();
            }
        }
        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = coneect.getConnection();
            if (con != null) {
                PreparedStatement statement = con.prepareStatement("insert into medicine (id,name,description,price,quantity) values(?,?,?,?,?)");
                statement.setString(1, id);
                statement.setString(2, name);
                statement.setString(3, desc);
                statement.setDouble(4, price);
                statement.setInt(5, quantity);
                int result = statement.executeUpdate();
                if (result > 0) {
                    System.out.println("DATA SAVE SUCCESSFULLY");
                } else {
                    System.out.println("Insertion failed");
                }
            } else {
                System.out.println("no connection");
            }
        } catch (Exception e) {
            System.out.println(e + "");

        }
        System.out.println("Do you want to add  more medicine?(yes/no) ");
        String answer = in.nextLine();
        if (answer.equalsIgnoreCase("yes")) {
            again = 0;

        } else {
            again = 1;
        }
    }
}
void deleteMedicine(){
        Scanner in = new Scanner(System.in);
    System.out.println("Enter medicine name you want to delete: ");
    String name = in.nextLine();
    Connection con = null;
    PreparedStatement preparedStatement = null;
    con = coneect.getConnection();
    if(con != null) {
        try {
            PreparedStatement prestatement = con.prepareStatement("SELECT * FROM medicine WHERE name = ? AND status = TRUE");
            prestatement.setString(1, name);
            ResultSet resultSet = prestatement.executeQuery();

    if (resultSet.next()) {
        System.out.println("Are you sure you want to delete the medicine? (yes/no)");
        String answer = in.nextLine();
        if (answer.equalsIgnoreCase("yes")) {

            try {
                PreparedStatement statement = con.prepareStatement("UPDATE medicine SET status = FALSE WHERE name = ?");
                statement.setString(1, name);
                int result = statement.executeUpdate();

                if (result > 0) {
                    System.out.println("Medicine deleted successfully");
                }

            } catch (Exception e) {
                System.out.println(e + " ");
            }
        }else{
            System.out.println("Medicine deletion cancel");
        }
    }else {
        System.out.println("Medicine not found");
    }
        }catch (Exception e){
            System.out.println(e + "");
        }
    }//if main null
}//main end
void updateMedicine(){
    Scanner in = new Scanner(System.in);
    System.out.println("Enter medicine name you want to update: ");
    String name = in.nextLine();
    Connection con = null;
    PreparedStatement preparedStatement = null;
    con = coneect.getConnection();
    if(con != null) {
        try {

            PreparedStatement prestatement = con.prepareStatement("SELECT * FROM medicine WHERE name = ? AND status = TRUE");
            prestatement.setString(1, name);
            ResultSet resultSet = prestatement.executeQuery();
//Az
            if (resultSet.next()) {
                System.out.println("Enter  new Medicine id");
                String id = in.nextLine();
                System.out.println("Enter new Medicine name");
                String newName = in.nextLine();
                System.out.println("Enter new Medicine description");
                String newDesc = in.nextLine();
                System.out.println("Enter new Medicine price");
                double newPrice = in.nextDouble();
                System.out.println("Enter new Medicine quantity");
                int newQuantity = in.nextInt();
                in.nextLine();
                System.out.println("Are you sure you want to update the medicine? (yes/no)");
                String answer = in.nextLine();
                if (answer.equalsIgnoreCase("yes")) {

                    try {
                        PreparedStatement statement = con.prepareStatement("update medicine set id=?  , name=?, description=?, price=?, quantity=? where name=?");
                statement.setString(1, id);
                statement.setString(2, newName);
                statement.setString(3, newDesc);
                statement.setDouble(4, newPrice);
                statement.setInt(5, newQuantity);
                statement.setString(6, name);
                        int result = statement.executeUpdate();
                        if (result > 0) {
                            System.out.println("Medicine updated successfully...");
                        }

                    } catch (Exception e) {
                        System.out.println(e + " ");
                    }
                }else{
                    System.out.println("Medicine updation cancel...");
                }
            }else {
                System.out.println("Medicine not found...");
            }
        }catch (Exception e){
            System.out.println(e + "");
        }
    }//if main null

}
void searchMedicine(){

    Scanner in = new Scanner(System.in);
System.out.println("Enter medicine name you want to search: ");
String name = in.nextLine();
Connection con = null;
PreparedStatement preparedStatement = null;
con = coneect.getConnection();
if(con != null) {
    try {
        PreparedStatement statement = con.prepareStatement("select * from medicine where name=? AND status = TRUE");
   statement.setString(1,name);
   ResultSet resultSet = statement.executeQuery();
   if (resultSet.next()) {
       System.out.println("  ID: " + resultSet.getString("id"));
       System.out.println("  Name: " + resultSet.getString("name"));
       System.out.println("  Description: " + resultSet.getString("description"));
       System.out.println("  Price: " + resultSet.getDouble("price"));
       System.out.println("  Quantity: " + resultSet.getInt("quantity"));
   }else{
       System.out.println("Medicine not found...");
   }

    }catch (Exception e){
        System.out.println(e + "");
    }
}
}

}
