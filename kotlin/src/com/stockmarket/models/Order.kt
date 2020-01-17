package com.stockmarket.models

class Order(side: Side, price: Int, quantity: Int, id: Int, bookName: String) {
    private val side: Side
    private val price: Int
    private val startQuantity: Int
    private var currentQuantity: Int
    private val id: Int
    val bookName: String
    private var state: OrderState
    fun getStartQuantity(): Int {
        return startQuantity
    }

    fun getId(): Int {
        return id
    }

    fun getPrice(): Int {
        return price
    }

    fun getSide(): Side {
        return side
    }

    fun getState(): OrderState {
        return state
    }

    fun getCurrentQuantity(): Int {
        return currentQuantity
    }

    fun setCurrentQuantity(currentQuantity: Int?) {
        this.currentQuantity = currentQuantity!!
    }

    fun closeOrder() {
        if (state === OrderState.Open) state = OrderState.Closed
    }

    fun cancelOrder() {
        if (state === OrderState.Open) state = OrderState.Canceled
    }

    init {
        this.side = side
        this.price = price
        startQuantity = quantity
        currentQuantity = quantity
        this.id = id
        this.bookName = bookName
        state = OrderState.Open
    }
}