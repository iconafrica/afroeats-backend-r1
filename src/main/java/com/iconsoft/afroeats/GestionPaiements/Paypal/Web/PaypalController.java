package com.iconsoft.afroeats.GestionPaiements.Paypal.Web;

import com.iconsoft.afroeats.GestionPaiements.Payment.Model.PaymentAfroeats;
import com.iconsoft.afroeats.GestionPaiements.Paypal.Metier.MetierPaypal;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaypalController {

        @Autowired
        MetierPaypal metierPaypal;

        public static final String SUCCESS_URL = "pay/success";
        public static final String CANCEL_URL = "pay/cancel";



        @GetMapping(value = CANCEL_URL)
        public String cancelPay() {
            return "cancel";
        }

        @GetMapping(value = SUCCESS_URL)
        public Payment successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {

            Payment payment = null;
            try {
                payment = metierPaypal.executePayment(paymentId, payerId);
            } catch (PayPalRESTException e) {
                e.printStackTrace();
            }

            return payment;
        }

    }

