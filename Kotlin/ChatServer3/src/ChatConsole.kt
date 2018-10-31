//Name : GeonHui Yoon
//Student number : 1706207
//File description : Outputs message in the IntelliJ console only
class ChatConsole : ChatObserver{
    //Prints chat messages in the console only
    override fun getUpdate(message: ChatMessage) {
        System.out.println(message)
    }
    //Registers as an observer ob ChatHistory
    fun start(){
        ChatHistory.register(this)
    }
}