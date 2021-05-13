package com.mercadolibre.desafiofinaljosejimenez.unit.beans;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.model.OrderDE;
import com.mercadolibre.desafiofinaljosejimenez.repositories.OrderRepository;
import com.mercadolibre.desafiofinaljosejimenez.util.GeneralTestUtils;
import com.mercadolibre.desafiofinaljosejimenez.util.OrderSorterUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrderSorterUtilsTest {
    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Gets orders by dealer number")
    public void getOrdersByDealerNumber() throws Exception {
        List<OrderDE> ordersDB = GeneralTestUtils.getOrdersDEDB();
        List<OrderResponseDTO> orders = GeneralTestUtils.getOrdersDE();

        when(orderRepository.findByDealerRequest(any())).thenReturn(ordersDB);

        Map<String, String> params = new HashMap<>();

        params.put("dealerNumber", "1234");

        List<OrderDE> responseOrders = OrderSorterUtils.getSortedList(params, orderRepository);

        Assertions.assertEquals(orders.size(), responseOrders.size());
    }

    /*@Test
    @DisplayName("Gets orders by dealer number and delivery status")
    public void getOrdersByDealerNumberAndDeliveryStatus() throws Exception {
        List<OrderDE> ordersDB = GeneralTestUtils.getOrdersDEDBWithFilters();
        List<OrderResponseDTO> orders = GeneralTestUtils.getOrdersDEWithFilters();

        when(orderRepository.findByDealerAndStatus(any(), any())).thenReturn(ordersDB);

        Map<String, String> params = new HashMap<>();

        params.put("dealerNumber", "1234");
        params.put("deliveryStatus", "P");

        List<OrderDE> responseOrders = OrderSorterUtils.getSortedList(params, orderRepository);

        Assertions.assertEquals(orders.size(), responseOrders.size());
    }*/

    @Test
    @DisplayName("Gets orders by dealer number ascending")
    public void getOrdersByDealerNumberOrderAsc() throws Exception {
        List<OrderDE> ordersDB = GeneralTestUtils.getOrdersDEDB();
        List<OrderResponseDTO> orders = GeneralTestUtils.getOrdersDE();

        when(orderRepository.findByDealerAscending(any())).thenReturn(ordersDB);

        Map<String, String> params = new HashMap<>();

        params.put("dealerNumber", "1234");
        params.put("order", "1");

        List<OrderDE> responseOrders = OrderSorterUtils.getSortedList(params, orderRepository);

        Assertions.assertEquals(orders.size(), responseOrders.size());
    }

    @Test
    @DisplayName("Gets orders by dealer number descending")
    public void getOrdersByDealerNumberOrderDesc() throws Exception {
        List<OrderDE> ordersDB = GeneralTestUtils.getOrdersDEDB();
        List<OrderResponseDTO> orders = GeneralTestUtils.getOrdersDE();

        when(orderRepository.findByDealerDescending(any())).thenReturn(ordersDB);

        Map<String, String> params = new HashMap<>();

        params.put("dealerNumber", "1234");
        params.put("order", "2");

        List<OrderDE> responseOrders = OrderSorterUtils.getSortedList(params, orderRepository);

        Assertions.assertEquals(orders.size(), responseOrders.size());
    }

    /*@Test
    @DisplayName("Gets orders by dealer number and delivery status ascending")
    public void getOrdersByDealerNumberAndDeliveryStatusOrderAsc() throws Exception {
        List<OrderDE> ordersDB = GeneralTestUtils.getOrdersDEDBWithFilters();
        List<OrderResponseDTO> orders = GeneralTestUtils.getOrdersDEWithFilters();

        when(orderRepository.findByDealerAndStatusAscending(any(), any())).thenReturn(ordersDB);

        Map<String, String> params = new HashMap<>();

        params.put("dealerNumber", "1234");
        params.put("deliveryStatus", "P");
        params.put("order", "1");

        List<OrderDE> responseOrders = OrderSorterUtils.getSortedList(params, orderRepository);

        Assertions.assertEquals(orders.size(), responseOrders.size());
    }

    @Test
    @DisplayName("Gets orders by dealer number and delivery status descending")
    public void getOrdersByDealerNumberAndDeliveryStatusOrderDesc() throws Exception {
        List<OrderDE> ordersDB = GeneralTestUtils.getOrdersDEDBWithFilters();
        List<OrderResponseDTO> orders = GeneralTestUtils.getOrdersDEWithFilters();

        when(orderRepository.findByDealerAndStatusDescending(any(), any())).thenReturn(ordersDB);

        Map<String, String> params = new HashMap<>();

        params.put("dealerNumber", "1234");
        params.put("deliveryStatus", "P");
        params.put("order", "2");

        List<OrderDE> responseOrders = OrderSorterUtils.getSortedList(params, orderRepository);

        Assertions.assertEquals(orders.size(), responseOrders.size());
    }*/
}
