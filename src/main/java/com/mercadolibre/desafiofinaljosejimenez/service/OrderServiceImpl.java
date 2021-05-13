package com.mercadolibre.desafiofinaljosejimenez.service;


import com.mercadolibre.desafiofinaljosejimenez.dtos.internal.PartQuantityDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.request.OrderDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.request.UpdateOrderDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.*;
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
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final SubsidiaryRepository subsidiaryRepository;
    private final OrderCMRepository orderCMRepository;
    private final PartRepository partRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final StockCMRepository stockCMRepository;
    ModelMapper mapper;

    public OrderServiceImpl(OrderRepository orderRepository, SubsidiaryRepository subsidiaryRepository, OrderCMRepository orderCMRepository, PartRepository partRepository,OrderDetailRepository orderDetailRepository, StockCMRepository stockCMRepository) {
        this.orderRepository = orderRepository;
        this.subsidiaryRepository = subsidiaryRepository;
        this.orderCMRepository = orderCMRepository;
        this.partRepository = partRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.stockCMRepository = stockCMRepository;
    }


    @Override
    public OrderDEResponseDTO getOrders(Map<String, String> params) throws Exception {

        Validator.validFiltersOrders(params);

        List<OrderDE> dbOrders  = OrderSorterUtils.getSortedList(params, orderRepository);

        List<OrderResponseDTO> result = dbOrders.stream().map(order -> { return OrderMapper.mapOrderToResponse(order); }).collect(Collectors.toList());

        return new OrderDEResponseDTO(params.get("dealerNumber"),result);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public String saveOrder(OrderDTO orderDTO) throws Exception {

        Optional<Subsidiary> subsidiary = subsidiaryRepository.findBySubsidiaryNumber(orderDTO.getSubsidiaryNumber());

        if (subsidiary.isPresent()) {
            OrderCM orderCM = new OrderCM();
            String subsidiaryNumber = subsidiary.get().getSubsidiaryNumber();
            String orderNumber = orderDTO.getOrderNumber();
            orderCM.setOrderNumber(orderNumber);
            orderCM.setShippingType_id(1L);
            orderCM.setDeliveryStatus_id(1L);
            orderCM.setCarrier_id(1L);
            orderCM.setOrderStatus_id(1L);
            orderCM.setOrderType_id(1L);
            orderCM.setSubsidiary(subsidiary.get());
            orderCM = orderCMRepository.save(orderCM);

            for (PartQuantityDTO partCode: orderDTO.getParts()) {
                OrderDetail orderDetail = new OrderDetail();
                Part part = partRepository.findByPartCodeAndProvider_id(partCode.getPartCode(),2L);
                if ( part == null) throw new NotFoundException("Part not found");
                orderDetail.setAccountType_id(1L);
                orderDetail.setPartStatus_id(1L);
                orderDetail.setPart(part);
                orderDetail.setOrder(orderCM);
                orderDetail.setQuantity(partCode.getQuantity());
                orderDetail= orderDetailRepository.save(orderDetail);
            }
            return "Order saved";
        } else {
            throw new NotFoundException("Subsidiary not Found");
        }
    }

    public OrderCMResponseDTO getOrdersCM(String orderNumber){
        String[] parts = orderNumber.split("-");
        List<OrderCM> dbOrders = orderCMRepository.findByDealerAndStatusAscending(parts[0], parts[1], parts[2]);
        List<OrderResponseCMDTO> result = dbOrders.stream().map(order -> {
        return OrderMapper.mapOrderToResponseCM(order, parts[1]);
            }).collect(Collectors.toList());
        return new OrderCMResponseDTO(result);
        }

    public StatusCodeDTO updateOrder(UpdateOrderDTO updateOrderDTO) throws NotFoundException {
        //bring order cm with order number
        Optional<OrderCM> orderCM = orderCMRepository.findOrderCMSByOrderNumber(updateOrderDTO.getOrderNumber());
        if (!orderCM.isPresent()) throw new NotFoundException("Order not found");
        // if order is finished
        if(orderCM.get().getOrderStatus().getDescription().equals("F")) throw new NotFoundException("Order is already finished");
        // if order is cancelled
        if(orderCM.get().getOrderStatus().getDescription().equals("C")) throw new NotFoundException("Order is already cancelled");
        //get order details
        List<OrderDetail> orderDetails = orderDetailRepository.findOrderDetailByOrder(orderCM.get().getId());
        if(orderDetails.size() == 0) throw new NotFoundException("Order has no details");

        //change to cancelled status
        if(updateOrderDTO.getOrderStatus().equals( "C")) {
            orderCM.get().setOrderStatus_id(1L);
            OrderCM save = orderCMRepository.save(orderCM.get());
            return new StatusCodeDTO(200, "Order changed to Cancelled Successfully");
        }
        //change to delayed status
        if(updateOrderDTO.getOrderStatus().equals( "D")) {
            if(orderCM.get().getOrderStatus().getDescription().equals( "D")) throw new NotFoundException("Order is already delayed");
            orderCM.get().setOrderStatus_id(3L);
            OrderCM save = orderCMRepository.save(orderCM.get());
            return new StatusCodeDTO(200, "Order changed to Delayed Successfully");
        }
        //change to pending status
        if(updateOrderDTO.getOrderStatus().equals( "P" )) {
            if(orderCM.get().getOrderStatus().getDescription().equals("P")) throw new NotFoundException("Order is already pending");
            orderCM.get().setOrderStatus_id(2L);
            OrderCM save = orderCMRepository.save(orderCM.get());
            return new StatusCodeDTO(200, "Order changed to Pending Successfully");
        }

        //change to finished status
        if(updateOrderDTO.getOrderStatus().equals( "F" )) {
            List<OrderDetail> orders = orderCM.get().getOrderDetail();

            for (OrderDetail order : orders) {

                Optional<StockCM> stock = stockCMRepository.findByPart_id(order.getPart().getId());
                if(!stock.isPresent()) throw new NotFoundException("No stock available for that part id");
                if (stock.get().getQuantity() < order.getQuantity()) throw new NotFoundException("No stock available for this part");
                stock.get().setQuantity(stock.get().getQuantity() - order.getQuantity());
                stockCMRepository.save(stock.get());
            }
            orderCM.get().setOrderStatus_id(4L);
            OrderCM save = orderCMRepository.save(orderCM.get());
            return new StatusCodeDTO(200, "Order changed to F Successfully");
        }

        return new StatusCodeDTO(404, "No matching order Status type");

    }




}
