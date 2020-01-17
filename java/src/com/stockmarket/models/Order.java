package com.stockmarket.models;

public class Order {
    private Side side;
    private Integer price;
    private Integer startQuantity;
    private Integer currentQuantity;
    private Integer id;
    private String bookName;
    private OrderState state;

    public Order(Side side, Integer price, Integer quantity, Integer id, String bookName) {
        this.side = side;
        this.price = price;
        startQuantity = quantity;
        currentQuantity = quantity;
        this.id = id;
        this.bookName = bookName;
        state = OrderState.Open;
    }

    public Integer getStartQuantity() {
        return startQuantity;
    }

    public String getBookName() {
        return bookName;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public Side getSide() {
        return side;
    }

    public OrderState getState() {
        return state;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(Integer currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public void closeOrder() {
        if (state == OrderState.Open)
            state = OrderState.Closed;
    }

    public void cancelOrder() {
        if (state == OrderState.Open)
            state = OrderState.Canceled;
    }
}
