package com.stockmarket.core

import java.io.FileWriter
import java.io.IOException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

object Logger {
    private const val LOG_FILE_NAME = "log.txt"
    fun toLog(txt: String, toConsole: Boolean) {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        val date = Date()
        var out: String = dateFormat.format(date).toString() + " " + txt
        if (toConsole) {
            System.out.println(txt)
            out += " (from console)"
        }
        try {
            FileWriter(LOG_FILE_NAME, true).use({ writer ->
                writer.write(out)
                writer.write(System.getProperty("line.separator"))
                writer.flush()
            })
        } catch (ex: IOException) {
            System.out.println(ex.message)
        }
    }

    fun toLog(txt: String) {
        toLog(txt, true)
    }
}