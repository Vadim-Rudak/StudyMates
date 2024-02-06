package com.vr.app.sh.ui.messages.allChats.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.domain.model.messages.UserInChat
import com.vr.app.sh.ui.base.MyChatsViewModelFactory
import com.vr.app.sh.ui.messages.allChats.adapter.ChatsViewAdapter
import com.vr.app.sh.ui.messages.allChats.adapter.UserItemDecoration
import com.vr.app.sh.ui.messages.allChats.viewModel.MyChatsViewModel
import com.vr.app.sh.ui.messages.chat.view.ChatWithUser

class ChatsFragment : Fragment() {

    @javax.inject.Inject
    lateinit var factory: MyChatsViewModelFactory

    lateinit var viewModel: MyChatsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.injectMyChats(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_chats, container, false)

        viewModel = ViewModelProvider(this, factory)
            .get(MyChatsViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.listMyChats)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(UserItemDecoration(context))
            adapter = viewModel.adapter
        }

        viewModel.listChatWithUser.observe(viewLifecycleOwner){
            viewModel.adapter.setMyChats(it)
        }

        viewModel.adapter.setListener(object :ChatsViewAdapter.Listener{
            override fun click(userInChat: UserInChat) {
                val intent = Intent(requireActivity(), ChatWithUser::class.java).apply {
                    putExtra("userId",userInChat.userId)
                    putExtra("userName",userInChat.user?.name)
                    putExtra("lastName",userInChat.user?.lastName)
                }
                startActivity(intent)
            }
        })

        return view
    }

}