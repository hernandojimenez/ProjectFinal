package com.mercadolibre.desafiofinaljosejimenez.controller;

import com.mercadolibre.desafiofinaljosejimenez.dtos.request.PartDTO;
import com.mercadolibre.desafiofinaljosejimenez.security.JwtTokenUtil;
import com.mercadolibre.desafiofinaljosejimenez.service.JwtUserDetailsService;
import com.mercadolibre.desafiofinaljosejimenez.service.PartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/parts")
public class PartController {

    private PartService partService;

    private JwtTokenUtil jwtTokenUtil;

    private JwtUserDetailsService jwtUserDetailsService;

    public PartController(PartService partService, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService jwtUserDetailsService) {
        this.partService = partService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getParts(@RequestParam Map<String, String> params, @RequestHeader("Authorization") String token) throws Exception {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        boolean res = jwtUserDetailsService.autorizado(username,params);
        return new ResponseEntity(partService.getParts(params), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> savePart(@RequestBody PartDTO part, @RequestHeader("Authorization") String token) throws Exception {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        boolean res = jwtUserDetailsService.autorizado(username, new HashMap<>());

        return new ResponseEntity(partService.savePart(part), HttpStatus.CREATED);
    }
}
