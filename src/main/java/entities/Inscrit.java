package entities;

import java.util.Date;

public class Inscrit {

    int id_inscrit,id_panier, id_user, id_formation, id_achat;
    public Date inscription_date;

    public int getId_inscrit() {
        return id_inscrit;
    }

    public void setId_inscrit(int id_inscrit) {
        this.id_inscrit = id_inscrit;
    }

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

    public int getId_achat() {
        return id_achat;
    }

    public void setId_achat(int id_achat) {
        this.id_achat = id_achat;
    }

    public Date getInscription_date() {
        return inscription_date;
    }

    public void setInscription_date(Date inscription_Date) {
        this.inscription_date = inscription_Date;
    }

    @Override
    public String toString() {
        return "Inscrit{" +
                "id_inscrit=" + id_inscrit +
                ", id_panier=" + id_panier +
                ", id_user=" + id_user +
                ", id_formation=" + id_formation +
                ", id_achat=" + id_achat +
                ", inscription_date=" + inscription_date +
                '}';
    }

    public Inscrit() {
    }

    public Inscrit(int id_inscrit,int id_panier, int id_user, int id_formation, int id_achat, Date inscription_date) {
        this.id_inscrit = id_inscrit;
        this.id_panier = id_panier;
        this.id_user = id_user;
        this.id_formation = id_formation;
        this.id_achat = id_achat;
        this.inscription_date = inscription_date;
    }


    public Inscrit(int id_user,int id_panier, int id_formation, int id_achat, Date inscription_date) {
        this.id_panier = id_panier;
        this.id_user = id_user;
        this.id_formation = id_formation;
        this.id_achat = id_achat;
        this.inscription_date = inscription_date;
    }
}
