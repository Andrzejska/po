# Laboratorium 5
## Testy jednostkowe
## Autorzy: Andrii Trishch, Uladzislau Tumilovich


#### Krok 1. Zmiana wartości procentowej z _22_ na _23_:

**a)** Została zamieniona wartość oczekiwana testu _testPriceWithTexesWithoutRoundUp_ z _2.44_ na _2.46_


```java
public class OrderTest {
  
  ...
  
	@Test
	public void testPriceWithTaxesWithoutRoundUp() {
		// given

		// when
		Order order = getOrderWithCertainProductPrice(2); // 2 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(2.46)); // 2.46 PLN
	}
  
  ...
}
```

**b)** W klasie **Order** zostało zmienione pole _TAX_VALUE_ z _BigDecimal.valueOf(1.22)_ na _BigDecimal.valueOf(1.23)_

```java
public class Order {
  private static final BigDecimal TAX_VALUE = BigDecimal.valueOf(1.23);
 
  ...
}
```

**c)** Po uruchomieniu wszystkich testów

![tests results](img/test1.jpg)


#### Krok 2. Rozszerzenie funkcjalności systemu:

**a)** Została wprowadzona modyfikacja API klasy **Order** w taki spośob, żeby przechowywać i zwracać liste produktów


```java
public class Order {
    private static final BigDecimal TAX_VALUE = BigDecimal.valueOf(1.23);
	private final UUID id;
    // private final Product product;
    private boolean paid;
    private Shipment shipment;
    private ShipmentMethod shipmentMethod;
    private PaymentMethod paymentMethod;

    public Order(List<Product> product) {
        // this.product = product;
        id = UUID.randomUUID();
        paid = false;
    }

    public UUID getId() {
        return id;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isSent() {
        return shipment != null && shipment.isShipped();
    }

    public boolean isPaid() { return paid; }

    public Shipment getShipment() {
        return shipment;
    }

    public BigDecimal getPrice() {
        return null; //product.getPrice();
    }

    public BigDecimal getPriceWithTaxes() {
        return getPrice().multiply(TAX_VALUE).setScale(Product.PRICE_PRECISION, Product.ROUND_STRATEGY);
    }

    public List<Product> getProducts() {
        return null;
    }

    public ShipmentMethod getShipmentMethod() {
        return shipmentMethod;
    }

    public void setShipmentMethod(ShipmentMethod shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }

    public void send() {
        boolean sentSuccesful = getShipmentMethod().send(shipment, shipment.getSenderAddress(), shipment.getRecipientAddress());
        shipment.setShipped(sentSuccesful);
    }

    public void pay(MoneyTransfer moneyTransfer) {
        moneyTransfer.setCommitted(getPaymentMethod().commit(moneyTransfer));
        paid = moneyTransfer.isCommitted();
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }
}
```

