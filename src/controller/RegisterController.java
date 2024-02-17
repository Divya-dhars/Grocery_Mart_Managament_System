package controller;
import model.RegisterModel;
public class RegisterController {
    RegisterModel model=new RegisterModel();

    public boolean registerUser(String username,String email,String password,String role,String mobile,String address) throws Exception {
        return model.registerUser(username,email,password,role,mobile,address);
    }
}
