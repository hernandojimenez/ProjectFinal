package com.mercadolibre.desafiofinaljosejimenez.controller;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderDEResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.security.JwtTokenUtil;
import com.mercadolibre.desafiofinaljosejimenez.service.JwtUserDetailsService;
import com.mercadolibre.desafiofinaljosejimenez.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<OrderDEResponseDTO> getParts(@RequestParam Map<String, String> params,@RequestHeader("Authorization") String token) throws Exception {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        boolean res = jwtUserDetailsService.autorizado(username,params);
        OrderDEResponseDTO ode = orderService.getOrders(params);
        return new ResponseEntity(ode, HttpStatus.OK);
    }
}
