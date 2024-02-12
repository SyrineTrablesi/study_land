package services;

import java.sql.SQLException;
import java.util.List;

public interface IAchat <A> {
    public void ajouterAchat(A a) throws SQLException;
    public void modifierAchat(A a) throws SQLException;
    public void supprimerAchat(A a) throws SQLException;
    public List<A> afficherAchat() throws SQLException;

}
