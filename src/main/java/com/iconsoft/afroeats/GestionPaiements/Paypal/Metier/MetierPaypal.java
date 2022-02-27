package com.iconsoft.afroeats.GestionPaiements.Paypal.Metier;

import com.iconsoft.afroeats.GestionCommande.Models.Commande;
import com.iconsoft.afroeats.GestionPaiements.Payment.Model.PaymentAfroeats;
import com.iconsoft.afroeats.GestionPaiements.Paypal.Model.OrderPaiement;
import com.iconsoft.afroeats.GestionPaiements.Paypal.Model.ResponsePaypal;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface MetierPaypal {
    PaymentAfroeats createPayment(Commande commande);
    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
}
