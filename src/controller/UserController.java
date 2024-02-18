package controller;
import java.util.*;
import model.UserModel;
public class UserController{
    UserModel model=new UserModel();
    public List<String> displayProducts() {
       return model.displayProducts();
    }
    public String addToCart(int id,String q,String email) {
        return model.addToCart(id,q,email);
    }
    public String buyProduct(int id1,String quan,String email){
        return model.buyProduct(id1,quan,email);
   }
   public List<String> viewHistory(String email){
    return model.viewHistory(email);
   }
   public List<String> viewCart(String email){
    return model.viewCart(email);
   }
}