package rios.carlos.guessthenumber

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import rios.carlos.guessthenumber.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var number = 0
    private val targetNumber = 42

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewBelow.text = "Current number: $number"

        binding.down.setOnClickListener {
            number--
            binding.textViewBelow.text = "Current number: $number"
            checkGuess()
        }

        binding.up.setOnClickListener {
            number++
            binding.textViewBelow.text = "Current number: $number"
            checkGuess()
        }

        binding.generate.setOnClickListener {
            binding.anotherButton.visibility = View.GONE
            number = 0
            binding.textViewBelow.text = "Current number: $number"
        }
        binding.anotherButton.setOnClickListener {
            binding.anotherButton.visibility = View.GONE
            Toast.makeText(this, "¡Adivinaste correctamente!", Toast.LENGTH_SHORT).show()
        }
    }


    private fun checkGuess() {
        if (number == targetNumber) {
            binding.anotherButton.visibility = View.VISIBLE 
        }
    }
}
