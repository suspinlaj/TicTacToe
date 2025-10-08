package com.example.tictactoe_susanapineros

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import android.widget.ImageView
import com.example.tictactoe_susanapineros.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        gifs()
    }

    fun onClickPantalla1(vista : View) {
        val intent = Intent(this, PantallaJuego::class.java)

        intent.putExtra("nombreJugador1", binding.plaintvJugador1.text.toString())
        startActivity(intent)

        intent.putExtra("nombreJugador2", binding.plaintvJugador2.text.toString())
        startActivity(intent)
    }

    fun gifs() {
        val gato1 = findViewById<ImageView>(R.id.imageView6)
        val gato2 = findViewById<ImageView>(R.id.imageView5)

        Glide.with(this)
            .asGif()
            .load(R.drawable.portada1)
            .into(gato1)

        Glide.with(this)
            .asGif()
            .load(R.drawable.portada2)
            .into(gato2)
    }
}