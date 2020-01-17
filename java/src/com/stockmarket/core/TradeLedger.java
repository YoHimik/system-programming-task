package com.stockmarket.core;

import com.stockmarket.models.Order;
import com.stockmarket.models.Trade;

import java.util.HashMap;

public final class TradeLedger {
    private static HashMap<Integer, Trade> trades = new HashMap<>();
    private static Integer lastTradeId = 0;

    private TradeLedger() {
    }

    static Integer addTrade(Order buyOrder, Order sellOrder) {
        Logger.toLog("New trade with ID: " + lastTradeId + ", buy order ID: " + buyOrder.getId() + " and sell order ID: "
                + sellOrder.getId() + " added to " + sellOrder.getBookName());
        trades.put(lastTradeId, new Trade(buyOrder.getId(), sellOrder.getId(), buyOrder.getPrice(),
                sellOrder.getPrice(), Math.abs(buyOrder.getPrice() - sellOrder.getPrice()),
                lastTradeId, sellOrder.getBookName()));
        return lastTradeId++;
    }

    public static Trade getTradeById(Integer id) {
        return trades.get(id);
    }
}
