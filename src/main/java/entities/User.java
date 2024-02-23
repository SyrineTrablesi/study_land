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
    private  String  confirmerPassword;
    private String role;

    // Get et set
    public String getNom() {
        return nom;
    }

    public String getConfirmerPassword() {
        return confirmerPassword;
    }

    public void setConfirmerPassword(String confirmerPassword) {
        if (confirmerPassword.equals(password)) {
            this.confirmerPassword = confirmerPassword;
        } else {
            System.out.println("Le mot de passe de confirmation ne correspond pas au mot de passe.");
        }
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

    public boolean isEmailValid(String email) {
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


    //Constructeur sans id
    public User(String nom, String prenom, String email, String password, String role, String confirmerPassword) {
        this.nom = nom;
        this.prenom = prenom;
        setEmail(email);
        this.password = password;
        setConfirmerPassword(confirmerPassword);
        this.role = role;
    }
    //Apprenant:
    public User(int id ,String nom, String prenom, String email, String password, String role, String confirmerPassword) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        setEmail(email);
        this.password = password;
        setConfirmerPassword(confirmerPassword);
        this.role = role;
    }
//Constructeur Admin+Formateur (sans confirmerpass ==> pas de regeter))
    //constructeur avec id
    public User(int id, String nom, String prenom, String email, String password, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.id = id;
    }
//Constructeur Admin+Formateur (sans confirmerpass ==> pas de regeter))
    public User(String nom, String prenom, String email, String password,String role) {
        this.nom = nom;
        this.prenom = prenom;
        setEmail(email);
        this.email = email;
        this.role=role;
        this.password = password;
    }
    //sans mdp pour la modification
    public User( String nom, String prenom, String email,String role,int id) {
        this.nom = nom;
        this.prenom = prenom;
        setEmail(email);
        this.email = email;
        this.role=role;
        this.id=id;
    }


}
