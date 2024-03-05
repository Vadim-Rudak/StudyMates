package com.vr.app.sh.domain.model.messages

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vr.app.sh.domain.model.User

class UserInChat() {

    @SerializedName("id")
    @Expose
    var id:Int? = 0

    @SerializedName("chatid")
    @Expose
    var chatId:Int? = 0

    @SerializedName("userid")
    @Expose
    var userId:Int? = 0

    @SerializedName("gr")
    @Expose
    var group:Int? = 0

    var user:User? = null

    constructor(id: Int?, chatId: Int?, userId: Int?, group: Int?) : this() {
        this.id = id
        this.chatId = chatId
        this.userId = userId
        this.group = group
    }

    override fun toString(): String {
        return "UserInChat(id=$id, chatId=$chatId, userId=$userId, group=$group)"
    }


}