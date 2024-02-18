package services;

import entities.Message;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceMessage implements IService<Message>{

    private Connection connection;

    public ServiceMessage(){
        connection= MyDB.getInstance().getConnection();

    }
    @Override
    public void ajouter(Message message) throws SQLException{
       /* String req ="insert into message (message,date,id_sender,id_diss)" +
                "values('"+message.getMessage()+"','"+message.getDate()+"','"+message.getId_sender()+"','"+message.getId_diss()+"')";*/

              String req ="insert into message (message,date,id_sender,id_diss)" +
                "values('"+message.getMessage()+"','"+message.getDate()+"','"+message.getId_sender()+"','"+message.getId_diss()+"')";

        Statement ste= connection.createStatement();
        ste.executeUpdate(req);


    }

    @Override
    public void modifier(Message message) throws SQLException{
        String req= "update message set message=? , date=? , id_sender=? , id_diss=? where id_message=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1,message.getMessage());
        pre.setDate(2,message.getDate());
        pre.setInt(3,message.getId_sender());
        pre.setInt(4,message.getId_diss());
        pre.setInt(5,message.getId_message());

        pre.executeUpdate();

    }

    @Override
    public void supprimer(Message message) throws SQLException{

        String req = " delete from message where id_message=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1,message.getId_message());
        pre.executeUpdate();


    }

    @Override
    public List<Message> afficher() throws SQLException{
        String req ="select * from message";
        Statement ste = connection.createStatement();
        ResultSet res = ste.executeQuery(req);
        List<Message> list = new ArrayList<>();
        while (res.next()) {
            Message m = new Message();
            m.setId_message(res.getInt(1));
            m.setMessage(res.getString("message"));
            m.setDate(res.getDate(3));
            m.setId_sender(res.getInt(4));
            m.setId_diss(res.getInt(5));

            list.add(m);
        }

        return list;
    }

}
