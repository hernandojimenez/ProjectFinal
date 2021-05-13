package com.mercadolibre.desafiofinaljosejimenez.service;


import com.mercadolibre.desafiofinaljosejimenez.dtos.internal.PartQuantityDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.request.OrderDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.request.UpdateDeliveryDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.request.UpdateOrderDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.*;
import com.mercadolibre.desafiofinaljosejimenez.exceptions.OrderNumberException;
import com.mercadolibre.desafiofinaljosejimenez.mapper.OrderMapper;
import com.mercadolibre.desafiofinaljosejimenez.model.*;
import com.mercadolibre.desafiofinaljosejimenez.repositories.*;
import com.mercadolibre.desafiofinaljosejimenez.util.OrderSorterUtils;
import com.mercadolibre.desafiofinaljosejimenez.util.Validator;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final SubsidiaryRepository subsidiaryRepository;
    private final OrderCMRepository orderCMRepository;
    private final PartRepository partRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final StockCMRepository stockCMRepository;
    private final SubsidiaryStockRepository subsidiaryStockRepository;
    ModelMapper mapper;

    public OrderServiceImpl(OrderRepository orderRepository, SubsidiaryRepository subsidiaryRepository, OrderCMRepository orderCMRepository, PartRepository partRepository, OrderDetailRepository orderDetailRepository, StockCMRepository stockCMRepository, SubsidiaryStockRepository subsidiaryStockRepository) {
        this.orderRepository = orderRepository;
        this.subsidiaryRepository = subsidiaryRepository;
        this.orderCMRepository = orderCMRepository;
        this.partRepository = partRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.stockCMRepository = stockCMRepository;
        this.subsidiaryStockRepository = subsidiaryStockRepository;

    }


    @Override
    public OrderDEResponseDTO getOrders(Map<String, String> params) throws Exception {

        Validator.validFiltersOrders(params);

        List<OrderDE> dbOrders = OrderSorterUtils.getSortedList(params, orderRepository);

        List<OrderResponseDTO> result = dbOrders.stream().map(order -> {
            return OrderMapper.mapOrderToResponse(order);
        }).collect(Collectors.toList());

        return new OrderDEResponseDTO(params.get("dealerNumber"), result);
    }



    @Override
    @Transactional(rollbackOn = Exception.class)
    public StatusCodeDTO saveOrder(OrderDTO orderDTO) throws Exception {

        Subsidiary subsidiary = subsidiaryRepository.findBySubsidiaryNumber(orderDTO.getSubsidiaryNumber());
        OrderCM orderCMFromRepo = orderCMRepository.findOrderCMSByOrderNumber(orderDTO.getOrderNumber());
        if (subsidiary != null) {
            if(orderCMFromRepo == null) throw new OrderNumberException("Order Number " + orderDTO.getOrderNumber() + "already exists");

            OrderCM orderCM = new OrderCM();
            String orderNumber = orderDTO.getOrderNumber();
            orderCM.setOrderNumber(orderNumber);
            orderCM.setShippingType_id(1L);
            orderCM.setDeliveryStatus_id(1L);
            orderCM.setCarrier_id(1L);
            orderCM.setOrderStatus_id(2L);
            orderCM.setOrderType_id(1L);
            orderCM.setSubsidiary(subsidiary);
            orderCM = orderCMRepository.save(orderCM);

            for (PartQuantityDTO partCode : orderDTO.getParts()) {
                OrderDetail orderDetail = new OrderDetail();
                Part part = partRepository.findByPartCodeAndProvider_id(partCode.getPartCode(), 2L);
                if (part == null) throw new NotFoundException("Part not found");
                orderDetail.setAccountType_id(1L);
                orderDetail.setPartStatus_id(1L);
                orderDetail.setPart(part);
                orderDetail.setOrder(orderCM);
                orderDetail.setQuantity(partCode.getQuantity());
                orderDetail = orderDetailRepository.save(orderDetail);
            }
            return new StatusCodeDTO(200,"Order saved successfully");


        } else {
            throw new NotFoundException("Subsidiary not Found");
        }

    }

    //update order Status
    public StatusCodeDTO updateOrderStatus(UpdateOrderDTO updateOrderDTO) throws NotFoundException {
        //bring order cm with order number
        OrderCM orderCM = orderCMRepository.findOrderCMSByOrderNumber(updateOrderDTO.getOrderNumber());
        if (orderCM == null) throw new NotFoundException("Order not found");

        // if order is finished
        if (orderCM.getOrderStatus().getDescription().equals("F"))
            throw new NotFoundException("Order is already finished");

        // if order is cancelled
        if (orderCM.getOrderStatus().getDescription().equals("C"))
            throw new NotFoundException("Order is already cancelled");


        //change status
        return updateSpecificOrder(updateOrderDTO.getOrderStatus(), orderCM);

    }


    public StatusCodeDTO updateSpecificOrder(String updateOrderStatus, OrderCM orderCM) throws NotFoundException {

        if (orderCM.getOrderStatus().getDescription().equals(updateOrderStatus))
            return new StatusCodeDTO(400, "Order status is already " + updateOrderStatus);

        switch (updateOrderStatus) {
            case "C":
                orderCM.setOrderStatus_id(1L);
                orderCM.setDeliveryDate(new Date());
                break;
            case "P":
                orderCM.setOrderStatus_id(2L);
                break;
            case "D":
                orderCM.setOrderStatus_id(3L);
                break;
            case "F":
                updateFinishedOrder(orderCM);
                orderCM.setOrderStatus_id(4L);
                break;
        }

        OrderCM save = orderCMRepository.save(orderCM);
        return new StatusCodeDTO(200, "Order changed to " + updateOrderStatus + " Successfully");

    }

    @Transactional(rollbackOn = Exception.class)
    public void updateFinishedOrder(OrderCM orderCM) throws NotFoundException {
        List<OrderDetail> orderDetails = orderCM.getOrderDetail();
        for (OrderDetail order : orderDetails) {

            //check if stock is found
            StockCM stock = stockCMRepository.findByPart_id(order.getPart().getId());
            if (stock == null) throw new NotFoundException("No stock available for that part id");
            if (stock.getQuantity() < order.getQuantity())
                throw new NotFoundException("No stock available for this part");

            //update stock
            updateStockFinished(subsidiaryStockRepository.findByPart_id(order.getPart().getId()), order, stock, orderCM);

        }
    }

    @Transactional(rollbackOn = Exception.class)
    public void updateStockFinished(Optional<SubsidiaryStock> subsidiaryStock, OrderDetail orderDetail, StockCM stock, OrderCM orderCM) throws NotFoundException {
        if (!subsidiaryStock.isPresent()) {
            SubsidiaryStock subsidiaryStockModel = new SubsidiaryStock(orderDetail.getQuantity(), orderDetail.getPart(), orderCM.getSubsidiary());
            subsidiaryStockRepository.save(subsidiaryStockModel);
        } else {
            subsidiaryStock.get().setQuantity(subsidiaryStock.get().getQuantity() + orderDetail.getQuantity());
            subsidiaryStockRepository.save(subsidiaryStock.get());
        }
        stock.setQuantity(stock.getQuantity() - orderDetail.getQuantity());
        stockCMRepository.save(stock);
    }

    //update delivery Status
    public StatusCodeDTO updateDeliveryStatus(UpdateDeliveryDTO updateDeliveryDTO) throws NotFoundException {

        //bring order cm with order number
        OrderCM orderCM = orderCMRepository.findOrderCMSByOrderNumber(updateDeliveryDTO.getOrderNumber());
        if (orderCM == null) throw new NotFoundException("Order not found");

        // if deliver is finished
        if (orderCM.getDeliveryS().getDescription().equals("F"))
            throw new NotFoundException("Deliver is already finished");

        // if deliver is cancelled
        if (orderCM.getDeliveryS().getDescription().equals("C"))
            throw new NotFoundException("Deliver is already cancelled");

        //change status
        return updateSpecificDelivery(updateDeliveryDTO.getDeliveryStatus(), orderCM);

    }

    public OrderCMResponseDTO getOrdersCM(String orderNumber) {
        String[] parts = orderNumber.split("-");
        List<OrderCM> dbOrders = orderCMRepository.findBySubsidiaryAndDealerAndOrderNumberAscending(parts[0], parts[1], parts[2]);
        List<OrderResponseCMDTO> result = dbOrders.stream().map(order -> {
            return OrderMapper.mapOrderToResponseCM(order, parts[1]);
        }).collect(Collectors.toList());
        return new OrderCMResponseDTO(result);
    }

    public StatusCodeDTO updateSpecificDelivery(String updateOrderStatus, OrderCM orderCM) throws NotFoundException {

        if (orderCM.getOrderStatus().getDescription().equals(updateOrderStatus))
            return new StatusCodeDTO(400, "Order status is already " + updateOrderStatus);

        switch (updateOrderStatus) {
            case "P":
                orderCM.setOrderStatus_id(1L);

            case "D":
                orderCM.setOrderStatus_id(2L);

            case "F":
                orderCM.setOrderStatus_id(3L);

            case "C":
                orderCM.setDeliveryStatus_id(4L);
        }

        OrderCM save = orderCMRepository.save(orderCM);
        return new StatusCodeDTO(200, "Order changed to " + updateOrderStatus + " Successfully");

    }


}
