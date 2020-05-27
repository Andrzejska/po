package pl.edu.agh.internetshop;

import pl.edu.agh.internetshop.filter.SearchStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrdersHistory {
    private final List<Order> pastOrders;

    public OrdersHistory(List<Order> pastOrders) {
        this.pastOrders = Objects.requireNonNull(pastOrders, "pastOrders cannot be null");
        this.pastOrders.forEach((p) -> Objects.requireNonNull(p,"order cannot be null"));
    }

    public void addOrder(Order order){
        this.pastOrders.add(order);
    }

    public List<Order> getPastOrders() {
        return pastOrders;
    }

    public List<Order> getFilteredOrders(SearchStrategy searchStrategy) {
        List<Order> resultList = new ArrayList<>();
        for (Order order: pastOrders) {
            if (searchStrategy.filter(order)) {
                resultList.add(order);
            }
        }
        return resultList;
    }
}
