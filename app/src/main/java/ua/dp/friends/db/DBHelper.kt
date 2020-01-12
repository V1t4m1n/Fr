package ua.dp.friends.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ua.dp.friends.utils.User


class DBHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME($COL_ID INTEGER PRIMARY KEY, $COL_TITLE TEXT, $COL_FIRST TXT," +
            " $COL_LAST TEXT, $COL_URL_AVATAR TEXT, $COL_EMAIL TEXT, $COL_GENDER TEXT, $COL_PHONE TEXT ")

    val DROP_TABLE = ("DROP TABLE IF EXITS $TABLE_NAME")

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "FriendsDB.db"

        //Table name
        private val TABLE_NAME = "Friends"

        private val COL_ID = "Id"

        private val COL_TITLE = "Title"
        private val COL_FIRST = "First"
        private val COL_LAST = "Last"

        private val COL_URL_AVATAR = "Avatar"

        private val COL_GENDER = "Gender"
        private val COL_PHONE = "Phone"
        private val COL_EMAIL = "Email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(DROP_TABLE)
        onCreate(db!!)
    }


    val AllFriendsList: List<User>
        get() {
            val listFr = ArrayList<User>()
            val selectQuery = "SELECT *FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)

            if(cursor.moveToFirst()) {

                do {
                    val friend = User()
                    friend.Id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    friend.titleName = cursor.getString(cursor.getColumnIndex(COL_TITLE))
                    friend.firstName = cursor.getString(cursor.getColumnIndex(COL_TITLE))
                    friend.lastName = cursor.getString(cursor.getColumnIndex(COL_LAST))
                    friend.email = cursor.getString(cursor.getColumnIndex(COL_EMAIL))
                    friend.gender = cursor.getString(cursor.getColumnIndex(COL_GENDER))
                    friend.phone = cursor.getString(cursor.getColumnIndex(COL_PHONE))
                    friend.large = cursor.getString(cursor.getColumnIndex(COL_URL_AVATAR))

                    listFr.add(friend)
                } while (cursor.moveToNext())
            }
            db.close()
            return listFr
        }

    fun AddFriend(friend: User) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COL_ID, friend.Id)
        values.put(COL_TITLE, friend.titleName)
        values.put(COL_FIRST, friend.firstName)
        values.put(COL_LAST, friend.lastName)
        values.put(COL_PHONE, friend.phone)
        values.put(COL_GENDER, friend.gender)
        values.put(COL_EMAIL, friend.email)
        values.put(COL_URL_AVATAR, friend.medium)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun UpdateFriend(friend: User) : Int{
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COL_ID, friend.Id)
        values.put(COL_TITLE, friend.titleName)
        values.put(COL_FIRST, friend.firstName)
        values.put(COL_LAST, friend.lastName)
        values.put(COL_PHONE, friend.phone)
        values.put(COL_GENDER, friend.gender)
        values.put(COL_EMAIL, friend.email)
        values.put(COL_URL_AVATAR, friend.medium)

        return db.update(TABLE_NAME, values, "$COL_ID=?", arrayOf(friend.Id.toString()))
    }

    fun DeleteFriend(friend: User){
        val db = this.writableDatabase

        db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(friend.Id.toString()))
        db.close()
    }
}