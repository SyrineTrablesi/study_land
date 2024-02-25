package entities;

import java.time.LocalDate;

public class Panier {
    private int id_panier;
    private int id_user;
    private int id_formation;
    private LocalDate date_ajout;
    private String typeFormation;

    // Attributs hérités de la table Formation
    private int idFormation;
    private String titre;
    private String description;
    private int duree;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double prix;
    private String niveau;
    private String nomCategorie;


    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_formation() {
        return id_formation;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

    public LocalDate getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(LocalDate date_ajout) {
        this.date_ajout = date_ajout;
    }

    public String getTypeFormation() {
        return typeFormation;
    }

    public void setTypeFormation(String typeFormation) {
        this.typeFormation = typeFormation;
    }



    // Getters and setters des attributs hérités

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

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public Panier() {

    }

    public Panier(int id_user, int id_formation, LocalDate date_ajout, String typeFormation) {
        this.id_user = id_user;
        this.id_formation = id_formation;
        this.date_ajout = date_ajout;
        this.typeFormation = typeFormation;
    }

    public Panier(int id_panier, int id_user, int id_formation, LocalDate date_ajout, String typeFormation) {
        this.id_panier = id_panier;
        this.id_user = id_user;
        this.id_formation = id_formation;
        this.date_ajout = date_ajout;
        this.typeFormation = typeFormation;
    }

    public Panier(int id_panier) {
        this.id_panier = id_panier;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id_panier=" + id_panier +
                ", id_user=" + id_user +
                ", id_formation=" + id_formation +
                ", date_ajout=" + date_ajout +
                ", typeFormation='" + typeFormation + '\'' +
                ", idFormation=" + idFormation +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", duree=" + duree +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", prix=" + prix +
                ", niveau='" + niveau + '\'' +
                ", nomCategorie='" + nomCategorie + '\'' +
                '}';
    }

}
