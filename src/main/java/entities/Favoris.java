package entities;

import java.util.Date;

public class Favoris {

   public int id_panier,id_favoris;

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getId_favoris() {
        return id_favoris;
    }

    public void setId_favoris(int id_favoris) {
        this.id_favoris = id_favoris;
    }

    @Override
    public String toString() {
        return "Favoris{" +
                "id_panier=" + id_panier +
                ", id_favoris=" + id_favoris +
                '}';
    }

    public Favoris() {
    }

    public Favoris(int id_panier) {
        this.id_panier = id_panier;
    }

    public Favoris( int id_favoris,int id_panier) {
        this.id_panier = id_panier;
        this.id_favoris = id_favoris;
    }
}
