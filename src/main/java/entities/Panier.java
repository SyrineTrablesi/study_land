package entities;

import java.util.Date;
import java.util.List;





public class Panier {
    public enum typeFormation {
        favoris,
        inscrite;
    }
    public int id_panier , id_user , id_formation;
    public Date date_ajout;
    public static typeFormation typeFormation;
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

    public Date getDate_ajout() {
        return  date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }

    public typeFormation getTypeFormation() {
        return typeFormation;
    }

    public void setTypeFormation(typeFormation typeFormation) {
        this.typeFormation = typeFormation;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id_panier=" + id_panier +
                ", id_user=" + id_user +
                ", id_formation=" + id_formation +
                ", date_ajout=" + date_ajout +
                ", typeFormation=" + typeFormation +
                ", favorisList=" + favorisList +
                ", inscritList=" + inscritList +
                '}';
    }

    public Panier() {

    }

    public Panier(int id_user, int id_formation, Date date_ajout, String typeFormation) {

        this.id_user = id_user;
        this.id_formation = id_formation;
        this.date_ajout = date_ajout;
        this.typeFormation = Panier.typeFormation.valueOf(typeFormation);


    }

    public Panier(int id_panier, int id_user, int id_formation,Date date_ajout, String typeFormation) {

        this.id_panier = id_panier;
        this.id_user = id_user;
        this.id_formation = id_formation;
        this.date_ajout = date_ajout;
        this.typeFormation = Panier.typeFormation.valueOf(typeFormation);

    }
}
