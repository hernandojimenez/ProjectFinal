package com.mercadolibre.desafiofinaljosejimenez.service;

import com.mercadolibre.desafiofinaljosejimenez.dtos.request.OrderDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.request.UpdateDeliveryDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.request.UpdateOrderDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderCMResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderDEResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.StatusCodeDTO;
import javassist.NotFoundException;

import java.util.Map;

public interface OrderService {

  OrderDEResponseDTO getOrders(Map<String, String> params) throws Exception;

  StatusCodeDTO saveOrder(OrderDTO orderDTO) throws Exception;
  OrderCMResponseDTO getOrdersCM(String orderNumber);
  StatusCodeDTO updateOrderStatus(UpdateOrderDTO updateOrderDTO) throws NotFoundException;

  StatusCodeDTO updateDeliveryStatus(UpdateDeliveryDTO updateDeliveryDTO) throws NotFoundException;
}
