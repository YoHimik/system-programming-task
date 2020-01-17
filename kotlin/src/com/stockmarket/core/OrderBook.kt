package com.stockmarket.core

import com.stockmarket.models.Order
import com.stockmarket.models.Side
import java.util.Collection
import java.util.HashMap
import com.stockmarket.core.OrderBook

class OrderBook(val name: String) {
    private val openOrders: HashMap<Int, Order> = HashMap()
    private val closedOrders: HashMap<Int, Order> = HashMap()
    private val canceledOrders: HashMap<Int, Order> = HashMap()
    fun addOrder(side: Side, price: Int, quantity: Int): Int {
        openOrders.put(lastOrderId, Order(side, price, quantity, lastOrderId, name))
        Logger.toLog("New \'" + side.name + "\' order with ID: " + lastOrderId.toString() + ", price: " + price.toString() + " and quantity: " + quantity.toString() + " added to " + name)
        return lastOrderId++
    }

    private fun closeOrder(order: Order) {
        openOrders.remove(order.getId())
        order.setCurrentQuantity(null)
        order.closeOrder()
        closedOrders.put(order.getId(), order)
        Logger.toLog("Order with ID: " + order.getId().toString() + " closed at " + name)
    }

    fun cancelOrder(order: Order) {
        openOrders.remove(order.getId())
        order.setCurrentQuantity(null)
        order.cancelOrder()
        canceledOrders.put(order.getId(), order)
        Logger.toLog("Order with ID: " + order.getId().toString() + " canceled at " + name)
    }

    fun updateOrders(buyOrder: Order, sellOrder: Order) {
        if (isQuantityGreater(sellOrder, buyOrder)) return
        if (isQuantityGreater(buyOrder, sellOrder)) return
        closeOrder(sellOrder)
        closeOrder(buyOrder)
    }

    private fun isQuantityGreater(firstOrder: Order, secondOrder: Order): Boolean {
        if (secondOrder.getCurrentQuantity() > firstOrder.getCurrentQuantity()) {
            secondOrder.setCurrentQuantity(secondOrder.getCurrentQuantity() - firstOrder.getCurrentQuantity())
            Logger.toLog("Order with ID: " + secondOrder.getId().toString() + " updated at " + name + ". New quantity: " + secondOrder.getCurrentQuantity())
            closeOrder(firstOrder)
            return true
        }
        return false
    }

    fun getOpenOrders(): MutableCollection<Order> {
        return openOrders.values
    }

    fun getOrderById(id: Int): Order? {
        if (openOrders.containsKey(id)) return openOrders.get(id)
        if (closedOrders.containsKey(id)) return closedOrders.get(id)
        if (canceledOrders.containsKey(id)) canceledOrders.get(id)
        throw IllegalArgumentException()
    }

    companion object {
        private var lastOrderId = 0
    }

}