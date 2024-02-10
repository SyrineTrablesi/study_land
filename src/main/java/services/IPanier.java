package services;

import java.sql.SQLException;
import java.util.List;

public interface IPanier<P> {
    public void ajouterPanier(P p) throws SQLException;
    public void modifierPanier(P p) throws SQLException;
    public void supprimerPanier(P p) throws SQLException;
    public List<P> afficherPanier() throws SQLException;

}
