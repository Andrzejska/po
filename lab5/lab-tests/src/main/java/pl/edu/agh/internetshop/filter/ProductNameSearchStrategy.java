package pl.edu.agh.internetshop.filter;

import pl.edu.agh.internetshop.Order;
import pl.edu.agh.internetshop.Product;

import java.util.List;

public class ProductNameSearchStrategy implements SearchStrategy {
    private String name;

    public ProductNameSearchStrategy(String name) {
        this.name = name;
    }

    @Override
    public boolean filter(Order order) {
        List<Product> products = order.getProducts();
        for (Product product: products) {
            if (product.getName().equals(this.name)) {
                return true;
            }
        }
        return false;
    }
}