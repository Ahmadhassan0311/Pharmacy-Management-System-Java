
import java.util.Scanner;

public class admin  extends security {

    @Override
    public int signin() {
        try {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username: ");
        username = sc.nextLine();
        System.out.print("Enter password: ");
        password = sc.nextLine();
        if(username.equals("ahmad") && password.equals("ahmad123")){
            return 1;
        }else{
            System.out.println("Invalid username or password");
            return 0;
        }

        } catch (Exception e) {
            System.out.println(e);
        return 0;
        }
    }
}