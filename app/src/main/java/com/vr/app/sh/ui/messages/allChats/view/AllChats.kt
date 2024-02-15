package com.vr.app.sh.ui.messages.allChats.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.ui.base.MyChatsViewModelFactory
import com.vr.app.sh.ui.base.SelectChatsViewModelFactory
import com.vr.app.sh.ui.menu.viewModel.MenuViewModel
import com.vr.app.sh.ui.messages.allChats.adapter.ChatsPagerAdapter
import com.vr.app.sh.ui.messages.allChats.adapter.SelectedUsersItemDecoration
import com.vr.app.sh.ui.messages.allChats.viewModel.AllChatsViewModel
import com.vr.app.sh.ui.messages.allChats.viewModel.MyChatsViewModel
import com.vr.app.sh.ui.messages.allUsers.view.AllUsers

class AllChats : AppCompatActivity() {

    @javax.inject.Inject
    lateinit var factory: SelectChatsViewModelFactory

    lateinit var viewModel: AllChatsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_chats)

        (applicationContext as App).appComponent.injectAllChats(this)

        viewModel = ViewModelProvider(this, factory)
            .get(AllChatsViewModel::class.java)

        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }
        val viewTitle = findViewById<TextView>(R.id.viewTitle)
        viewTitle.text = resources.getString(R.string.chatsTitle)
        val btnAllUsers = findViewById<ImageButton>(R.id.btn_all_users)
        btnAllUsers.setOnClickListener {
            Intent(this, AllUsers::class.java).also {
                startActivity(it)
            }
        }

        val recyclerSelectedUsers = findViewById<RecyclerView>(R.id.listSelectedUsers)
        recyclerSelectedUsers.apply {
            layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL, false)
            addItemDecoration(SelectedUsersItemDecoration(context))
            adapter = viewModel.adapterSelectedUsers
        }

        val tabBtnMyChats = findViewById<MaterialButton>(R.id.tapBtnChats)
        val tabBtnMyGroups = findViewById<MaterialButton>(R.id.tapBtnGroups)
        val fab = findViewById<FloatingActionButton>(R.id.FabChats)

        val viewPager = findViewById<ViewPager2>(R.id.pager_chats)
        viewPager.adapter = ChatsPagerAdapter(this)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                if (position==0){
                    fab.visibility = View.GONE
                    tabBtnMyChats.apply {
                        setBackgroundColor(ContextCompat.getColor(this@AllChats,R.color.first_color))
                        setTextColor(ContextCompat.getColor(this@AllChats,R.color.white))
                    }
                    tabBtnMyGroups.apply {
                        setBackgroundColor(ContextCompat.getColor(this@AllChats,R.color.gray2))
                        setTextColor(ContextCompat.getColor(this@AllChats,R.color.first_color))
                    }
                }else{
                    fab.visibility = View.VISIBLE
                    tabBtnMyChats.apply {
                        setBackgroundColor(ContextCompat.getColor(this@AllChats,R.color.gray2))
                        setTextColor(ContextCompat.getColor(this@AllChats,R.color.first_color))
                    }
                    tabBtnMyGroups.apply {
                        setBackgroundColor(ContextCompat.getColor(this@AllChats,R.color.first_color))
                        setTextColor(ContextCompat.getColor(this@AllChats,R.color.white))
                    }
                }
            }
        })
    }
}