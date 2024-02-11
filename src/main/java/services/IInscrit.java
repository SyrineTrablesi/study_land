package services;

import java.sql.SQLException;
import java.util.List;

public interface IInscrit<I> {
    public void ajouterInscrit(I i) throws SQLException;
    public void modifierInscrit(I i) throws SQLException;
    public void supprimerInscrit(I i) throws SQLException;
    public List<I> afficherInscrit() throws SQLException;

}
