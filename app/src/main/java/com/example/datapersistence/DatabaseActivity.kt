package com.example.datapersistence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_database.*

class DatabaseActivity : AppCompatActivity(), PersonCallback {

    val databaseHelper by lazy{PersonDatabaseHelper(this)}
    val adapter by lazy{PersonAdapter(databaseHelper.getAllPeopleFromDatabase(), this)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
    }

    override fun passPerson(person: Person) {
        etFirstName.setText(person.firstName)
        etLastName.setText(person.lastName)
        etSsn.setText(person.ssn)


    }

    fun onClick(view: View) {
        when (view.id) {

            R.id.btnSave -> {
                val firstName = etFirstName.text.toString()
                val lastName = etLastName.text.toString()
                val ssn = etSsn.text.toString()
                databaseHelper.insertPersonIntoDatabase(Person(firstName,lastName,ssn))
            }
            R.id.btnAddPersonToList -> {
                val firstName = etFirstName.text.toString()
                val lastName = etLastName.text.toString()
                val ssn = etSsn.text.toString()
                databaseHelper.insertPersonIntoDatabase(Person(firstName,lastName,ssn))

            }
            R.id.btnUpdatePersonToList -> {
                val ssn = etSsn.text.toString()
            }

        }





    }

}
