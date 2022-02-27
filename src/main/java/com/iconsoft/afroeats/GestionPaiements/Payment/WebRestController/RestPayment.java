package com.iconsoft.afroeats.GestionPaiements.Payment.WebRestController;

import com.iconsoft.afroeats.GestionCommande.Models.Commande;
import com.iconsoft.afroeats.GestionPaiements.Payment.Metier.MetierPaymentAfroEats;
import com.iconsoft.afroeats.GestionPaiements.Payment.Model.PaymentAfroeats;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@CrossOrigin("*")
@RequestMapping("payment")
public class RestPayment {
    @Autowired
    MetierPaymentAfroEats metierPaymentAfroEats;

    @PostMapping("valide")
    Commande do_payment(@RequestBody PaymentAfroeats paymentAfroeats){
        return metierPaymentAfroEats.do_payment(paymentAfroeats);
    }

    @GetMapping("/success/{referencecommande}")
    public RedirectView localRedirectsucces(@PathVariable String referencecommande) {
        metierPaymentAfroEats.successpayment(referencecommande);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("https://afroeats.herokuapp.com/");
        return redirectView;
    }

    @GetMapping("/cancel/{referencecommande}")
    public RedirectView cancelRedirectsucces(@PathVariable String referencecommande) {
        metierPaymentAfroEats.cancelpayment(referencecommande);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("https://afroeats.herokuapp.com/");
        return redirectView;
    }
}
