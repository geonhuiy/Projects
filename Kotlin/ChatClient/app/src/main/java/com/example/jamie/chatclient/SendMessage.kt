package com.example.jamie.chatclient

import android.util.PrintStreamPrinter
import android.util.Printer
import java.io.PrintStream

class SendMessage(val message : String): Runnable {
    override fun run() {
        //Gets socket outputstream
        val output = SocketHandler.socket.getOutputStream()
        val printer = PrintStream(output)
        printer.println(message)
    }
}