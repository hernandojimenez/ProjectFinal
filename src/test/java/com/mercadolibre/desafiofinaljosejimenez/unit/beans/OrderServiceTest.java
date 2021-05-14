package com.mercadolibre.desafiofinaljosejimenez.unit.beans;

import com.mercadolibre.desafiofinaljosejimenez.dtos.request.OrderDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.request.UpdateOrderDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.*;
import com.mercadolibre.desafiofinaljosejimenez.exceptions.NotFoundException;
import com.mercadolibre.desafiofinaljosejimenez.model.*;
import com.mercadolibre.desafiofinaljosejimenez.repositories.*;
import com.mercadolibre.desafiofinaljosejimenez.service.OrderServiceImpl;
import com.mercadolibre.desafiofinaljosejimenez.util.GeneralTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

    @Mock
    private OrderCMRepository orderCMRepository;

    @Mock
    private SubsidiaryRepository subsidiaryRepository;

    @Mock
    private PartRepository partRepository;

    @Mock
    private OrderDetailRepository orderDetailRepository;

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

    @Test
    @DisplayName("Saves an order")
    public void saveOrder() throws Exception {
        OrderDTO order = GeneralTestUtils.getOrdersDTO().get(0);

        Subsidiary subsidiary = GeneralTestUtils.getSubsidiaries().get(0);
        OrderCM orderCM = GeneralTestUtils.getOrdersCMDB().get(0);

        when(subsidiaryRepository.findBySubsidiaryNumber(any())).thenReturn(subsidiary);
        when(orderCMRepository.findOrderCMSByOrderNumber(any())).thenReturn(null);
        when(orderCMRepository.save(any())).thenReturn(orderCM);
        when(partRepository.findByPartCodeAndProvider_id(12345678, 2L)).thenReturn(new Part(1L));
        when(partRepository.findByPartCodeAndProvider_id(87654321, 2L)).thenReturn(new Part(2L));
        when(partRepository.findByPartCodeAndProvider_id(11223344, 2L)).thenReturn(new Part(3L));
        when(orderDetailRepository.save(any())).thenReturn(orderCM.getOrderDetail().get(0));

        StatusCodeDTO responseStatus = orderService.saveOrder(order);

        Assertions.assertEquals(200, responseStatus.getNumber());
        Assertions.assertEquals("Order saved successfully", responseStatus.getMessage());
    }

    @Test
    @DisplayName("Gets an exception when saving an order for not finding a subsidiary")
    public void subsidiaryNotFoundException() throws Exception {
        OrderDTO order = GeneralTestUtils.getOrdersDTO().get(0);

        OrderCM orderCM = GeneralTestUtils.getOrdersCMDB().get(0);

        when(subsidiaryRepository.findBySubsidiaryNumber(any())).thenReturn(null);
        when(orderCMRepository.findOrderCMSByOrderNumber(any())).thenReturn(orderCM);
        when(orderCMRepository.save(any())).thenReturn(orderCM);
        when(partRepository.findByPartCodeAndProvider_id(12345678, 2L)).thenReturn(new Part(1L));
        when(partRepository.findByPartCodeAndProvider_id(87654321, 2L)).thenReturn(new Part(2L));
        when(partRepository.findByPartCodeAndProvider_id(11223344, 2L)).thenReturn(new Part(3L));
        when(orderDetailRepository.save(any())).thenReturn(orderCM.getOrderDetail().get(0));

        try {
            StatusCodeDTO responseStatus = orderService.saveOrder(order);
        }
        catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains("Subsidiary not Found"));
        }
    }

    @Test
    @DisplayName("Gets an exception when updating an order status for not finding the order")
    public void orderNotFoundException() throws Exception {
        OrderDTO order = GeneralTestUtils.getOrdersDTO().get(0);

        when(orderCMRepository.findOrderCMSByOrderNumber(any())).thenReturn(null);

        try {
            StatusCodeDTO responseStatus = orderService.updateOrderStatus(new UpdateOrderDTO("12345678", "P"));
        }
        catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains("Order not found"));
        }
    }

    @Test
    @DisplayName("Gets an exception when updating an order status for the order being finished")
    public void orderFinishedNotFoundException() throws Exception {
        OrderDTO order = GeneralTestUtils.getOrdersDTO().get(0);

        OrderCM orderCM = GeneralTestUtils.getOrdersCMDB().get(0);

        when(orderCMRepository.findOrderCMSByOrderNumber(any())).thenReturn(orderCM);

        try {
            StatusCodeDTO responseStatus = orderService.updateOrderStatus(new UpdateOrderDTO("12345678", "P"));
        }
        catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains("Order is already finished"));
        }
    }

    @Test
    @DisplayName("Gets an exception when updating an order status for the order being cancelled")
    public void orderCancelledNotFoundException() throws Exception {
        OrderDTO order = GeneralTestUtils.getOrdersDTO().get(0);

        OrderCM orderCM = GeneralTestUtils.getOrdersCancelledCMDB().get(0);

        when(orderCMRepository.findOrderCMSByOrderNumber(any())).thenReturn(orderCM);

        try {
            StatusCodeDTO responseStatus = orderService.updateOrderStatus(new UpdateOrderDTO("12345678", "P"));
        }
        catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains("Order is already cancelled"));
        }
    }

    @Test
    @DisplayName("Order status already set")
    public void orderStatusAlreadyExistent() throws Exception {
        OrderCM orderCM = GeneralTestUtils.getOrdersCancelledCMDB().get(0);

        StatusCodeDTO responseStatus = orderService.updateSpecificOrder("C", orderCM);

        Assertions.assertEquals(400, responseStatus.getNumber());
        Assertions.assertEquals("Order status is already C", responseStatus.getMessage());
    }

    @Test
    @DisplayName("Change order status to cancelled")
    public void updateOrderStatusC() throws Exception {
        OrderCM orderCM = GeneralTestUtils.getOrdersCancelledCMDB().get(0);

        orderCM.getOrderStatus().setDescription("P");

        when(orderDetailRepository.save(any())).thenReturn(orderCM.getOrderDetail().get(0));

        StatusCodeDTO responseStatus = orderService.updateSpecificOrder("C", orderCM);

        Assertions.assertEquals(200, responseStatus.getNumber());
        Assertions.assertEquals("Order changed to C Successfully", responseStatus.getMessage());
    }

    @Test
    @DisplayName("Change order status to pending")
    public void updateOrderStatusP() throws Exception {
        OrderCM orderCM = GeneralTestUtils.getOrdersCancelledCMDB().get(0);

        when(orderDetailRepository.save(any())).thenReturn(orderCM.getOrderDetail().get(0));

        StatusCodeDTO responseStatus = orderService.updateSpecificOrder("P", orderCM);

        Assertions.assertEquals(200, responseStatus.getNumber());
        Assertions.assertEquals("Order changed to P Successfully", responseStatus.getMessage());
    }

    @Test
    @DisplayName("Change order status to delivered")
    public void updateOrderStatusD() throws Exception {
        OrderCM orderCM = GeneralTestUtils.getOrdersCancelledCMDB().get(0);

        when(orderDetailRepository.save(any())).thenReturn(orderCM.getOrderDetail().get(0));

        StatusCodeDTO responseStatus = orderService.updateSpecificOrder("D", orderCM);

        Assertions.assertEquals(200, responseStatus.getNumber());
        Assertions.assertEquals("Order changed to D Successfully", responseStatus.getMessage());
    }

    @Test
    @DisplayName("Change order status to finished")
    public void updateOrderStatusF() throws Exception {
        OrderCM orderCM = GeneralTestUtils.getOrdersCMDB().get(0);

        orderCM.getOrderStatus().setDescription("P");
        orderCM.setOrderDetail(new ArrayList<>());

        when(orderDetailRepository.save(any())).thenReturn(new OrderDetail());

        StatusCodeDTO responseStatus = orderService.updateSpecificOrder("F", orderCM);

        Assertions.assertEquals(200, responseStatus.getNumber());
        Assertions.assertEquals("Order changed to F Successfully", responseStatus.getMessage());
    }
}
