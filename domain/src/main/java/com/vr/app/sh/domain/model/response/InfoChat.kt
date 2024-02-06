package com.vr.app.sh.domain.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vr.app.sh.domain.model.messages.Message
import com.vr.app.sh.domain.model.messages.UserInChat

class InfoChat {

    @SerializedName("usersInChatList")
    @Expose
    var usersInChatList: List<UserInChat>?=null

    @SerializedName("messagesList")
    @Expose
    var messagesList: List<Message>?=null
}