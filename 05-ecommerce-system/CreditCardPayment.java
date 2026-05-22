public class CreditCardPayment implements PaymentMethod {
    private String cardNumber;
    private String cardHolderName;
    private String cvv;
    private String transactionId;

    public CreditCardPayment(String cardNumber, String cardHolderName, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("[Credit Card] Processing Rs." + amount + " ...");
        System.out.println("  Card: **** **** **** " + cardNumber.substring(cardNumber.length() - 4));
        System.out.println("  Holder: " + cardHolderName);
        this.transactionId = "CC" + System.currentTimeMillis() % 1000000;
        System.out.println("  Transaction ID: " + transactionId);
        System.out.println("  Payment SUCCESSFUL!");
        return true;
    }

    @Override
    public String getPaymentType()    { return "Credit Card"; }

    @Override
    public String getTransactionId()  { return transactionId; }
}
