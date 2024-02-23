package  entities;

import security.UserInfo;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Properties;
import java.util.Random;
import javax.mail.Session;
import javax.activation.DataHandler;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender {
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String EMAIL_USERNAME = "studyland002@gmail.com";
    private static final String EMAIL_PASSWORD = "pmgt trqw atrj xyai";
    public static String verificationCode;


    public static void sendInfoFormateur(String recipientEmail,Formateur formateur) {
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("StudyLand Resultat concours");
            message.setText("");
            Transport.send(message);
            Multipart multipart = new MimeMultipart();
            String emailContentWithSignature = "<html>" +
                    "<body>" +
                    "<p>Cher " + formateur.getNom() + ",</p>" +
                    "<p>Nous avons le plaisir de vous informer que vous avez été admis à StudyLand en tant que formateur.</p>" +
                    "<p><strong>Informations d'inscription à renseigner :</strong></p>" +
                    "<p>Votre email : " + formateur.getEmail() + "</p>" +
                    "<p>Votre mot de passe : " + formateur.getPassword() + "</p>" +
                    "<p>Cordialement,<br>StudyLand</p>" +
                    "<img src=\"cid:logo\" width=\"100px\">" +
                    "</body>" +
                    "</html>";
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(emailContentWithSignature, "text/html");
            multipart.addBodyPart(textPart);
            MimeBodyPart imagePart = new MimeBodyPart();
            byte[] imageBytes = new byte[0];
            try {
                imageBytes = Files.readAllBytes(Paths.get("D:\\syrine_3A26\\pidev\\StudyLand\\src\\main\\resources\\logo1.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            DataSource imageDataSource = new ByteArrayDataSource(imageBytes, "image/png");
            imagePart.setDataHandler(new DataHandler(imageDataSource));
            imagePart.setHeader("Content-ID", "<logo>");
            imagePart.setDisposition(MimeBodyPart.INLINE);
            multipart.addBodyPart(imagePart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // sendWelcomeEmailWithSignature:
    public static void sendWelcomeEmailWithSignature(String recipientEmail ,String nom) {
            try {

                User user= new User();
                MimeMessage message = new MimeMessage(getSession());
                message.setFrom(new InternetAddress(EMAIL_USERNAME));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
                message.setSubject("Bienvenu dans notre Platforme StudyLand");
                Multipart multipart = new MimeMultipart();
                String emailContentWithSignature = "<html>" +
                        "<body>" +
                        "<p>Cher</p>"+"<p>"+" " + nom+"</p>"+
                        "<p>Merci de vous être inscrit à notre plateforme. Veuillez répondre à cet e-mail en confirmant votre inscription en incluant le mot-clé 'CONFIRMATION' dans votre réponse.</p>" +
                        "<p>Cordialement,<br>StudyLand</p>" +
                        "<img src=\"cid:logo\" width=\"100px\">"+
                "</body>" +
                        "</html>";
                MimeBodyPart textPart = new MimeBodyPart();
                textPart.setContent(emailContentWithSignature, "text/html");
                multipart.addBodyPart(textPart);


                // Image
                MimeBodyPart imagePart = new MimeBodyPart();
                byte[] imageBytes = Files.readAllBytes(Paths.get("D:\\syrine_3A26\\pidev\\StudyLand\\src\\main\\resources\\logo1.png"));
                DataSource imageDataSource = new ByteArrayDataSource(imageBytes, "image/png");
                imagePart.setDataHandler(new DataHandler(imageDataSource));
                imagePart.setHeader("Content-ID", "<logo>");
                imagePart.setDisposition(MimeBodyPart.INLINE);
                multipart.addBodyPart(imagePart);
                message.setContent(multipart);
                Transport.send(message);
               // System.out.println("Email de slaut " + recipientEmail);
            } catch (MessagingException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    private static Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });
    }

    //Email de verification de modifier mdp
    public static void sendVerificationCode(String recipientEmail) {
        String code = generateVerificationCode();
        verificationCode=code;

        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Code de vérification");
            message.setText("Votre code de vérification est : " + code);
            Transport.send(message);
            System.out.println("Code de vérification envoyé avec succès à : " + recipientEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static String generateVerificationCode() {
        Random random = new Random();
        int code = 1000 + random.nextInt(9000);
        return String.valueOf(code);
    }


}
