package com.example.jamie.chatclient

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.itemlist.view.*
import kotlinx.android.synthetic.main.receivedlist.view.*

class CustomAdapter(val context: Context, val arrayList: ArrayList<ChatMessage>, val username: String) : BaseAdapter() {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view: RelativeLayout
        //Adapter uses different layouts to inflate view to differentiate between user messages and received messages
        if (getItemViewType(p0) == 0) {
            if (p1 == null) {
                //Instantiates layout XML file into corresponding view object
                val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                view = inflater.inflate(R.layout.itemlist, null) as RelativeLayout
            } else {
                view = p1 as RelativeLayout
            }
            //Sets text from chat messages as texts of different view objects
            view.message.text = arrayList[p0].message
            view.time.text = arrayList[p0].time
            view.userName.text = arrayList[p0].name
            return view
        } else {
            if (p1 == null) {
                val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                view = inflater.inflate(R.layout.receivedlist, null) as RelativeLayout
            } else {
                view = p1 as RelativeLayout
            }
            view.message1.text = arrayList[p0].message
            view.time1.text = arrayList[p0].time
            view.userName1.text = arrayList[p0].name
            return view
        }

    }
    //Counts number of items in the data set of the adapter
    override fun getCount(): Int {
        return arrayList.size
    }
    //Gets data item associated with specified position in the data set
    override fun getItem(position: Int): Any {
        return arrayList[position]
    }
    //Gets row ID with specified position
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    //Defines which layout XML file should be used to inflate the view.
    override fun getItemViewType(position: Int): Int {
        //If user defined username is the same as in the message received, it is a user message
        return if (username == arrayList[position].name) {
            0
        } else {
            1
        }
    }
    //Number of types of views to be created
    override fun getViewTypeCount(): Int {
        return 2
    }
}