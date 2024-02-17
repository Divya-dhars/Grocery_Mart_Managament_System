package view;
import java.util.*;
import controller.RegisterController;
public class RegisterView {
    public static void viewRegister()throws Exception{
        Scanner in=new Scanner(System.in);
        RegisterController register=new RegisterController();
        System.out.print("Username:");
        String username=in.nextLine();
        System.out.print("Email:");
        String email=in.nextLine();
        System.out.print("Password:");
        String password=in.nextLine();
        System.out.println("Role:");
        System.out.println("1.User  ||  2.Dealer");
        String role=in.nextLine();
        boolean flag=false;
        if(role.equalsIgnoreCase("dealer")){
            System.out.print("Mobile Number:");
            String mobileNumber=in.nextLine();
            System.out.print("Address:");
            String address=in.nextLine();
            flag=register.registerUser(username,email,password,role,mobileNumber,address);
        }
        /*else if(role.equalsIgnoreCase("Dealer")){
            System.out.println("Contact Number:");
            String contactNumber=in.nextLine();
            flag=register.registerUser(username,email,password,role,contactNumber);
        }*/
        if(flag){
            LoginView.viewLogin();
        }
        in.close();
    }
}
