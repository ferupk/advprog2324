package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;

    public Payment(String id, String method, Map<String,String> paymentData) {
        this.id = id;
        this.paymentData = paymentData;

        if (PaymentMethod.contains(method)) {
            this.method = method;
        } else {
            throw new IllegalArgumentException();
        }

        if (this.method.equals(PaymentMethod.VOUCHER_CODE.getValue())) {
            this.status = isVoucherDataValid() ? "SUCCESS" : "REJECTED";
        } else {
            this.status = isCashOnDeliveryDataValid() ? "SUCCESS" : "REJECTED";
        }
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean isVoucherDataValid() {
        String voucherCode = this.paymentData.get("voucherCode");
        
        if (voucherCode.length() != 16) {
            return false;
        } else if (!voucherCode.substring(0,5).equals("ESHOP")) {
            return false;
        } else if (countDigitsInCode(voucherCode) != 8) {
            return false;
        }

        return true;
    }

    private int countDigitsInCode(String voucherCode) {
        int sum = 0;
        for (int i = 0; i < voucherCode.length(); i++) {
            if (Character.isDigit(voucherCode.charAt(i)))
                sum++;
        }
        return sum;
    }

    private boolean isCashOnDeliveryDataValid() {
        String address = this.paymentData.get("address");
        String deliveryFee = this.paymentData.get("deliveryFee");

        if (address == null || address.isEmpty()) {
            return false;
        } else if (deliveryFee == null || deliveryFee.isEmpty()) {
            return false;
        }

        return true;
    }
}
