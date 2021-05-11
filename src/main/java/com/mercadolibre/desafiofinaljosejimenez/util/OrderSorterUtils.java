package com.mercadolibre.desafiofinaljosejimenez.util;

import com.mercadolibre.desafiofinaljosejimenez.exceptions.InvalidFilterInformation;
import com.mercadolibre.desafiofinaljosejimenez.exceptions.NotFoundException;
import com.mercadolibre.desafiofinaljosejimenez.model.OrderDE;
import com.mercadolibre.desafiofinaljosejimenez.model.Part;
import com.mercadolibre.desafiofinaljosejimenez.repositories.OrderRepository;
import com.mercadolibre.desafiofinaljosejimenez.repositories.PartRepository;
import org.aspectj.weaver.ast.Or;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderSorterUtils {
    //Sorting orders Second Requirement
    public static List<OrderDE> getSortedList(Map<String, String> params, OrderRepository repository)  {
        List<OrderDE> sorterList = new ArrayList<OrderDE>();
        String dealerNumber = params.get("dealerNumber");

        String order = params.getOrDefault("order","");

        String deliveryStatus = params.getOrDefault("deliveryStatus","");
        sorterList = getSortedListWithParams(repository, order, dealerNumber, deliveryStatus);

        if(sorterList.size() == 0) throw new NotFoundException("404 Not Found");
        return sorterList;


    }
    private static List<OrderDE> getSortedListWithParams(OrderRepository repository, String order, String dealerNumber, String deliveryStatus) {
        // query dealer number
        if (order.equals("") && deliveryStatus.equals(""))
            return repository.findByDealerRequest(dealerNumber);

        // query dealer number and status
        if (order.equals("") && ! deliveryStatus.equals(""))
            return repository.findByDealerAndStatus(dealerNumber, deliveryStatus);

        // query dealer number and order
        if (! order.equals("") && deliveryStatus.equals(""))
            return getSorterByOrderAndDelear(repository,order,dealerNumber);

        // query dealer number, order and status
       return getSorterByOrderAndDelearNumberAndDeliveryStatus(repository,order,dealerNumber,deliveryStatus);

    }

    private static List<OrderDE> getSorterByOrderAndDelearNumberAndDeliveryStatus(OrderRepository repository, String order, String dealerNumber, String deliveryStatus) {
        if (order.equals("2")) return repository.findByDealerAndStatusDescending(dealerNumber,deliveryStatus);
        return repository.findByDealerAndStatusAscending(dealerNumber,deliveryStatus);
    }


    public static List<OrderDE> getSorterByOrderAndDelear(OrderRepository repository, String order, String dealerNumber)  {
        if (order.equals("2")) return repository.findByDealerDescending(dealerNumber);
        return repository.findByDealerAscending(dealerNumber);
    }


}
