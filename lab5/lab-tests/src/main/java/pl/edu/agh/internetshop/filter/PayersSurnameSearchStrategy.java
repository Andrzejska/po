package pl.edu.agh.internetshop.filter;

import pl.edu.agh.internetshop.Order;

public class PayersSurnameSearchStrategy implements SearchStrategy {
    private String payersSurname;

    public PayersSurnameSearchStrategy(String payersSurname) {
        this.payersSurname = payersSurname;
    }

    @Override
    public boolean filter(Order order) {
        if (order.getOrdersPayerSurname() != null) {
            return order.getOrdersPayerSurname().equals(this.payersSurname);
        }
        return false;
    }
}