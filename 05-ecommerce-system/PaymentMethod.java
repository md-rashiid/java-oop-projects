public interface PaymentMethod {
    boolean processPayment(double amount);
    String getPaymentType();
    String getTransactionId();
}
