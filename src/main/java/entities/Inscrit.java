package entities;

import java.util.Date;

public class Inscrit extends Formation {
    int idInscrit;
    int id_user;
    private String type;
    private Date dateAjout;




    // Getter et setter pour l'attribut type

    public int getIdInscrit() {
        return idInscrit;
    }

    public void setIdInscrit(int idInscrit) {
        this.idInscrit = idInscrit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getter et setter pour l'attribut dateAjout
    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Inscrit{" +
                "idInscrit=" + getIdInscrit() +
                "id_user" + getId_user() +
                "idFormation=" + getIdFormation() +
                ", nomCategorie='" + getNomCategorie() + '\'' +
                ", titre='" + getTitre() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", duree=" + getDuree() +
                ", dateDebut=" + getDateDebut() +
                ", dateFin=" + getDateFin() +
                ", prix=" + getPrix() +
                ", niveau='" + getNiveau() + '\'' +
                ", type='" + type + '\'' +
                ", dateAjout=" + dateAjout +
                '}';
    }
    // Appel du constructeur

    //constructeur par défaut
    public Inscrit() {
        super(); // Appel du constructeur de la classe mère Formation
    }
    //constructeur sans ID
    public Inscrit(int id_user,int idFormation, String nomCategorie, String titre, String description, int duree, Date dateDebut, Date dateFin, float prix, String niveau, Date dateAjout) {
        super(idFormation, nomCategorie, titre, description, duree, dateDebut, dateFin, prix, niveau);
        this.id_user = id_user;
        this.type = "Inscrit";
        this.dateAjout = dateAjout;
    }
    //constructeur avec ID

    public Inscrit(int idInscrit,int id_user,int idFormation, String nomCategorie, String titre, String description, int duree, Date dateDebut, Date dateFin, float prix, String niveau,  Date dateAjout) {
        super(idFormation, nomCategorie, titre, description, duree, dateDebut, dateFin, prix, niveau);
        this.idInscrit = idInscrit;
        this.id_user = id_user;
        this.type = "Inscrit";
        this.dateAjout = dateAjout;
    }
    public Inscrit(int id_user,int idFormation,  Date  dateAjout) {
        super(idFormation);
        this.id_user = id_user;
        this.type = "Inscrit";
        this.dateAjout = dateAjout;
    }
    public Inscrit(int idInscrit) {
        super();
        this.idInscrit = idInscrit;
    }
    public Inscrit(int id_user,int idFormation, String nomCategorie, String titre, String description, int duree, float prix, String niveau) {
        super(idFormation, description, titre,  duree, prix, niveau,nomCategorie);
        this.idInscrit = idInscrit;
        this.id_user = id_user;
        this.type = "Inscrit";
        this.dateAjout = dateAjout;
    }
 // deux id
 public Inscrit(int id_user,int idFormation) {
     super(idFormation);
     this.id_user = id_user;
     this.type="Inscrit";

 }
}
