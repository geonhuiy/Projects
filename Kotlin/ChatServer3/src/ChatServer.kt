//Name : GeonHui Yoon
//Student number : 1706207
//File description : Handles connections by utilizing sockets and threads
import java.io.IOException
import java.net.ServerSocket

class ChatServer {
    fun serve() {
        //Starts console
        ChatConsole().start()
            try {
                //Creates socket for connection
                val serverSocket = ServerSocket(30001, 4)
                while (true) {
                    //Waits for a connection
                    val accept = serverSocket.accept()
                    //Creates a thread consisting of CommandInterpreter for every connection made
                    val thread = Thread(CommandInterpreter(accept.getInputStream(),accept.getOutputStream()))
                    thread.start()
                println("New connection from " + accept.inetAddress.hostAddress + " " + accept.port)
                }
            } catch (e: IOException) {
                println("Some error")
            }
        }
    }