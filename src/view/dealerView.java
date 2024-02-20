package view;

import java.util.*;
import controller.dealerController;

public class dealerView {
    public static void viewProducts(){
       Scanner s=new Scanner(System.in);
       dealerController controller=new dealerController();
       int i=1;
       while(i==1){
        System.out.println("\n********************************************************");
        System.out.println("\t     1.Enter 1 add  Stock                               ");
        System.out.println("\t     2.Enter 2 to display Stock                         ");
        System.out.println("\t     3.Exit                                             ");
        System.out.println("**********************************************************");
        int ch=s.nextInt();
        s.nextLine();
        switch(ch){
            case 1:
            System.out.println("Enter id");
            int id=s.nextInt();
            s.nextLine();
            System.out.println("Enter product quantity");
            String q=s.nextLine();
            if(controller.sendStock(id,q)){
                System.out.println("Send Successfully");
            }else{
                System.out.println("Not Sendesd");
            }
            break;
            case 2:
            List<String> ls=controller.displayStock();
            System.out.println("****************************************************************************");
            System.out.printf("| %-5s | %-20s | %-10s | %-10s\n", "id", "product name", "quantity","price");
            System.out.println("----------------------------------------------------------------------------");
            for(String str:ls){
                String[] parts = str.split("\\s+");
                System.out.printf("| %-5s | %-20s | %-10s | %-10s\n", parts[2] ,parts[5], parts[8],parts[11]);
            }
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("*****************************************************************************");
            break;
            case 3:
            System.out.println("Exiting...");
            break;
            
          }
       }
    }
}
