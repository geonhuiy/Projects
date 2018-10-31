package com.example.jamie.chatclient

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {
    lateinit var name: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Intent created to move on to next activity
        val intent = Intent(this, Main2Activity::class.java)
        //Thread created to handle the connection between the server and client
        val connectThread = Thread(SocketHandler)
        //Thread is started
        connectThread.start()
        buttonConnect.setOnClickListener {
            name = userName.text.toString()
            //Username passed to singleton "Username" to be used later
            Username.userName = name
            //Username validity check
            if (name.isNullOrBlank() || name.isNullOrEmpty()) {
                Toast.makeText(this,"Improper username. Please try again",Toast.LENGTH_LONG).show()
            }
            //Passes username to next activity
            else {
                intent.putExtra("username", name)
                startActivity(intent)
            }
        }
    }
}
