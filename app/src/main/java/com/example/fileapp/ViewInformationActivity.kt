package com.example.fileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.TextView
import java.io.BufferedReader
import java.io.File

class ViewInformationActivity : AppCompatActivity() {
    var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_information)

        textView = findViewById<View>(R.id.textView_get_saved_data) as TextView
    }

    fun showPublicData(view: View?) {

        val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        val file = File(folder, "QuintrixTrainingPublicFile.txt")
        val data = getdata(file)
        if(data != null) {
            textView!!.text = data
        }
        else {
            textView!!.text = "No Data Found"
        }
    }

    fun showPrivateData(view: View?) {

        val folder = getExternalFilesDir("AndroidKotlinTraining")

        val file = File(folder, "QuintrixTrainingPrivateFile.txt")
        val data = getdata(file)
        if (data != null) {
            textView!!.text = data
        }
        else {
            textView!!.text = "No Data Found"
        }
    }

    fun back(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun getdata(myfile: File): String? {

        var inputString = ""

        val bufferedReader: BufferedReader = myfile.bufferedReader()

        inputString = bufferedReader.use { it.readText() }

        return inputString
    }
}