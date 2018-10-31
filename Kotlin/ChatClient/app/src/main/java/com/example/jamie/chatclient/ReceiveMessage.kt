package com.example.jamie.chatclient

import java.util.*

class ReceiveMessage(val activity: Main2Activity) : Runnable {
    override fun run() {
        //Gets socket inputstream
        val input = SocketHandler.socket.getInputStream()
        val scanner = Scanner(input)
        //Reads incoming messages
        while (true) {
            var userMessage = scanner.nextLine()
            //Divides messages from the server into different variables to be displayed in the list
            if (userMessage.contains(":")) {
                var userName = userMessage.substringBefore(":")
                var time = userMessage.substringAfter("at")
                var message = userMessage.substringAfter(":").substringBefore("at")
                var newMessage = ChatMessage(userName, message, time)
                //Updates list with defined format
                activity.updateList(newMessage)
            }
        }
    }
}