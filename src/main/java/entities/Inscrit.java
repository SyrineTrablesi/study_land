package entities;

public class Inscrit {

    int id_inscrit, id_user, id_formation, id_achat;

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

    @Override
    public String toString() {
        return "Inscrit{" +
                "id_inscrit=" + id_inscrit +
                ", id_user=" + id_user +
                ", id_formation=" + id_formation +
                ", id_achat=" + id_achat +
                '}';
    }

    public Inscrit() {
    }

    public Inscrit(int id_inscrit, int id_user, int id_formation, int id_achat) {
        this.id_inscrit = id_inscrit;
        this.id_user = id_user;
        this.id_formation = id_formation;
        this.id_achat = id_achat;
    }


    public Inscrit(int id_user, int id_formation, int id_achat) {
        this.id_user = id_user;
        this.id_formation = id_formation;
        this.id_achat = id_achat;
    }
}
