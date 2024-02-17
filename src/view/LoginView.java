package view;
import java.util.*;
import controller.LoginController;
public class LoginView {
    public static void viewLogin()throws Exception{
        Scanner in=new Scanner(System.in);
        System.out.print("Email:");
        String email=in.nextLine();
        System.out.print("Password:");
        String password=in.nextLine();
        LoginController login=new LoginController();
        int flag=login.checkUser(email,password);
        if(flag==1){
            System.out.print("Login Successfully");
            dealerView.viewProducts();
        }
        else if(flag==2){
            //dealerView.viewJobProviders();
        }
        else if(flag==3){
           // dealerView.viewAdmin();
        }
        else{
            System.out.println("Invalid Username or Password");
        }
        in.close();
    }
}