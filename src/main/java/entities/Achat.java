package entities;

import java.util.Date;

public class Achat extends Formation {
    int idAchat;
    int id_user;
    private Date dateAjout;
    private String cardNumber;
    private String exprMonth;
    private String exprYear;
    private String cvv;




    // Getter et setter pour l'attribut type

    public int getIdAchat() {
        return idAchat;
    }

    public void setIdAchat(int idAchat) {
        this.idAchat = idAchat;
    }



    // Getter et setter pour l'attribut dateAjout
    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }


    // Getter et setter pour cardNumber
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    // Getter et setter pour exprMonth
    public String getExprMonth() {
        return exprMonth;
    }

    public void setExprMonth(String exprMonth) {
        this.exprMonth = exprMonth;
    }

    // Getter et setter pour exprYear
    public String getExprYear() {
        return exprYear;
    }

    public void setExprYear(String exprYear) {
        this.exprYear = exprYear;
    }

    // Getter et setter pour cvv
    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "Achat{" +
                "idAchat=" + getIdAchat() +
                "id_user" + getId_user() +
                "idFormation=" + getIdFormation() +
                ", nomCategorie='" + getNomCategorie() + '\'' +
                ", titre='" + getTitre() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", duree=" + getDuree() +
                ", dateDebut=" + getDateDebut() +
                ", dateFin=" + getDateFin() +
                ", prix=" + getPrix() +
                ", niveau='" + getNiveau() + '\'' +
                ", dateAjout=" + dateAjout +
                '}';
    }
    // Appel du constructeur

    //constructeur par défaut
    public Achat() {
        super(); // Appel du constructeur de la classe mère Formation
    }
    //constructeur sans ID
    public Achat(int id_user,int idFormation, String nomCategorie, String titre, String description, int duree, Date dateDebut, Date dateFin, float prix, String niveau, Date dateAjout) {
        super(idFormation, nomCategorie, titre, description, duree, dateDebut, dateFin, prix, niveau);
        this.id_user = id_user;
        this.dateAjout = dateAjout;
    }
    //constructeur avec ID

    public Achat(int idAchat,int id_user,int idFormation, String nomCategorie, String titre, String description, int duree, Date dateDebut, Date dateFin, float prix, String niveau,  Date dateAjout) {
        super(idFormation, nomCategorie, titre, description, duree, dateDebut, dateFin, prix, niveau);
        this.idAchat = idAchat;
        this.id_user = id_user;
        this.dateAjout = dateAjout;
    }
    public Achat(int id_user,int idFormation,  Date  dateAjout) {
        super(idFormation);
        this.id_user = id_user;
        this.dateAjout = dateAjout;
    }
    public Achat(int idAchat) {
        super();
        this.idAchat = idAchat;
    }
    public Achat(int id_user,int idFormation, String nomCategorie, String titre, String description, int duree, float prix, String niveau) {
        super(idFormation, description, titre,  duree, prix, niveau,nomCategorie);
        this.idAchat = idAchat;
        this.id_user = id_user;
        this.dateAjout = dateAjout;
    }
    // deux id
    public Achat(int id_user,int idFormation) {
        super(idFormation);
        this.id_user = id_user;
    }
    public Achat (int id_user,int idFormation, String nomCategorie, String titre, String description, int duree, float prix, String niveau,Date  dateAjout, String cardNumber,String exprMonth, String exprYear, String cvv){
        super(idFormation, description, titre,  duree, prix, niveau,nomCategorie);
        this.id_user = id_user;
        this.dateAjout = dateAjout;
        this.cardNumber=cardNumber;
        this.exprMonth=exprMonth;
        this.exprYear=exprYear;
        this.cvv=cvv;
    }
    public Achat(int id_user,int idFormation,  Date  dateAjout, String cardNumber,String exprMonth, String exprYear, String cvv) {
        super(idFormation);
        this.id_user = id_user;
        this.dateAjout = dateAjout;
        this.cardNumber=cardNumber;
        this.exprMonth=exprMonth;
        this.exprYear=exprYear;
        this.cvv=cvv;
    }

}
