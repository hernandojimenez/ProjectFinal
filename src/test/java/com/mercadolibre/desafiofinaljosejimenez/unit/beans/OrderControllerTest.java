package com.mercadolibre.desafiofinaljosejimenez.unit.beans;

import com.mercadolibre.desafiofinaljosejimenez.controller.OrderController;
import com.mercadolibre.desafiofinaljosejimenez.dtos.request.OrderDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.request.UpdateDeliveryDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.request.UpdateOrderDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderCMResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderDEResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.StatusCodeDTO;
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

    @Test
    @DisplayName("Saves an order")
    public void saveOrder() throws Exception {
        OrderDTO order = GeneralTestUtils.getOrdersDTO().get(0);

        when(orderService.saveOrder(any())).thenReturn(new StatusCodeDTO(200, "Order saved successfully"));
        when(jwtTokenUtil.getUsernameFromToken(any())).thenReturn("User");
        when(jwtUserDetailsService.autorizado(any(), any(), any())).thenReturn(true);

        ResponseEntity<StatusCodeDTO> responseStatus = orderController.saveOrder(order, null);

        Assertions.assertEquals("Order saved successfully", responseStatus.getBody().getMessage());
    }

    @Test
    @DisplayName("Updates an order")
    public void updateOrder() throws Exception {
        UpdateOrderDTO order = GeneralTestUtils.getUpdateOrdersDTO().get(0);

        when(orderService.updateOrderStatus(any())).thenReturn(new StatusCodeDTO(200, "OK"));
        when(jwtTokenUtil.getUsernameFromToken(any())).thenReturn("User");
        when(jwtUserDetailsService.autorizado(any(), any(), any())).thenReturn(true);

        ResponseEntity<StatusCodeDTO> responseStatus = orderController.updateOrder(order, null);

        Assertions.assertEquals(200, responseStatus.getBody().getNumber());
        Assertions.assertEquals("OK", responseStatus.getBody().getMessage());
    }

    @Test
    @DisplayName("Updates delivery status of an order")
    public void updateDeliveryStatus() throws Exception {
        UpdateDeliveryDTO delivery = GeneralTestUtils.getUpdateDeliveriesDTO().get(0);

        when(orderService.updateDeliveryStatus(any())).thenReturn(new StatusCodeDTO(200, "OK"));
        when(jwtTokenUtil.getUsernameFromToken(any())).thenReturn("User");
        when(jwtUserDetailsService.autorizado(any(), any(), any())).thenReturn(true);

        ResponseEntity<StatusCodeDTO> responseStatus = orderController.updateDeliveryStatus(delivery, null);

        Assertions.assertEquals(200, responseStatus.getBody().getNumber());
        Assertions.assertEquals("OK", responseStatus.getBody().getMessage());
    }
}
