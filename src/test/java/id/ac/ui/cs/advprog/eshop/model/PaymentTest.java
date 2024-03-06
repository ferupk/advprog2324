package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    @Test
    void testCreatePaymentInvalidMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP4673F4XM191");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("c9e142a2-3513-4b83-8e72-f1cc73567877",
                    "Bank Transfer", paymentData);
        });
    }

    @Test
    void testCreatePaymentSuccessVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP4673F4XM191");

        Payment payment = new Payment("c9e142a2-3513-4b83-8e72-f1cc73567877",
                PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidVoucherCodeLength() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP46734X191");

        Payment payment = new Payment("c9e142a2-3513-4b83-8e72-f1cc73567877",
                PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidVoucherCodePrefix() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ORDER4673F4XM191");

        Payment payment = new Payment("c9e142a2-3513-4b83-8e72-f1cc73567877",
                PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidVoucherCodeNums() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOPSAMPLE60253");

        Payment payment = new Payment("c9e142a2-3513-4b83-8e72-f1cc73567877",
                PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentSuccessCashOnDelivery() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Mayora 2 No. 9, Jakarta Timur");
        paymentData.put("deliveryFee", "Rp50.000");

        Payment payment = new Payment("c9e142a2-3513-4b83-8e72-f1cc73567877",
                PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentEmptyCashOnDeliveryAddress() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", null);
        paymentData.put("deliveryFee", "Rp50.000");

        Payment payment = new Payment("c9e142a2-3513-4b83-8e72-f1cc73567877",
                PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentEmptyCashOnDeliveryFee() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Mayora 2 No. 9, Jakarta Timur");
        paymentData.put("deliveryFee", null);

        Payment payment = new Payment("c9e142a2-3513-4b83-8e72-f1cc73567877",
                PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOPSAMPLE60253");

        Payment payment = new Payment("c9e142a2-3513-4b83-8e72-f1cc73567877",
                PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP4673F4XM191");

        Payment payment = new Payment("c9e142a2-3513-4b83-8e72-f1cc73567877",
                PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP4673F4XM191");

        Payment payment = new Payment("c9e142a2-3513-4b83-8e72-f1cc73567877",
                PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("EXPIRED"));
    }
}
