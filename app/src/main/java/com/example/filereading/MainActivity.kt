package com.example.filereading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    var FILE_NAME = "myfile.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*Button SAVE*/
        save.setOnClickListener({
            var msg:String=txtid.text.toString()
            txtid.setText("")
            Toast.makeText(this,"Data Added to file 1",Toast.LENGTH_LONG).show()
            var appFile=checkandcreateDir() // /sdcard/SqlExampleApp/myfie.txt
            var fos:FileOutputStream = FileOutputStream(appFile)
            fos.write(msg.encodeToByteArray())
            fos.close()
        })

        /*Read Button*/
        Read.setOnClickListener({
            var appFile=checkandcreateDir() //sdcard/SqlExampleApp/myfile.txt
            var fis= FileInputStream(appFile)
            val b= ByteArray(fis.available())
            fis.read(b)
            fis.close()
            txtid.setText(String(b))
        })





        save1.setOnClickListener({
            var msg:String=txtid.text.toString()
            txtid.setText("")
            Toast.makeText(this,"Data Added to file 2",Toast.LENGTH_LONG).show()
            var appFile=checkandcreateDir1() // /sdcard/SqlExampleApp/myfie.txt
            var fos:FileOutputStream = FileOutputStream(appFile)
            fos.write(msg.encodeToByteArray())
            fos.close()
        })

        /*Read Button*/
        Read1.setOnClickListener({
            var appFile=checkandcreateDir1() //sdcard/SqlExampleApp/myfile.txt
            var fis= FileInputStream(appFile)
            val b= ByteArray(fis.available())
            fis.read(b)
            fis.close()
            txtid.setText(String(b))
        })




        save2.setOnClickListener({
            var msg:String=txtid.text.toString()
            txtid.setText("")
            Toast.makeText(this,"Data Added to file 3",Toast.LENGTH_LONG).show()
            var appFile=checkandcreateDir2() // /sdcard/SqlExampleApp/myfie.txt
            var fos:FileOutputStream = FileOutputStream(appFile)
            fos.write(msg.encodeToByteArray())
            fos.close()
        })

        /*Read Button*/
        Read2.setOnClickListener({
            var appFile=checkandcreateDir2() //sdcard/SqlExampleApp/myfile.txt
            var fis= FileInputStream(appFile)
            val b= ByteArray(fis.available())
            fis.read(b)
            fis.close()
            txtid.setText(String(b))
        })





    }
    private fun checkandcreateDir(): File {
        var appFile:File //null
        appFile = getExternalFilesDir("/storage/emulated/0/")!!
        Log.d("file path",appFile.toString())// /sdcard
        appFile= File(appFile,getString(R.string.app_name))// /sdcard/SqlExampleApp
        if(!appFile.exists()) // check if exists
        {
            if(appFile.mkdir()) // /sdcard/sqlExampleApp
            {
                Toast.makeText(this,"File Created",Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(this,"File not Created",Toast.LENGTH_LONG).show()
        }
        /*file Already Exists*/
        appFile=File(appFile,FILE_NAME)
        return appFile // /sdcard/SqlExampleApp/myfile.txt
    }

    private fun checkandcreateDir1(): File {
        var appFile:File //null
        appFile = getExternalFilesDir("/storage/emulated/0/")!!
        Log.d("file path",appFile.toString())// /sdcard
        appFile= File(appFile,getString(R.string.app_name))// /sdcard/SqlExampleApp
        if(!appFile.exists()) // check if exists
        {
            if(appFile.mkdir()) // /sdcard/sqlExampleApp
            {
                Toast.makeText(this,"File Created",Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(this,"File not Created",Toast.LENGTH_LONG).show()
        }
        /*file Already Exists*/
        appFile=File(appFile,"FILE_NAME.txt")
        return appFile // /sdcard/SqlExampleApp/myfile.txt
    }
    private fun checkandcreateDir2(): File {
        var appFile:File //null
        appFile = getExternalFilesDir("/storage/emulated/0/")!!
        Log.d("file path",appFile.toString())// /sdcard
        appFile= File(appFile,getString(R.string.app_name))// /sdcard/SqlExampleApp
        if(!appFile.exists()) // check if exists
        {
            if(appFile.mkdir()) // /sdcard/sqlExampleApp
            {
                Toast.makeText(this,"File Created",Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(this,"File not Created",Toast.LENGTH_LONG).show()
        }
        /*file Already Exists*/
        appFile=File(appFile,"FILE_NAME1.txt")
        return appFile // /sdcard/SqlExampleApp/myfile.txt
    }
}