package com.appsbig.app4dm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class ListCanciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_canciones)

        val lv_canciones =findViewById<ListView>(R.id.lv_canciones)
        val list_canciones: ArrayList<String> = ArrayList()

        val canciones = CancionesHelper(this, "cancionesdb", null,1)
        val db = canciones.writableDatabase
        val consulta = db.rawQuery("select id, titulo, cantante from cancion", null)
        while(consulta.moveToNext()){
            val id = consulta.getString(0)
            val titulo = consulta.getString(1)
            val cantante = consulta.getString(2)
            list_canciones.add(id+ "- "+titulo+"- "+cantante)
        }
        val adaptador = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_canciones)
        lv_canciones.adapter = adaptador
        db.close()
    }
}
