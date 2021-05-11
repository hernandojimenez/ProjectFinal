package com.mercadolibre.desafiofinaljosejimenez.dtos.response;

public class StatusCodeDTO {
    private int number;
    private String message;

    public StatusCodeDTO(int number, String message) {
        this.number = number;
        this.message = message;
    }
}
