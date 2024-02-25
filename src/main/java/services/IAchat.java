package services;

import entities.Achat;

import java.sql.SQLException;
import java.util.List;

public interface IAchat {
    void ajouterAchat(Achat achat) throws SQLException;

    void modifierAchat(Achat achat) throws SQLException;

    void supprimerAchat(Achat achat) throws SQLException;
    List<Achat> afficherAchatAdmin() throws SQLException;
    List<Achat> afficherAchatUser(int idUser) throws SQLException;
}