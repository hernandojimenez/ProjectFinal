package com.mercadolibre.desafiofinaljosejimenez.util;

import com.mercadolibre.desafiofinaljosejimenez.dtos.request.PartDTO;
import com.mercadolibre.desafiofinaljosejimenez.exceptions.InvalidFilterInformation;

import java.util.Map;

public class Validator {
    public static boolean validFilters(Map<String, String> filters) {
        if (filters.size() == 0) return true;

        for (Map.Entry<String, String> entry : filters.entrySet()) {
            if(!(entry.getKey().equals("queryType") || entry.getKey().equals("date") || entry.getKey().equals("order"))) throw new InvalidFilterInformation("You´ve entered invalid Filters");
        }

        return validFilterLogic(filters.getOrDefault("queryType",""),filters.getOrDefault("date",""),filters.getOrDefault("order",""));
    }

    public static boolean validFilterLogic(String queryType, String date, String order ) {

        //if query is empty
        if (queryType.equals("")) throw new InvalidFilterInformation("You did not enter any query type");

        // if query is C type
        if (queryType.equals("C")) return true;

        //if query is  P or V --> date must not be empty
        if ((queryType.equals("P") || queryType.equals("V")) && date.equals("")) throw new InvalidFilterInformation("You did not enter any date");

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
    public static boolean isValidPass(String pass)  {
        String regex = "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$";
        return pass.matches(regex);
    }


    public static boolean validFiltersOrders(Map<String, String> filters) {
       if (filters.size() == 0)  throw new InvalidFilterInformation("You did not enter any filter");

        for (Map.Entry<String, String> entry : filters.entrySet()) {
            if(!(entry.getKey().equals("dealerNumber") || entry.getKey().equals("deliveryStatus") || entry.getKey().equals("order"))) throw new InvalidFilterInformation("You´ve entered invalid Filters");
        }

        return validFilterLogicOrders(filters.getOrDefault("dealerNumber",""),filters.getOrDefault("deliveryStatus",""),filters.getOrDefault("order",""));
    }

    private static boolean validFilterLogicOrders(String dealerNumber, String deliveryStatus, String order) {

        //if dealer number is empty
        if (dealerNumber.equals("")) throw new InvalidFilterInformation("You did not enter any dealer number");

        // if dealer number is not 4 digits long
        if (dealerNumber.length() != 4 ) throw new InvalidFilterInformation("Dealer number must have 4 digits");

        // if dealer number is not 4 digits long
        if (!isNumeric(dealerNumber)) throw new InvalidFilterInformation("Dealer number must have all numeric characters");

        //Valid type of delivery Status
        if (!deliveryStatus.equals("") && !((deliveryStatus.equals("P") || deliveryStatus.equals("D")) || deliveryStatus.equals("F") || deliveryStatus.equals("C"))) throw new InvalidFilterInformation("Invalid Type of delivery status");

        //If order is not in query
        if (order.equals("")) return true;

        //If valid order
        if (!(order.equals("1") || order.equals("2"))) throw new InvalidFilterInformation("Invalid order number");

        return true;
    }

    public static boolean validPartDTO(PartDTO part) {

        return true;
    }

    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        }
        catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }
}
