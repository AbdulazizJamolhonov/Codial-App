package developer.abdulaziz.my_codial_app.Classes

class Mentor {
    var id: Int? = null
    var surname: String? = null
    var name: String? = null
    var number: String? = null
    var myKurs: Int? = null

    constructor()
    constructor(id: Int?, surname: String?, name: String?, number: String?, myKurs: Int?) {
        this.id = id
        this.surname = surname
        this.name = name
        this.number = number
        this.myKurs = myKurs
    }

    constructor(surname: String?, name: String?, number: String?, myKurs: Int?) {
        this.surname = surname
        this.name = name
        this.number = number
        this.myKurs = myKurs
    }
}