package com.stockmarket.core;

import com.stockmarket.models.Order;
import com.stockmarket.models.Side;

import java.util.HashMap;

public class MatchingEngine extends Thread {
    private final Integer MIN_PRICE = -1;
    private final Integer MAX_PRICE = Integer.MAX_VALUE;
    private final Integer SLEEP_TIME = 1000;

    private HashMap<String, OrderBook> orderBooks;
    private HashMap<Integer, String> orderIdBookTable = new HashMap<>();

    MatchingEngine(HashMap<String, OrderBook> orderBooks) {
        this.orderBooks = orderBooks;
    }

    public void run() {
        while (!isInterrupted()) {
            for (OrderBook book : orderBooks.values())
                findMatch(book);
            try {
                sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                Logger.toLog("***" + getName() + " error: " + e.getMessage() + "***", false);
                break;
            }
        }
    }

    private boolean findMatch(OrderBook book) {
        int minBuyPrice = MAX_PRICE;
        Order minBuyOrder = null;
        int maxSellPrice = MIN_PRICE;
        Order maxSellOrder = null;
        for (Order order : book.getOpenOrders()) {
            if (order.getSide() == Side.Buy && order.getPrice() < minBuyPrice) {
                minBuyPrice = order.getPrice();
                minBuyOrder = order;
                continue;
            }
            if (order.getSide() == Side.Sell && order.getPrice() > maxSellPrice) {
                maxSellPrice = order.getPrice();
                maxSellOrder = order;
            }
        }
        if (maxSellPrice == MIN_PRICE || minBuyPrice == MAX_PRICE || minBuyPrice > maxSellPrice)
            return false;
        TradeLedger.addTrade(minBuyOrder, maxSellOrder);
        book.updateOrders(minBuyOrder, maxSellOrder);
        return true;
    }

    void addNewOrderToBook(String bookName, Side side, int price, int quantity) {
        Integer id = orderBooks.get(bookName).addOrder(side, price, quantity);
        orderIdBookTable.put(id, bookName);
    }

    void cancelOrder(Integer id) {
        orderBooks.get(getBookNameByOrderId(id)).cancelOrder(orderBooks.get(getBookNameByOrderId(id)).getOrderById(id));
    }

    boolean isOrderExists(Integer id) {
        return orderIdBookTable.containsKey(id);
    }

    private String getBookNameByOrderId(Integer id) {
        return orderIdBookTable.get(id);
    }

    void addBook(String bookName) throws IllegalArgumentException {
        if (isBookExists(bookName))
            throw new IllegalArgumentException();
        orderBooks.put(bookName, new OrderBook(bookName));
    }

    boolean isBookExists(String bookName) {
        return orderBooks.containsKey(bookName);
    }

    String getBooksNames() {
        StringBuilder books = new StringBuilder();
        for (OrderBook book : orderBooks.values())
            books.append(book.getName()).append(", ");
        return books.toString();
    }
}
