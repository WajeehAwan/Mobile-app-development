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
import android.media.Image
import android.widget.ImageView
import com.android.volley.Request.Method.GET

import com.android.volley.toolbox.ImageRequest

import com.android.volley.RequestQueue




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textview1)

        val btnurl: Button = findViewById(R.id.btnurl)

        btnurl.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val url = "https://run.mocky.io/v3/5849a142-82fb-4f67-a523-088e5cf132f8"
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener { response ->
                    textView.text = response.toString()

                },
                Response.ErrorListener { textView.text = "That didn't work!" })

            queue.add(stringRequest)
        }


        val btnjson: Button = findViewById(R.id.btnjson)
        btnjson.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val url = "https://run.mocky.io/v3/c4a2cf71-568a-485c-a789-43a5df39e52c"
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener { response ->
                    textView.text = response.toString()

                },
                Response.ErrorListener { textView.text = "That didn't work!" })

            queue.add(stringRequest)
        }

        ivImageView = (ImageView)findViewById(R.id.);
        mRequestQueue = Volley.newRequestQueue(this.getApplicationContext());
        ImageRequest imageRequest = new ImageRequest(url, new BitmapListener(), 0, 0, null, null, new MyErrorListener());

        mRequestQueue.add(imageRequest);
    }

    private class BitmapListener implements Response.Listener<Bitmap> {
        @Override
        public void onResponse(Bitmap response) {
// response = your url's bitmap
            ivImageView.setImageBitmap(response);

        }
    }

    private class MyErrorListener implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
//store a default image if connection failed
            ivImageView.setImageResource(R.drawable.error_icon);
        }
    }
}
