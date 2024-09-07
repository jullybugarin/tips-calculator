package com.example.tips_calculator

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tips_calculator.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }


        var percentage = 0
        binding.btnOp1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                percentage = 10
        }
        binding.btnOp2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                percentage = 15
        }
        binding.btnOp3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                percentage = 20
        }


        binding.btnCalculate.setOnClickListener {
            val totalTableTemp = binding.tieTotal.text
            val totalPeopleTemp = binding.tiePeople.text
            if (totalPeopleTemp?.isEmpty() == true || totalPeopleTemp?.isEmpty() == true
            ) {
                Snackbar
                    .make(binding.tieTotal, "Fill in all empty fields", Snackbar.LENGTH_LONG)
                    .show()

            } else {

                val totalPrice: Float = binding.tieTotal.text.toString().toFloat()
                val totalPeople: Int = binding.tiePeople.text.toString().toInt()

                val totalTemp = totalPrice / totalPeople
                val tips = totalTemp * percentage / 100
                val totalWithTips = totalTemp + tips
                binding.tvResult.text = "Total with tips: $totalWithTips"
                println("Total is:" + totalWithTips)
            }
        }


        binding.btnClear.setOnClickListener {
            binding.tieTotal.setText("")
            binding.tvResult.text = ""
            binding.tiePeople.setText("")
            binding.btnOp1.isChecked = false
            binding.btnOp2.isChecked = false
            binding.btnOp3.isChecked = false
        }
    }
}