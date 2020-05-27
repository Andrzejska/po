package pl.edu.agh.internetshop.filter;

import pl.edu.agh.internetshop.Order;

public interface SearchStrategy {
    boolean filter(Order order);
}
