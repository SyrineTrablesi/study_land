package entities;

import java.time.LocalDate;
import java.util.Date;

public class Formation {
    private int idFormation;
    private String titre;
    private String description;
    private int duree;
    private Date dateDebut;
    private Date dateFin;
    private float prix;
    private String niveau;
    private String nomCategorie;

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public int getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "Formation{" +
                "idFormation=" + idFormation +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", duree=" + duree +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", prix=" + prix +
                ", niveau='" + niveau  +
                ", nomCategorie='" + nomCategorie + '\'' +
                '}';
    }

    public Formation() {
    }

    public Formation(int idFormation,String nomCategorie, String titre, String description, int duree, Date dateDebut, Date dateFin, float prix, String niveau) {
        this.idFormation = idFormation;
        this.titre = titre;
        this.description = description;
        this.duree = duree;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.niveau = niveau;
        this.nomCategorie = nomCategorie;

    }

    public Formation(String titre, String description, int duree, Date dateDebut, Date dateFin, float prix, String niveau, String nomCategorie) {
        this.titre = titre;
        this.description = description;
        this.duree = duree;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.niveau = niveau;
        this.nomCategorie = nomCategorie;
    }
}
