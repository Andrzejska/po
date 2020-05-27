package pl.edu.agh.internetshop.filter;

import org.junit.jupiter.api.Test;
import pl.edu.agh.internetshop.Order;
import pl.edu.agh.internetshop.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CompositeSearchStrategyTest {
    private Order getMockedOrder() {
        Order order = mock(Order.class);
        List<Product> productList = Arrays.asList(
                new Product("Banana", BigDecimal.valueOf(43.05), BigDecimal.valueOf(0.5)),
                new Product("Orange", BigDecimal.valueOf(54.83), BigDecimal.valueOf(0.23))
        );
        given(order.getProducts()).willReturn(productList);
        given(order.getPriceWithTaxes()).willReturn(BigDecimal.valueOf(10));
        given(order.getOrdersPayerSurname()).willReturn("Surname");
        return order;
    }

    @Test
    public void sameAllParameters() {
        // given
        Order order = getMockedOrder();
        SearchStrategy productNameSearchStrategy = new ProductNameSearchStrategy("Banana");
        SearchStrategy payersSurnameSearchStrategy = new PayersSurnameSearchStrategy("Surname");
        SearchStrategy totalPriceSearchStrategy = new TotalPriceSearchStrategy(BigDecimal.valueOf(10));

        // when
        CompositeSearchStrategy searchStrategy = new CompositeSearchStrategy(
                Arrays.asList(productNameSearchStrategy, payersSurnameSearchStrategy, totalPriceSearchStrategy));

        // then
        assertTrue(searchStrategy.filter(order));
    }

    @Test
    public void sameAllParametersNotProductName() {
        // given
        Order order = getMockedOrder();
        SearchStrategy productNameSearchStrategy = new ProductNameSearchStrategy("Apple");
        SearchStrategy payersSurnameSearchStrategy = new PayersSurnameSearchStrategy("Surname");
        SearchStrategy totalPriceSearchStrategy = new TotalPriceSearchStrategy(BigDecimal.valueOf(10));

        // when
        CompositeSearchStrategy searchStrategy = new CompositeSearchStrategy(
                Arrays.asList(productNameSearchStrategy, payersSurnameSearchStrategy, totalPriceSearchStrategy));

        // then
        assertFalse(searchStrategy.filter(order));
    }

    @Test
    public void sameAllParametersNotPayersSurname() {
        // given
        Order order = getMockedOrder();
        SearchStrategy productNameSearchStrategy = new ProductNameSearchStrategy("Orange");
        SearchStrategy payersSurnameSearchStrategy = new PayersSurnameSearchStrategy("notSurname");
        SearchStrategy totalPriceSearchStrategy = new TotalPriceSearchStrategy(BigDecimal.valueOf(10));

        // when
        CompositeSearchStrategy searchStrategy = new CompositeSearchStrategy(
                Arrays.asList(productNameSearchStrategy, payersSurnameSearchStrategy, totalPriceSearchStrategy));

        // then
        assertFalse(searchStrategy.filter(order));
    }

    @Test
    public void sameAllParametersNotTotalPrice() {
        // given
        Order order = getMockedOrder();
        SearchStrategy productNameSearchStrategy = new ProductNameSearchStrategy("Orange");
        SearchStrategy payersSurnameSearchStrategy = new PayersSurnameSearchStrategy("Surname");
        SearchStrategy totalPriceSearchStrategy = new TotalPriceSearchStrategy(BigDecimal.valueOf(9));

        // when
        CompositeSearchStrategy searchStrategy = new CompositeSearchStrategy(
                Arrays.asList(productNameSearchStrategy, payersSurnameSearchStrategy, totalPriceSearchStrategy));

        // then
        assertFalse(searchStrategy.filter(order));
    }
}
