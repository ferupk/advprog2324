package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP4673F4XM191");
        Payment payment1 = new Payment("c9e142a2-3513-4b83-8e72-f1cc73567877",
                PaymentMethod.VOUCHER_CODE.getValue(), paymentData1);
        payments.add(payment1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("voucherCode", "ESHOPSAMPLE60253");
        Payment payment2 = new Payment("652248b6-b8ad-4dfe-95d8-c9417b71fd4e",
                PaymentMethod.VOUCHER_CODE.getValue(), paymentData2);
        payments.add(payment2);

        Map<String, String> paymentData3 = new HashMap<>();
        paymentData3.put("address", "Jl. Mayora 2 No. 9, Jakarta Timur");
        paymentData3.put("deliveryFee", "Rp50.000");
        Payment payment3 = new Payment("c7bb02cc-db58-42c7-ad10-65628a9e3d68",
                PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentData3);
        payments.add(payment3);
    }

    @Test
    void testSavePayment() {
        Payment payment = payments.get(0);
        Payment savedPayment = paymentRepository.savePayment(payment);

        Payment getPayment = paymentRepository.getPaymentById(payments.get(0).getId());
        assertEquals(payment.getId(), savedPayment.getId());
        assertEquals(payment.getId(), getPayment.getId());
        assertEquals(payment.getMethod(), getPayment.getMethod());
        assertEquals(payment.getStatus(), getPayment.getStatus());
        assertEquals(payment.getPaymentData(), getPayment.getPaymentData());
    }

    @Test
    void testUpdatePayment() {
        Payment payment = payments.get(1);
        paymentRepository.savePayment(payment);
        Payment newPayment = new Payment(payment.getId(), payment.getMethod(), payment.getPaymentData());
        newPayment.setStatus("SUCCESS");
        Payment savedPayment = paymentRepository.savePayment(newPayment);

        Payment getPayment = paymentRepository.getPaymentById(payments.get(1).getId());
        assertEquals(payment.getId(), savedPayment.getId());
        assertEquals(payment.getId(), getPayment.getId());
        assertEquals(payment.getMethod(), getPayment.getMethod());
        assertEquals(OrderStatus.SUCCESS.getValue(), getPayment.getStatus());
        assertEquals(payment.getPaymentData(), getPayment.getPaymentData());
    }

    @Test
    void testGetPaymentByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.savePayment(payment);
        }

        Payment getPayment = paymentRepository.getPaymentById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), getPayment.getId());
        assertEquals(payments.get(1).getMethod(), getPayment.getMethod());
        assertEquals(payments.get(1).getStatus(), getPayment.getStatus());
        assertEquals(payments.get(1).getPaymentData(), getPayment.getPaymentData());
    }

    @Test
    void testGetPaymentByIdIfIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.savePayment(payment);
        }

        Payment getPayment = paymentRepository.getPaymentById("not-an-id");
        assertNull(getPayment);
    }

    @Test
    void testGetAllPaymentsIfEmpty() {
        Iterator<Payment> paymentIterator = paymentRepository.getAllPayments();
        assertFalse(paymentIterator.hasNext());
    }

    @Test
    void testGetAllPaymentsIfManyPayments() {
        for (Payment payment : payments) {
            paymentRepository.savePayment(payment);
        }

        Iterator<Payment> paymentIterator = paymentRepository.getAllPayments();
        assertTrue(paymentIterator.hasNext());
        Payment getPayment = paymentIterator.next();
        assertEquals(payments.get(0).getId(), getPayment.getId());
        getPayment = paymentIterator.next();
        assertEquals(payments.get(1).getId(), getPayment.getId());
        getPayment = paymentIterator.next();
        assertEquals(payments.get(2).getId(), getPayment.getId());
        assertFalse(paymentIterator.hasNext());
    }
}
