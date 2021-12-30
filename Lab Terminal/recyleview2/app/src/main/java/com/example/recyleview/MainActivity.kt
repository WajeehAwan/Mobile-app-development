package com.example.recyleview

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.recyleview.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var bindview: ActivityMainBinding
    private lateinit var binding: ActivityMainBinding
//    var adapterSocial: customadapter? = null
//    var mList = ArrayList<customadapter>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindview= ActivityMainBinding.inflate(layoutInflater)


        btnservices.setOnClickListener {
            val intent = Intent(this, Services::class.java)
            startActivity(intent)
        }

        reccyle.layoutManager=LinearLayoutManager(this)
        reccyle.adapter= customadapter()


        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.RECEIVE_SMS) !=
            PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.RECEIVE_SMS,android.Manifest.permission.SEND_SMS),
                111
            )
        }
        else{
            reciveMsg()
        }
        bindview.btnbuy.setOnClickListener{
            var sms = SmsManager.getDefault()
            sms.sendTextMessage(phnumber.text.toString(),"ME",message.text.toString(),null,null)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 111&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
            reciveMsg()
        }
    }

    private fun reciveMsg() {
        val br = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                    for (sms in Telephony.Sms.Intents.getMessagesFromIntent(intent)){
                        Toast.makeText(applicationContext,sms.displayMessageBody, Toast.LENGTH_LONG).show()
                        bindview.phnumber.setText(sms.originatingAddress)
                        bindview.message.setText(sms.displayMessageBody)
                    }
                }
            }

        }
        registerReceiver(br, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))
    }

}



//        val requestQueue = Volley.newRequestQueue(this)
//
//        val jsonObjectRequest = JsonObjectRequest(
//            Request.Method.GET, url, null,
//            { response ->
//                for (i in 0..2) {
//                    val title = response.getJSONArray("data").getJSONObject(i).getString("t")
//                    val quantity = response.getJSONArray("data").getJSONObject(i).getInt("q")
//                    mList.add(ItemModel(title, quantity))
//
//                    adapterSocial = customadapter(mList)
//                    adapterSocial?.mListener = this
//                    binding.apply {
//                        recyclerView.apply {
//                            layoutManager = LinearLayoutManager(this@MainActivity)
//                            adapter = adapterSocial
//                        }
//                    }
//                }
//            },
//            { error ->
//
//            }
//        )
//
//        requestQueue.add(jsonObjectRequest)