package com.mercadolibre.desafiofinaljosejimenez.dtos.request;

public class UserRequestDTO {

    private Long central;
    private Long central2;
    private String username;
    private String password;

    public Long getCentral() {
        return central;
    }

    public void setCentral(Long central) {
        this.central = central;
    }

    public Long getCentral2() {
        return central2;
    }

    public void setCentral2(Long central2) {
        this.central2 = central2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
