package entities;

import java.util.Date;

public class Favoris extends Formation {
    private int idFavoris;
    private int id_user;
    private String type;
    private Date dateAjout;

    // Getter et setter pour l'attribut idFavoris
    public int getIdFavoris() {
        return idFavoris;
    }

    public void setIdFavoris(int idFavoris) {
        this.idFavoris = idFavoris;
    }

    // Getter et setter pour l'attribut type
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

    // Getter et setter pour l'attribut id_user
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    // Méthode toString pour afficher les informations
    @Override
    public String toString() {
        return "Favoris{" +
                "idFavoris=" + idFavoris +
                ", id_user=" + id_user +
                ", idFormation=" + getIdFormation() +
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

    // Constructeurs
    // Constructeur par défaut
    public Favoris() {
        super(); // Appel du constructeur de la classe mère Formation
    }

    // Constructeur sans ID
    public Favoris(int id_user, int idFormation, String nomCategorie, String titre, String description, int duree, Date dateDebut, Date dateFin, float prix, String niveau, String type, Date dateAjout) {
        super(idFormation, nomCategorie, titre, description, duree, dateDebut, dateFin, prix, niveau);
        this.id_user = id_user;
        this.type = "Favoris";
        this.dateAjout = dateAjout;
    }

    // Constructeur avec ID
    public Favoris(int idFavoris, int id_user, int idFormation, String nomCategorie, String titre, String description, int duree, Date dateDebut, Date dateFin, float prix, String niveau, String type, Date dateAjout) {
        super(idFormation, nomCategorie, titre, description, duree, dateDebut, dateFin, prix, niveau);
        this.idFavoris = idFavoris;
        this.id_user = id_user;
        this.type = "Favoris";
        this.dateAjout = dateAjout;
    }

    public Favoris(int id_user,int idFormation,  Date  dateAjout) {
        super(idFormation);
        this.id_user = id_user;
        this.type = "Favoris";
        this.dateAjout = dateAjout;
    }
    public Favoris(int idFavoris) {
        super();
        this.idFavoris = idFavoris;
    }
}
