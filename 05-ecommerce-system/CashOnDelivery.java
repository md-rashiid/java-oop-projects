public class CashOnDelivery implements PaymentMethod {
    private String transactionId;

    @Override
    public boolean processPayment(double amount) {
        System.out.println("[Cash on Delivery] Order Rs." + amount + " will be paid on delivery.");
        this.transactionId = "COD" + System.currentTimeMillis() % 1000000;
        System.out.println("  Reference ID: " + transactionId);
        System.out.println("  COD confirmed!");
        return true;
    }

    @Override
    public String getPaymentType()   { return "Cash on Delivery"; }

    @Override
    public String getTransactionId() { return transactionId; }
}
