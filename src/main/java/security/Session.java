package security;

import entities.User;
import services.ServiceUser;

public class Session {
     public  static UserInfo userInfo;
     ServiceUser  serviceuser =new ServiceUser();
     //Methode login
    public void login(String email,String password){
        try {
            User user =serviceuser.connexion(email,password);
            userInfo=new UserInfo(user.getId(), user.getNom(), user.getPrenom(), user.getRole(), user.getEmail(), user.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


 }
