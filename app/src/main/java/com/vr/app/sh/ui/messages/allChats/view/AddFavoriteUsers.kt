package com.vr.app.sh.ui.messages.allChats.view

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.ui.base.FavoriteUsersViewModelFactory
import com.vr.app.sh.ui.messages.allChats.adapter.UserItemDecoration
import com.vr.app.sh.ui.messages.allChats.viewModel.FavoriteUsersViewModel

class AddFavoriteUsers : AppCompatActivity() {

    @javax.inject.Inject
    lateinit var factory: FavoriteUsersViewModelFactory

    lateinit var viewModel: FavoriteUsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_favorite_users)

        (applicationContext as App).appComponent.injectFavoriteUsers(this)

        viewModel = ViewModelProvider(this, factory).get(FavoriteUsersViewModel::class.java)

        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }
        val viewTitle = findViewById<TextView>(R.id.viewTitle)
        viewTitle.text = resources.getString(R.string.favoriteUsersTitle)

        val listSelectUsers = findViewById<RecyclerView>(R.id.listUsers)
        listSelectUsers.apply {
            layoutManager = LinearLayoutManager(this@AddFavoriteUsers)
            addItemDecoration(UserItemDecoration(this@AddFavoriteUsers))
            adapter = viewModel.adapter
        }
    }
}