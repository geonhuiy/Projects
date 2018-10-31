//Name : GeonHui Yoon
//Student number : 1706207
//File description : Observable Interface
interface ChatObservable {
    fun register (observer: ChatObserver){

    }
    fun deregister (observer: ChatObserver){

    }
    fun notify (message: ChatMessage){

    }
}