//package services;
//
//import entities.Panier;
//import utils.MyDB;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ServicePanier implements IService<Panier> {
//
//    private final Connection connection;
//
//    public ServicePanier() {
//        connection = MyDB.getInstance().getConnection(); // MyDB représente votre gestionnaire de connexion à la base de données
//    }
//
//    @Override
//    public void ajouter(Panier panier) throws SQLException {
//        String req = "INSERT INTO panier (idFormation, nomCategorie, titre, description, duree, dateDebut, dateFin, prix, niveau, dateAjout, typeAjout) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        PreparedStatement pstmt = connection.prepareStatement(req);
//
//        pstmt.setInt(1, panier.getIdFormation());
//        pstmt.setString(2, panier.getNomCategorie());
//        pstmt.setString(3, panier.getTitre());
//        pstmt.setString(4, panier.getDescription());
//        pstmt.setInt(5, panier.getDuree());
//        pstmt.setDate(6, new java.sql.Date(panier.getDateDebut().getTime()));
//        pstmt.setDate(7, new java.sql.Date(panier.getDateFin().getTime()));
//        pstmt.setFloat(8, panier.getPrix());
//        pstmt.setString(9, panier.getNiveau());
//        pstmt.setDate(10, new java.sql.Date(panier.getDateAjout().getTime()));
//        pstmt.setString(11, panier.getTypeAjout());
//
//        pstmt.executeUpdate();
//    }
//
//    @Override
//    public void modifier(Panier panier) throws SQLException {
//        String req = "UPDATE panier SET nomCategorie=?, titre=?, description=?, duree=?, dateDebut=?, dateFin=?, prix=?, niveau=?, dateAjout=?, typeAjout=? WHERE idFormation=?";
//        PreparedStatement pstmt = connection.prepareStatement(req);
//
//        pstmt.setString(1, panier.getNomCategorie());
//        pstmt.setString(2, panier.getTitre());
//        pstmt.setString(3, panier.getDescription());
//        pstmt.setInt(4, panier.getDuree());
//        pstmt.setDate(5, new java.sql.Date(panier.getDateDebut().getTime()));
//        pstmt.setDate(6, new java.sql.Date(panier.getDateFin().getTime()));
//        pstmt.setFloat(7, panier.getPrix());
//        pstmt.setString(8, panier.getNiveau());
//        pstmt.setDate(9, new java.sql.Date(panier.getDateAjout().getTime()));
//        pstmt.setString(10, panier.getTypeAjout());
//        pstmt.setInt(11, panier.getIdFormation());
//
//        pstmt.executeUpdate();
//    }
//
//    @Override
//    public void supprimer(Panier panier) throws SQLException {
//        String req = "DELETE FROM panier WHERE idFormation=?";
//        PreparedStatement pstmt = connection.prepareStatement(req);
//        pstmt.setInt(1, panier.getIdFormation());
//        pstmt.executeUpdate();
//    }
//
//    @Override
//    public List<Panier> afficher() throws SQLException {
//        List<Panier> paniers = new ArrayList<>();
//        String req = "SELECT * FROM panier";
//
//        try (Statement stmt = connection.createStatement();
//             ResultSet resultSet = stmt.executeQuery(req)) {
//            while (resultSet.next()) {
//                Panier panier = new Panier();
//                panier.setIdFormation(resultSet.getInt("idFormation"));
//                panier.setNomCategorie(resultSet.getString("nomCategorie"));
//                panier.setTitre(resultSet.getString("titre"));
//                panier.setDescription(resultSet.getString("description"));
//                panier.setDuree(resultSet.getInt("duree"));
//                panier.setDateDebut(resultSet.getDate("dateDebut"));
//                panier.setDateFin(resultSet.getDate("dateFin"));
//                panier.setPrix(resultSet.getFloat("prix"));
//                panier.setNiveau(resultSet.getString("niveau"));
//                panier.setDateAjout(resultSet.getDate("dateAjout"));
//                panier.setTypeAjout(resultSet.getString("typeAjout"));
//
//                paniers.add(panier);
//            }
//        }
//        return paniers;
//    }
//}
