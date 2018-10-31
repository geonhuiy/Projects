package com.example.jamie.chatclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*
import kotlin.concurrent.thread

class Main2Activity : AppCompatActivity() {
    private var doubleBackPressed = false
    val messageList = ArrayList<ChatMessage>()
    lateinit var adapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val intent = intent
        //Receives string sent from previous activity and reformats it to be used as a command with
        //the chat server
        val userName = ":user " + intent.getStringExtra("username")
        //Sets list adapter
        adapter = CustomAdapter(this,messageList,intent.getStringExtra("username") )
        chatList.adapter = adapter
        //Sets username
        Thread(SendMessage(userName)).start()
        //Thread for receiving messages started
        val receiveThread = Thread(ReceiveMessage(this))
        receiveThread.start()
        buttonSend.setOnClickListener {
            //Sends message whenever send button is clicked and resets field
            var message = input.text.toString()
            val sendThread = Thread(SendMessage(message))
            sendThread.start()
            input.setText("")
        }
    }
    //Updates listview of messages
    fun updateList(message: ChatMessage) {
        //As list is not accessible in the thread
        runOnUiThread {
            //Adds messages to the list
            messageList.add(message)
            //Notifies adapter for changes
            adapter.notifyDataSetChanged()
        }
    }
    //Quit application when back button is pressed TWICE
   override fun onBackPressed() {
       if (doubleBackPressed) {
           super.onBackPressed()
           this.finish()
           return
       }
       this.doubleBackPressed = true
       Toast.makeText(this, "Press the back button once more to log out", Toast.LENGTH_LONG).show()
    }
}
