package pl.edu.agh.internetshop;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class Order {
    private static final BigDecimal TAX_VALUE = BigDecimal.valueOf(1.23);
	private final UUID id;
    private final List<Product> products;
    private boolean paid;
    private Shipment shipment;
    private ShipmentMethod shipmentMethod;
    private PaymentMethod paymentMethod;
    private BigDecimal discount;
    private String ordersPayerSurname;

    public Order(List<Product> products) {
        this.products = Objects.requireNonNull(products, "products cannot be null");
        this.products.forEach((p) -> Objects.requireNonNull(p,"product cannot be null"));
        id = UUID.randomUUID();
        paid = false;
    }

    public BigDecimal getPrice() {
        BigDecimal price = BigDecimal.valueOf(0.0);
        for (Product product: products) {
            price = price.add(product.getPrice());
        }
        return price;
    }

    public BigDecimal getPriceWithProductDiscount() {
        BigDecimal price = BigDecimal.valueOf(0.0);
        for (Product product: products) {
            price = price.add(product.getPriceWithDiscount());
        }
        return price;
    }

    public BigDecimal getPriceWithDiscount() {
        if (discount != null) {
            return getPriceWithProductDiscount()
                    .subtract(getPriceWithProductDiscount().multiply(discount)
                    .setScale(Product.PRICE_PRECISION, Product.ROUND_STRATEGY));
        }
        return getPriceWithProductDiscount();
    }

    public BigDecimal getPriceWithTaxes() {
        return getPriceWithDiscount().multiply(TAX_VALUE).setScale(Product.PRICE_PRECISION, Product.ROUND_STRATEGY);
    }

    public void send() {
        boolean sentSuccessful = getShipmentMethod().send(shipment, shipment.getSenderAddress(), shipment.getRecipientAddress());
        shipment.setShipped(sentSuccessful);
    }

    public void pay(MoneyTransfer moneyTransfer) {
        moneyTransfer.setCommitted(getPaymentMethod().commit(moneyTransfer));
        paid = moneyTransfer.isCommitted();
    }

    public UUID getId() {
        return id;
    }

    public boolean isSent() {
        return shipment != null && shipment.isShipped();
    }

    public boolean isPaid() { return paid; }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setDiscount(BigDecimal discount) { this.discount = discount; }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setOrdersPayerSurname(String ordersPayerSurname) { this.ordersPayerSurname = ordersPayerSurname; }

    public String getOrdersPayerSurname() {
        return ordersPayerSurname;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setShipmentMethod(ShipmentMethod shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }

    public ShipmentMethod getShipmentMethod() {
        return shipmentMethod;
    }

    public List<Product> getProducts() {
        return products;
    }
}
