package security;

import entities.User;
import services.ServiceUser;

public class Session {
    private static Session instance;
    private UserInfo userInfo;
    private ServiceUser serviceuser = new ServiceUser();

    // Constructeur privé pour empêcher l'instanciation directe depuis l'extérieur de la classe
    private Session() {}

    // Méthode statique pour obtenir l'instance unique de Session
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    // Méthode login
    public void login(String email, String password) {
        try {
            User user = serviceuser.connexion(email, password);
            userInfo = new UserInfo(user.getId(), user.getNom(), user.getPrenom(), user.getRole(), user.getEmail(), user.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Autres méthodes pour accéder aux informations de session
    public UserInfo getUserInfo() {
        return userInfo;
    }
}

