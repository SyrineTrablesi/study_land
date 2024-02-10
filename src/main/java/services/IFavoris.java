package services;

import java.sql.SQLException;
import java.util.List;

public interface IFavoris<F>  {
    public void ajouterFavoris(F f) throws SQLException;
    public void modifieFavoris(F f) throws SQLException;
    public void supprimerFavoris(F f) throws SQLException;
    public List<F> afficherFavoris() throws SQLException;
}
