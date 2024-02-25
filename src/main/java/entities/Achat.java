package entities;

import java.time.LocalDate;

public class Achat {
    public int id_achat, idFormation , id_user ;
    public double facture;
    public LocalDate date_achat;


    // hérité
    private String titre;
    private String description;
    private int duree;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double prix;
    private String niveau;
    private String nomCategorie;


    public int getId_achat() {
        return id_achat;
    }

    public void setId_achat(int id_achat) {
        this.id_achat = id_achat;
    }

    public int getId_formation() {
        return idFormation;
    }

    public void setId_formation(int idFormation) {
        this.idFormation = idFormation;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public double getFacture() {
        return facture;
    }

    public void setFacture(double facture) {
        this.facture = facture;
    }

    public LocalDate getDate_achat() {
        return date_achat;
    }

    public void setDate_achat(LocalDate date_achat) {
        this.date_achat = date_achat;
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
        return "Achat{" +
                "id_achat=" + id_achat +
                ", id_formation=" + idFormation +
                ", id_user=" + id_user +
                ", facture=" + facture +
                ", date_achat=" + date_achat +
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

    public Achat() {
    }

    public Achat(int id_achat, int idFormation, int id_user, double facture, LocalDate date_achat) {
        this.id_achat = id_achat;
        this.idFormation = idFormation;
        this.id_user = id_user;
        this.facture = facture;
        this.date_achat = date_achat;
    }

    public Achat(int idFormation, int id_user, double facture, LocalDate date_achat) {
        this.idFormation = idFormation;
        this.id_user = id_user;
        this.facture = facture;
        this.date_achat = date_achat;
    }
}
