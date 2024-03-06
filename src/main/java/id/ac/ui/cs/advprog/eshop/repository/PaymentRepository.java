package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class PaymentRepository {
    private List<Payment> orderPaymentData = new ArrayList<>();

    public Payment savePayment(Payment payment) {
        int i = 0;
        for (Payment savedPayment : orderPaymentData) {
            if (savedPayment.getId().equals(payment.getId())) {
                orderPaymentData.remove(i);
                orderPaymentData.add(i, payment);
                return payment;
            }
            i += 1;
        }

        orderPaymentData.add(payment);
        return payment;
    }

    public Payment getPaymentById(String id) {
        for (Payment savedPayment : orderPaymentData) {
            if (savedPayment.getId().equals(id)) {
                return savedPayment;
            }
        }
        return null;
    }

    public Iterator<Payment> getAllPayments() {
        return orderPaymentData.iterator();
    }
}
