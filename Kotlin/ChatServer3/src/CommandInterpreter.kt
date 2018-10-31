//Name : GeonHui Yoon
//Student number : 1706207
//File description : Handles user commands
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*
class CommandInterpreter (var input : InputStream , var output : OutputStream) : Runnable,ChatObserver{
    //Register CommandInterpreter class as an observer of ChatHistory singleton
    init {
        ChatHistory.register(this)
    }
    val scanner = Scanner(input)
    val printer = PrintStream(output)
    override fun run() {
        var userName = ""
        var userNameSet = false

        //Some welcome message
        //printer.println("Welcome to the chat server!")
        //printer.println("Please enter username with :user username")
        //printer.println(":help for a list of useful commands")
        //Loop runs until user decides to quit
        loop@ while (true) {
            //Takes user input
            val input = (scanner.nextLine())
            //Recognizes if the input is a command
            if (input.startsWith(":"))  {
                //If it is a command, determines different cases for different commands
                var command = input.split(" ") [0]
                when (command) {
                    ":user" -> {
                        //Some error checking
                       try {
                           //Saves username
                            userName = input.split(" ")[1]
                            if (userName.isNotEmpty() || userName.isNotBlank()) {
                                if (Users.allUsers.contains(userName)) {
                                    printer.println("Name is already taken")
                                }
                                else {
                                    //Add username to a set of all users
                                    Users.addUsers(userName)
                                    printer.println("Username set as " + userName)
                                    userNameSet = true
                                }
                            }
                            //In case a username is a blank/space
                            else {
                                printer.println("Empty/blank username. Please try again")
                            }
                        }
                       //In case user inputs no username
                        catch (e : IndexOutOfBoundsException) {
                            printer.println("Weird username")
                        }
                    }

                    ":help" -> {
                        printer.println(":user -> To set username")
                        printer.println(":help -> You're looking at it right now")
                        printer.println(":quit -> To exit the chat server")
                        printer.println(":users -> Displays all connected users")
                        printer.println(":history -> Shows message history")
                    }
                    ":history" -> {
                        printer.println(ChatHistory)
                    }
                    ":users" -> {
                        Users.allUsers.forEach {printer.println (it)}
                    }
                    ":quit" -> {
                        Users.removeUsers(userName)
                        scanner.close()
                        break@loop
                    }
                    else -> printer.println("Unknown command")
                }
            }
            else if (!userNameSet) {
                printer.println("Please enter a username first!")
            }
            //After username is set and if input is not a command
            else {
                var chatMessage = ChatMessage(userName,input, LocalTime.now())
                //printer.println(chatMessage)
                //Saves user message into a message history list
                ChatHistory.insert(chatMessage)
                //Sends message to all connected users
                ChatHistory.notify(chatMessage)
            }
        }
    }
    //Prints chat message into terminal
    override fun getUpdate(message: ChatMessage) {
        printer.println(message)
    }
}
