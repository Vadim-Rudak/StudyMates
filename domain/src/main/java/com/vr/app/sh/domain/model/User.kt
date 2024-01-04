package com.vr.app.sh.domain.model
class User(){
    var id:Int=0
    var name:String?=null
    var lastName:String?=null
    var gender:String? = "man"
    var dateBirthday:String?=null
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


    override fun toString(): String {
        return "User(id=$id, name=$name, lastName=$lastName, gender=$gender, dateBirthday=$dateBirthday, cityLive=$cityLive, auth=$auth, school=$school, photo=$photo)"
    }
}


