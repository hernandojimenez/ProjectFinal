package com.mercadolibre.desafiofinaljosejimenez.service;

import com.mercadolibre.desafiofinaljosejimenez.dtos.request.OrderDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderDEResponseDTO;

import java.util.List;
import java.util.Map;

public interface OrderService {

  OrderDEResponseDTO getOrders(Map<String, String> params) throws Exception;

  String saveOrder(OrderDTO orderDTO) throws Exception;
}
