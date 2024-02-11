package entities;

import java.util.Arrays;

public class Cours {
    private int IdCour;
    private String NomCours;
    private  byte[] Description_Cours;

    public int getIdCour() {
        return IdCour;
    }

    public void setIdCour(int idCour) {
        IdCour = idCour;
    }

    public String getNomCours() {
        return NomCours;
    }

    public void setNomCours(String nomCours) {
        NomCours = nomCours;
    }

    public byte[] getDescription_Cours() {
        return Description_Cours;
    }

    public void setDescription_Cours(byte[] description_Cours) {
        Description_Cours = description_Cours;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "IdCour=" + IdCour +
                ", NomCours='" + NomCours + '\'' +
                ", Description_Cours=" + Arrays.toString(Description_Cours) +
                '}';
    }

    public Cours() {
    }

    public Cours(int idCour, String nomCours, byte[] description_Cours) {
        IdCour = idCour;
        NomCours = nomCours;
        Description_Cours = description_Cours;
    }

    public Cours(String nomCours, byte[] description_Cours) {
        NomCours = nomCours;
        Description_Cours = description_Cours;
    }
}
