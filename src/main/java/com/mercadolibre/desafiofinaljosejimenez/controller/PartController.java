package com.mercadolibre.desafiofinaljosejimenez.controller;

import com.mercadolibre.desafiofinaljosejimenez.service.PartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/parts")
public class PartController {

    private PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getParts(){
        return new ResponseEntity(partService.getParts(), HttpStatus.OK);
    }
}
