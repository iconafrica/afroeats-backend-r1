package com.iconsoft.afroeats.GestionPaiements.Payment.Service;

import com.iconsoft.afroeats.Configuration.GenerateRandom;
import com.iconsoft.afroeats.GestionCommande.Metier.CommandeMetier;
import com.iconsoft.afroeats.GestionCommande.Models.Commande;
import com.iconsoft.afroeats.GestionCommande.Models.CommandeStatut;
import com.iconsoft.afroeats.GestionErreurs.ErrorMessages;
import com.iconsoft.afroeats.GestionPaiements.CONSTANTE;
import com.iconsoft.afroeats.GestionPaiements.Payment.Dao.DaoPaymentAfroEats;
import com.iconsoft.afroeats.GestionPaiements.Payment.Metier.MetierPaymentAfroEats;
import com.iconsoft.afroeats.GestionPaiements.Payment.Model.PaymentAfroeats;
import com.iconsoft.afroeats.GestionPaiements.Paypal.Metier.MetierPaypal;
import com.iconsoft.afroeats.GestionPaiements.Stripe.Metier.MetierStripe;
import com.iconsoft.afroeats.GestionPaiements.Stripe.Model.Paymentstripe;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@Transactional
public class ServicePaymentAfroEats implements MetierPaymentAfroEats {
    @Autowired
    DaoPaymentAfroEats daoPaymentAfroEats;
    @Autowired
    MetierStripe metierStripe;
    @Autowired
    MetierPaypal metierPaypal;
    @Autowired
    CommandeMetier commandeMetier;

    @Override
    public PaymentAfroeats save(PaymentAfroeats paymentAfroeats) {
        return daoPaymentAfroEats.save(paymentAfroeats);
    }

    @Override
    public PaymentAfroeats findByIdpayment(Long idpayement) {
        return daoPaymentAfroEats.findByIdpayment(idpayement);
    }

    @Override
    public PaymentAfroeats findByPaymentreference(String paymentreference) {
        return daoPaymentAfroEats.findByPaymentreference(paymentreference);
    }

    @Override
    public List<PaymentAfroeats> findAll() {
        return daoPaymentAfroEats.findAll();
    }

    @Override
    public Commande do_payment(PaymentAfroeats paymentAfroeats) {
        Commande commande=commandeMetier.getCommandesByreference(paymentAfroeats.getReferencecommande());
        if (commande==null) throw new ErrorMessages("Commande non trouvé", HttpStatus.NOT_FOUND);
        if (paymentAfroeats.getType_payment().equals(CONSTANTE.STRIPE)){
            Paymentstripe paymentstripe=new Paymentstripe();
            paymentstripe.setAmount((int) commande.getNetapayer()*100);
            paymentstripe.setCreated(commande.getNomclient());
            paymentstripe.setCurrency("eur");
            paymentstripe.setQuantity(1);
            paymentstripe.setSuccessUrl( "https://afroeatsbackend.herokuapp.com/payment/success/"+commande.getReferencecommande());
            paymentstripe.setCancelUrl("https://afroeatsbackend.herokuapp.com/payment/cancel/"+commande.getReferencecommande());
            try {
                String resul=metierStripe.paymentWithCheckoutPage(paymentstripe);
                System.out.println(resul);
                paymentAfroeats.setUrl_redict_topayment(resul);
                paymentAfroeats.setState("pending");
            } catch (StripeException e) {
                e.printStackTrace();
            }
            paymentAfroeats.setType_payment(CONSTANTE.STRIPE);
        }else  if (paymentAfroeats.getType_payment().equals(CONSTANTE.PAYPAL)) {
            paymentAfroeats=metierPaypal.createPayment(commande);
            paymentAfroeats.setType_payment(CONSTANTE.PAYPAL);

        }
        paymentAfroeats.setAmount(commande.getNetapayer());
        paymentAfroeats=save(paymentAfroeats);
        commande.setPaymentAfroeats(paymentAfroeats);
        return commande;
    }

    @Override
    public void successpayment(String referencecommande) {
        Commande commande=commandeMetier.getCommandesByreference(referencecommande);
        commande.setStatut(CommandeStatut.EN_PREPARATION);
        commande.setCodecloture(GenerateRandom.randomString(5));
        commande.getPaymentAfroeats().setState("Payer");
    }

    @Override
    public void cancelpayment(String referencecommande) {
        Commande commande=commandeMetier.getCommandesByreference(referencecommande);
        commande.getPaymentAfroeats().setState("refuser");
    }
}
