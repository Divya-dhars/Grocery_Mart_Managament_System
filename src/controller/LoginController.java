package controller;
import model.LoginModel;
public class LoginController {
    private LoginModel model=new LoginModel();
    public int checkUser(String email, String password) throws Exception{
        return model.checkUser(email, password);
    }
}

