package com.mercadolibre.desafiofinaljosejimenez.unit.beans;

import com.mercadolibre.desafiofinaljosejimenez.exceptions.InvalidFilterInformation;
import com.mercadolibre.desafiofinaljosejimenez.util.DateUtils;
import com.mercadolibre.desafiofinaljosejimenez.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Date;

public class DateUtilsTest {
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Validates date")
    public void validateDate() {
        Date date = DateUtils.getDateFromString("2021-08-08");

        Assertions.assertEquals("Sun Aug 08 00:00:00 ART 2021", date.toString());
    }

    @Test
    @DisplayName("Throws exception because of not valid filters")
    public void validateNotValidFiltersException() {
        Date date = DateUtils.getDateFromString("2021-18-48");

        Assertions.assertEquals(null, date);
    }
}
