package developer.abdulaziz.my_codial_app.Object

import developer.abdulaziz.my_codial_app.Classes.Group
import developer.abdulaziz.my_codial_app.Classes.Kurslar
import developer.abdulaziz.my_codial_app.Classes.Talaba

object MyObject {
    var kurslar = Kurslar()
    var positionKurs = -1

    var group = Group()
    var positionGuruh = -1
    var openClose = -1

    var talaba = Talaba()
    var psitionTalaba = -1

    val listTime = arrayListOf("12:00 - 14:00", "14:00 - 16:00", "16:00 - 18:00", "18:00 - 20:00")
}