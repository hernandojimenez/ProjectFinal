package com.mercadolibre.desafiofinaljosejimenez.util;

import com.mercadolibre.desafiofinaljosejimenez.exceptions.InvalidFilterInformation;

import java.util.Map;

public class Validator {
    public static boolean validFilters(Map<String, String> filters) {
        if (filters.size() ==0) return true;

        for (Map.Entry<String, String> entry : filters.entrySet()) {
            if(! (entry.getKey().equals("queryType")|| entry.getKey().equals("date")|| entry.getKey().equals("order") ) ) throw new InvalidFilterInformation("You´ve entered invalid Filters");
        }
        return validFilterLogic(filters.getOrDefault("queryType",""),filters.getOrDefault("date",""),filters.getOrDefault("order",""));
    }

    public static boolean validFilterLogic(String queryType, String date ,String order ) {

        //if query is empty
        if (queryType.equals("")) throw new InvalidFilterInformation("You did not enter any query type");

        // if query is C type
        if (queryType.equals("C")) return true;

        //if query is  P or V --> date must not be empty
        if ((queryType.equals("P") || queryType.equals("V")) && date.equals("")) throw new InvalidFilterInformation("You did not enter any date ");

        //Validating Date format
        if (!isValidFormatDate(date)) throw new InvalidFilterInformation("Invalid date format");

        //If order is not in query
        if (order.equals("")) return true;

        //If valid order
        if (!(order.equals("1") || order.equals("2"))) throw new InvalidFilterInformation("Invalid order number");

        return true;
    }

    public static boolean isValidFormatDate(String availableDate)  {
        String regex = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|[3][01])";
        return availableDate.matches(regex);
    }

}
