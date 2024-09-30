import java.sql.SQLException;
import java.util.*;
import java.util.InputMismatchException;
import static java.lang.System.exit;

public class Main {

    int securitycheck(){
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Enter 1 for manager");
            System.out.println("Enter 2 for employee");
            System.out.println("Enter 3 to exit");
            try {
                        admin a = new admin();
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Enter 1 for signin");
                        System.out.println("Enter 2 for exit");
                        int signin = input.nextInt();
                        try {
                            switch (signin) {
                                case 1:
                                    int k = a.signin();
                                    if (k == 1) {
                                        return 1;
                                    } else {
                                        return 0;
                                    }
                                case 2:
                                    System.out.println("Exiting...");
                                    return 3;
                            }
                        }catch (InputMismatchException e) {
                            System.out.println("Invalid input");
                            input.nextLine();
                        }
                        break;
                    case 2:
                        employee e = new employee();
                        System.out.println("Enter 1 for signin");
                        System.out.println("Enter 2 for signup");
                        System.out.println("Enter 3 to exit");
                        try {
                            int signup = input.nextInt();
                            switch (signup) {
                                case 1:
                                    int k = e.signin();
                                    if (k == 1) {
                                        return 2;
                                    } else {
                                        return 0;
                                    }

                                case 2:
                                    e.register();
                                    break;
                            }
                        }catch (InputMismatchException p){
                            System.out.println("Invalid input");
                        }
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        return 3;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                input.nextLine(); // Consume newline character to clear the input buffer
            }
        }
    }
    }

void empmenu() {
    medicine med = new medicine();
    Scanner input = new Scanner(System.in);
    boolean flag = true; // Set flag to true to enter the loop
    while (flag) {
        System.out.println("Enter 1 for add medicine");
        System.out.println("Enter 2 for delete medicine");
        System.out.println("Enter 3 to update medicine");
        System.out.println("Enter 4 to search medicine");
        System.out.println("Enter 5 to exit ");
        try {
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    med.add();
                    break;
                case 2:
                    med.deleteMedicine();
                    break;
                case 3:
                    med.updateMedicine();
                    break;
                case 4:
                    med.searchMedicine();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    flag = false; // Set flag to false to exit the loop
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            input.next(); // Clear the invalid input
        }
    }
}


public  void main(String[] args) {
    stock  st = new stock();

        sales_Record sl =new sales_Record();
    while (true) {
       Main m=new Main();
       try {
           int j = m.securitycheck();
           if (j == 1) {
               System.out.println("Welcome to the manager  System!");
               boolean loop=true;
               do {
                   System.out.println("Enter 1 for CRUD operations");
                   System.out.println("Enter 2 for show full stock");
                   System.out.println("Enter 3 for show low stock");
                   System.out.println("Enter 4 for show todays sales record");
                   System.out.println("Enter 5 for to show sales by month record");
                   System.out.println("Enter 6 to exit");
                   Scanner input = new Scanner(System.in);
                   try{
                       int choice = input.nextInt();
                       switch (choice) {
                           case 1:
                               empmenu();
                               break;
                           case 2:
                               st.stock_management();
                               break;
                           case 3:
                               st.low_stock_management();
                               break;
                           case 4:
                            sl.record();
                               break;
                           case 5:
                               sl.month();
                               break;
                               case 6:
                               loop=false;

                       }
                   }catch (Exception e){
                       System.out.println("Invalid input. Please try again.");
                       loop=true;
                   }

               }while(loop);
               break;
           }else if(j==2){
               sales menu = new sales();
               boolean loop=true;
               do {
                   System.out.println("Welcome to the Employee System!");
                   System.out.println("Enter 1 for CRUD operations");
                   System.out.println("Enter 2 for sales system");
                   System.out.println("Enter 3 for search medicine");
                   System.out.println("Enter 4 for show full stock");
                   System.out.println("Enter 5 to exit");
                   Scanner input = new Scanner(System.in);
                   try{
                   int choice = input.nextInt();
                   switch (choice) {
                       case 1:
                           empmenu();
                           break;
                       case 2:
                           menu.purchase();
                           break;
                       case 3:
                        menu.searchMedicine();
                        break;
                       case 4:
                           st.stock_management();
                           break;
                             case 5:
                        loop=false;
                   }
                   }catch (Exception e){
                       System.out.println("Invalid input. Please try again.");
                       loop=true;
                   }
               }while(loop);
           } else if (j==3) {
               break;
           }
       }catch (Exception e) {
           System.out.println("Invalid input. Please try again.");
        }
    }
}
