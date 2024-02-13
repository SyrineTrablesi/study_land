package entities;
import java.sql.Date;
public class Message {
    int id_message;
    int id_sender;
    int id_diss;
    String message;
    Date date;



    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        this.id_message = id_message;
    }

    public int getId_sender() {
        return id_sender;
    }

    public void setId_sender(int id_sender) {
        this.id_sender = id_sender;
    }

    public int getId_diss() {
        return id_diss;
    }

    public void setId_diss(int id_diss) {
        this.id_diss = id_diss;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public java.sql.Date getDate() {
        return (java.sql.Date) date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id_message=" + id_message +
                ", id_sender=" + id_sender +
                ", id_diss=" + id_diss +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }


    public Message() {
    }

    public Message(int id_message, int id_sender, int id_diss, String message, Date date) {
        this.id_message = id_message;
        this.id_sender = id_sender;
        this.id_diss = id_diss;
        this.message = message;
        this.date = date;
    }

    public Message(int id_sender, int id_diss, String message, Date date) {
        this.id_sender = id_sender;
        this.id_diss = id_diss;
        this.message = message;
        this.date = date;
    }
}
