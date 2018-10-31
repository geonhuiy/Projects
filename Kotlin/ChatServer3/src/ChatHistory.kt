//Name : GeonHui Yoon
//Student number : 1706207
//File description : Adds all messages into a mutable list and displays the contents of the list when :history is used
import javafx.beans.Observable
import java.time.format.DateTimeFormatter

object ChatHistory : ChatObservable {
    //List of chat history
    var allChat = mutableListOf<ChatMessage>()
    //List of observers
    var observers = mutableListOf<ChatObserver>()

    //Adds user input into the chat history list
    fun insert(message: ChatMessage) {
        allChat.add(message)
    }
    //Functions for dealing with observers
    override fun register(observer: ChatObserver) {
        observers.add(observer)
    }

    override fun deregister(observer: ChatObserver) {
        observers.remove(observer)
    }

    override fun notify(message: ChatMessage) {
        observers.forEach { it.getUpdate(message) }
    }
    //When command :history is used
    override fun toString(): String {
        var string = "Message History"
        allChat.forEach { string += " \n\r${it.message } from ${it.user} at ${it.currentTime.format(DateTimeFormatter.ofPattern("HH:mm"))}"}
        return string
    }
}