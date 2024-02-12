package entities;

import java.util.Date;

public class Achat {
    int id_achat, id_panier , id_user , facture;
    Date date_achat;

    public int getId_achat() {
        return id_achat;
    }

    public void setId_achat(int id_achat) {
        this.id_achat = id_achat;
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

    public int getFacture() {
        return facture;
    }

    public void setFacture(int facture) {
        this.facture = facture;
    }

    public Date getDate_achat() {
        return date_achat;
    }

    public void setDate_achat(Date date_achat) {
        this.date_achat = date_achat;
    }

    @Override
    public String toString() {
        return "Achat{" +
                "id_achat=" + id_achat +
                ", id_panier=" + id_panier +
                ", id_user=" + id_user +
                ", facture=" + facture +
                ", date_achat=" + date_achat +
                '}';
    }

    public Achat() {
    }

    public Achat(int id_achat, int id_panier, int id_user, int facture, Date date_achat) {
        this.id_achat = id_achat;
        this.id_panier = id_panier;
        this.id_user = id_user;
        this.facture = facture;
        this.date_achat = date_achat;
    }

    public Achat(int id_panier, int id_user, int facture, Date date_achat) {
        this.id_panier = id_panier;
        this.id_user = id_user;
        this.facture = facture;
        this.date_achat = date_achat;
    }
}
