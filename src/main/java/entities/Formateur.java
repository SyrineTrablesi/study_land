package entities;

public class Formateur extends User{
    public Formateur(){
        super();
    }
    //avecID
    public Formateur(int id,String nom, String prenom, String email, String password) {
        super( id,nom, prenom, email, password, "Formateur");
    }
    //sansID
    public Formateur(String nom, String prenom, String email, String password){
        super(nom, prenom, email, password, "Formateur");
    }
}
