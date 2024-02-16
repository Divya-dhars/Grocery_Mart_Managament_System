package view;

public class UserView {
    public void printLoginPrompt(){
        System.out.println("Please enter your username and password to login:");
    }
    public void printRegisterPrompt(){
        System.out.println("Please enter your username and password to register:");
    }
    public void printSuccessMessage(String message){
        System.out.println("Success: " +message);
    }
    public void printErrorMessage(String message){
        System.out.println("Error: " +message);
    }
}

