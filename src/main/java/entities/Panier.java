package entities;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    private List<Inscrit> formationsInscrits;
    private List<Favoris> formationsFavoris;

    public Panier() {
        this.formationsInscrits = new ArrayList<>();
        this.formationsFavoris = new ArrayList<>();
    }

    public List<Inscrit> getFormationsInscrits() {
        return formationsInscrits;
    }

    public void setFormationsInscrits(List<Inscrit> formationsInscrits) {
        this.formationsInscrits = formationsInscrits;
    }

    public List<Favoris> getFormationsFavoris() {
        return formationsFavoris;
    }

    public void setFormationsFavoris(List<Favoris> formationsFavoris) {
        this.formationsFavoris = formationsFavoris;
    }

    // Méthode pour ajouter une formation à la liste des inscrits
    public void ajouterInscris (Inscrit inscrit) {
        this.formationsInscrits.add(inscrit);
    }

    // Méthode pour ajouter une formation à la liste des favoris
    public void ajouterFavoris(Favoris favoris) {
        this.formationsFavoris.add(favoris);
    }

    // Autres méthodes peuvent être ajoutées pour manipuler les formations dans le panier
}
