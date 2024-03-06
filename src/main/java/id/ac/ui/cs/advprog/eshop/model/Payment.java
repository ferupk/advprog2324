package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
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

        String[] methodList = {"Voucher Code", "Cash On Delivery"};
        if (Arrays.stream(methodList).noneMatch(item -> (item.equals(method)))) {
            throw new IllegalArgumentException();
        } else {
            this.method = method;
        }

        if (this.method.equals("Voucher Code")) {
            this.status = isVoucherDataValid() ? "SUCCESS" : "REJECTED";
        } else {
            this.status = isCashOnDeliveryDataValid() ? "SUCCESS" : "REJECTED";
        }
    }

    public void setStatus(String status) {
        String[] statusList = {"SUCCESS", "REJECTED"};
        if (Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))) {
            throw new IllegalArgumentException();
        } else {
            this.status = status;
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
