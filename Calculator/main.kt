package com.example.calculator_project

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculator_project.ui.theme.Calculator_projectTheme

class MainActivity : ComponentActivity() {
    private lateinit var inputDisplay:TextView
    private lateinit var outputDisplay:TextView
    private var value1:Double=0.0
    private var value2:Double=0.0
    private var currentOperation:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)

        inputDisplay=findViewById(R.id.input)
        outputDisplay=findViewById(R.id.output)

        val buttons= listOf(
            R.id.btn0,R.id.btn1, R.id.btn2,
            R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.add, R.id.sub, R.id.div, R.id.clear, R.id.eql, R.id.mul
        )
        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener { onclickButton(it as Button) }

        }

    }

    private fun onclickButton(button: Button)
    {
        when(val text=button.text.toString())
        {
            "C"->calClear()
            "="->calculate()
            "+","-","*","/"->setOperation(text)
            else->appendToInput(text)
        }
    }

    private fun appendToInput(value: String) {
        inputDisplay.append(value)

    }

    private fun setOperation(op: String) {
        if(inputDisplay.text.isNotEmpty())
        {
            value1=inputDisplay.text.toString().toDouble()
            currentOperation=op
            inputDisplay.text=""
        }

    }

    @SuppressLint("SetTextI18n")
    private fun calculate() {
        if(inputDisplay.text.isNotEmpty() && currentOperation!=null)
        {
            value2=inputDisplay.text.toString().toDouble()
            val result=when(currentOperation)
            {
                "+"->value1+value2
                "-"->value1-value2
                "*"->value1*value2
                "/"->if(value2!=0.0) value1/value2 else Double.NaN
                else->0.0
            }
            outputDisplay.text=result.toString()
            currentOperation=null
        }
    }

    private fun calClear() {
        inputDisplay.text=""
        outputDisplay.text=""
        value1=0.0
        value2=0.0
        currentOperation=null
    }


}

