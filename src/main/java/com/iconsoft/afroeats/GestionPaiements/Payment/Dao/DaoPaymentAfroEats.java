package com.iconsoft.afroeats.GestionPaiements.Payment.Dao;

import com.iconsoft.afroeats.GestionPaiements.Payment.Model.PaymentAfroeats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoPaymentAfroEats extends JpaRepository<PaymentAfroeats,Long> {
    PaymentAfroeats findByIdpayment(Long idpayment);
    PaymentAfroeats findByPaymentreference(String paymentreference);
}
