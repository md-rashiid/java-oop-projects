public class UPIPayment implements PaymentMethod {
    private String upiId;
    private String transactionId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("[UPI] Processing Rs." + amount + " ...");
        System.out.println("  UPI ID: " + upiId);
        this.transactionId = "UPI" + System.currentTimeMillis() % 1000000;
        System.out.println("  Transaction ID: " + transactionId);
        System.out.println("  Payment SUCCESSFUL!");
        return true;
    }

    @Override
    public String getPaymentType()   { return "UPI"; }

    @Override
    public String getTransactionId() { return transactionId; }
}
