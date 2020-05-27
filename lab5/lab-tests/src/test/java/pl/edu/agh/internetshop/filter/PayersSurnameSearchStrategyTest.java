package pl.edu.agh.internetshop.filter;

import org.junit.jupiter.api.Test;
import pl.edu.agh.internetshop.Order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class PayersSurnameSearchStrategyTest {

    @Test
    public void samePayerSurname() {
        // given
        Order order = mock(Order.class);
        given(order.getOrdersPayerSurname()).willReturn("Surname");

        // when
        PayersSurnameSearchStrategy searchStrategy = new PayersSurnameSearchStrategy("Surname");

        // then
        assertTrue(searchStrategy.filter(order));
    }

    @Test
    public void notSamePayerSurname() {
        // given
        Order order = mock(Order.class);
        given(order.getOrdersPayerSurname()).willReturn("notSurname");

        // when
        PayersSurnameSearchStrategy searchStrategy = new PayersSurnameSearchStrategy("Surname");

        // then
        assertFalse(searchStrategy.filter(order));
    }

    @Test
    public void payersSurnameIsNull() {
        // given
        Order order = mock(Order.class);
        given(order.getOrdersPayerSurname()).willReturn(null);

        // when
        PayersSurnameSearchStrategy searchStrategy = new PayersSurnameSearchStrategy("Surname");

        // then
        assertFalse(searchStrategy.filter(order));
    }
}
