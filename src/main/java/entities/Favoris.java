package entities;

import java.time.LocalDate;

public class Favoris {
    private int id_favoris;
    private int id_user;
    private int idFormation;
    private LocalDate date_ajout;


    // hérité
    private String titre;
    private String description;
    private int duree;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double prix;
    private String niveau;
    private String nomCategorie;





    public int getId_favoris() {
        return id_favoris;
    }

    public void setId_favoris(int id_favoris) {
        this.id_favoris = id_favoris;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_formation() {
        return idFormation;
    }

    public void setId_formation(int idFormation) {
        this.idFormation = idFormation;
    }

    public LocalDate getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(LocalDate date_ajout) {
        this.date_ajout = date_ajout;
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

    @Override
    public String toString() {
        return "Favoris{" +
                "id_favoris=" + id_favoris +
                ", id_user=" + id_user +
                ", id_formation=" + idFormation +
                ", date_ajout=" + date_ajout +
                '}';
    }


    public Favoris() {}

    public Favoris(int id_user, int idFormation, LocalDate date_ajout) {
        this.id_user = id_user;
        this.idFormation = idFormation;
        this.date_ajout = date_ajout;
    }
}