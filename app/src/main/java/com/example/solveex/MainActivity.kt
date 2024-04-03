package com.example.solveex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.LayoutInflater
import com.example.solveex.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Startbtn.setOnClickListener {
            val random = Random


            val operand1 = random.nextInt(90) + 10
            val operand2 = random.nextInt(90) + 10
            val operation = arrayOf('*', '/', '-', '+')
            val operator = operation[random.nextInt(operation.size)]

            binding.fOperand.text = operand1.toString()
            binding.sOperand.text = operand2.toString()
            binding.Operation.text = operator.toString()
        }
    }
}