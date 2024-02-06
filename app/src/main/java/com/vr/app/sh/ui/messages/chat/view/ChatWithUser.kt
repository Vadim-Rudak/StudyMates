package com.vr.app.sh.ui.messages.chat.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.ui.base.ChatViewModelFactory
import com.vr.app.sh.ui.messages.chat.viewModel.ChatWithUserViewModel
import javax.inject.Inject

class ChatWithUser : AppCompatActivity() {

    @Inject
    lateinit var factory: ChatViewModelFactory

    lateinit var viewModel: ChatWithUserViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_with_user)

        (applicationContext as App).appComponent.injectChat(this)

        viewModel = ViewModelProvider(this, factory)
            .get(ChatWithUserViewModel::class.java)

        val sharedPrefs = getSharedPreferences("user_info", Context.MODE_PRIVATE)

        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }

        val nameUser = findViewById<TextView>(R.id.textUserName)
        nameUser.text = "${intent.extras?.getString("lastName")} ${intent.extras?.getString("userName")}"

        val inputMessage = findViewById<EditText>(R.id.textMessage)
        val btnSend = findViewById<ImageButton>(R.id.btnSendMsg)
        btnSend.setOnClickListener {
            viewModel.sendMessage(
                "default",
                sharedPrefs.getInt("id",0),
                intent.extras!!.getInt("userId"),
                inputMessage.text.toString())
        }
    }
}