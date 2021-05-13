package com.mercadolibre.desafiofinaljosejimenez.unit.beans;

import com.mercadolibre.desafiofinaljosejimenez.exceptions.InvalidFilterInformation;
import com.mercadolibre.desafiofinaljosejimenez.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

public class ValidatorTest {
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Validates query with no filters applied")
    public void validateNoFilters() {
        Map<String, String> params = new HashMap<>();

        boolean response = Validator.validFilters(params);

        Assertions.assertTrue(response);
    }

    @Test
    @DisplayName("Throws exception because of not valid filters")
    public void validateNotValidFiltersException() {
        Map<String, String> params = new HashMap<>();

        params.put("madeUpParam", "12345678");

        try {
            boolean response = Validator.validFilters(params);
        }
        catch (InvalidFilterInformation e) {
            Assertions.assertTrue(e.getMessage().contains("You´ve entered invalid Filters"));
        }
    }

    @Test
    @DisplayName("Throws exception because of empty query type")
    public void validateEmptyQueryTypeException() {
        Map<String, String> params = new HashMap<>();

        params.put("queryType", "");

        try {
            boolean response = Validator.validFilters(params);
        }
        catch (InvalidFilterInformation e) {
            Assertions.assertTrue(e.getMessage().contains("You did not enter any query type"));
        }
    }

    @Test
    @DisplayName("Validates query with a query type 'C'")
    public void validateQueryTypeCFilter() throws Exception {
        Map<String, String> params = new HashMap<>();

        params.put("queryType", "C");

        boolean response = Validator.validFilters(params);

        Assertions.assertTrue(response);
    }

    @Test
    @DisplayName("Throws exception because of query type being V and having no date parameter")
    public void validateQueryTypePNoDateException() {
        Map<String, String> params = new HashMap<>();

        params.put("queryType", "V");

        try {
            boolean response = Validator.validFilters(params);
        }
        catch (InvalidFilterInformation e) {
            Assertions.assertTrue(e.getMessage().contains("You did not enter any date"));
        }
    }

    @Test
    @DisplayName("Throws exception because of date format being invalid")
    public void validateDateFormatException() {
        Map<String, String> params = new HashMap<>();

        params.put("queryType", "V");
        params.put("date", "08/09/2021");

        try {
            boolean response = Validator.validFilters(params);
        }
        catch (InvalidFilterInformation e) {
            Assertions.assertTrue(e.getMessage().contains("Invalid date format"));
        }
    }

    @Test
    @DisplayName("Invalid password format")
    public void validatePasswordFormat() {
        boolean response = Validator.isValidPass("password");

        Assertions.assertFalse(response);
    }

    @Test
    @DisplayName("Validates query with a query type 'V'")
    public void validateQueryTypeVFilter() {
        Map<String, String> params = new HashMap<>();

        params.put("queryType", "V");
        params.put("date", "2021-09-08");

        boolean response = Validator.validFilters(params);

        Assertions.assertTrue(response);
    }

    @Test
    @DisplayName("Throws exception because of order being invalid")
    public void validateOrderException() {
        Map<String, String> params = new HashMap<>();

        params.put("queryType", "V");
        params.put("date", "2021-09-08");
        params.put("order", "3");

        try {
            boolean response = Validator.validFilters(params);
        }
        catch (InvalidFilterInformation e) {
            Assertions.assertTrue(e.getMessage().contains("Invalid order number"));
        }
    }

    @Test
    @DisplayName("Validates query with no filters applied")
    public void validateOrderNoFiltersException() {
        Map<String, String> params = new HashMap<>();

        try {
            boolean response = Validator.validFiltersOrders(params);
        }
        catch (InvalidFilterInformation e) {
            Assertions.assertTrue(e.getMessage().contains("You did not enter any filter"));
        }
    }

    @Test
    @DisplayName("Throws exception because of not valid filters")
    public void validateNotValidFiltersOrderException() {
        Map<String, String> params = new HashMap<>();

        params.put("madeUpParam", "12345678");

        try {
            boolean response = Validator.validFiltersOrders(params);
        }
        catch (InvalidFilterInformation e) {
            Assertions.assertTrue(e.getMessage().contains("You´ve entered invalid Filters"));
        }
    }

    @Test
    @DisplayName("Throws exception because of empty dealer number")
    public void validateEmptyDealerNumberOrderException() {
        Map<String, String> params = new HashMap<>();

        params.put("dealerNumber", "");

        try {
            boolean response = Validator.validFiltersOrders(params);
        }
        catch (InvalidFilterInformation e) {
            Assertions.assertTrue(e.getMessage().contains("You did not enter any dealer number"));
        }
    }

    @Test
    @DisplayName("Throws exception because dealer number's length is not 4")
    public void validateDealerNumberLengthException() throws Exception {
        Map<String, String> params = new HashMap<>();

        params.put("dealerNumber", "123");

        try {
            boolean response = Validator.validFiltersOrders(params);
        }
        catch (InvalidFilterInformation e) {
            Assertions.assertTrue(e.getMessage().contains("Dealer number must have 4 digits"));
        }
    }

    @Test
    @DisplayName("Throws exception because dealer number is not a number")
    public void validateDealerNumberNotNumericException() throws Exception {
        Map<String, String> params = new HashMap<>();

        params.put("dealerNumber", "asde");

        try {
            boolean response = Validator.validFiltersOrders(params);
        }
        catch (InvalidFilterInformation e) {
            Assertions.assertTrue(e.getMessage().contains("Dealer number must have all numeric characters"));
        }
    }

    @Test
    @DisplayName("Throws exception because of delivery status being invalid")
    public void validateDeliveryStatusException() {
        Map<String, String> params = new HashMap<>();

        params.put("dealerNumber", "1234");
        params.put("deliveryStatus", "A");

        try {
            boolean response = Validator.validFiltersOrders(params);
        }
        catch (InvalidFilterInformation e) {
            Assertions.assertTrue(e.getMessage().contains("Invalid Type of delivery status"));
        }
    }

    @Test
    @DisplayName("Throws exception because of order being invalid")
    public void validateOrderOrdersException() {
        Map<String, String> params = new HashMap<>();

        params.put("dealerNumber", "1234");
        params.put("order", "3");

        try {
            boolean response = Validator.validFiltersOrders(params);
        }
        catch (InvalidFilterInformation e) {
            Assertions.assertTrue(e.getMessage().contains("Invalid order number"));
        }
    }
}
