package com.stevenswang.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.stevenswang.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelectDate.setOnClickListener { view ->
            clickDatePicker(view)
        }
        

    }

    private fun clickDatePicker(view: View) {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "${selectedMonth + 1}/$selectedDayOfMonth/$selectedYear"
                binding.tvSelectedDate.text = selectedDate

                val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate!!.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMinutes = currentDate!!.time / 60000
                val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes

                binding.tvAgeInMinutes.text = differenceInMinutes.toString()
        }, year, month, day)

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }

}