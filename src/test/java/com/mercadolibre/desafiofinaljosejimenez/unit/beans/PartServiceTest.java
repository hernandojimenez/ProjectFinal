package com.mercadolibre.desafiofinaljosejimenez.unit.beans;

import com.mercadolibre.desafiofinaljosejimenez.controller.PartController;
import com.mercadolibre.desafiofinaljosejimenez.repositories.PartRepository;
import com.mercadolibre.desafiofinaljosejimenez.service.PartService;
import com.mercadolibre.desafiofinaljosejimenez.service.PartServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PartServiceTest {
    @Mock
    private PartRepository partRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PartService partService;

    @BeforeEach
    void setUp() {
        partService = new PartServiceImpl(partRepository, modelMapper);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Gets all the hotels with no filters applied")
    public void getAllHotels() throws Exception {
        List<HotelDTO> hotels = GeneralTestUtils.getHotels();

        when(hotelRepository.findHotels(any())).thenReturn(hotels);

        List<HotelDTO> responseHotels = hotelService.findHotelsByFilters(null, null, null);

        Assertions.assertEquals(hotels, responseHotels);
    }
}
