package com.appsbig.app4dm

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.google.firebase.database.FirebaseDatabase

class Peliculas : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_peliculas)

        val ptTitulo = findViewById<EditText>(R.id.ptTitulopeli)
        val spGenero = findViewById<Spinner>(R.id.spGeneroP)
        val btnGuardar = findViewById<Button>(R.id.btnSaveMovie)

        btnGuardar.setOnClickListener{
            val titulo = ptTitulo.text.toString()

            val genero = spGenero.selectedItem.toString()

            val database = FirebaseDatabase.getInstance()


            val peliculasRef = database.getReference("peliculas")

            val peliculaid = peliculasRef.push().key

            val pelicula = Pelicula(titulo,genero)

            peliculaid?.let {
                peliculasRef.child(it).setValue(pelicula)
                    .addOnSuccessListener {
                        Toast.makeText(this,"se agrego la pelicula", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "error al guardar la pelicula",Toast.LENGTH_SHORT).show()
                        println("error al guardar la pelicula en Firebase: ${it.message}")
                    }
            }
            val intent = Intent(this,ListPeliculas::class.java)
            startActivity(intent)
        }
    }
}
