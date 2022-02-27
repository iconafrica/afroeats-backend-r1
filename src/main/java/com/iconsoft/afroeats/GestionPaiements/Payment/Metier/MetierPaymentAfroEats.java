package com.iconsoft.afroeats.GestionPaiements.Payment.Metier;

import com.iconsoft.afroeats.GestionCommande.Models.Commande;
import com.iconsoft.afroeats.GestionPaiements.Payment.Model.PaymentAfroeats;

import java.util.List;

public interface MetierPaymentAfroEats {
    PaymentAfroeats save(PaymentAfroeats paymentAfroeats);
    PaymentAfroeats findByIdpayment(Long idpayement);
    PaymentAfroeats findByPaymentreference(String paymentreference);
    List<PaymentAfroeats> findAll();
    Commande do_payment(PaymentAfroeats paymentAfroeats);
    void successpayment(String referencecommande);
    void cancelpayment(String referencecommande);

}
