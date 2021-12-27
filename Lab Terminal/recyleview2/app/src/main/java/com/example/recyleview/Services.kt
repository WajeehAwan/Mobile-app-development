package com.example.recyleview

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.TextView
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_services.*
import org.w3c.dom.Text
import java.util.*

class Services : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services)
        btnstart.setOnClickListener{
            Intent(this,Airplanemode::class.java).also {
                startService(it)
                txt1.text="Services is Running"
            }
        }
        btnstop.setOnClickListener{
            Intent(this,Airplanemode::class.java).also {
                stopService(it)
                txt1.text="Services is Stop"
            }
        }

    }

}

