package view;
import java.util.*;
import controller.LoginController;
public class LoginView {
    @SuppressWarnings("static-access")
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
            UserView.viewProducts(email);
        }
        else if(flag==2){
            //dealerView.viewJobProducts();
        }
        else if(flag==3){
           //AdminView.viewProducts();
        }
        else{
            System.out.println("Invalid Username or Password");
        }
        in.close();
    }
}