package developer.abdulaziz.my_codial_app.Classes

class Talaba {
    var id: Int? = null
    var surname: String? = null
    var name: String? = null
    var lastname: String? = null
    var regDate: String? = null
    var myGuruh: Int? = null

    constructor()
    constructor(
        id: Int?,
        surname: String?,
        name: String?,
        lastname: String?,
        regDate: String?,
        myGuruh: Int?
    ) {
        this.id = id
        this.surname = surname
        this.name = name
        this.lastname = lastname
        this.regDate = regDate
        this.myGuruh = myGuruh
    }

    constructor(
        surname: String?,
        name: String?,
        lastname: String?,
        regDate: String?,
        myGuruh: Int?
    ) {
        this.surname = surname
        this.name = name
        this.lastname = lastname
        this.regDate = regDate
        this.myGuruh = myGuruh
    }

}