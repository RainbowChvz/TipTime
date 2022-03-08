package com.chaveze.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chaveze.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        if (cost == null) {
            updateTip( 0.0 )
            return
        }

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent              -> 0.2
            R.id.option_eighteen_percent            -> 0.18
            else /*R.id.option_fifteen_percent*/    -> 0.15
        }

        var tip = cost * tipPercentage
        if (binding.roundUpSwitch.isChecked)
            tip = kotlin.math.ceil(tip)

        updateTip( tip )
    }

    fun updateTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}