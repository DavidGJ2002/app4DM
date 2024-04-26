package com.appsbig.app4dm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler.Value
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ListPeliculas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_peliculas)

        val lv_peliculas = findViewById<ListView>(R.id.lv_peliculas)
        val list_peliculas: ArrayList<String> = ArrayList()

        val db = FirebaseDatabase.getInstance()
        val peliculasRef = db.getReference("peliculas")

        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_peliculas)
        lv_peliculas.adapter = adaptador

        peliculasRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                adaptador.clear()
                for (registro in snapshot.children) {
                    val pelicula = registro.getValue(Pelicula::class.java)
                    val textoPelicula = "Titulo: " + pelicula?.titulo + ", Genero:" + pelicula?.genero
                    list_peliculas.add(textoPelicula)
                }
                adaptador.notifyDataSetChanged() // Notify the adapter that the data set has changed
            }

            override fun onCancelled(error: DatabaseError) {
                println("Error al consultar las peliculas: ${error.message}")
            }
        })

    }
}