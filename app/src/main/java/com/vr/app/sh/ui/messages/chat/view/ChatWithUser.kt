package com.vr.app.sh.ui.messages.chat.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.ui.base.ChatViewModelFactory
import com.vr.app.sh.ui.messages.chat.adapter.MessageItemDecoration
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

        factory.initChatId(intent.getIntExtra("chatId",0),intent.getIntExtra("userId",0))

        viewModel = ViewModelProvider(this, factory)
            .get(ChatWithUserViewModel::class.java)

        val layoutNotMsg = findViewById<LinearLayout>(R.id.layoutNotMsg)
        val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.anim_fade_in)
        val anim = findViewById<LottieAnimationView>(R.id.animationView)
        anim.apply {
            startAnimation(animationFadeIn)
            repeatCount = LottieDrawable.INFINITE
            repeatMode = LottieDrawable.REVERSE
            setAnimation("not_msg.json")
        }.playAnimation()

        val recyclerView = findViewById<RecyclerView>(R.id.listMessages)
        val linearLayout = LinearLayoutManager(this)
        linearLayout.reverseLayout = true
        recyclerView.apply {
            layoutManager = linearLayout
            addItemDecoration(MessageItemDecoration(context))
            adapter = viewModel.adapter
        }

        viewModel.listMessages.observe(this){
            if (it.isEmpty()){
                layoutNotMsg.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }else{
                layoutNotMsg.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
            viewModel.adapter.setMessages(it)
        }

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
            inputMessage.setText("")
            inputMessage.clearFocus()
        }
    }
}