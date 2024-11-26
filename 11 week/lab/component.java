import java.util.List;

interface IUserService {
    User getUser(long userId);
}

interface IProductService {
    Product getProduct(long productId);
}

interface IPaymentService {
    PaymentResult processPayment(Payment payment);
}

interface INotificationService {
    void sendOrderUpdateNotification(User user, Order order);
}

interface IOrderService {
    Order createOrder(Order order);
}


class OrderServiceImpl implements IOrderService {
    private final IUserService userService;
    private final IProductService productService;
    private final IPaymentService paymentService;

    public OrderServiceImpl(IUserService userService, IProductService productService,
                            IPaymentService paymentService) {
        this.userService = userService;
        this.productService = productService;
        this.paymentService = paymentService;
        
    }

    @Override
    public Order createOrder(Order order) {
        return null;
    }
}

class NotificationSefviceImpl implements INotificationService{
    private final IUserService;

    public NotificationSefviceImpl(IUserService userService){
        this.userService = userService;
    }

    @Override 
    public void sendOrderUpdateNotification(){
        System.out.println();
    }
}


