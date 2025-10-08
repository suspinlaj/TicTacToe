package com.example.tictactoe_susanapineros

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe_susanapineros.databinding.ActivityPantallaJuegoBinding


class PantallaJuego : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaJuegoBinding
    var textoJugador1 = ""
    var textoJugador2 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPantallaJuegoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textoJugador1 = intent.getStringExtra("nombreJugador1").toString()
        binding.tvJugador1.text = textoJugador1.ifEmpty {
            "Jugador 1"
        }

       textoJugador2 = intent.getStringExtra("nombreJugador2").toString()
        binding.tvJugador2.text = if (textoJugador1.isEmpty()) {
            "Jugador 2"
        } else {
            textoJugador2
        }

        fondoInicial = binding.imgbtn1.drawable.constantState
        binding.imgbtnTurno.setImageResource(R.drawable.jugador1)
    }

    var contador = 0;
    var ganador = ""
    var fondoInicial: Drawable.ConstantState? = null

    fun cogerNombreGanador() {
        when (ganador) {
            "Jugador 1" -> ganador = binding.tvJugador1.text.toString()
            "Jugador 2" -> ganador = binding.tvJugador2.text.toString()
            else -> ganador = "Empate"
        }
    }


    fun onClick(vista: View) {
        val boton = vista as ImageButton
        contador++

        if (contador % 2 == 0) {
            boton.setImageResource(R.drawable.jugador2)
            binding.imgbtnTurno.setImageResource(R.drawable.jugador1)
        } else {
            boton.setImageResource(R.drawable.jugador1)
            binding.imgbtnTurno.setImageResource(R.drawable.jugador2)
        }
        boton.isEnabled = false

        if(contador >= 5) {
            partidaFinalizada()
            if(ganador.isNotEmpty() || contador == 9) {
                cogerNombreGanador()
                val intent = Intent(this, PantallaResultado::class.java)
                intent.putExtra("nombreGanador", ganador)
                intent.putExtra("nombreJugador1", textoJugador1)
                intent.putExtra("nombreJugador2", textoJugador2)
                startActivity(intent)
            }
        }
    }

    fun partidaFinalizada() {

        val posiblesResultados = resultados()

        posiblesResultados.forEach { resultado ->
            val posicion1 = resultado.get(0).drawable.constantState

            if (posicion1 == resultado.get(1).drawable.constantState &&
                posicion1 == resultado.get(2).drawable.constantState && posicion1 != fondoInicial) {

                if (posicion1 == binding.imgJugador1.drawable.constantState) {
                    ganador = "Jugador 1"
                } else {
                    ganador = "Jugador 2"
                }
            }
        }
    }

    private fun resultados(): List<Array<ImageButton>> {

        //Combinaciones de partidas ganadas
        val resultado1 = arrayOf(binding.imgbtn1, binding.imgbtn2, binding.imgbtn3)
        val resultado2 = arrayOf(binding.imgbtn4, binding.imgbtn5, binding.imgbtn6)
        val resultado3 = arrayOf(binding.imgbtn7, binding.imgbtn8, binding.imgbtn9)
        val resultado4 = arrayOf(binding.imgbtn1, binding.imgbtn4, binding.imgbtn7)
        val resultado5 = arrayOf(binding.imgbtn2, binding.imgbtn5, binding.imgbtn8)
        val resultado6 = arrayOf(binding.imgbtn3, binding.imgbtn6, binding.imgbtn9)
        val resultado7 = arrayOf(binding.imgbtn1, binding.imgbtn5, binding.imgbtn9)
        val resultado8 = arrayOf(binding.imgbtn3, binding.imgbtn5, binding.imgbtn7)

        return listOf(
            resultado1, resultado2, resultado3, resultado4,
            resultado5, resultado6, resultado7, resultado8
        )
    }
}


