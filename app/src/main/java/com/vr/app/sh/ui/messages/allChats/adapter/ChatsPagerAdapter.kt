package com.vr.app.sh.ui.messages.allChats.adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vr.app.sh.ui.messages.allChats.view.ChatsFragment
import com.vr.app.sh.ui.messages.allChats.view.GroupsFragment

class ChatsPagerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int) = if (position==0) ChatsFragment() else GroupsFragment()
}