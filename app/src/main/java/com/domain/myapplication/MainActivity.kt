package com.domain.myapplication

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.domain.myapplication.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var isLastInputNumber = false
    var hasDotInTextView = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }

    fun clickOnNumber(view: View) {
        val button = view as Button
        binding.textViewShow.append(button.text)
        isLastInputNumber = true
    }

    fun clickOnDot(view: View) {
        if (isLastInputNumber && !hasDotInTextView) {
            binding.textViewShow.append(".")
            hasDotInTextView = true
        }
    }

    fun clickOnClear(view: View) {
        binding.textViewShow.text = ""
        isLastInputNumber = false
        hasDotInTextView = false
    }

    fun onOperatorClicked(view: View) {
        val button = view as Button
        if (isLastInputNumber && !isOperatorExist(binding.textViewShow.text.toString())) {
            binding.textViewShow.append(button.text)
            isLastInputNumber = false
            hasDotInTextView = false
        }
    }

    fun isOperatorExist(input: String): Boolean {
        if (input.startsWith("-")) {
            return false
        }
        if (input.contains("+") || input.contains("-") || input.contains("/") || input.contains("*")) {
            return true
        } else {
            return false
        }
    }

    fun clickOnEqual(view: View) {
        var prefix = ""

        if (!isLastInputNumber) return

        var inputValue = binding.textViewShow.text.toString()

        if (inputValue.contains("+")) {
            val splitValueArray = inputValue.split("+")
            val firstNumber = splitValueArray[0]
            val secondNumber = splitValueArray[1]
            val result = firstNumber.toDouble() + secondNumber.toDouble()
            binding.textViewShow.text = result.toString()
        }
        if (inputValue.contains("/")) {
            val splitValueArray = inputValue.split("/")
            val firstNumber = splitValueArray[0]
            val secondNumber = splitValueArray[1]
            val result = firstNumber.toDouble() / secondNumber.toDouble()
            binding.textViewShow.text = result.toString()
        }
        if (inputValue.contains("*")) {
            val splitValueArray = inputValue.split("*")
            val firstNumber = splitValueArray[0]
            val secondNumber = splitValueArray[1]
            val result = firstNumber.toDouble() * secondNumber.toDouble()
            binding.textViewShow.text = result.toString()
        }
        if (inputValue.startsWith("-")) {
            prefix = "-"
            inputValue = inputValue.substring(1)
        }

        if (inputValue.contains("-")) {
            val splitValueArray = inputValue.split("-")
            var firstNumber = splitValueArray[0]
            val secondNumber = splitValueArray[1]
            if (!prefix.isEmpty()) {
                firstNumber = prefix + firstNumber
            }
            val result = firstNumber.toDouble() - secondNumber.toDouble()
            binding.textViewShow.text = result.toString()
        }


    }


}
