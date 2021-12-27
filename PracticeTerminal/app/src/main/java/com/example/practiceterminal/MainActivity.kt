package com.example.practiceterminal

import android.app.*
import android.app.Notification.EXTRA_MESSAGES
import android.app.Notification.EXTRA_TEXT
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.provider.LiveFolders.INTENT
import android.text.format.DateFormat.is24HourFormat
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.NonCancellable.cancel
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    var day = 0
    var month = 0
    var year = 0

    var sday = 0
    var smonth = 0
    var syear = 0

    lateinit var receiver: AirplaneMode
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pickDate()
        receiver = AirplaneMode()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiver, it)
        }

        //Intents
        var btnIntent: Button = findViewById(R.id.btnintent)
        val te: TextView = findViewById(R.id.editTextTextPersonName)
        val message = te.toString()
        btnIntent.setOnClickListener {
            val sendIntent = Intent(this, secound::class.java).apply {
                //putExtra(EXTRA_MESSAGE, message)
            }
            try {
                startActivity(sendIntent)
            } catch (e: ActivityNotFoundException) {
                //log("No class found")
            }
        }
        //Intents Ends
        fun onCreateOptionsMenu() {

        }
        //Alerts
        val btnalert: Button = findViewById(R.id.btnalert)
        val option = arrayOf("First option", "secound option", "third option")

        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Its is an alert")
            .setSingleChoiceItems(option, 0) { DialogInterface, i ->
                Toast.makeText(this, "You clicked on ${option[i]}", Toast.LENGTH_LONG).show()
            }
//                .setMessage("Hello I am an alert")
//                .setIcon(R.drawable.ic_baseline_notifications_24)
            .setPositiveButton("Yes") { DialogInterface, _ ->
                Toast.makeText(this, "Do you want to add new alert", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("No") { DialogInterface, _ ->
                Toast.makeText(this, "No alert", Toast.LENGTH_LONG).show()
            }.create()

        btnalert.setOnClickListener {
            alertDialog.show()
        }
        // Alerts Ends
    }

    // Date And Time Picker
    private fun calenderdate() {
        val cal: Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)

    }

    private fun pickDate() {
        val btndate: Button = findViewById(R.id.btndate)
        btndate.setOnClickListener {
            calenderdate()
            DatePickerDialog(this, this, year, month, day).show()
        }
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        sday = day
        smonth = month
        syear = year
        val te: TextView = findViewById(R.id.editTextTextPersonName)
        DatePickerDialog(this, this, sday, smonth, syear)

        te.setText("${syear},${smonth},${syear}")
    }
    // Ends Date and time code

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.example_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> Toast.makeText(this, "First Item you clicked on", Toast.LENGTH_LONG)
                .show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateNavigateUpTaskStack(builder: TaskStackBuilder?) {
        super.onCreateNavigateUpTaskStack(builder)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }

}



