import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Scanner;

public class sales extends medicine {
    void purchase() {
        Scanner in = new Scanner(System.in);
        boolean b = false;
        int customerid = getId();
    double bill = 0;
        do {
            System.out.println("Enter name of medicine you want to buy: ");
            String name = in.nextLine();
            Connection con = null;
            con = coneect.getConnection();
            if (con != null) {
                try {
                    PreparedStatement statement = con.prepareStatement("SELECT * FROM medicine WHERE name=? AND status = TRUE");
                    statement.setString(1, name);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        System.out.println("Enter quantity you want to buy: ");
                        int quantity = in.nextInt();
                        int quantity1 = resultSet.getInt("quantity");
                        if (quantity1 >= quantity && quantity1 > 0 && quantity > 0) {
                            int quantity2 = quantity1 - quantity;
                            PreparedStatement updateStatement = con.prepareStatement("UPDATE medicine SET quantity=? WHERE name=?");
                            updateStatement.setInt(1, quantity2);
                            updateStatement.setString(2, name);
                            updateStatement.executeUpdate();

                            String name1 = resultSet.getString("name");
                            double price1 = resultSet.getDouble("price");
                            PreparedStatement insertStatement = con.prepareStatement("INSERT INTO sales(customer_id, medicine_name, price, quantity, date) VALUES (?, ?, ?, ?, ?)");
                            insertStatement.setInt(1, customerid);
                            insertStatement.setString(2, name1);
                            insertStatement.setDouble(3, price1);
                            insertStatement.setInt(4, quantity);
                            insertStatement.setObject(5, LocalDateTime.now());
                            insertStatement.executeUpdate();
                        } else {
                            System.out.println("Medicine does not have enough quantity.");
                        }
                    } else {
                        System.out.println("Medicine not found.");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    try {
                        if (con != null) {
                            con.close();
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
            System.out.println("Do you want to continue? (yes/no)");
            String answer = in.next();
            in.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                b = true;
            } else {
                try {
                    con = coneect.getConnection();
                    if (con != null) {
                        PreparedStatement statement = con.prepareStatement("SELECT * FROM sales WHERE customer_id=?");
                        statement.setInt(1, customerid);
                        ResultSet resultSet = statement.executeQuery();
                        boolean found = false;
                        System.out.println("Name           Price        Quantity");
                        while (resultSet.next()) {
                            found = true;
                            System.out.print(resultSet.getString("medicine_name"));
                            System.out.print("          " + resultSet.getDouble("price"));
                            System.out.println("          " + resultSet.getInt("quantity"));
                            int a = (int) resultSet.getDouble("price");
                            int c = resultSet.getInt("quantity");
                            bill = bill + (a * c);
                        }
                        if(!found){
                          System.out.println("User did not buy any medicine");
                        }
                        try {
                          int i=  getId();
                            System.out.println("\nYour total bill is " + bill);
                            PreparedStatement statement2 = con.prepareStatement("insert into bill (id, amount, date) values(?,?,?)");
                            statement2.setInt(1, i);
                            statement2.setDouble(2, bill);
                            statement2.setObject(3, LocalDateTime.now());
                            statement2.executeUpdate();
                        } catch (Exception k) {
                            System.out.println(k);
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    try {
                        if (con != null) {
                            con.close();
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                b = false;
            }
        } while (b);
    }

    int getId() {
        int newCustomerId = 1; // Default value if no records exist
        Connection con = null;
        try {
            con = coneect.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT MAX(customer_id) AS max_id FROM sales");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                newCustomerId = resultSet.getInt("max_id") + 1;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return newCustomerId;
    }
}
