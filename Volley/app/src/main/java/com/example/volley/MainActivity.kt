package com.example.volley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import android.graphics.Bitmap

import com.android.volley.toolbox.ImageRequest

import com.android.volley.RequestQueue




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textview1)

        val btnurl:Button=findViewById(R.id.btnurl)

        btnurl.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val url = "https://run.mocky.io/v3/5849a142-82fb-4f67-a523-088e5cf132f8"
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener { response ->
                    textView.text=response.toString()

                                  },
                Response.ErrorListener { textView.text = "That didn't work!" })

            queue.add(stringRequest)
        }


        val btnjson:Button=findViewById(R.id.btnjson)
        btnjson.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val url = "https://run.mocky.io/v3/c4a2cf71-568a-485c-a789-43a5df39e52c"
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener { response ->
                    textView.text=response.toString()

                },
                Response.ErrorListener { textView.text = "That didn't work!" })

            queue.add(stringRequest)
        }

    }
}