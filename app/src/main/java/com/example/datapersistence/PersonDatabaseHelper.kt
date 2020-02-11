package com.example.datapersistence

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class PersonDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    override fun onCreate(sqLiteDatabase: SQLiteDatabase?) {

        // Don't want to pass a nullified database
        sqLiteDatabase?.execSQL(CREATE_PERSON_TABLE)

    }

    override fun onUpgrade(sqlLiteDatabase: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onCreate(sqlLiteDatabase)

    }

    fun insertPersonIntoDatabase(person: Person) {

        val database = writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COLUMN_FIRSTNAME, person.firstName)
        contentValues.put(COLUMN_LASTNAME, person.lastName)
        contentValues.put(COLUMN_SSN, person.ssn)

        database.insert(TABLE_NAME, null, contentValues)
        database.close()

    }

    fun getOnePersonFromDatabase(ssn: String): Person? {

        val database = readableDatabase
        var person: Person? = null


        val cursor = database.rawQuery(
            "SELECT * FROM $TABLE_NAME WHERE $COLUMN_SSN =  '$ssn'", null
        )



        if (cursor.moveToFirst()) {

            val firstName = cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME))
            val lastName = cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME))
            val ssn = cursor.getString(cursor.getColumnIndex(COLUMN_SSN))

            val resultPerson = Person(firstName, lastName, ssn)

            return resultPerson
        }

        cursor.close()
        database.close()
        return null

    }

    fun getAllPeopleFromDatabase(): ArrayList<Person> {

        val database = readableDatabase
        var personList: ArrayList<Person> = ArrayList<Person>()
        var person: Person? = null


        val cursor = database.rawQuery(
            "SELECT * FROM $TABLE_NAME", null
        )

        if (cursor.moveToFirst()) {

            do {
                val firstName = cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME))
                val lastName = cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME))
                val ssn = cursor.getString(cursor.getColumnIndex(COLUMN_SSN))
                val person = Person(firstName, lastName, ssn)
                personList.add(person)

            } while (cursor.moveToNext())

        }

        cursor.close()
        database.close()
        return personList

    }

    fun updatePersonInDatabase(person: Person) {

        val database = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_FIRSTNAME, person.firstName)
        contentValues.put(COLUMN_LASTNAME, person.lastName)
        contentValues.put(COLUMN_SSN, person.ssn)

        database.update(TABLE_NAME, contentValues, "$COLUMN_SSN = '?'", arrayOf(person.ssn))
        database.close()

    }

    fun removePersonFromDatabase(ssn: String) {

        val database = writableDatabase
        database.delete(TABLE_NAME, "$COLUMN_SSN = '?'", arrayOf(ssn))
        database.close()

    }
}