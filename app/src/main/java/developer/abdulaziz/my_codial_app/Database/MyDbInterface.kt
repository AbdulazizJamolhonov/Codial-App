package developer.abdulaziz.my_codial_app.Database

import developer.abdulaziz.my_codial_app.Classes.*

interface MyDbInterface {
    //    Kurslar
    fun createKurslar(kurslar: Kurslar)
    fun readKurslar(): ArrayList<Kurslar>
    fun updateKurslar(kurslar: Kurslar): Int
    fun deleteKurslar(kurslar: Kurslar)

    //    Mentorlar
    fun createMentor(mentor: Mentor)
    fun readMentor(): ArrayList<Mentor>
    fun updateMentor(mentor: Mentor): Int
    fun deleteMentor(mentor: Mentor)

    //    Talaba
    fun createTalaba(talaba: Talaba)
    fun readTalaba(): ArrayList<Talaba>
    fun updateTalaba(talaba: Talaba): Int
    fun deleteTalaba(talaba: Talaba)

    //    Group
    fun createGroup(group: Group)
    fun readGroup(): ArrayList<Group>
    fun updateGroup(group: Group): Int
    fun deleteGroup(group: Group)
    fun getMentorById(id: Int): Mentor
}