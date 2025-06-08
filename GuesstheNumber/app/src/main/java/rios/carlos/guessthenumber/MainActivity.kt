package rios.carlos.guessthenumber

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import rios.carlos.guessthenumber.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var minValue = 0
    var maxValue = 100
    var num: Int = 0
    var won = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessings: TextView = findViewById(R.id.textViewBelow)
        val down: TextView = findViewById(R.id.down)
        val up: TextView = findViewById(R.id.up)
        val generate: TextView = findViewById(R.id.generate)
        val guessed: TextView = findViewById(R.id.guessed)

        guessings.text = "Toca generar para empezar"
        guessed.visibility = View.GONE
        up.isEnabled = false
        down.isEnabled = false

        generate.setOnClickListener {
            num = Random.nextInt(minValue, maxValue)
            guessings.text = num.toString()
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
            up.isEnabled = true
            down.isEnabled = true
        }

        up.setOnClickListener {
            minValue = num + 1
            if (checkingLimits()) {
                num = Random.nextInt(minValue, maxValue)
                guessings.text = num.toString()
            } else {
                guessings.text = "¡No puede ser! Ganaste :("
                up.isEnabled = false
                down.isEnabled = false
                guessed.text = "Volver a jugar"
                won = true
            }
        }

        down.setOnClickListener {
            maxValue = num - 1
            if (checkingLimits()) {
                num = Random.nextInt(minValue, maxValue)
                guessings.text = num.toString()
            } else {
                guessings.text = "¡No puede ser! Ganaste :("
                up.isEnabled = false
                down.isEnabled = false
                guessed.text = "Volver a jugar"
                won = true
            }
        }

        guessed.setOnClickListener {
            if (!won) {
                guessings.text = "Adiviné, tu número es el $num"
                guessed.text = "Volver a jugar"
                up.isEnabled = false
                down.isEnabled = false
                won = true
            } else {
                resetValues()
                guessings.text = "Toca generar para empezar"
                guessed.text = "Guessed"
                guessed.visibility = View.GONE
                generate.visibility = View.VISIBLE
                up.isEnabled = false
                down.isEnabled = false
            }
        }
    }

    private fun resetValues() {
        minValue = 0
        maxValue = 100
        num = 0
        won = false
    }

    private fun checkingLimits(): Boolean {
        return (maxValue - minValue) > 0
    }
}