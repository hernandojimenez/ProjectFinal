package com.mercadolibre.desafiofinaljosejimenez.unit.beans;

import com.mercadolibre.desafiofinaljosejimenez.controller.OrderController;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderCMResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderDEResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.exceptions.InvalidFilterInformation;
import com.mercadolibre.desafiofinaljosejimenez.security.JwtTokenUtil;
import com.mercadolibre.desafiofinaljosejimenez.service.JwtUserDetailsService;
import com.mercadolibre.desafiofinaljosejimenez.service.OrderService;
import com.mercadolibre.desafiofinaljosejimenez.util.GeneralTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrderControllerTest {
    @Mock
    private OrderService orderService;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private JwtUserDetailsService jwtUserDetailsService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        orderController = new OrderController(orderService, jwtTokenUtil, jwtUserDetailsService);
    }

    @Test
    @DisplayName("Gets orders DE by dealer number")
    public void getOrdersDEByDealerNumber() throws Exception {
        OrderDEResponseDTO order = GeneralTestUtils.getOrderDEResponse();

        when(orderService.getOrders(any())).thenReturn(order);
        when(jwtTokenUtil.getUsernameFromToken(any())).thenReturn("User");
        when(jwtUserDetailsService.autorizado(any(), any(), any())).thenReturn(true);

        Map<String, String> filters = new HashMap<>();

        filters.put("dealerNumber", "1234");

        ResponseEntity<OrderDEResponseDTO> responseOrder = (ResponseEntity<OrderDEResponseDTO>)orderController.getOrders(filters, null, null);

        Assertions.assertEquals(order, responseOrder.getBody());
    }

    @Test
    @DisplayName("Gets orders DE by dealer number and delivery status")
    public void getOrdersDEByDealerNumberAndDeliveryStatus() throws Exception {
        OrderDEResponseDTO order = GeneralTestUtils.getOrderDEResponseWithFilters();

        when(orderService.getOrders(any())).thenReturn(order);

        Map<String, String> filters = new HashMap<>();

        filters.put("dealerNumber", "1234");
        filters.put("deliveryStatus", "P");

        ResponseEntity<OrderDEResponseDTO> responseOrder = (ResponseEntity<OrderDEResponseDTO>)orderController.getOrders(filters, null, null);

        Assertions.assertEquals(order, responseOrder.getBody());
    }

    @Test
    @DisplayName("Gets an exception from not sending any filters")
    public void getEmptyFiltersException() throws Exception {
        when(orderService.getOrders(any())).thenThrow(new InvalidFilterInformation("You did not enter any filter"));

        try {
            orderController.getOrders(new HashMap<>(), null, null);
        }
        catch (InvalidFilterInformation e) {
            Assertions.assertTrue(e.getMessage().contains("You did not enter any filter"));
        }
    }

    @Test
    @DisplayName("Gets orders CM")
    public void getOrdersCM() throws Exception {
        OrderCMResponseDTO order = GeneralTestUtils.getOrderCMResponse();

        when(orderService.getOrdersCM(any())).thenReturn(order);
        when(jwtTokenUtil.getUsernameFromToken(any())).thenReturn("User");
        when(jwtUserDetailsService.autorizado(any(), any(), any())).thenReturn(true);

        Map<String, String> filters = new HashMap<>();

        ResponseEntity<OrderDEResponseDTO> responseOrder = (ResponseEntity<OrderDEResponseDTO>) orderController.getOrders(filters, "12345678", null);

        Assertions.assertEquals(order, responseOrder.getBody());
    }
}
