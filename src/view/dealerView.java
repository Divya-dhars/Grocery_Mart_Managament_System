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
        System.out.println("\t     1.Enter 1 send Stock                               ");
        System.out.println("\t     2.Enter 2 to display Stock                         ");
        System.out.println("**********************************************************");
        int ch=s.nextInt();
        s.nextLine();
        switch(ch){
            case 1:
            int j=0;
            while(j==1){
                System.out.println("\n********************************************************");
                System.out.println("\t     1.Enter 1 add new Product                         ");
                System.out.println("\t     2.Enter 2 to add existing Product                  ");
                System.out.println("\t     3.Enter 3 to Exit                                  ");
                System.out.println("**********************************************************");
                int choice=s.nextInt();
                s.nextLine();
                switch(choice){
                    case 1:
                    System.out.println("Enter id");
                    int id=s.nextInt();
                    System.out.println("Enter product name");
                    String p=s.nextLine();
                    System.out.println("Enter product quantity");
                    String q=s.nextLine();
                    if(controller.sendStock(id,p,q)){
                        System.out.println("send Successfully");
                    }
                    else{
                        System.out.print("Not sended");
                    }
                    break;
                    case 2:
                    break;
                    case 3:
                    System.out.println("Exiting")
                }
            }
            break;
            case 2:
            List<String> ls=controller.displayStock();
            System.out.println("****************************************************************************");
            System.out.printf("| %-5s | %-20s | %-10s |\n", "id", "product name", "quantity");
            System.out.println("----------------------------------------------------------------------------");
            for(String str:ls){
                String[] parts = str.split("\\s+");
                System.out.printf("| %-5s | %-20s | %-10s |\n", parts[2] ,parts[4], parts[6]);
            }
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("*****************************************************************************");
            break;
            
          }
       }
    }
}
