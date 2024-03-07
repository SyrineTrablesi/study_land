package entities;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Panier {
    private List<Favoris> favorisList;
    private List<Inscrit> inscritsList;
    private List<Achat> achatsList;

    public Panier() {
        favorisList = new ArrayList<>();
        inscritsList = new ArrayList<>();
        achatsList = new ArrayList<>();
    }

    public void addFavoris(Favoris favoris) {
        favorisList.add(favoris);
    }

    public void addInscrit(Inscrit inscrit) {
        inscritsList.add(inscrit);
    }

    public void addAchat(Achat achat) {
        achatsList.add(achat);
    }

    public List<Favoris> getFavorisList() {
        return favorisList;
    }

    public List<Inscrit> getInscritsList() {
        return inscritsList;
    }

    public List<Achat> getAchatsList() {
        return achatsList;
    }


}
