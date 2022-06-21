package developer.abdulaziz.my_codial_app.Classes

class Group {
    var id: Int? = null
    var name: String? = null
    var mentor: Mentor? = null
    var time: String? = null
    var date: String? = null
    var openClose: Int? = null
    var myKurs: Int? = null

    constructor(
        id: Int?,
        name: String?,
        mentor: Mentor?,
        time: String?,
        date: String?,
        openClose: Int?,
        myKurs: Int?
    ) {
        this.id = id
        this.name = name
        this.mentor = mentor
        this.time = time
        this.date = date
        this.openClose = openClose
        this.myKurs = myKurs
    }

    constructor(
        name: String?,
        mentor: Mentor?,
        time: String?,
        date: String?,
        openClose: Int?,
        myKurs: Int?
    ) {
        this.name = name
        this.mentor = mentor
        this.time = time
        this.date = date
        this.openClose = openClose
        this.myKurs = myKurs
    }

    constructor()
}