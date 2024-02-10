package entities;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Panier {

    int id_panier , id_user , id_formation;
    Date date_ajout;
    List<Favoris> favorisList;

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
    public Date getDate_ajout() {
        return  date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id_panier=" + id_panier +
                ", id_user=" + id_user +
                ", id_formation=" + id_formation +
                ", date_ajout=" + date_ajout +
                ", favorisList=" + favorisList +
                '}';
    }

    public Panier() {

    }

    public Panier(int id_panier, int id_user, int id_formation, Date date_ajout) {
        this.id_panier = id_panier;
        this.id_user = id_user;
        this.id_formation = id_formation;
        this.date_ajout = date_ajout;
    }

    public Panier(int id_user, int id_formation, Date date_ajout) {
        this.id_user = id_user;
        this.id_formation = id_formation;
        this.date_ajout = date_ajout;
    }
}
