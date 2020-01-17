package com.stockmarket.models

class Trade(buyOrderId: Int, sellOrderId: Int, buyPrice: Int, sellPrice: Int, quantity: Int, id: Int, bookName: String) {
    private val buyOrderId: Int
    private val sellOrderId: Int
    private val buyPrice: Int
    private val sellPrice: Int
    private val quantity: Int
    private val id: Int
    val bookName: String
    fun getId(): Int {
        return id
    }

    fun getQuantity(): Int {
        return quantity
    }

    fun getSellPrice(): Int {
        return sellPrice
    }

    fun getBuyPrice(): Int {
        return buyPrice
    }

    fun getSellOrderId(): Int {
        return sellOrderId
    }

    fun getBuyOrderId(): Int {
        return buyOrderId
    }

    init {
        this.buyOrderId = buyOrderId
        this.sellOrderId = sellOrderId
        this.buyPrice = buyPrice
        this.sellPrice = sellPrice
        this.quantity = quantity
        this.id = id
        this.bookName = bookName
    }
}