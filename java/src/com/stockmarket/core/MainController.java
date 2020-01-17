package com.stockmarket.core;

import com.stockmarket.Main;
import com.stockmarket.models.Side;

import java.util.Scanner;

public final class MainController {
    private static final Integer COMMAND_INDEX = 0;
    private static final String EXIT_COMMAND = "exit";
    private static final String HELP_COMMAND = "help";
    private static final String BOOKS_COMMAND = "books";

    private static final String ADD_ORDER_COMMAND = "add";
    private static final Integer ADD_ORDER_WORD_COUNT = 5;
    private static final Integer BOOK_NAME_INDEX_ADD_COMMAND = 1;
    private static final Integer SIDE_INDEX_ADD_COMMAND = 2;
    private static final Integer PRICE_INDEX_ADD_COMMAND = 3;
    private static final Integer QUANTITY_INDEX_ADD_COMMAND = 4;

    private static final String CANCEL_ORDER_COMMAND = "cancel";
    private static final Integer CANCEL_ORDER_WORD_COUNT = 2;
    private static final Integer ORDER_ID_INDEX_CANCEL_COMMAND = 1;

    private static final String INCORRECT_ARGUMENTS_COUNT_ERROR_MSG = "Incorrect arguments count. Use command \'help\'.";
    private static final String INCORRECT_BOOK_NAME_ERROR_MSG = "Incorrect book name.";
    private static final String INCORRECT_ORDER_SIDE_ERROR_MSG = "Incorrect order side.";
    private static final String INCORRECT_DIGIT_INPUT_ERROR_MSG = "Incorrect digit input.";
    private static final String INCORRECT_ORDER_ID_ERROR_MSG = "Incorrect order ID.";
    private static final String UNKNOWN_COMMAND_ERROR_MSG = "Unknown command. Use command \'help\'.";
    private static final String NO_AVAILABLE_BOOKS_ERROR_MSG = "No available books.";

    private static MatchingEngine matchingEngine;

    public static void startSimulator() {
        matchingEngine = new MatchingEngine(Main.getOrderBooks());
        matchingEngine.setName("Matching Engine");
        matchingEngine.start();
        readInputCycle();
        matchingEngine.interrupt();
    }

    private static void readInputCycle() {
        Scanner reader = new Scanner(System.in);
        String line = reader.nextLine();
        Logger.toLog("User input \'" + line + "\'", false);
        while (!line.equals(EXIT_COMMAND)) {
            processInput(line);
            line = reader.nextLine();
            Logger.toLog("User input \'" + line + "\'", false);
        }
    }

    private static void processInput(String line) {
        String[] formattedLine = line.split(" ");
        if (formattedLine.length == 0)
            return;
        switch (formattedLine[COMMAND_INDEX]) {
            case ADD_ORDER_COMMAND:
                addOrder(formattedLine);
                break;
            case CANCEL_ORDER_COMMAND:
                cancelOrder(formattedLine);
                break;
            case HELP_COMMAND:
                writeHelp();
                break;
            case BOOKS_COMMAND:
                writeBooks();
                break;
            default:
                Logger.toLog(UNKNOWN_COMMAND_ERROR_MSG);
                break;
        }
    }

    private static void addOrder(String[] formattedLine) {
        if (!isAddInputCorrect(formattedLine))
            return;
        if (formattedLine[SIDE_INDEX_ADD_COMMAND].equals(Side.Buy.name()))
            matchingEngine.addNewOrderToBook(formattedLine[BOOK_NAME_INDEX_ADD_COMMAND],
                    Side.Buy, Integer.parseInt(formattedLine[PRICE_INDEX_ADD_COMMAND]), Integer.parseInt(formattedLine[QUANTITY_INDEX_ADD_COMMAND]));
        else
            matchingEngine.addNewOrderToBook(formattedLine[BOOK_NAME_INDEX_ADD_COMMAND],
                    Side.Sell, Integer.parseInt(formattedLine[PRICE_INDEX_ADD_COMMAND]), Integer.parseInt(formattedLine[QUANTITY_INDEX_ADD_COMMAND]));
    }

    private static boolean isAddInputCorrect(String[] formattedLine) {
        if (formattedLine.length != ADD_ORDER_WORD_COUNT) {
            Logger.toLog(INCORRECT_ARGUMENTS_COUNT_ERROR_MSG);
            return false;
        }
        if (!matchingEngine.isBookExists(formattedLine[BOOK_NAME_INDEX_ADD_COMMAND])) {
            Logger.toLog(INCORRECT_BOOK_NAME_ERROR_MSG);
            return false;
        }
        if (!formattedLine[SIDE_INDEX_ADD_COMMAND].equals(Side.Buy.name()) && !formattedLine[2].equals(Side.Sell.name())) {
            Logger.toLog(INCORRECT_ORDER_SIDE_ERROR_MSG);
            return false;
        }
        try {
            Integer.parseInt(formattedLine[PRICE_INDEX_ADD_COMMAND]);
            Integer.parseInt(formattedLine[QUANTITY_INDEX_ADD_COMMAND]);
        } catch (NumberFormatException e) {
            Logger.toLog(INCORRECT_DIGIT_INPUT_ERROR_MSG);
            return false;
        }
        return true;
    }

    private static void cancelOrder(String[] formattedLine) {
        if (!isCancelInputCorrect(formattedLine))
            return;
        Integer id = Integer.parseInt(formattedLine[ORDER_ID_INDEX_CANCEL_COMMAND]);
        if (!matchingEngine.isOrderExists(id)) {
            Logger.toLog(INCORRECT_ORDER_ID_ERROR_MSG);
            return;
        }
        matchingEngine.cancelOrder(id);
    }

    private static boolean isCancelInputCorrect(String[] formattedLine) {
        if (formattedLine.length != CANCEL_ORDER_WORD_COUNT) {
            Logger.toLog(INCORRECT_ARGUMENTS_COUNT_ERROR_MSG);
            return false;
        }
        try {
            Integer.parseInt(formattedLine[ORDER_ID_INDEX_CANCEL_COMMAND]);
        } catch (NumberFormatException e) {
            Logger.toLog(INCORRECT_DIGIT_INPUT_ERROR_MSG);
            return false;
        }
        return true;
    }

    private static void writeBooks() {
        String books = matchingEngine.getBooksNames();
        if (books.equals("")) {
            Logger.toLog(NO_AVAILABLE_BOOKS_ERROR_MSG);
            return;
        }
        Logger.toLog("Available books: " + books);
    }

    private static void writeHelp() {
        Logger.toLog("Use command \'add\' to place your orders. Use following syntax:");
        Logger.toLog("add Buy/Sell *Book Name* *Price* *Quantity*");
        Logger.toLog("Use command \'cancel\' to cancel placed orders. Use following syntax:");
        Logger.toLog("cancel *Order ID*");
        Logger.toLog("Use command \'books\' to view available books.");
        Logger.toLog("Use command \'exit\' to correctly close simulator.");
    }
}
