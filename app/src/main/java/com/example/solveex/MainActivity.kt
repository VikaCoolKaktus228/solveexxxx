package com.example.solveex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.LayoutInflater
import android.widget.Toast
import com.example.solveex.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.Checkbtn.isEnabled = false

        var operand1 = 0
        var operand2 = 0
        var operator = '+'
        var correctAnswers = binding.Right.text.toString().toInt()
        var wrongAnswers = binding.Wrong.text.toString().toInt()
        var totalAnswers = binding.Allrightexamples.text.toString().toInt()

        fun generateQuestion() {
            val random = Random(System.currentTimeMillis())

            operand1 = random.nextInt(90) + 10
            operand2 = random.nextInt(90) + 10
            val operations = arrayOf('*', '/', '-', '+')
            operator = operations[random.nextInt(operations.size)]

            // Ensure the division results in an integer
            if (operator == '/') {
                while (operand1 % operand2 != 0) {
                    operand1 = random.nextInt(90) + 10
                    operand2 = random.nextInt(90) + 10
                }
            }

            binding.fOperand.text = operand1.toString()
            binding.sOperand.text = operand2.toString()
            binding.Operation.text = operator.toString()
        }

        fun calculateAnswer(op1: Int, op2: Int, oper: Char): Int {
            return when (oper) {
                '*' -> op1 * op2
                '/' -> op1 / op2
                '-' -> op1 - op2
                '+' -> op1 + op2
                else -> 0
            }
        }

        fun updateStatistics() {
            val percentage = if (totalAnswers > 0) (correctAnswers.toDouble() / totalAnswers * 100) else 0.0
            binding.Procent.text = "${String.format("%.2f", percentage)}%"
        }

        binding.Startbtn.setOnClickListener {
            generateQuestion()

            binding.Startbtn.isEnabled = false
            binding.Checkbtn.isEnabled = true
            binding.answerEditText.isEnabled = true
        }

        binding.Checkbtn.setOnClickListener(){
            val userAnswer = binding.answerEditText.text.toString().toIntOrNull()
            if (userAnswer != null) {
                val correctAnswer = calculateAnswer(operand1, operand2, operator)
                totalAnswers++
                binding.Allrightexamples.text = totalAnswers.toString()
                if (userAnswer == correctAnswer) {
                    correctAnswers++
                    binding.Right.text = correctAnswers.toString()
                    Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
                    updateStatistics()
                } else {
                    wrongAnswers++
                    binding.Wrong.text = wrongAnswers.toString()
                    Toast.makeText(this, "Incorrect! The correct answer is $correctAnswer", Toast.LENGTH_SHORT).show()
                    updateStatistics()
                }
                updateStatistics()
                binding.Checkbtn.isEnabled = false
                binding.answerEditText.isEnabled = false
                binding.Startbtn.isEnabled = true
            } else {
                Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
            }
        }
    }
}