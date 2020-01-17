package com.stockmarket.core

import com.stockmarket.Main
import com.stockmarket.models.Side
import java.util.Scanner

object MainController { // статический класс переведён в объект
    private val COMMAND_INDEX: Int = 0
    private const val EXIT_COMMAND = "exit"
    private const val HELP_COMMAND = "help"
    private const val BOOKS_COMMAND = "books"
    private const val ADD_ORDER_COMMAND = "add"
    private val ADD_ORDER_WORD_COUNT: Int = 5
    private val BOOK_NAME_INDEX_ADD_COMMAND: Int = 1
    private val SIDE_INDEX_ADD_COMMAND: Int = 2
    private val PRICE_INDEX_ADD_COMMAND: Int = 3
    private val QUANTITY_INDEX_ADD_COMMAND: Int = 4
    private const val CANCEL_ORDER_COMMAND = "cancel"
    private val CANCEL_ORDER_WORD_COUNT: Int = 2
    private val ORDER_ID_INDEX_CANCEL_COMMAND: Int = 1
    private const val INCORRECT_ARGUMENTS_COUNT_ERROR_MSG = "Incorrect arguments count. Use command \'help\'."
    private const val INCORRECT_BOOK_NAME_ERROR_MSG = "Incorrect book name."
    private const val INCORRECT_ORDER_SIDE_ERROR_MSG = "Incorrect order side."
    private const val INCORRECT_DIGIT_INPUT_ERROR_MSG = "Incorrect digit input."
    private const val INCORRECT_ORDER_ID_ERROR_MSG = "Incorrect order ID."
    private const val UNKNOWN_COMMAND_ERROR_MSG = "Unknown command. Use command \'help\'."
    private const val NO_AVAILABLE_BOOKS_ERROR_MSG = "No available books."
    private var matchingEngine: MatchingEngine? = null
    fun startSimulator() {
        matchingEngine = MatchingEngine(Main.orderBooks)
        matchingEngine!!.setName("Matching Engine")
        matchingEngine!!.start()
        readInputCycle()
        matchingEngine!!.interrupt()
    }

    private fun readInputCycle() {
        val reader = Scanner(System.`in`)
        var line: String = reader.nextLine()
        Logger.toLog("User input \'$line\'", false)
        while (!line.equals(EXIT_COMMAND)) {
            processInput(line)
            line = reader.nextLine()
            Logger.toLog("User input \'$line\'", false)
        }
    }

    private fun processInput(line: String) {
        val formattedLine: List<String> = line.split(" ")
        if (formattedLine.size == 0) return
        when (formattedLine[COMMAND_INDEX]) { // switch изменем за when
            ADD_ORDER_COMMAND -> addOrder(formattedLine)
            CANCEL_ORDER_COMMAND -> cancelOrder(formattedLine)
            HELP_COMMAND -> writeHelp()
            BOOKS_COMMAND -> writeBooks()
            else -> Logger.toLog(UNKNOWN_COMMAND_ERROR_MSG)
        }
    }

    private fun addOrder(formattedLine: List<String>) {
        if (!isAddInputCorrect(formattedLine)) return
        if (formattedLine[SIDE_INDEX_ADD_COMMAND].equals(Side.Buy.name)) matchingEngine?.addNewOrderToBook(formattedLine[BOOK_NAME_INDEX_ADD_COMMAND],
                Side.Buy, Integer.parseInt(formattedLine[PRICE_INDEX_ADD_COMMAND]), Integer.parseInt(formattedLine[QUANTITY_INDEX_ADD_COMMAND])) else matchingEngine?.addNewOrderToBook(formattedLine[BOOK_NAME_INDEX_ADD_COMMAND],
                Side.Sell, Integer.parseInt(formattedLine[PRICE_INDEX_ADD_COMMAND]), Integer.parseInt(formattedLine[QUANTITY_INDEX_ADD_COMMAND]))
    }

    private fun isAddInputCorrect(formattedLine: List<String>): Boolean {
        if (formattedLine.size != ADD_ORDER_WORD_COUNT) {
            Logger.toLog(INCORRECT_ARGUMENTS_COUNT_ERROR_MSG)
            return false
        }
        if (!matchingEngine?.isBookExists(formattedLine[BOOK_NAME_INDEX_ADD_COMMAND])!!) {
            Logger.toLog(INCORRECT_BOOK_NAME_ERROR_MSG)
            return false
        }
        if (!formattedLine[SIDE_INDEX_ADD_COMMAND].equals(Side.Buy.name) && !formattedLine[2].equals(Side.Sell.name)) {
            Logger.toLog(INCORRECT_ORDER_SIDE_ERROR_MSG)
            return false
        }
        try {
            Integer.parseInt(formattedLine[PRICE_INDEX_ADD_COMMAND])
            Integer.parseInt(formattedLine[QUANTITY_INDEX_ADD_COMMAND])
        } catch (e: NumberFormatException) {
            Logger.toLog(INCORRECT_DIGIT_INPUT_ERROR_MSG)
            return false
        }
        return true
    }

    private fun cancelOrder(formattedLine: List<String>) {
        if (!isCancelInputCorrect(formattedLine)) return
        val id: Int = Integer.parseInt(formattedLine[ORDER_ID_INDEX_CANCEL_COMMAND])
        if (!matchingEngine?.isOrderExists(id)!!) {
            Logger.toLog(INCORRECT_ORDER_ID_ERROR_MSG)
            return
        }
        matchingEngine!!.cancelOrder(id)
    }

    private fun isCancelInputCorrect(formattedLine: List<String>): Boolean {
        if (formattedLine.size != CANCEL_ORDER_WORD_COUNT) {
            Logger.toLog(INCORRECT_ARGUMENTS_COUNT_ERROR_MSG)
            return false
        }
        try {
            Integer.parseInt(formattedLine[ORDER_ID_INDEX_CANCEL_COMMAND])
        } catch (e: NumberFormatException) {
            Logger.toLog(INCORRECT_DIGIT_INPUT_ERROR_MSG)
            return false
        }
        return true
    }

    private fun writeBooks() {
        val books: String? = matchingEngine?.booksNames
        if (books.equals("")) {
            Logger.toLog(NO_AVAILABLE_BOOKS_ERROR_MSG)
            return
        }
        Logger.toLog("Available books: $books")
    }

    private fun writeHelp() {
        Logger.toLog("Use command \'add\' to place your orders. Use following syntax:")
        Logger.toLog("add Buy/Sell *Book Name* *Price* *Quantity*")
        Logger.toLog("Use command \'cancel\' to cancel placed orders. Use following syntax:")
        Logger.toLog("cancel *Order ID*")
        Logger.toLog("Use command \'books\' to view available books.")
        Logger.toLog("Use command \'exit\' to correctly close simulator.")
    }
}