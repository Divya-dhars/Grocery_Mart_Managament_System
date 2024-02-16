import java.util.*;
import view.LoginView;
import view.RegisterView;
public class Grocery{
    public static void main(String[] args) throws Exception{
        Scanner s=new Scanner(System.in);
        int i=1;
        System.out.println("\n\t----------------------------------Welcome to G-Mart--------------------------------\n");
        System.out.println();
        while(i==1){
            System.out.println("                                      1.Login                                        ");
            System.out.println("                                      2.Register                                     ");
            System.out.println("                                      3.Exit                                         ");
            int choice=s.nextInt();
            switch(choice){
                case 1:
                    LoginView.viewLogin();
                    break;
                case 2:
                    RegisterView.viewRegister();
                    break;
                case 3:
                    System.out.println("Exiting...  ");
                    i=0;
                    break;
            }
        }
        s.close();
    }
}