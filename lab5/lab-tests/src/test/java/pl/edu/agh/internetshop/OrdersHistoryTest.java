package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;
import pl.edu.agh.internetshop.filter.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class OrdersHistoryTest {
    @Test
    void getMultipleOrdersFromOrdersHistory() {
        // given
        List<Order> orders = Arrays.asList(mock(Order.class), mock(Order.class));

        // when
        OrdersHistory ordersHistory = new OrdersHistory(orders);

        // then
        assertEquals(2, ordersHistory.getPastOrders().size());
        assertSame(orders.get(0), ordersHistory.getPastOrders().get(0));
        assertSame(orders.get(1), ordersHistory.getPastOrders().get(1));
    }

    @Test
    void getPastOrdersWithAddingOrders() {
        // given
        Order expectedOrder = mock(Order.class);

        // when
        OrdersHistory ordersHistory = new OrdersHistory(new ArrayList<>());
        ordersHistory.addOrder(expectedOrder);

        // then
        assertEquals(1, ordersHistory.getPastOrders().size());
        assertSame(expectedOrder, ordersHistory.getPastOrders().get(0));
    }

    @Test
    public void pastPastOrdersListIsNull() {
        // when then
        assertThrows(NullPointerException.class, () -> new OrdersHistory(null));
    }

    @Test
    public void listPastOrdersIsNull() {
        // given
        List<Order> pastOrders = Arrays.asList(mock(Order.class), null);

        // when then
        assertThrows(NullPointerException.class, () -> new OrdersHistory(pastOrders));
    }

    @Test
    void getFilteredOrdersWithProductName() {
        // given
        Product product = mock(Product.class);
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);
        Product product3 = mock(Product.class);

        given(product.getName()).willReturn("Apple");
        given(product1.getName()).willReturn("Banana");
        given(product2.getName()).willReturn("Orange");
        given(product3.getName()).willReturn("Lemon");

        Order order = mock(Order.class);
        Order order1 = mock(Order.class);
        Order order2 = mock(Order.class);

        given(order.getProducts()).willReturn(Arrays.asList(product, product1));
        given(order1.getProducts()).willReturn(Arrays.asList(product1, product3));
        given(order2.getProducts()).willReturn(Arrays.asList(product, product1, product2, product3));

        SearchStrategy searchStrategy = new ProductNameSearchStrategy("Apple");

        // when
        OrdersHistory ordersHistory = new OrdersHistory(Arrays.asList(order, order1, order2));

        // then
        assertEquals(2, ordersHistory.getFilteredOrders(searchStrategy).size());
        assertSame(order, ordersHistory.getFilteredOrders(searchStrategy).get(0));
        assertSame(order2, ordersHistory.getFilteredOrders(searchStrategy).get(1));
    }

    @Test
    void getFilteredOrdersWithPayersSurname() {
        // given
        Order order = mock(Order.class);
        Order order1 = mock(Order.class);
        Order order2 = mock(Order.class);

        given(order.getOrdersPayerSurname()).willReturn("Surname1");
        given(order1.getOrdersPayerSurname()).willReturn("Surname2");
        given(order2.getOrdersPayerSurname()).willReturn("Surname1");

        SearchStrategy searchStrategy = new PayersSurnameSearchStrategy("Surname1");

        // when
        OrdersHistory ordersHistory = new OrdersHistory(Arrays.asList(order, order1, order2));

        // then
        assertEquals(2, ordersHistory.getFilteredOrders(searchStrategy).size());
        assertSame(order, ordersHistory.getFilteredOrders(searchStrategy).get(0));
        assertSame(order2, ordersHistory.getFilteredOrders(searchStrategy).get(1));
    }

    @Test
    void getFilteredOrdersWithTotalPrice() {
        // given
        Order order = mock(Order.class);
        Order order1 = mock(Order.class);
        Order order2 = mock(Order.class);

        given(order.getPriceWithTaxes()).willReturn(BigDecimal.valueOf(10));
        given(order1.getPriceWithTaxes()).willReturn(BigDecimal.valueOf(20));
        given(order2.getPriceWithTaxes()).willReturn(BigDecimal.valueOf(10));

        SearchStrategy searchStrategy = new TotalPriceSearchStrategy(BigDecimal.valueOf(10));

        // when
        OrdersHistory ordersHistory = new OrdersHistory(Arrays.asList(order, order1, order2));

        // then
        assertEquals(2, ordersHistory.getFilteredOrders(searchStrategy).size());
        assertSame(order, ordersHistory.getFilteredOrders(searchStrategy).get(0));
        assertSame(order2, ordersHistory.getFilteredOrders(searchStrategy).get(1));
    }

    @Test
    void getCompositeFilteredOrders() {
        // given
        Product product = mock(Product.class);
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);
        Product product3 = mock(Product.class);

        given(product.getName()).willReturn("Apple");
        given(product1.getName()).willReturn("Banana");
        given(product2.getName()).willReturn("Orange");
        given(product3.getName()).willReturn("Lemon");

        Order order = mock(Order.class);
        Order order1 = mock(Order.class);
        Order order2 = mock(Order.class);

        given(order.getProducts()).willReturn(Arrays.asList(product, product1, product3));
        given(order1.getProducts()).willReturn(Arrays.asList(product1, product3));
        given(order2.getProducts()).willReturn(Arrays.asList(product, product1, product2, product3));

        given(order.getOrdersPayerSurname()).willReturn("Surname1");
        given(order1.getOrdersPayerSurname()).willReturn("Surname2");
        given(order2.getOrdersPayerSurname()).willReturn("Surname1");

        given(order.getPriceWithTaxes()).willReturn(BigDecimal.valueOf(20));
        given(order1.getPriceWithTaxes()).willReturn(BigDecimal.valueOf(10));
        given(order2.getPriceWithTaxes()).willReturn(BigDecimal.valueOf(10));

        SearchStrategy searchStrategy = new CompositeSearchStrategy(Arrays.asList(
                new ProductNameSearchStrategy("Lemon"),
                new PayersSurnameSearchStrategy("Surname1"),
                new TotalPriceSearchStrategy(BigDecimal.valueOf(10))
        ));

        // when
        OrdersHistory ordersHistory = new OrdersHistory(Arrays.asList(order, order1, order2));

        // then
        assertEquals(1, ordersHistory.getFilteredOrders(searchStrategy).size());
        assertSame(order2, ordersHistory.getFilteredOrders(searchStrategy).get(0));
    }

    @Test
    public void searchStrategyIsNull() {
        // given

        // when
        OrdersHistory ordersHistory = new OrdersHistory(Arrays.asList(mock(Order.class), mock(Order.class)));

        // then
        assertThrows(NullPointerException.class, () -> ordersHistory.getFilteredOrders(null));
    }
}
