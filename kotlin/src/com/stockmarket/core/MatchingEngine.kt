package com.stockmarket.core

import com.stockmarket.models.Order
import com.stockmarket.models.Side
import java.util.*

class MatchingEngine internal constructor(private val orderBooks: HashMap<String, OrderBook>?) : Thread() {
    private val MIN_PRICE = -1
    private val MAX_PRICE = Int.MAX_VALUE
    private val SLEEP_TIME = 1000
    private val orderIdBookTable = HashMap<Int, String?>()
    override fun run() {
        while (!isInterrupted) {
            if (orderBooks != null) {
                for (book in orderBooks.values) findMatch(book)
            }
            try {
                sleep(SLEEP_TIME.toLong())
            } catch (e: InterruptedException) {
                Logger.toLog("***" + name + " error: " + e.message + "***", false)
                break
            }
        }
    }

    private fun findMatch(book: OrderBook?): Boolean {
        var minBuyPrice = MAX_PRICE
        var minBuyOrder: Order? = null
        var maxSellPrice = MIN_PRICE
        var maxSellOrder: Order? = null
        for (order in book!!.getOpenOrders()) {
            if (order.getSide() == Side.Buy && order.getPrice() < minBuyPrice) {
                minBuyPrice = order.getPrice()
                minBuyOrder = order
                continue
            }
            if (order.getSide() == Side.Sell && order.getPrice() > maxSellPrice) {
                maxSellPrice = order.getPrice()
                maxSellOrder = order
            }
        }
        if (maxSellPrice == MIN_PRICE || minBuyPrice == MAX_PRICE || minBuyPrice > maxSellPrice) return false
        TradeLedger.addTrade(minBuyOrder, maxSellOrder)
        book.updateOrders(minBuyOrder!!, maxSellOrder!!)
        return true
    }

    fun addNewOrderToBook(bookName: String?, side: Side?, price: Int, quantity: Int) {
        val id = orderBooks?.get(bookName)!!.addOrder(side!!, price, quantity)
        orderIdBookTable[id.toInt()] = bookName
    }

    fun cancelOrder(id: Int) {
        orderBooks?.get(getBookNameByOrderId(id))!!.getOrderById(id)?.let { orderBooks?.get(getBookNameByOrderId(id))!!.cancelOrder(it) }
    }

    fun isOrderExists(id: Int?): Boolean {
        return orderIdBookTable.containsKey(id)
    }

    private fun getBookNameByOrderId(id: Int): String? {
        return orderIdBookTable[id]
    }

    @Throws(IllegalArgumentException::class)
    fun addBook(bookName: String?) {
        require(!isBookExists(bookName))
        orderBooks?.set(bookName!!, OrderBook(bookName!!))
    }

    fun isBookExists(bookName: String?): Boolean {
        if (orderBooks != null) {
            return orderBooks.containsKey(bookName)
        }
        return false
    }

    val booksNames: String
        get() {
            val books = StringBuilder()
            if (orderBooks != null) {
                for (book in orderBooks.values) books.append(book.name).append(", ")
            }
            return books.toString()
        }

}