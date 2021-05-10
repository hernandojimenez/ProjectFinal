package com.mercadolibre.desafiofinaljosejimenez.unit.beans;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderDEResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.PartResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.exceptions.NotFoundException;
import com.mercadolibre.desafiofinaljosejimenez.model.OrderDE;
import com.mercadolibre.desafiofinaljosejimenez.model.Part;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Gets orders by dealer number")
    public void getOrdersByDealerNumber() throws Exception {
        List<OrderResponseDTO> ordersDE = GeneralTestUtils.getOrdersDE();
        List<OrderDE> ordersDEDB = GeneralTestUtils.getOrdersDEDB();

        when(orderRepository.findByDealerRequest(any())).thenReturn(ordersDEDB);

        Map<String, String> params = new HashMap<>();

        params.put("dealerNumber", "1234");

        OrderDEResponseDTO orderResponse = orderService.getOrders(params);

        Assertions.assertEquals(ordersDE.size(), orderResponse.getOrders().size());
        Assertions.assertEquals(ordersDE.get(0).getDeliveryStatus(), orderResponse.getOrders().get(1).getDeliveryStatus());
    }

    /*@Test
    @DisplayName("Gets an exception because it could not find any parts")
    public void partsNotFoundException() throws Exception {
        List<Part> partsDB = new ArrayList<>();

        when(orderRepository.findByModifiedAttributeDesc(any())).thenReturn(partsDB);

        try {
            List<PartResponseDTO> responseParts = orderService.getParts(new HashMap<>());
        }
        catch (NotFoundException e) {
            Assertions.assertTrue(e.getMessage().contains("404 Not Found"));
        }
    }*/
}
