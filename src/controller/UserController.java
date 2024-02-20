package controller;
import java.util.*;
import model.UserModel;
public class UserController{
    UserModel model=new UserModel();
    public List<String> displayProducts() {
       return model.displayProducts();
    }
    public boolean addToCart(int id,String q,String email) {
        return model.addToCart(id,q,email);
    }
    public boolean buyProduct(int id1,String quan,String email){
        return model.buyProduct(id1,quan,email);
   }
   public List<String> viewHistory(String email){
    return model.viewHistory(email);
   }
   public List<String> viewCart(String email){
    return model.viewCart(email);
   }
   public String displayTotAmount(String email){
    return model.displayTotAmount(email);
   }
   public boolean storeOrderDetails(int id2,String quan1,String email){
    return model.storeOrderDetails(id2,quan1,email);
  }
  public boolean removeAllProductsFromCart(String email){
     return model.removeAllProductsFromCart(email);
  }
  public boolean removeProduct(int proId,String email){
    return model.removeProduct(proId,email);
  }
}