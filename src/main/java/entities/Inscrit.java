package entities;

import java.time.LocalDate;
import java.util.Date;

public class Inscrit {

    private int id_inscrit;
    private int id_user;
    private int idFormation;
    private LocalDate inscription_date;

    // Attributs hérités de la table Formation
    private String titre;
    private String description;
    private int duree;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double prix;
    private String niveau;
    private String nomCategorie;

    // Constructeurs, getters et setters


    public int getId_inscrit() {
        return id_inscrit;
    }

    public void setId_inscrit(int id_inscrit) {
        this.id_inscrit = id_inscrit;
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

    public void setId_formation(int id_formation) {
        this.idFormation = idFormation;
    }

    public LocalDate getInscription_date() {
        return inscription_date;
    }

    public void setInscription_date(LocalDate inscription_date) {
        this.inscription_date = inscription_date;
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
        return "Inscrit{" +
                "id_inscrit=" + id_inscrit +
                ", id_user=" + id_user +
                ", idFormation=" + idFormation +
                ", inscription_date=" + inscription_date +
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

    public Inscrit() {
    }

    public Inscrit(int id_inscrit, int id_user, int idFormation, LocalDate inscription_date) {
        this.id_inscrit = id_inscrit;
        this.id_user = id_user;
        this.idFormation = idFormation;
        this.inscription_date = inscription_date;
    }

    public Inscrit(int id_user, int idFormation, LocalDate inscription_date) {
        this.id_user = id_user;
        this.idFormation = idFormation;
        this.inscription_date = inscription_date;
    }

    public Inscrit(int id_inscrit) {
        this.id_inscrit = id_inscrit;
    }
}
