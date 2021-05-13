package com.mercadolibre.desafiofinaljosejimenez.dtos.response;

public class StatusCodeDTO {
    private int number;
    private String message;

    public StatusCodeDTO(int number, String message) {
        this.number = number;
        this.message = message;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
