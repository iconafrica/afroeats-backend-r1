package com.iconsoft.afroeats.GestionPaiements.Stripe.ServiceStripe;

import com.iconsoft.afroeats.GestionPaiements.Stripe.Metier.MetierStripe;
import com.iconsoft.afroeats.GestionPaiements.Stripe.Model.Paymentstripe;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceStripe implements MetierStripe {
   // @Value("${Stripe.apiKey}")
    String apiKey="sk_test_51JkzwsKeetERrAwwaPtcTJEp4KlrKmwmoPOHO1UEGOGYlj1wKEVVvGjkvoIy7qm69XkH3nyLaiPaxfIOoCIogOzq006zJk8dHj";

    @Override
    public Paymentstripe do_payment(int montant) throws StripeException {
        Stripe.apiKey = apiKey;
        List<Object> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("bancontact");
        paymentMethodTypes.add("card");
        paymentMethodTypes.add("eps");
        paymentMethodTypes.add("giropay");
        paymentMethodTypes.add("ideal");
        paymentMethodTypes.add("p24");
        paymentMethodTypes.add("sepa_debit");
        paymentMethodTypes.add("sofort");
        Map<String, Object> params = new HashMap<>();
        params.put("amount", montant);
        params.put("currency", "eur");
        params.put("payment_method_types", paymentMethodTypes);
        Paymentstripe paymentstripe=new Paymentstripe();
          PaymentIntent paymentIntent  = PaymentIntent.create(params);
          paymentstripe.setClient_secret(paymentIntent.getClientSecret());
        paymentstripe.setId(paymentIntent.getId());
        return paymentstripe;
    }

    @Override
    public String paymentWithCheckoutPage(Paymentstripe payment) throws StripeException {
        Stripe.apiKey = apiKey;

        // We create a  stripe session parameters


        // We create a  stripe session parameters
        SessionCreateParams params = SessionCreateParams.builder()
                // We will use the credit card payment method
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(payment.getSuccessUrl())
                .setCancelUrl(payment.getCancelUrl())
                .addLineItem(SessionCreateParams
                        .LineItem.builder()
                        .setQuantity(payment.getQuantity())
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency(payment.getCurrency()).setUnitAmount((long) payment.getAmount ())
                                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                                        .builder().setName(payment.getCreated()).build())
                                                .build())
                                .build())
                .build();
        // create a stripe session
        Session session = Session.create(params);
        Map<String, String> responseData = new HashMap<>();
        // We get the sessionId and we putted inside the response data you can get more info from the session object
System.out.println(session.toJson());

        // We can return only the sessionId as a String
        return session.getUrl();
    }

}
