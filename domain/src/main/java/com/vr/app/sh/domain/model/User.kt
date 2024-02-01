package com.vr.app.sh.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User(){
    @SerializedName("id")
    @Expose
    var id:Int=0

    @SerializedName("name")
    @Expose
    var name:String?=null

    @SerializedName("lastName")
    @Expose
    var lastName:String?=null

    @SerializedName("gender")
    @Expose
    var gender:String? = "man"

    @SerializedName("dateBirthday")
    @Expose
    var dateBirthday:String?=null

    @SerializedName("cityLive")
    @Expose
    var cityLive:String?=null
    var auth = Auth()
    var school = School()
    var photo = Photo()

    constructor(
        id: Int,
        name: String?,
        lastName: String?,
        gender: String?,
        dateBirthday: String?,
        cityLive: String?,
        auth: Auth,
        school: School,
        photo: Photo
    ) : this() {
        this.id = id
        this.name = name
        this.lastName = lastName
        this.gender = gender
        this.dateBirthday = dateBirthday
        this.cityLive = cityLive
        this.auth = auth
        this.school = school
        this.photo = photo
    }

    constructor(
        id: Int,
        name: String?,
        lastName: String?,
        gender: String?,
        dateBirthday: String?,
        cityLive: String?
    ) : this() {
        this.id = id
        this.name = name
        this.lastName = lastName
        this.gender = gender
        this.dateBirthday = dateBirthday
        this.cityLive = cityLive
    }


    override fun toString(): String {
        return "User(id=$id, name=$name, lastName=$lastName, gender=$gender, dateBirthday=$dateBirthday, cityLive=$cityLive, auth=$auth, school=$school, photo=$photo)"
    }
}


