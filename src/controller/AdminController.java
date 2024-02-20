package controller;
import java.util.*;
import model.AdminModel;
public class AdminController {
    AdminModel model=new AdminModel();
    public boolean addProduct(String name,String q,String price){
        return model.addProduct(name,q,price);
    }
    public boolean deleteProduct(int id){
        return model.deleteProduct(id);
    }
    public boolean updateQuantity(int id2, String q){
        return model.updateQuantity(id2,q);
    }
    public boolean updatePrice(int id1,String p){
        return model.updatePrice(id1,p);
    }
    public List<String> displayProducts(){
        return model.displayProducts();
    }
    public List<String> displayProductsLess(){
        return model.displayProducts();
    }
    public List<String> displayDealer(){
        return model.displayDealer();
    }
    public List<String> orderHistory(){
        return model.orderHistory();
    }
    public boolean addStocks(String productId)throws Exception{
        return model.addStock(productId);
    }
}
