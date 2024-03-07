package entities;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

public class Payment {
//    public static void main (String[] args){
//        Stripe.apiKey = "sk_test_51OrKaRDOYVI5uEqTE8p6z8idDW3U03DPu84cAFKM3xJ7TadBHNE55nyAe1xrJBORV8saCYHBO1g57iPtJyk3f83c00t4i6bYvJ";
//        try {
//                Account account = Account.retrieve();
//            System.out.println("Account ID:"+account.getId());
//        } catch (StripeException e) {
//            throw new RuntimeException(e);
//        }


    public void processPayment(long price) {
        try {
            // Set your secret key here
            Stripe.apiKey = "sk_test_51OrKaRDOYVI5uEqTE8p6z8idDW3U03DPu84cAFKM3xJ7TadBHNE55nyAe1xrJBORV8saCYHBO1g57iPtJyk3f83c00t4i6bYvJ";

            // Create a PaymentIntent with other payment details
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(price) // Amount in cents (e.g., $10.00)
                    .setCurrency("usd")
                    .build();

            PaymentIntent intent = PaymentIntent.create(params);

            // If the payment was successful, display a success message
            System.out.println("Payment successful. PaymentIntent ID: " + intent.getId());
        } catch (StripeException e) {
            // If there was an error processing the payment, display the error message
            System.out.println("Payment failed. Error: " + e.getMessage());
        }
    }
}
