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
                    ls.clear();
                    break;
                case 2:
                    System.out.println("Enter product id to add the product to cart");
                    int id=s.nextInt();
                    s.nextLine();
                    System.out.println("Enter quantity you want");
                    String q=s.nextLine();
                    if(controller.addToCart(id,q,email)){
                        System.out.println("Product added in Cart");
                    }else{
                        System.out.println("Product not added to the cart");
                    }
                    break;
                case 3:
                    System.out.println("Enter product id to buy the product");
                    String id1=s.nextLine();
                    System.out.println("Enter quantity you want");
                    String quan=s.nextLine();
                    String amounttoPay=controller.displayAmount(id1);
                    int pay=Integer.parseInt(amounttoPay)*Integer.parseInt(quan);
                    System.out.println("Total amount to pay " + pay);
                    System.out.print("Enter the amount ");
                    String userAmountpay=s.nextLine();
                    if(controller.buyProduct(id1,quan,email) && String.valueOf(pay).equals(userAmountpay)){ 
                        System.out.println("Order Placed");
                    }else{
                        System.out.println("Order Not Placed");
                    }
                    break;
                case 4:
                    List<String> ls1=controller.viewHistory(email);
                    System.out.println("****************************************************************************");
                    System.out.printf("| %-5s | %-20s | %-10s |  %-10s\n", "id", "product name", "quantity", "price");
                    System.out.println("----------------------------------------------------------------------------");
                    for(String str:ls1){
                        String[] parts = str.split("\\s+");
                        System.out.printf("| %-5s | %-20s | %-10s | %-10s\n", parts[2] ,parts[5], parts[7],parts[9]);
                    }
                    System.out.println("-----------------------------------------------------------------------------");
                    System.out.println("*****************************************************************************");
                    break;
                case 5:
                    List<String> ls2=controller.viewCart(email);
                    System.out.println("****************************************************************************");
                    System.out.println("----------------------------------------------------------------------------");
                    System.out.printf("| %-5s | %-20s | %-10s | %-10s \n", "id", "product name", "quantity","price");
                    System.out.println("----------------------------------------------------------------------------");
                    for(String str:ls2){
                    String[] parts = str.split("\\s+");
                        System.out.printf("| %-5s | %-20s | %-10s | %-10s \n", parts[3] ,parts[7], parts[10],parts[13]);
                    }
                    System.out.println("-----------------------------------------------------------------------------");
                    System.out.println("*****************************************************************************");
                    int k=1;
                    while(k==1){
                        System.out.println("-------Do you want to buy the products?----------");
                        System.out.println("*************************************************");
                        System.out.println("       1.Continue buying                         ");
                        System.out.println("       2.Remove Product from cart                ");
                        System.out.println("       3.Cancel                                  ");  
                        System.out.println("*************************************************");
                        int choice=s.nextInt();
                        s.nextLine();
                        switch(choice){
                            case 1:
                                String amount=controller.displayTotAmount(email);
                                System.out.println("Total amount to pay " + amount);
                                System.out.print("Enter the amount ");
                                String userAmount=s.nextLine();
                                if(amount.equals(userAmount)){
                                    for(String str : ls2){
                                        String[] part = str.split("\\s+");
                                        int idFromCart = Integer.parseInt(part[3]);
                                        String quantityFromCart = part[10];
                                        System.out.print(idFromCart + " "+quantityFromCart);
                                        if(controller.storeOrderDetails(idFromCart, quantityFromCart, email)){
                                            System.out.println("Order details stored successfully.");
                                        } else {
                                            System.out.println("Failed to store order details.");
                                        }
                                    }
                                }else{
                                    System.out.println("Payment Failed.Order Not Placed");
                                }
                                break;
                            case 2:
                               System.out.println("*******************************************");
                               System.out.println("-------------------------------------------");
                               System.out.println("         1.Remove all products             ");
                               System.out.println("         2.Remove particular products      ");
                               System.out.println("         3.Cancel                          ");
                               System.out.println("*******************************************");
                               System.out.println("-------------------------------------------");
                               int c=s.nextInt();
                               s.nextLine();
                               switch(c){
                                case 1:
                                  if(controller.removeAllProductsFromCart(email)){
                                    System.out.println("All products removed from cart.");
                                  }else{
                                    System.out.println("Not Removed");
                                  }
                                break;
                                case 2:
                                System.out.println("Enter product id to remove from cart");
                                int proId=s.nextInt();
                                s.nextLine();
                                if(controller.removeProduct(proId,email)){
                                    System.out.println("Product removed from cart.");
                                }else{
                                    System.out.println("Not Removed");
                                }
                                break;
                                case 3:
                                System.out.println("Exiting....");
                                break;
                               }
                            break;
                            case 3:
                                System.out.println("Exiting....");
                                k=0;
                                break;
                            }
                        }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    i=0;
                    break;
                }
            }
            // s.nextLine();
            s.close();
        }
}
