package com.appsbig.app4dm

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast

class Canciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canciones)

        val ptTitulo = findViewById<EditText>(R.id.titulo)
        val ptCantante = findViewById<EditText>(R.id.cantante)
        val btnGuardar = findViewById<Button>(R.id.buttonSave)

        btnGuardar.setOnClickListener {
            val canciones = CancionesHelper(this, "cancionesdb", null, 1)
            val db = canciones.writableDatabase
            val registro = ContentValues()
            registro.put("titulo", ptTitulo.text.toString())
            registro.put("cantante", ptCantante.text.toString())
            db.insert("cancion", null, registro)
            db.close()
            ptTitulo.setText("")
            ptCantante.setText("")
            Toast.makeText(this,"Se agrego la canción",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,ListCanciones::class.java)
            startActivity(intent)
        }
    }
}