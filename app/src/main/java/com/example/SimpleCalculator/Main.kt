package com.example.SimpleCalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.diceroller.R


/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
class Main : AppCompatActivity() {
    var operand1: String = ""
    var operand2: String = ""
    var operator: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val Buttons: Array<Button> = arrayOf(findViewById(R.id.btn0),
            findViewById(R.id.btn1), findViewById(R.id.btn2), findViewById(R.id.btn3),
            findViewById(R.id.btn4), findViewById(R.id.btn5), findViewById(R.id.btn6),
            findViewById(R.id.btn7), findViewById(R.id.btn8), findViewById(R.id.btn9),
            findViewById(R.id.btnPlus), findViewById(R.id.btnSub), findViewById(R.id.btnMul), findViewById(R.id.btnDiv),
            findViewById(R.id.btnDot), findViewById(R.id.btnResult),
            findViewById(R.id.btnC), findViewById(R.id.btnCE), findViewById(R.id.btnBS), findViewById(R.id.btnPosNeg))
        setupButtonClicks(Buttons)
    }
    private fun setupButtonClicks(Buttons: Array<Button>) {
        val tvResult: TextView = findViewById(R.id.tvResult)

        val numbuttons = Buttons.sliceArray(0..9)
        val operateors: Array<Button> = Buttons.sliceArray(10 .. 13)

        numbuttons.forEach { button ->
            button.setOnClickListener {
                when(it.id) {
                    R.id.btn0,
                    R.id.btn1,
                    R.id.btn2,
                    R.id.btn3,
                    R.id.btn4,
                    R.id.btn5,
                    R.id.btn6,
                    R.id.btn7,
                    R.id.btn8,
                    R.id.btn9 -> {
                        val text = (findViewById<View>(it.id) as Button).text.toString()
                        val currentText = tvResult.text.toString()

                        if(currentText == "0") {
                            tvResult.text = ""
                        }
                        // Append new text
                        tvResult.append(text)

                    }

                }
            }
        }
        operateors.forEach { button ->
            button.setOnClickListener {
                when(it.id) {
                    R.id.btnPlus,
                    R.id.btnSub,
                    R.id.btnMul,
                    R.id.btnDiv,
                     -> {
                        if(tvResult.text.toString() != "") {
                            if (operand1 == "") {
                                operand1 = tvResult.text.toString()
                                //.text = ""
                            }
                            else if (operand2 == ""){
                                operand2 = tvResult.text.toString()
                                tvResult.text = cal(operand1, operand2, operator)
                                operand1 = tvResult.text.toString()
                                operand2 = ""
                            }
                            operator = (findViewById<View>(it.id) as Button).text.toString()
                            tvResult.text = ""
                        }
                    }

                }
            }
        }
        Buttons.sliceArray(14..19).forEach {
            it.setOnClickListener {
                when(it.id){
                    R.id.btnDot -> {

                    }
                    R.id.btnBS -> {
                        val currentText = tvResult.text.toString()
                        if(currentText.isNotEmpty()) {
                            val newText = currentText.dropLast(1)
                            tvResult.text = newText
                        }
                    }
                    R.id.btnCE -> {
                        tvResult.text = ""
                        if (operand2 != "") operand2 = ""
                        else if (operand1 != "") operand1 = ""
                    }
                    R.id.btnC -> {
                        operator = ""
                    }
                    R.id.btnPosNeg -> {
                        if (operand2 != ""){
                            var temp = Integer.parseInt(operand2)
                            temp = -temp;
                            operand2 = temp.toString()
                            tvResult.text = operand2
                        }
                        else if (operand1 != ""){
                            var temp = Integer.parseInt(operand1)
                            temp = -temp;
                            operand1 = temp.toString()
                            tvResult.text = operand1
                        }
                    }
                    R.id.btnResult -> {
                        operand2 = tvResult.text.toString()
                        tvResult.text = cal(operand1, operand2, operator)
                    }

                }
            }
        }

    }

    private fun cal(operand1: String, operand2: String, operator: String): String {
        val op1: Int = operand1.toInt()
        val op2: Int = operand2.toInt()
        var res1: Int = 0
        when(operator){
            "+" -> {res1 = op1 + op2 }
            "-" -> {res1 = op1 - op2 }
            "*" -> {res1 = op1 * op2 }
            "/" -> {res1= op1 / op2 }
        }
        return res1.toString()
    }

}





