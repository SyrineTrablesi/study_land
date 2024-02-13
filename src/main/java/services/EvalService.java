package services;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import entities.evaluation;
import utils.MyDB;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class EvalService implements EvaluationService<evaluation> {
    public Connection connection;


    public EvalService() {
        connection = MyDB.getInstance().getConnection();

    }

    @Override
    public void ajouter(evaluation evaluation) throws SQLException {
        String req = "insert into evaluation (titre_evaluation,description,difficulte,nb_questions,duree,resultat,testDate,createur,prix,domaine) values('" +
                evaluation.getTitre_evaluation() + "','" + evaluation.getDescription() + "','" + evaluation.getDescription()+ "','" +
                evaluation.getNb_questions() + "','" + evaluation.getDuree() + "','" + evaluation.getResultat() + "','" + evaluation.getTestDate() +"','"+evaluation.getCreateur()+ "','"  +evaluation.getPrix()+"','"  +evaluation.getDomaine()+"')";


        Statement st = connection.createStatement();

        st.executeUpdate(req);

    }

    @Override
    public void modifier(evaluation evaluation) throws SQLException {
        String req="UPDATE  evaluation SET  titre_evaluation=? ,description=?,difficulte=? ,nb_questions=?,duree=?,resultat=?,testDate=?,createur=?,prix=?,domaine=? WHERE id_evaluation=?";
PreparedStatement pre=connection.prepareStatement(req);
pre.setString(1,evaluation.getTitre_evaluation());
pre.setString(2, evaluation.getDescription());
pre.setString(3 ,evaluation.getDescription());
pre.setInt(4,evaluation.getNb_questions());
pre.setTimestamp(5,new Timestamp(evaluation.getDuree().getTime()));
pre.setFloat(6,evaluation.getResultat());
pre.setDate(7,new java.sql.Date(evaluation.getTestDate().getTime()));
pre.setString(8,evaluation.getCreateur());
pre.setFloat(9,evaluation.getPrix());
        pre.setInt(11,evaluation.getId_evaluation());
        pre.setString(10,evaluation.getDomaine());



pre.executeUpdate(  );
        System.out.println("modification avec succée");
    }

    @Override
    public void supprimer(evaluation evaluation) throws SQLException {
        String req = "delete from  evaluation where id_evaluation=?";
        PreparedStatement pre =connection.prepareStatement(req);
        pre.setInt(1,evaluation.getId_evaluation());
        pre.executeUpdate();

    }

    @Override
    public List<evaluation> afficher()throws SQLException {

        String req = "SELECT * FROM evaluation";
        Statement st = connection.createStatement();
        ResultSet res = st.executeQuery(req);
        List<evaluation> list = new ArrayList<>();

        while (res.next()) {
            evaluation f = new evaluation();
            f.setId_evaluation(res.getInt("id_evaluation"));
            f.setTitre_evaluation(res.getString("titre_evaluation"));
            f.setDescription(res.getString("description"));
            f.setDuree(res.getTime("Duree"));
            f.setTestDate(res.getDate("testDate"));
            f.setPrix(res.getFloat("prix"));
            f.setResultat(res.getFloat("Resultat"));
            f.setCreateur(res.getString("Createur"));
            f.setNb_questions(res.getInt("nb_questions"));
            f.setDifficulte(res.getString("Difficulte"));
            f.setDomaine(res.getString("domaine"));

            list.add(f);
        }
        return list;
    }}