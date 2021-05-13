package com.mercadolibre.desafiofinaljosejimenez.unit.beans;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderCMResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderDEResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.model.OrderCM;
import com.mercadolibre.desafiofinaljosejimenez.model.OrderDE;
import com.mercadolibre.desafiofinaljosejimenez.repositories.OrderCMRepository;
import com.mercadolibre.desafiofinaljosejimenez.repositories.OrderRepository;
import com.mercadolibre.desafiofinaljosejimenez.service.OrderServiceImpl;
import com.mercadolibre.desafiofinaljosejimenez.util.GeneralTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderCMRepository orderCMRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Gets orders DE by dealer number")
    public void getOrdersDEByDealerNumber() throws Exception {
        List<OrderResponseDTO> ordersDE = GeneralTestUtils.getOrdersDE();
        List<OrderDE> ordersDEDB = GeneralTestUtils.getOrdersDEDB();

        when(orderRepository.findByDealerRequest(any())).thenReturn(ordersDEDB);

        Map<String, String> params = new HashMap<>();

        params.put("dealerNumber", "1234");

        OrderDEResponseDTO orderResponse = orderService.getOrders(params);

        Assertions.assertEquals(ordersDE.size(), orderResponse.getOrders().size());
        Assertions.assertEquals(ordersDE.get(0).getDeliveryStatus(), orderResponse.getOrders().get(1).getDeliveryStatus());
    }

    @Test
    @DisplayName("Gets orders CM")
    public void getOrdersCM() throws Exception {
        OrderCMResponseDTO orderCM = GeneralTestUtils.getOrderCMResponse();
        List<OrderCM> ordersCMDB = GeneralTestUtils.getOrdersCMDB();

        when(orderCMRepository.findBySubsidiaryAndDealerAndOrderNumberAscending(any(), any(), any())).thenReturn(ordersCMDB);

        OrderCMResponseDTO orderResponse = orderService.getOrdersCM("1234-3001-12345678");

        Assertions.assertEquals(orderCM.getOrders().get(0).getOrderDetails().size(), orderResponse.getOrders().size());
        Assertions.assertTrue(orderResponse.getOrders().get(0).getOrderNumber().contains(orderCM.getOrders().get(0).getOrderNumber()));
    }
}
