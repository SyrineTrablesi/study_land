package security;

public class UserInfo {
   public String nom, prenom,role,email,mdp;
  public Integer id;

   public UserInfo(int id,String nom, String prenom, String role, String email, String mdp) {
      this.id=id;
      this.nom = nom;
      this.prenom = prenom;
      this.role = role;
      this.email = email;
      this.mdp = mdp;
   }

}
