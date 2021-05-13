package com.mercadolibre.desafiofinaljosejimenez.util;

import com.mercadolibre.desafiofinaljosejimenez.dtos.internal.PartQuantityDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.request.OrderDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.request.PartDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.request.UpdateDeliveryDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.request.UpdateOrderDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.*;
import com.mercadolibre.desafiofinaljosejimenez.model.*;
import org.aspectj.weaver.ast.Or;

import java.util.*;

public class GeneralTestUtils {
    public static List<PartResponseDTO> getParts() {
        List<PartResponseDTO> parts = new ArrayList<PartResponseDTO>();

        parts.add(new PartResponseDTO(12345678, "Motor V8", "Ford" , 4 , "A20", 2000, 2500, 60, 80, 80, 80, "2021-01-01"));
        parts.add(new PartResponseDTO(87654321, "Caja de cambios", "Fiat" , 48 , "A25", 1000, 1300, 20, 40, 30, 50, "2021-02-02"));
        parts.add(new PartResponseDTO(11223344, "Llantas", "Chevrolet" , 80 , "A20", 800, 1000, 50, 100, 100, 20, "2021-03-03"));

        return parts;
    }

    public static List<PartResponseDTO> getPartsWithFilters() {
        List<PartResponseDTO> parts = new ArrayList<PartResponseDTO>();

        parts.add(new PartResponseDTO(12345678, "Motor V8", "Ford" , 4 , "A20", 2000, 2500, 60, 80, 80, 80, "2021-01-01"));
        parts.add(new PartResponseDTO(11223344, "Llantas", "Chevrolet" , 80 , "A20", 800, 1000, 50, 100, 100, 20, "2021-03-03"));

        return parts;
    }

    public static List<Part> getPartsDB() {
        List<Part> parts = new ArrayList<Part>();

        Set<PartRecord> partRecordSet1 = new HashSet<>();
        partRecordSet1.add(new PartRecord(1L, new Date(), 2000, 1500, 2500, "normal_price", new DiscountRate(1L, "A20", 0.25)));

        Set<PartRecord> partRecordSet2 = new HashSet<>();
        partRecordSet2.add(new PartRecord(2L, new Date(), 1000, 800, 1300, "normal_price", new DiscountRate(1L, "A25", 0.25)));

        Set<PartRecord> partRecordSet3 = new HashSet<>();
        partRecordSet3.add(new PartRecord(3L, new Date(), 800, 700, 100, "normal_price", new DiscountRate(1L, "A20", 0.25)));

        parts.add(new Part(1L, 12345678, "Motor V8", 80, 80, 80, 60, new StockCM(4, 1L, 1L), new Provider(1L, "Ford"), partRecordSet1));
        parts.add(new Part(2L, 87654321, "Caja de cambios", 30, 50, 40, 20, new StockCM(48, 1L, 1L), new Provider(1L, "Fiat"), partRecordSet2));
        parts.add(new Part(3L, 11223344, "Llantas", 100, 20, 100, 50, new StockCM(80, 1L, 1L), new Provider(1L, "Chevrolet"), partRecordSet3));

        return parts;
    }

    public static List<PartDTO> getPartsDTO() {
        List<PartDTO> parts = new ArrayList<>();

        parts.add(new PartDTO(12345678, "Motor V8", 80, 80, 80, 60, 4, "Buenos Aires"));
        parts.add(new PartDTO(10000010, "Caja de cambios", 30, 50, 40, 20, 48, "Buenos Aires"));
        parts.add(new PartDTO(11223344, "Llantas", 100, 20, 100, 50, 80, "Buenos Aires"));

        return parts;
    }

    public static List<OrderResponseDTO> getOrdersDE() {
        List<OrderResponseDTO> orders = new ArrayList<OrderResponseDTO>();

        List<OrderDetailDTO> orderDetails = new ArrayList<>();

        orderDetails.add(new OrderDetailDTO(12345678, "desc", 3, "R", "P"));

        orders.add(new OrderResponseDTO("11232323", new Date(), new Date(), 4, "P", orderDetails));
        orders.add(new OrderResponseDTO("87654321", new Date(), new Date(), 5, "D", orderDetails));
        orders.add(new OrderResponseDTO("11223344", new Date(), new Date(), 6, "F", orderDetails));

        return orders;
    }

    public static OrderDEResponseDTO getOrderDEResponse() {
        List<OrderResponseDTO> orders = new ArrayList<OrderResponseDTO>();

        List<OrderDetailDTO> orderDetails = new ArrayList<>();

        orderDetails.add(new OrderDetailDTO(12345678, "desc", 3, "R", "P"));

        orders.add(new OrderResponseDTO("11232323", new Date(), new Date(), 4, "P", orderDetails));
        orders.add(new OrderResponseDTO("87654321", new Date(), new Date(), 5, "D", orderDetails));
        orders.add(new OrderResponseDTO("11223344", new Date(), new Date(), 6, "F", orderDetails));

        return new OrderDEResponseDTO("1234", orders);
    }

    public static OrderDEResponseDTO getOrderDEResponseWithFilters() {
        List<OrderResponseDTO> orders = new ArrayList<OrderResponseDTO>();

        List<OrderDetailDTO> orderDetails = new ArrayList<>();

        orderDetails.add(new OrderDetailDTO(12345678, "desc", 3, "R", "P"));

        orders.add(new OrderResponseDTO("11232323", new Date(), new Date(), 4, "P", orderDetails));

        return new OrderDEResponseDTO("1234", orders);
    }

    public static List<OrderDE> getOrdersDEDB() {
        List<OrderDE> orders = new ArrayList<>();
        List<OrderDetailDE> orderDetails = new ArrayList<>();

        List<Part> parts = getPartsDB();

        OrderDetailDE orderDetailDE = new OrderDetailDE(1L, parts.get(0), new AccountType(1L, "G"), 5);

        orderDetails.add(orderDetailDE);

        orderDetailDE = new OrderDetailDE(2L, parts.get(1), new AccountType(2L, "R"), 2);

        orderDetails.add(orderDetailDE);

        orderDetailDE = new OrderDetailDE(3L, parts.get(2), new AccountType(1L, "G"), 3);

        orderDetails.add(orderDetailDE);

        Subsidiary subsidiary = new Subsidiary("1234");

        orders.add(new OrderDE(11232323L, "11232323", new Date(), new Date(), new DeliveryStatus(1L, 4000, "C"), new OrderStatus(1L, 1000, "estado 1"), new Dealer("1234", subsidiary), orderDetails));
        orders.add(new OrderDE(87654321L, "87654321", new Date(), new Date(), new DeliveryStatus(2L, 4001, "P"), new OrderStatus(2L, 1001, "estado 2"), new Dealer("4321", subsidiary), orderDetails));
        orders.add(new OrderDE(11223344L, "11223344", new Date(), new Date(), new DeliveryStatus(3L, 4002, "D"), new OrderStatus(3L, 1002, "estado 3"), new Dealer("1122", subsidiary), orderDetails));

        return orders;
    }

    public static OrderCMResponseDTO getOrderCMResponse() {
        List<OrderDetailCMDTO> orderDetails = new ArrayList<>();

        orderDetails.add(new OrderDetailCMDTO(12345678, "Motor V8", 5, "R", "Compra", "Estado 1"));
        orderDetails.add(new OrderDetailCMDTO(87654321, "Caja de cambios", 20, "G", "Compra", "Estado 2"));
        orderDetails.add(new OrderDetailCMDTO(11223344, "Llantas", 35, "R", "Compra", "Estado 3"));

        List<OrderResponseCMDTO> list = new ArrayList<>();

        list.add(new OrderResponseCMDTO("12345678", new Date(), "", orderDetails));

        OrderCMResponseDTO order = new OrderCMResponseDTO(list);

        return order;
    }

    public static List<OrderCM> getOrdersCMDB() {
        List<OrderCM> orders = new ArrayList<>();
        List<OrderDetail> orderDetails = new ArrayList<>();

        List<Part> parts = getPartsDB();

        AccountType accountType = new AccountType(1L, "R");
        PartStatus partStatus = new PartStatus(1L, 3000, "Estado");

        OrderDetail orderDetail = new OrderDetail(1L, parts.get(0), accountType, partStatus, 5);

        orderDetails.add(orderDetail);

        orderDetail = new OrderDetail(2L, parts.get(1), accountType, partStatus, 15);

        orderDetails.add(orderDetail);

        orderDetail = new OrderDetail(3L, parts.get(2), accountType, partStatus, 20);

        orderDetails.add(orderDetail);

        OrderStatus orderStatus = new OrderStatus(1L, 1000, "F");

        orders.add(new OrderCM(1L, "12345678", new Date(), new Date(), orderStatus, orderDetails));
        orders.add(new OrderCM(2L, "87654321", new Date(), new Date(), orderStatus, orderDetails));
        orders.add(new OrderCM(3L, "11223344", new Date(), new Date(), orderStatus, orderDetails));

        return orders;
    }

    public static List<OrderCM> getOrdersCancelledCMDB() {
        List<OrderCM> orders = new ArrayList<>();
        List<OrderDetail> orderDetails = new ArrayList<>();

        List<Part> parts = getPartsDB();

        AccountType accountType = new AccountType(1L, "R");
        PartStatus partStatus = new PartStatus(1L, 3000, "Estado");

        OrderDetail orderDetail = new OrderDetail(1L, parts.get(0), accountType, partStatus, 5);

        orderDetails.add(orderDetail);

        orderDetail = new OrderDetail(2L, parts.get(1), accountType, partStatus, 15);

        orderDetails.add(orderDetail);

        orderDetail = new OrderDetail(3L, parts.get(2), accountType, partStatus, 20);

        orderDetails.add(orderDetail);

        OrderStatus orderStatus = new OrderStatus(1L, 1000, "C");

        orders.add(new OrderCM(1L, "12345678", new Date(), new Date(), orderStatus, orderDetails));
        orders.add(new OrderCM(2L, "87654321", new Date(), new Date(), orderStatus, orderDetails));
        orders.add(new OrderCM(3L, "11223344", new Date(), new Date(), orderStatus, orderDetails));

        return orders;
    }

    public static List<OrderDTO> getOrdersDTO() {
        List<OrderDTO> orders = new ArrayList<>();

        List<PartQuantityDTO> parts = new ArrayList<>();

        parts.add(new PartQuantityDTO(12345678, 5));
        parts.add(new PartQuantityDTO(87654321, 3));
        parts.add(new PartQuantityDTO(11223344, 7));

        orders.add(new OrderDTO("1234", "12345678", parts));

        return orders;
    }

    public static List<UpdateOrderDTO> getUpdateOrdersDTO() {
        List<UpdateOrderDTO> orders = new ArrayList<>();

        orders.add(new UpdateOrderDTO("12345678", "P"));
        orders.add(new UpdateOrderDTO("12345678", "C"));
        orders.add(new UpdateOrderDTO("12345678", "F"));
        orders.add(new UpdateOrderDTO("12345678", "D"));

        return orders;
    }

    public static List<UpdateDeliveryDTO> getUpdateDeliveriesDTO() {
        List<UpdateDeliveryDTO> deliveries = new ArrayList<>();

        deliveries.add(new UpdateDeliveryDTO("12345678", "P"));
        deliveries.add(new UpdateDeliveryDTO("12345678", "C"));
        deliveries.add(new UpdateDeliveryDTO("12345678", "F"));
        deliveries.add(new UpdateDeliveryDTO("12345678", "D"));

        return deliveries;
    }

    public static List<Subsidiary> getSubsidiaries() {
        List<Subsidiary> subsidiaries = new ArrayList<>();

        subsidiaries.add(new Subsidiary(1L, "1234", "Sub1", "Corrientes", "1144554433", "Argentina", 10));
        subsidiaries.add(new Subsidiary(2L, "4321", "Sub2", "Callao", "1122554433", "Argentina", 5));
        subsidiaries.add(new Subsidiary(3L, "1122", "Sub3", "Independencia", "1133554433", "Argentina", 2));

        return subsidiaries;
    }
}
