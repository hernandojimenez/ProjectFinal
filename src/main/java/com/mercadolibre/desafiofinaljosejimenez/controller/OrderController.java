package com.mercadolibre.desafiofinaljosejimenez.controller;

import com.mercadolibre.desafiofinaljosejimenez.dtos.request.OrderDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.request.UpdateOrderDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderCMResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderDEResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.StatusCodeDTO;
import com.mercadolibre.desafiofinaljosejimenez.security.JwtTokenUtil;
import com.mercadolibre.desafiofinaljosejimenez.service.JwtUserDetailsService;
import com.mercadolibre.desafiofinaljosejimenez.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/parts")
public class OrderController {

    private OrderService orderService;

    private JwtTokenUtil jwtTokenUtil;

    private JwtUserDetailsService jwtUserDetailsService;

    public OrderController(OrderService orderService, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService jwtUserDetailsService) {
        this.orderService = orderService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getOrders(@RequestParam Map<String, String> params, @RequestParam(required = false) String orderNumber,  @RequestHeader("Authorization") String token) throws Exception {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        OrderCMResponseDTO ocm = new OrderCMResponseDTO();
        OrderDEResponseDTO ode = new OrderDEResponseDTO();
        if(orderNumber!=null){
            boolean res = jwtUserDetailsService.autorizado(username,params,orderNumber);
            ocm =orderService.getOrdersCM(orderNumber);
            return new ResponseEntity(ocm, HttpStatus.OK);
        }else{
            ode =orderService.getOrders(params);
            boolean res = jwtUserDetailsService.autorizado(username,params,"");
            return new ResponseEntity(ode, HttpStatus.OK);
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<?> saveOrder(@Valid @RequestBody OrderDTO order, @RequestHeader("Authorization") String token) throws Exception {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        boolean res = jwtUserDetailsService.autorizado(username, new HashMap<>(),"");

        return new ResponseEntity(orderService.saveOrder(order), HttpStatus.CREATED);
    }

    @PostMapping("/orders/update_status")
    public ResponseEntity<StatusCodeDTO> updateOrder(@RequestBody UpdateOrderDTO updateOrderDTO, @RequestHeader("Authorization") String token) throws Exception {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        boolean res = jwtUserDetailsService.autorizado(username, new HashMap<>(),"");

        return new ResponseEntity(orderService.updateOrder(updateOrderDTO), HttpStatus.CREATED);
    }


}
