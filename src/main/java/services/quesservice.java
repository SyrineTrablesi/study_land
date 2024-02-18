package services;
import entities.question;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class quesservice implements EvaluationService<question> {

    public Connection connection;

    public quesservice() {
        connection = MyDB.getInstance().getConnection();    }



    @Override
    public int ajouter(question question) throws SQLException {
            String req = "insert into question (enonce,domaine) values(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setString(1, question.getEnonce());
        preparedStatement.setString(2, question.getDomaine());

        preparedStatement.executeUpdate();

        return 0;
    }



    @Override
    public void modifier(question question) throws SQLException {
        String req="UPDATE  question SET  enonce=? , domaine=? WHERE idQuestion=?";
        PreparedStatement pre=connection.prepareStatement(req);
        pre.setString(1,question.getEnonce());
        pre.setString(2, question.getDomaine());
        pre.setInt(3 ,question.getIdQuestion());

        pre.executeUpdate(  );
        System.out.println("modification avec succée");
    }

    @Override
    public void supprimer(question question) throws SQLException {

        String req ="delete from question where idQuestion=?";
        PreparedStatement pre= connection.prepareStatement(req);
        pre.setInt(1,question.getIdQuestion());
        pre.executeUpdate();
        System.out.println("delete avec succée");

    }

        @Override
        public List<question> afficher() throws SQLException {

            List<question> questionList = new ArrayList<>();

            String req = "SELECT * FROM question";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                question q =new question();
                q.setIdQuestion(resultSet.getInt("idQuestion"));
                q.setEnonce(resultSet.getString("enonce"));
                q.setDomaine(resultSet.getString("domaine"));
                questionList.add(q);

            }


            return questionList;
        }
    public question getQuestionById(int idQuestion) throws SQLException {
        String query = "SELECT * FROM question WHERE idQuestion = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idQuestion);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    question q = new question();
                    q.setIdQuestion(resultSet.getInt("idQuestion"));
                    q.setEnonce(resultSet.getString("enonce"));
                    q.setDomaine(resultSet.getString("domaine"));
                    return q;
                }
            }
        }

        // Return null if no question is found with the specified ID
        return null;
    }
}

