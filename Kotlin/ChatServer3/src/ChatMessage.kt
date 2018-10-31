//Name : GeonHui Yoon
//Student number : 1706207
//File description : Converts user input into a formatted string for other users
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

//Dealing with user input
class ChatMessage (var user : String, var message : String, var currentTime: LocalTime) {
    override fun toString () : String {
        val dtf : DateTimeFormatter =  DateTimeFormatter.ofPattern("HH:mm")
        return (user +": " +message +" at " + currentTime.format(dtf))
    }
}