package pl.edu.agh.internetshop.filter;

import org.junit.jupiter.api.Test;
import pl.edu.agh.internetshop.Order;
import pl.edu.agh.internetshop.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ProductNameSearchStrategyTest {
    private Order getMockedOrder() {
        Order order = mock(Order.class);
        List<Product> productList = Arrays.asList(
                new Product("Banana", BigDecimal.valueOf(43.05), BigDecimal.valueOf(0.5)),
                new Product("Orange", BigDecimal.valueOf(54.83), BigDecimal.valueOf(0.23))
        );
        given(order.getProducts()).willReturn(productList);
        return order;
    }

    @Test
    public void existsInList() {
        // given
        Order order = getMockedOrder();

        // when
        ProductNameSearchStrategy searchStrategy = new ProductNameSearchStrategy("Banana");

        // then
        assertTrue(searchStrategy.filter(order));
    }

    @Test
    public void notExistsInList() {
        // given
        Order order = getMockedOrder();

        // when
        ProductNameSearchStrategy searchStrategy = new ProductNameSearchStrategy("Apple");

        // then
        assertFalse(searchStrategy.filter(order));
    }
}
