//Name : GeonHui Yoon
//Student number : 1706207
//File description : Handles addition/removal of user upon connecting and disconnecting from the server
import java.util.*

object Users {
    val allUsers = mutableSetOf<String>()

    //Adds username to user list
    fun addUsers (username : String) {
        allUsers.add(username)
        TopChatter.showTopChatter()
    }
    //Removes users
    fun removeUsers (username: String) {
        allUsers.remove(username)
        TopChatter.showTopChatter()
    }
}