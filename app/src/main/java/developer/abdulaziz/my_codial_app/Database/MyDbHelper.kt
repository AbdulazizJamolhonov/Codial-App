package developer.abdulaziz.my_codial_app.Database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import developer.abdulaziz.my_codial_app.Classes.Group
import developer.abdulaziz.my_codial_app.Classes.Kurslar
import developer.abdulaziz.my_codial_app.Classes.Mentor
import developer.abdulaziz.my_codial_app.Classes.Talaba

@SuppressLint("Recycle")
class MyDbHelper(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, VERSION), MyDbInterface {
    companion object {
        const val DB_NAME = "Codial"
        const val VERSION = 1

        //        Kurslar
        const val KURSLAR_TABLE = "Kurslar"
        const val KURSLAR_ID = "KurslarId"
        const val KURSLAR_NAME = "KurslarName"
        const val KURSLAR_ABOUT = "KurslarAbout"

        //        Mentorar
        const val MENTOR_TABLE = "Mentor"
        const val MENTOR_ID = "MentorId"
        const val MENTOR_SURNAME = "MentorSurname"
        const val MENTOR_NAME = "MentorName"
        const val MENTOR_LASTNAME = "MentorLastname"
        const val MENTOR_KURS = "MentorKurs"

        //        Talabalar
        const val TALABA_TABLE = "Talaba"
        const val TALABA_ID = "TalabaId"
        const val TALABA_SURNAME = "TalabaSurname"
        const val TALABA_NAME = "TalabaName"
        const val TALABA_LASTNAME = "TalabaLastname"
        const val TALABA_REG_DATA = "TalabaRegData"
        const val TALABA_GROUP = "TalabaGroup"

        //        Group
        const val GROUP_TABLE = "Gruppalar"
        const val GROUP_ID = "GroupId"
        const val GROUP_NAME = "GroupNAME"
        const val GROUP_MENTOR = "GroupMentor"
        const val GROUP_TIME = "GroupTime"
        const val GROUP_DATE = "GroupDate"
        const val GROUP_MYOPEN = "GroupMyOper"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val kurslar =
            "CREATE TABLE $KURSLAR_TABLE ($KURSLAR_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $KURSLAR_NAME TEXT NOT NULL, $KURSLAR_ABOUT TEXT NOT NULL)"
        val mentor =
            "CREATE TABLE $MENTOR_TABLE ($MENTOR_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $MENTOR_SURNAME TEXT NOT NULL, $MENTOR_NAME TEXT NOT NULL, $MENTOR_LASTNAME TEXT NOT NULL, $MENTOR_KURS INTEGER NOT NULL)"
        val talaba =
            "CREATE TABLE $TALABA_TABLE ($TALABA_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $TALABA_SURNAME TEXT NOT NULL, $TALABA_NAME TEXT NOT NULL, $TALABA_LASTNAME TEXT NOT NULL, $TALABA_REG_DATA TEXT NOT NULL, $TALABA_GROUP INTEGER NOT NULL)"
        val group =
            "CREATE TABLE $GROUP_TABLE ($GROUP_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $GROUP_NAME TEXT NOT NULL, $GROUP_MENTOR INTEGER NOT NULL, $GROUP_TIME TEXT NOT NULL, $GROUP_DATE TEXT NOT NULL, $GROUP_MYOPEN INTEGER NOT NULL, FOREIGN KEY ($GROUP_MENTOR) REFERENCES $MENTOR_TABLE ($MENTOR_ID))"
        p0?.execSQL(kurslar)
        p0?.execSQL(mentor)
        p0?.execSQL(talaba)
        p0?.execSQL(group)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    /**    Kurs    */
    override fun createKurslar(kurslar: Kurslar) {
        val dataBase = writableDatabase
        val contentValue = ContentValues().apply {
            put(KURSLAR_NAME, kurslar.name)
            put(KURSLAR_ABOUT, kurslar.about)
        }
        dataBase.insert(KURSLAR_TABLE, null, contentValue)
        dataBase.close()
    }

    override fun readKurslar(): ArrayList<Kurslar> {
        val list = ArrayList<Kurslar>()
        val query = "SELECT * FROM $KURSLAR_TABLE"
        val database = readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val user = Kurslar(cursor.getInt(0), cursor.getString(1), cursor.getString(2))
                list.add(user)
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun updateKurslar(kurslar: Kurslar): Int {
        val contentValue = ContentValues()
        contentValue.put(KURSLAR_ID, kurslar.id)
        contentValue.put(KURSLAR_NAME, kurslar.name)
        contentValue.put(KURSLAR_ABOUT, kurslar.about)
        return writableDatabase.update(
            KURSLAR_TABLE,
            contentValue,
            "$KURSLAR_ID = ?",
            arrayOf("${kurslar.id}")
        )
    }

    override fun deleteKurslar(kurslar: Kurslar) {
        val database = writableDatabase
        database.delete(KURSLAR_TABLE, "$KURSLAR_ID = ?", arrayOf("${kurslar.id}"))
        database.close()
    }

    /**    Mentor    */
    override fun createMentor(mentor: Mentor) {
        val dataBase = writableDatabase
        val contentValue = ContentValues().apply {
            put(MENTOR_SURNAME, mentor.surname)
            put(MENTOR_NAME, mentor.name)
            put(MENTOR_LASTNAME, mentor.lastname)
            put(MENTOR_KURS, mentor.myKurs)
        }
        dataBase.insert(MENTOR_TABLE, null, contentValue)
        dataBase.close()
    }

    override fun readMentor(): ArrayList<Mentor> {
        val list = ArrayList<Mentor>()
        val query = "SELECT * FROM $MENTOR_TABLE"
        val database = readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val mentor = Mentor(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4)
                )
                list.add(mentor)
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun updateMentor(mentor: Mentor): Int {
        val contentValue = ContentValues()
        contentValue.put(MENTOR_ID, mentor.id)
        contentValue.put(MENTOR_SURNAME, mentor.surname)
        contentValue.put(MENTOR_NAME, mentor.name)
        contentValue.put(MENTOR_LASTNAME, mentor.lastname)
        contentValue.put(MENTOR_KURS, mentor.myKurs)
        return writableDatabase.update(
            MENTOR_TABLE,
            contentValue,
            "$MENTOR_ID = ?",
            arrayOf("${mentor.id}")
        )
    }

    override fun deleteMentor(mentor: Mentor) {
        val database = writableDatabase
        database.delete(MENTOR_TABLE, "$MENTOR_ID = ?", arrayOf("${mentor.id}"))
        database.close()
    }

    /**    Talaba    */
    override fun createTalaba(talaba: Talaba) {
        val dataBase = writableDatabase
        val contentValue = ContentValues().apply {
            put(TALABA_SURNAME, talaba.surname)
            put(TALABA_NAME, talaba.name)
            put(TALABA_LASTNAME, talaba.lastname)
            put(TALABA_REG_DATA, talaba.regDate)
            put(TALABA_GROUP, talaba.myGuruh)
        }
        dataBase.insert(TALABA_TABLE, null, contentValue)
        dataBase.close()
    }

    override fun readTalaba(): ArrayList<Talaba> {
        val list = ArrayList<Talaba>()
        val query = "SELECT * FROM $TALABA_TABLE"
        val database = readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val talaba = Talaba(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5)
                )
                list.add(talaba)
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun updateTalaba(talaba: Talaba): Int {
        val contentValue = ContentValues().apply {
            put(TALABA_ID, talaba.id)
            put(TALABA_SURNAME, talaba.surname)
            put(TALABA_NAME, talaba.name)
            put(TALABA_LASTNAME, talaba.lastname)
            put(TALABA_REG_DATA, talaba.regDate)
            put(TALABA_GROUP, talaba.myGuruh)
        }
        return writableDatabase.update(
            TALABA_TABLE,
            contentValue,
            "$TALABA_ID = ?",
            arrayOf("${talaba.id}")
        )
    }

    override fun deleteTalaba(talaba: Talaba) {
        val database = writableDatabase
        database.delete(TALABA_TABLE, "$TALABA_ID = ?", arrayOf("${talaba.id}"))
        database.close()
    }

    /**    Gruppa    */
    override fun createGroup(group: Group) {
        val dataBase = writableDatabase
        val contentValue = ContentValues().apply {
            put(GROUP_NAME, group.name)
            put(GROUP_MENTOR, group.mentor!!.id)
            put(GROUP_TIME, group.time)
            put(GROUP_DATE, group.date)
            put(GROUP_MYOPEN, group.openClose)
        }
        dataBase.insert(GROUP_TABLE, null, contentValue)
        dataBase.close()
    }

    override fun readGroup(): ArrayList<Group> {
        val list = ArrayList<Group>()
        val query = "SELECT * FROM $GROUP_TABLE"
        val database = readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val group = Group(
                    cursor.getInt(0),
                    cursor.getString(1),
                    getMentorById(cursor.getInt(2)),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5)
                )
                list.add(group)
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun updateGroup(group: Group): Int {
        val contentValue = ContentValues()
        contentValue.put(GROUP_ID, group.id)
        contentValue.put(GROUP_NAME, group.name)
        contentValue.put(GROUP_MENTOR, group.mentor?.id)
        contentValue.put(GROUP_TIME, group.time)
        contentValue.put(GROUP_DATE, group.date)
        contentValue.put(GROUP_MYOPEN, group.openClose)
        return writableDatabase.update(
            GROUP_TABLE,
            contentValue,
            "$GROUP_ID = ?",
            arrayOf("${group.id}")
        )
    }

    override fun deleteGroup(group: Group) {
        val database = writableDatabase
        database.delete(GROUP_TABLE, "$GROUP_ID = ?", arrayOf("${group.id}"))
        database.close()
    }

    override fun getMentorById(id: Int): Mentor {
        val db = readableDatabase
        val cursor = db.query(
            MENTOR_TABLE,
            arrayOf(
                MENTOR_ID,
                MENTOR_SURNAME,
                MENTOR_NAME,
                MENTOR_LASTNAME,
                MENTOR_KURS
            ),
            "$MENTOR_ID = ?",
            arrayOf(id.toString()),
            null, null, null
        )
        cursor.moveToFirst()
        return Mentor(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            cursor.getInt(4)
        )
    }
}