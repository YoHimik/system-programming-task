package com.stockmarket.core;

import com.stockmarket.models.Order;
import com.stockmarket.models.Side;

import java.util.Collection;
import java.util.HashMap;

public class OrderBook {
    private HashMap<Integer, Order> openOrders = new HashMap<>();
    private HashMap<Integer, Order> closedOrders = new HashMap<>();
    private HashMap<Integer, Order> canceledOrders = new HashMap<>();
    private String name;

    private static int lastOrderId;

    public OrderBook(String name) {
        this.name = name;
    }

    Integer addOrder(Side side, int price, int quantity) {
        openOrders.put(lastOrderId, new Order(side, price, quantity, lastOrderId, name));
        Logger.toLog("New \'" + side.name() + "\' order with ID: " + lastOrderId + ", price: " + price + " and quantity: " + quantity + " added to " + name);
        return lastOrderId++;
    }

    private void closeOrder(Order order) {
        openOrders.remove(order.getId());
        order.setCurrentQuantity(null);
        order.closeOrder();
        closedOrders.put(order.getId(),order);
        Logger.toLog("Order with ID: " + order.getId() + " closed at " + name);
    }

    void cancelOrder(Order order) {
        openOrders.remove(order.getId());
        order.setCurrentQuantity(null);
        order.cancelOrder();
        canceledOrders.put(order.getId(),order);
        Logger.toLog("Order with ID: " + order.getId() + " canceled at " + name);
    }

    void updateOrders(Order buyOrder, Order sellOrder){
        if (isQuantityGreater(sellOrder, buyOrder)) return;
        if (isQuantityGreater(buyOrder, sellOrder)) return;
        closeOrder(sellOrder);
        closeOrder(buyOrder);
    }

    private boolean isQuantityGreater(Order firstOrder, Order secondOrder) {
        if(secondOrder.getCurrentQuantity() > firstOrder.getCurrentQuantity()){
            secondOrder.setCurrentQuantity(secondOrder.getCurrentQuantity() - firstOrder.getCurrentQuantity());
            Logger.toLog("Order with ID: " + secondOrder.getId() + " updated at " + name + ". New quantity: " + secondOrder.getCurrentQuantity());
            closeOrder(firstOrder);
            return true;
        }
        return false;
    }

    String getName() {
        return name;
    }

    Collection<Order> getOpenOrders(){
        return openOrders.values();
    }

    Order getOrderById(int id) {
        if(openOrders.containsKey(id))
            return openOrders.get(id);
        if(closedOrders.containsKey(id))
            return closedOrders.get(id);
        if (canceledOrders.containsKey(id))
            canceledOrders.get(id);
        throw new IllegalArgumentException();
    }
}
