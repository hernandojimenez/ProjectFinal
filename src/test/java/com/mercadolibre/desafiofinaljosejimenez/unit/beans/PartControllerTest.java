package com.mercadolibre.desafiofinaljosejimenez.unit.beans;

import com.mercadolibre.desafiofinaljosejimenez.controller.PartController;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.PartResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.exceptions.InvalidFilterInformation;
import com.mercadolibre.desafiofinaljosejimenez.security.JwtTokenUtil;
import com.mercadolibre.desafiofinaljosejimenez.service.JwtUserDetailsService;
import com.mercadolibre.desafiofinaljosejimenez.service.PartService;
import com.mercadolibre.desafiofinaljosejimenez.util.GeneralTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PartControllerTest {
    @Mock
    private PartService partService;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private JwtUserDetailsService jwtUserDetailsService;

    @InjectMocks
    private PartController partController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        partController = new PartController(partService, jwtTokenUtil, jwtUserDetailsService);
    }

    @Test
    @DisplayName("Gets all the parts with no filters applied")
    public void getAllParts() throws Exception {
        List<PartResponseDTO> parts = GeneralTestUtils.getParts();

        when(partService.getParts(any())).thenReturn(parts);
        when(jwtTokenUtil.getUsernameFromToken(any())).thenReturn("User");
        when(jwtUserDetailsService.autorizado(any(), any(), any())).thenReturn(true);

        ResponseEntity<List<PartResponseDTO>> responseParts = (ResponseEntity<List<PartResponseDTO>>)partController.getParts(null, null);

        Assertions.assertEquals(parts, responseParts.getBody());
    }

    @Test
    @DisplayName("Gets all the parts with filters applied")
    public void getPartsWithFilters() throws Exception {
        List<PartResponseDTO> parts = GeneralTestUtils.getPartsWithFilters();

        when(partService.getParts(any())).thenReturn(parts);

        Map<String, String> filters = new HashMap<>();

        filters.put("querytype", "P");

        ResponseEntity<List<PartResponseDTO>> responseParts = (ResponseEntity<List<PartResponseDTO>>)partController.getParts(filters, null);

        Assertions.assertEquals(parts, responseParts.getBody());
    }

    @Test
    @DisplayName("Gets an exception from an incorrect DateFrom")
    public void getDateFromException() throws Exception {
        when(partService.getParts(any())).thenThrow(new InvalidFilterInformation("Invalid date format"));

        Map<String, String> filters = new HashMap<>();

        filters.put("querytype", "P");
        filters.put("date", "25/40/2021");

        try {
            partController.getParts(filters, null);
        }
        catch (InvalidFilterInformation e) {
            Assertions.assertTrue(e.getMessage().contains("Invalid date format"));
        }
    }
}
