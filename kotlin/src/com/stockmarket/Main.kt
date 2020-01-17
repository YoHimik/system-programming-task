package com.stockmarket

import com.stockmarket.core.Logger
import com.stockmarket.core.MainController
import com.stockmarket.core.OrderBook
import java.io.File
import java.io.IOException
import java.util.HashMap
import java.util.Scanner
object Main { // статический класс заменём объектом
    @JvmStatic // статическая функция
    fun main(args: Array<String>) {
        writeStartOut()
        MainController.startSimulator()
        writeEndOut()
    }

    private fun writeStartOut() {
        Logger.toLog("***Simulator started****", false)
        Logger.toLog("Welcome to stock market simulator. Use command \'help\' to get command description")
    }

    private fun writeEndOut() {
        Logger.toLog("***Simulator closed****", false)
    }

    val orderBooks: HashMap<String, OrderBook>? // функция сконвертирована в get свойство
        get() {
            val books: HashMap<String, OrderBook> = HashMap()
            var line: String = "" // Ооязательно требуетсяначальнео значение
            try {
                Scanner(File("books.txt")).use({ reader -> line = reader.nextLine() })
            } catch (ex: IOException) {
                return null
            }
            if (line == null) return null
            val stringBooks: List<String> = line.split(",")
            for (book in stringBooks) {
                books.put(book, OrderBook(book))
            }
            return books
        }

}