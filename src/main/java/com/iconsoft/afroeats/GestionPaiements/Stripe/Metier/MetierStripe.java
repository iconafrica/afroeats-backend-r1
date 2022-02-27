package com.iconsoft.afroeats.GestionPaiements.Stripe.Metier;

import com.iconsoft.afroeats.GestionPaiements.Stripe.Model.Paymentstripe;
import com.stripe.exception.StripeException;

public interface MetierStripe {
    Paymentstripe do_payment(int montant) throws StripeException;
    String  paymentWithCheckoutPage(Paymentstripe payment) throws StripeException;
}
