package com.mercadolibre.desafiofinaljosejimenez.unit.beans;

import com.mercadolibre.desafiofinaljosejimenez.controller.PartController;
import com.mercadolibre.desafiofinaljosejimenez.security.JwtTokenUtil;
import com.mercadolibre.desafiofinaljosejimenez.service.JwtUserDetailsService;
import com.mercadolibre.desafiofinaljosejimenez.service.PartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PartControllerTest {
    @MockBean
    private PartService partService;

    private JwtTokenUtil jwtTokenUtil;

    private JwtUserDetailsService jwtUserDetailsService;

    private PartController partController;

    @BeforeEach
    void setUp() {
        partController = new PartController(partService,jwtTokenUtil,jwtUserDetailsService);
    }

    @Test
    @DisplayName("Gets all the parts with no filters applied")
    public void getParts() throws Exception {
        /*List<> hotels = GeneralTestUtils.getHotels();

        when(hotelService.findHotelsByFilters(any(), any(), any())).thenReturn(hotels);

        ResponseEntity<List<HotelDTO>> responseHotels = hotelController.hotels(null, null, null);

        Assertions.assertEquals(hotels, responseHotels.getBody());*/
    }
}
