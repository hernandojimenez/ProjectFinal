package com.mercadolibre.desafiofinaljosejimenez.exceptions;

import lombok.Getter;

@Getter
public class ErrorMessage {

    private final int status;
    private final String error;
    private final String message;

    public ErrorMessage(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

}
