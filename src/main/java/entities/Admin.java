package entities;

public class Admin  extends User{
    //par defaut
    public Admin(){
        super();
    }
    //avecID
    public Admin(int id,String nom, String prenom, String email, String password) {
        super( id,nom, prenom, email, password, "Admin");
    }
    //sansID
    public Admin(String nom, String prenom, String email, String password){
        super(nom, prenom, email, password, "Admin");
    }


}
