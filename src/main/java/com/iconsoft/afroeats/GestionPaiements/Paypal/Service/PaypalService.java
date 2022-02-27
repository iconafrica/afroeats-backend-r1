package com.iconsoft.afroeats.GestionPaiements.Paypal.Service;

import com.iconsoft.afroeats.GestionCommande.Models.Commande;
import com.iconsoft.afroeats.GestionPaiements.Payment.Model.PaymentAfroeats;
import com.iconsoft.afroeats.GestionPaiements.Paypal.Metier.MetierPaypal;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaypalService implements MetierPaypal {

    @Autowired
    private APIContext apiContext;

    @Override
    public PaymentAfroeats createPayment(Commande commande) {
        PaymentAfroeats paymentAfroeats = new PaymentAfroeats();
        Amount amount = new Amount();
        amount.setCurrency("USD");
        commande.setNetapayer(new BigDecimal(commande.getNetapayer()).setScale(2, RoundingMode.HALF_UP).doubleValue());
        amount.setTotal(String.format("%.3f", commande.getNetapayer()));

        Transaction transaction = new Transaction();
        transaction.setDescription(commande.getReferencecommande());
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("https://afroeatsbackend.herokuapp.com/payment/cancel/"+commande.getReferencecommande());
        redirectUrls.setReturnUrl("https://afroeatsbackend.herokuapp.com/payment/success/"+commande.getReferencecommande());
        payment.setRedirectUrls(redirectUrls);
        try {
            payment = payment.create(apiContext);
            System.out.println(payment.toJSON());
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        paymentAfroeats.setPaymentreference(payment.getId());
        paymentAfroeats.setAmount(Double.parseDouble(payment.getTransactions().get(0).getAmount().getTotal()));
        paymentAfroeats.setCurrency(payment.getTransactions().get(0).getAmount().getCurrency());
        paymentAfroeats.setState(payment.getState());
        for (Links link : payment.getLinks()) {
            if (link.getRel().equals("approval_url")) {
                paymentAfroeats.setUrl_redict_topayment(link.getHref());
            }
        }
        commande.setPaymentAfroeats(paymentAfroeats);
        return paymentAfroeats;
    }

    @Override
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        payment=payment.execute(apiContext, paymentExecute);
        System.out.println(payment.toJSON());
        return payment;
    }

}