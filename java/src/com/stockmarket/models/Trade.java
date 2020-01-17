package com.stockmarket.models;

public class Trade {
    private Integer buyOrderId;
    private Integer sellOrderId;
    private Integer buyPrice;
    private Integer sellPrice;
    private Integer quantity;
    private Integer id;
    private String bookName;

    public Trade(Integer buyOrderId, Integer sellOrderId, Integer buyPrice, Integer sellPrice, Integer quantity, Integer id, String bookName) {
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
        this.id = id;
        this.bookName = bookName;
    }

    public Integer getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getSellPrice() {
        return sellPrice;
    }

    public Integer getBuyPrice() {
        return buyPrice;
    }

    public Integer getSellOrderId() {
        return sellOrderId;
    }

    public Integer getBuyOrderId() {
        return buyOrderId;
    }
}
