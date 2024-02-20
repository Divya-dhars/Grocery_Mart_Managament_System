package view;
import java.util.*;

import controller.AdminController;
public class AdminView {
    
    public static void displayProduct() throws Exception{
        Scanner s=new Scanner(System.in);
        AdminController controller=new AdminController();
        int i=1;
        while(i==1){
            System.out.println("\n********************************************************");
            System.out.println("\t     1.Enter 1 add Products                             ");
            System.out.println("\t     2.Enter 2 to delete Products                       ");
            System.out.println("\t     3.Enter 3 to Update Products                       ");
            System.out.println("\t     4.Enter 4 to view Products                         ");
            System.out.println("\t     5.Enter 5 to view the dealer Details               ");
            System.out.println("\t     6.Enter 6 to view the Order Details                ");
            System.out.println("\t     7.Enter 7 to Request Stocks                        ");
            System.out.println("\t     8.Enter 8 to Exit                                  ");
            System.out.println("\t     Enter the option                                   ");
            System.out.println("**********************************************************");
            int ch=s.nextInt();
            s.nextLine();
            switch(ch){
                case 1:
                    System.out.println("Enter product name:");
                    String pro=s.nextLine();
                    System.out.println("Enter Quantity:");
                    String q=s.nextLine();
                    System.out.println("Enter price");
                    String price=s.nextLine();
                    if(controller.addProduct(pro,q,price)){
                        System.out.println("Product Added");
                    }
                    else{
                        System.out.println("Product not added");
                    }
                    break;
                case 2:
                    System.out.println("Enter id to delete the product");
                    int id=s.nextInt();
                    if(controller.deleteProduct(id)){
                        System.out.println("Product Deleted");
                    }else{
                        System.out.println("Product not deleted");
                    }
                    break;
                case 3:
                    int j=1;
                    while(j==1){
                        System.out.println("\n********************************************************");
                        System.out.println("\t     1.Enter 2 update Product quantity                  ");
                        System.out.println("\t     2.Enter 3 to update Product price                  ");
                        System.out.println("\t     3.Enter 4 to Exit                                  ");
                        System.out.println("**********************************************************");
                        int choice=s.nextInt();
                        s.nextLine();
                        switch(choice){
                            case 1:
                            //controller.displayProducts();
                            System.out.println("Enter the Product id to change the Quantity of product:");
                            int id2=s.nextInt();
                            s.nextLine();
                            System.out.println("Enter the Quantity you want to change");
                            String q1=s.nextLine();
                            if(controller.updateQuantity(id2,q1)){
                                System.out.println("Updated Successfully");
                            }else{
                                System.out.println("Not Updated");
                            }
                            break;
                            case 2:
                            System.out.println("Enter the Product id to change the Price of product:");
                            int id1=s.nextInt();
                            s.nextLine();
                            System.out.println("Enter the Price you want to change");
                            String p=s.nextLine();
                            if(controller.updatePrice(id1,p)){
                                System.out.println("Updated Successfully");
                            }else{
                                System.out.println("Not Updated");
                            }
                            break;
                            case 3:
                            System.out.println("Exiting");
                            j=0;
                            break;
                        }
                    }
                    break;
                case 4:
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
                    ls.clear();
                    break;
                case 5:
                    List<String> lst=controller.displayDealer();
                    System.out.println("****************************************************************************");
                    System.out.printf("| %-5s | %-20s | %-10s | %-10s\n", "id", "name","email", "contact");
                    System.out.println("----------------------------------------------------------------------------");
                    for(String str:lst){
                        String[] parts = str.split("\\s+");
                        System.out.printf("| %-5s | %-20s | %-10s | %-10s\n", parts[2] ,parts[4],parts[6], parts[8]);
                    }
                    System.out.println("-----------------------------------------------------------------------------");
                    System.out.println("*****************************************************************************");
                    lst.clear();
                    break;
                case 6:
                    List<String> lst1=controller.orderHistory();
                    System.out.println("****************************************************************************");
                    System.out.printf("| %-5s | %-20s | %-10s | %-20s | %-10s\n", "id", "product name", "quantity","price","email");
                    System.out.println("----------------------------------------------------------------------------");
                    for(String str:lst1){
                        String[] parts = str.split("\\s+");
                        System.out.printf("| %-5s | %-20s | %-10s | %-20s| %-10s\n", parts[2] ,parts[5], parts[8],parts[11],parts[14]);
                    }
                    System.out.println("-----------------------------------------------------------------------------");
                    System.out.println("*****************************************************************************");
                    lst1.clear();
                    break;
                case 7:
                    ls=controller.displayProductsLess();
                    System.out.println("****************************************************************************");
                    System.out.printf("| %-5s | %-20s | %-10s |\n", "id", "product name", "price");
                    System.out.println("----------------------------------------------------------------------------");
                    for(String str:ls){
                        String[] parts = str.split("\\s+");
                        System.out.printf("| %-5s | %-20s | %-10s |\n", parts[2] ,parts[4], parts[6]);
                    }
                    System.out.println("-----------------------------------------------------------------------------");
                    System.out.println("*****************************************************************************");
                    System.out.println("Enter product Id");
                    String productId=s.nextLine();
                    boolean result=controller.addStocks(productId);
                    if(result){
                        System.out.println("Stock added successfully");
                    }
                    else{
                        System.out.println("Error in adding");
                    }
                    ls.clear();
                    break;
                case 8:
                    System.out.println("Exiting");
                    i=0;
                    break;
            }
        }
    }
}
