package com.example.datapersistence

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun saveValueToSharedPref(valueToSave: String) {

        // Keeps this sharedPref Private and not shared between other applications

        val sharedPref = getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
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

            R.id.saveInternal -> {

                saveStringToInternalFile("cool")



            }

            R.id.loadInternal -> {


                txtInternal.text = readStringFromInteralFile()

            }

        }
    }

    fun saveStringToInternalFile(valueToSave: String) {
        val fileOutputStream = openFileOutput("example.txt", Context.MODE_PRIVATE)
        val outputStream = OutputStreamWriter(fileOutputStream)
        outputStream.write(valueToSave)
    }

    fun readStringFromInteralFile(): String {
        val fileInputStream = openFileInput("example.txt")
        val inputStreamReader = InputStreamReader(fileInputStream)

        var inputBuffer = BufferedReader(inputStreamReader)
        var sb = StringBuilder()
        var currentRead: String? = null

        while ({ currentRead = inputBuffer.readLine(); currentRead }() != null) {
            sb.append(currentRead)


        }

        inputStreamReader.close()
        return sb.toString()

    }

}
