package com.stockmarket.core

import com.stockmarket.models.Order
import com.stockmarket.models.Trade
import java.util.HashMap
import com.stockmarket.core.TradeLedger

object TradeLedger {
    private val trades: HashMap<Int, Trade?>? = HashMap()
    private var lastTradeId: Int = 0
    fun addTrade(buyOrder: Order?, sellOrder: Order?): Int {
        if (sellOrder != null) {
            if (buyOrder != null) {
                Logger.toLog("New trade with ID: " + lastTradeId + ", buy order ID: " + buyOrder.getId() + " and sell order ID: "
                        + sellOrder.getId() + " added to " + sellOrder.bookName)
            }
        }
        if (trades != null) {
            if (buyOrder != null) {
                if (sellOrder != null) {
                    trades[lastTradeId] = Trade(buyOrder.getId(), sellOrder.getId(), buyOrder.getPrice(),
                            sellOrder.getPrice(), Math.abs(buyOrder.getPrice() - sellOrder.getPrice()),
                            lastTradeId, sellOrder.bookName)
                }
            }
        }
        return lastTradeId++
    }
}