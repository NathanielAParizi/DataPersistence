package com.example.datapersistence

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

const val STRING_KEY = "string_key"


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun saveValueToSharedPref(valueToSave: String) {

        val sharedPref = getSharedPreferences(
            "shared_pref",
            Context.MODE_PRIVATE
        ) // Keeps this sharedPref Private and not shared between other applications
        val editor = sharedPref.edit()
        editor.putString(STRING_KEY, valueToSave)
        editor.apply()
    }

    fun getValueFromSharedPref() {

        val sharedPref = getSharedPreferences(
            "shared_pref",
            Context.MODE_PRIVATE
        ) // Keeps this sharedPref Private and not shared between other applications
        val currentVal = sharedPref.getString(STRING_KEY, "no value currently saved")

        txtDisplay.text = currentVal


    }

    fun onClick(view: View) {

        when (view.id) {

            R.id.btnLoad -> {

                getValueFromSharedPref()
            }

            R.id.btnSave -> {

                saveValueToSharedPref(etSharedPrefs.text.toString())
            }

        }
    }

}
