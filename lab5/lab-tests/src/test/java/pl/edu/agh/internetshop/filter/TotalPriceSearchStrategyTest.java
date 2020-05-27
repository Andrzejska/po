package pl.edu.agh.internetshop.filter;

import org.junit.jupiter.api.Test;
import pl.edu.agh.internetshop.Order;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class TotalPriceSearchStrategyTest {
    private Order getMockedOrder() {
        Order order = mock(Order.class);
        given(order.getPriceWithTaxes()).willReturn(BigDecimal.valueOf(10));
        return order;
    }

    @Test
    public void sameTotalPrice() {
        // given
        Order order = getMockedOrder();

        // when
        TotalPriceSearchStrategy searchStrategy = new TotalPriceSearchStrategy(BigDecimal.valueOf(10));

        // then
        assertTrue(searchStrategy.filter(order));
    }

    @Test
    public void notSameTotalPrice() {
        // given
        Order order = getMockedOrder();

        // when
        TotalPriceSearchStrategy searchStrategy = new TotalPriceSearchStrategy(BigDecimal.valueOf(9));

        // then
        assertFalse(searchStrategy.filter(order));
    }
}
