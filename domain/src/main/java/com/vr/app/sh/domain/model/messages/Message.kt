package com.vr.app.sh.domain.model.messages

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Message() {

    @SerializedName("id")
    @Expose
    var id:Int = 0

    @SerializedName("chatid")
    @Expose
    var chatId:Int = 0

    @SerializedName("usertosendid")
    @Expose
    var userToSendId:Int = 0

    @SerializedName("type")
    @Expose
    var type:Int = 0

    @SerializedName("timesend")
    @Expose
    var timeSend:Int = 0

    @SerializedName("res")
    @Expose
    var res:String = ""

    constructor(id: Int, chatId: Int, userToSendId: Int, type: Int, timeSend: Int, res: String) : this() {
        this.id = id
        this.chatId = chatId
        this.userToSendId = userToSendId
        this.type = type
        this.timeSend = timeSend
        this.res = res
    }

    constructor(chatId: Int,userToSendId: Int, type: Int, res: String) : this() {
        this.chatId = chatId
        this.userToSendId = userToSendId
        this.type = type
        this.res = res
    }

    override fun toString(): String {
        return "Message(id=$id, chatId=$chatId, userToSendId=$userToSendId, type=$type, timeSend=$timeSend, res='$res')"
    }


}