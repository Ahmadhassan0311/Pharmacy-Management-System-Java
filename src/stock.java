import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class stock {
    void stock_management(){
        Scanner sc = new Scanner(System.in);
        Connection con =null;
        con=coneect.getConnection();
        if(con!=null){
            try {
                PreparedStatement pst = con.prepareStatement("select * from medicine where status=1");
                boolean found = false;
                ResultSet rs = pst.executeQuery();
                int i=1;
                System.out.println("Medicine no    id             name               description                  Price    Quantity");
                while (rs.next()) {
                    found = true;
                    System.out.printf("%-14s%-15s%-20s%-30s%-9.2f%5d%n",
                            i, rs.getString("id"), rs.getString("name"), rs.getString("description"),
                            rs.getDouble("price"), rs.getInt("quantity"));
                    i++;
                }
                if(!found){
                    System.out.println("No medicine found in the stock ");
                }
            }catch(Exception e){
                System.out.println(e);

            }
        }
    }
    void low_stock_management(){
        Scanner sc = new Scanner(System.in);
        Connection con =null;
        con=coneect.getConnection();
        if(con!=null){
            try {

                PreparedStatement pst = con.prepareStatement("select * from medicine where quantity<=5 AND status = TRUE");
                ResultSet rs = pst.executeQuery();
                int i=1;
                boolean found = false;
                System.out.println("Medicine no    id             name               description                  Price    Quantity");
                while (rs.next()) {
                    found = true;
                    System.out.printf("%-14s%-15s%-20s%-30s%-9.2f%5d%n",
                            i, rs.getString("id"), rs.getString("name"), rs.getString("description"),
                            rs.getDouble("price"), rs.getInt("quantity"));
                    i++;
                }
       if(!found){
           System.out.println("No medicine found in the stock is less than 5!");
       }
            }catch(Exception e){
                System.out.println(e);

            }
        }
    }
}
