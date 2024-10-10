package com.example.lab1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val chatList: List<Chat>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val senderName: TextView = itemView.findViewById(R.id.sender_name)
        val lastMessage: TextView = itemView.findViewById(R.id.last_message)
        val messageTime: TextView = itemView.findViewById(R.id.message_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat, parent, false)
        return ChatViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val currentChat = chatList[position]
        holder.senderName.text = currentChat.senderName
        holder.lastMessage.text = currentChat.lastMessage
        holder.messageTime.text = currentChat.time
    }

    override fun getItemCount(): Int {
        return chatList.size
    }
}