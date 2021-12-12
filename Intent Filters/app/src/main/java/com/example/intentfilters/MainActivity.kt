package com.example.intentfilters

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn_web:Button=findViewById(R.id.btn_weburl)

        btn_web.setOnClickListener{
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://google.com/android-applications/")
                )
            )
        }

        var btn_ph:Button=findViewById(R.id.Btn_phnumber)
        var text_phone: EditText = findViewById<EditText>(R.id.text_phone)

        btn_ph.setOnClickListener{
            var uri = Uri.parse("tel:"+text_phone.text.toString())
            startActivity(Intent(Intent.ACTION_VIEW,uri))
        }
    }
}