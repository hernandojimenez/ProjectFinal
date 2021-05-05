package com.mercadolibre.desafiofinaljosejimenez.controller;

import com.mercadolibre.desafiofinaljosejimenez.service.PartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/parts")
public class PartController {

    private PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getParts(@RequestParam Map<String, String> params) throws ParseException {
        return new ResponseEntity(partService.getParts(params), HttpStatus.OK);
    }
}
