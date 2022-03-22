package com.example.fileapp

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val EXTERNAL_STORAGE_PERMISSION_CODE = 23
    var editText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById<View>(R.id.editText_data) as EditText
    }

    fun savePublicly(view: View?) {

        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            EXTERNAL_STORAGE_PERMISSION_CODE
        )

        val editTextData = editText!!.text.toString()

        val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        val file = File(folder, "QuintrixTrainingPublicFile.txt")
        writeTextData(file, editTextData)
        editText!!.setText("")
    }

    fun savePrivately(view: View?) {
        val editTextData = editText!!.text.toString()

        val folder = getExternalFilesDir("AndroidKotlinTraining")

        val file = File(folder, "QuintrixTrainingPrivateFile.txt")
        writeTextData(file, editTextData)
        editText!!.setText("")
    }

    fun viewInformation(view: View?) {
        val intent = Intent(this@MainActivity, ViewInformationActivity::class.java)
        startActivity(intent)
    }

    private fun writeTextData(file: File, data: String) {
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(data.toByteArray())
            Toast.makeText(this, "Done" + file.absolutePath, Toast.LENGTH_LONG).show()
        } catch(e: Exception) {
            Toast.makeText(this, e.stackTraceToString(), Toast.LENGTH_LONG).show()
        } finally {
            if(fileOutputStream != null) {
                try {
                    fileOutputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}