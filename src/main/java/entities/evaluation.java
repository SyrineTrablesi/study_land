package entities;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class evaluation {
    public int id_evaluation;
    public String titre_evaluation;
    public String description;

    public String Difficulte;
    public int  nb_questions;
    public Time Duree;
    public float Resultat;
    public Date testDate;
    public String createur;
    public float prix;


    public evaluation(int id_evaluation, String titre_evaluation, String description, String difficulte, int nb_questions, Time duree, float resultat, Date testDate, String createur, float prix) {
        this.id_evaluation = id_evaluation;
        this.titre_evaluation = titre_evaluation;
        this.description = description;
        Difficulte = difficulte;
        this.nb_questions = nb_questions;
        Duree = duree;
        Resultat = resultat;
        this.testDate = testDate;
        this.createur = createur;
        this.prix = prix;
    }

    public evaluation(String titre_evaluation, String description, String difficulte, int nb_questions, Time duree, float resultat, Date testDate, String createur , float prix) {
        this.titre_evaluation = titre_evaluation;
        this.description = description;
        Difficulte = difficulte;
        this.nb_questions = nb_questions;
        Duree = duree;
        Resultat = resultat;
        this.testDate = testDate;
       this.createur=createur;
       this.prix=prix;
    }

    public evaluation( ) {
    }

    public int getId_evaluation() {
        return id_evaluation;
    }

    public String getTitre_evaluation() {
        return titre_evaluation;
    }

    public String getDescription() {
        return description;
    }

    public String getDifficulte(String difficulte) {
        return Difficulte;
    }

    public int getNb_questions() {
        return nb_questions;
    }

    public Time getDuree() {
        return Duree;
    }

    public float getResultat() {
        return Resultat;
    }

    public Date getTestDate() {
        return testDate;
    }

    public String getCreateur() {
        return this.createur;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setId_evaluation(int id_evaluation) {
        this.id_evaluation = id_evaluation;
    }

    public void setTitre_evaluation(String titre_evaluation) {
        this.titre_evaluation = titre_evaluation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDifficulte(String difficulte) {
        Difficulte = difficulte;
    }

    public void setNb_questions(int nb_questions) {
        this.nb_questions = nb_questions;
    }

    public void setDuree(Time duree) {
        this.Duree = duree;
    }

    public void setResultat(float resultat) {
        Resultat = resultat;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
    }

    @Override
    public String toString() {
        return "evaluation{" +
                "id_evaluation=" + id_evaluation +
                ", titre_evaluation='" + titre_evaluation + '\'' +
                ", description='" + description + '\'' +
                ", Difficulte='" + Difficulte + '\'' +
                ", nb_questions=" + nb_questions +
                ", Duree=" + Duree +
                ", Resultat=" + Resultat +
                ", testDate=" + testDate +
                ", createur='" + createur + '\'' +
                ", prix=" + prix +
                '}';
    }
}



