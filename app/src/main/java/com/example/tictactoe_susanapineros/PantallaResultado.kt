package com.example.tictactoe_susanapineros

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.example.tictactoe_susanapineros.databinding.ActivityPantallaResultadoBinding

class PantallaResultado : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaResultadoBinding

    var jugador1 = ""
    var jugador2 = ""
    var textoGanador = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPantallaResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textoGanador = intent.getStringExtra("nombreGanador").orEmpty().trim()
        jugador1 = intent.getStringExtra("nombreJugador1").orEmpty().trim().ifEmpty { "Jugador 1" }
        jugador2 = intent.getStringExtra("nombreJugador2").orEmpty().trim().ifEmpty { "Jugador 2" }

        binding.tvResultado.text = if (textoGanador == "Empate") {
            "★ EMPATE ★"
        } else {
            "Ha ganado \n★ $textoGanador ★"
        }
        imgGanador()
    }

    fun imgGanador() {
        val gato1 = binding.imgResultado1
        val gato2 = binding.imgResultado2

        when (textoGanador) {
            jugador1 -> {
                Glide.with(this)
                    .asGif()
                    .load(R.drawable.ganar1)
                    .into(gato1)

                Glide.with(this)
                    .asGif()
                    .load(R.drawable.perder2)
                    .into(gato2)
            }
            jugador2 -> {
                Glide.with(this)
                    .asGif()
                    .load(R.drawable.perder1)
                    .into(gato1)

                Glide.with(this)
                    .asGif()
                    .load(R.drawable.ganar2)
                    .into(gato2)
            }
            else -> {
                Glide.with(this)
                    .asGif()
                    .load(R.drawable.empate1)
                    .into(gato1)

                Glide.with(this)
                    .asGif()
                    .load(R.drawable.empate2)
                    .into(gato2)
            }
        }
    }

    fun resetearVariables() {
        jugador1 = ""
        jugador2 = ""
        textoGanador = ""
    }

    fun onClickPantalla3(vista : View) {
        resetearVariables()
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)  //Para borrar las actividades anteriores
        startActivity(intent)
    }
}