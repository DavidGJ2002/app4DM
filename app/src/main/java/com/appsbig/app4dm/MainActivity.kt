package com.appsbig.app4dm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSiguiente = findViewById<Button>(R.id.buttonSave)
        val ptUsuario = findViewById<EditText>(R.id.titulo)
        val ptContrasena = findViewById<EditText>(R.id.cantante)
        val ptApodo = findViewById<EditText>(R.id.apodo)

        btnSiguiente.setOnClickListener {
            val usuario =ptUsuario.text.toString()
            val contrasena = ptContrasena.text.toString()
            val apodo = ptApodo.text.toString()

            if(usuario == "david" && contrasena == "123"){
                val intent = Intent(this, Bienvenida::class.java)
                intent.putExtra("usuario", usuario)

                val sharedPref = this.getSharedPreferences("MiSharedPrefer", MODE_PRIVATE)
                with (sharedPref.edit()){
                    putString("apodo",apodo)
                    apply()
                }
                startActivity(intent)
            }else{
                Toast.makeText(this,"Datos incorrectos", Toast.LENGTH_SHORT).show()
            }
    }
}}
