package entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;





public class Panier {

    public enum type_formation {
        favoris,
        inscrite;
    }
    public int id_panier , id_user , id_formation;
    public Date date_ajout;
    public static String type_formation;
    public List<Favoris> favorisList;
    public List<Inscrit> inscritList;

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

    public List<Favoris> getFavorisList() {
        return favorisList;
    }

    public void setFavorisList(List<Favoris> favorisList) {
        this.favorisList = favorisList;
    }

    public List<Inscrit> getInscritList() {
        return inscritList;
    }

    public void setInscritList(List<Inscrit> inscritList) {
        this.inscritList = inscritList;
    }

    public java.sql.Date getDate_ajout() {
        return (java.sql.Date) date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }

    public String getTypeFormation() {
        return type_formation;
    }

    public void setTypeFormation(String typeFormation) {
        this.type_formation = typeFormation;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id_panier=" + id_panier +
                ", id_user=" + id_user +
                ", id_formation=" + id_formation +
                ", date_ajout=" + date_ajout +
                ", type_formation=" + type_formation +
                ", favorisList=" + favorisList +
                ", inscritList=" + inscritList +
                '}';
    }

    public Panier() {

    }

    public Panier(int id_user, int id_formation, LocalDate date_ajout, String type_formation) {

        this.id_user = id_user;
        this.id_formation = id_formation;
        this.date_ajout = java.sql.Date.valueOf(date_ajout);
        this.type_formation = Panier.type_formation.valueOf(type_formation);


    }

    public Panier(int id_panier, int id_user, int id_formation, LocalDate date_ajout, String type_formation) {

        this.id_panier = id_panier;
        this.id_user = id_user;
        this.id_formation = id_formation;
        this.date_ajout = java.sql.Date.valueOf(date_ajout);
        this.type_formation = Panier.type_formation.valueOf(type_formation);

    }
}
