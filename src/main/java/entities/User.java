package entities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private static final String validationEmail = "^(.+)@(.+)$";

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String role;

    // Get et set
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    private boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(validationEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void setEmail(String email) {
        if (isEmailValid(email)) {
            this.email = email;
        } else {
            System.out.println("L'e-mail n'est pas valide.");
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "User{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", Role='" + role + '\'' +
                ", id=" + id +
                '}';
    }

    //Constructeurs:
    //Constructeur par defaut 1
    public User() {
    }

    ;

    //Constructeur sans id
    public User(String nom, String prenom, String email, String password, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        role = this.role;
    }

    //constructeur avec id
    public User(int id, String nom, String prenom, String email, String password, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.id = id;
    }

    public User(String nom, String prenom, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }


}
