//Name : GeonHui Yoon
//Student number : 1706207
//File description : Displays the top 4 chatters of the chat server
import javafx.beans.Observable
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream

object TopChatter : ChatObserver{
    //Registers as an observer of ChatHistory
    init {
        ChatHistory.register(this)
    }
    //Map of all users and their message count
    val userMap = mutableMapOf<String , Int>()
    override fun getUpdate(message: ChatMessage) {
        //If user is already in the map, adds one to message count whenever message is input by the user
        if (userMap.containsKey(message.user )) {
            userMap.put(message.user, userMap[message.user]!!.plus(1))
        }
        //If not, sets message count to 1
        else {
            userMap.put(message.user,1)
        }
    }

    fun showTopChatter () {
        val topChatterList = userMap.toList()
        //Filters the list of users by showing the first 4 users in descending order
        val filteredList = topChatterList.sortedByDescending { it.second }.take(4).forEach(::println)
    }
}