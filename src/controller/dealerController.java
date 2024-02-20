package controller;
import java.util.*;
import model.dealerModel;
public class dealerController {
    dealerModel model=new dealerModel();
    public boolean sendStock(int id,String q){
        return model.sendStock(id,q);
    }
    public List<String> displayStock(){
        return model.displayStock();
    }
}
