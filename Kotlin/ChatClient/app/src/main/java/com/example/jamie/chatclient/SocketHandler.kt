package com.example.jamie.chatclient

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Printer
import java.io.*
import java.net.InetSocketAddress
import java.net.Socket
import java.net.UnknownHostException
import java.util.*
import kotlin.concurrent.thread
import kotlin.math.log

object SocketHandler : Runnable {
    //Hard-coded host address and port number
    val IP = "192.168.43.129"
    val PORT = 30001
    lateinit var socket: Socket
    override fun run() {
        try {
            //Connects to the serversocket with given host address and port number
                socket = Socket(IP, PORT)
        }
        //In case of some exceptions, locates the errors
        catch (e: UnknownHostException) {
            e.printStackTrace()
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
        catch (e: UninitializedPropertyAccessException) {
            println("Server is not running yet")
        }
    }
}
