public class modul09lab {
    public static void main(String[] args) {
        IPaymentProcessor internalProcessor = PaymentProcessorSelector.GetPaymentProcessor("US", "USD");
        internalProcessor.ProcessPayment(100.0);
        internalProcessor.RefundPayment(50.0);

        IPaymentProcessor adapterA = PaymentProcessorSelector.GetPaymentProcessor("EU", "EUR");
        adapterA.ProcessPayment(200.0);
        adapterA.RefundPayment(100.0);

        IPaymentProcessor adapterB = PaymentProcessorSelector.GetPaymentProcessor("UK", "GBP");
        adapterB.ProcessPayment(300.0);
        adapterB.RefundPayment(150.0);
    }
}