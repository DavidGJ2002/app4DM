package com.appsbig.app4dm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Bienvenida : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenida)

        val tvBienvenida = findViewById<TextView>(R.id.tvBienvenida)

        val nombreUsuario = intent.getStringExtra("usuario")
        val sharedPref = this.getSharedPreferences("MiSharedPrefer", MODE_PRIVATE)
        val apodo = sharedPref.getString("apodo","")

        tvBienvenida.append(" "+apodo)

        val btnCancion = findViewById<Button>(R.id.btnSingles)
        val btnPelicula = findViewById<Button>(R.id.btnMovies)

        btnCancion.setOnClickListener {
            val intentC = Intent(this, Canciones::class.java)
            startActivity(intentC)
        }

        btnPelicula.setOnClickListener { // Cambiado a btnPelicula
            val intentP = Intent(this, Peliculas::class.java)
            startActivity(intentP)
        }
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }*/



}