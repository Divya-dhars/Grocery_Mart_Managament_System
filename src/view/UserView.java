package view;
import java.util.*;

import controller.UserController;
public class UserView {
    public static void viewProducts(String email){
        Scanner s=new Scanner(System.in);
        UserController controller=new UserController();
        int i=1;
        while(i==1){
        System.out.println("\n********************************************************");
        System.out.println("\t     1.Enter 1 to display Available Products            ");
        System.out.println("\t     2.Enter 2 to add the product to cart               ");
        System.out.println("\t     3.Enter 3 to buy the product directly              ");
        System.out.println("\t     4.Enter 4 to view the Order history                ");
        System.out.println("\t     5.Enter 5 to view the Cart history                 ");
        System.out.println("\t     6.Enter 6 to Exit                                  ");
        System.out.println("\t     Enter the option                                   ");
        System.out.println("**********************************************************");
        int ch=s.nextInt();
        s.nextLine();
        switch(ch){
            case 1:
            List<String> ls=controller.displayProducts();
            System.out.println("****************************************************************************");
            System.out.printf("| %-5s | %-20s | %-10s |\n", "id", "product name", "price");
            System.out.println("----------------------------------------------------------------------------");
            for(String str:ls){
                String[] parts = str.split("\\s+");
                System.out.printf("| %-5s | %-20s | %-10s |\n", parts[2] ,parts[4], parts[6]);
            }
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("*****************************************************************************");
            break;
            case 2:
            controller.displayProducts();
            System.out.println("Enter product id to add the product to cart");
            int id=s.nextInt();
            s.nextLine();
            System.out.println("Enter quantity you want");
            String q=s.nextLine();
            controller.addToCart(id,q,email);
            break;
            case 3:
            controller.displayProducts();
            System.out.println("Enter product id to buy the product");
            int id1=s.nextInt();
            s.nextLine();
            System.out.println("Enter quantity you want");
            String quan=s.nextLine();
            controller.buyProduct(id1,quan,email);
            break;
            case 4:
            List<String> ls1=controller.viewHistory(email);
            for(String str:ls1){
                System.out.print(str);
            }
            break;
            case 5:
            List<String> ls2=controller.viewCart(email);
            for(String str:ls2){
                System.out.print(str);
            }
            break;
            case 6:
            System.out.print("Exiting...");
            i=0;
            break;
         }
        }
        s.close();
    }
}
