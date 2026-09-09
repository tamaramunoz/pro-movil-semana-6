package com.example.tamara_munoz_semana6

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.tamara_munoz_semana6.database.InfraccionBD
import com.example.tamara_munoz_semana6.entidades.Infraccion

class MainActivity : AppCompatActivity() {

    private lateinit var listViewInfracciones: ListView
    private lateinit var btnAgregar: Button
    private lateinit var dbInfraccion: InfraccionBD
    private lateinit var listaInfracciones: ArrayList<Infraccion>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listViewInfracciones = findViewById(R.id.listViewInfracciones)
        btnAgregar = findViewById(R.id.btnAgregar)
        dbInfraccion = InfraccionBD(this)

        // botón para abrir la vista de ingreso/registro de una infracción
        btnAgregar.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        cargarListaInfracciones()
    }

    override fun onResume() {
        super.onResume()
        cargarListaInfracciones()
    }

    private fun cargarListaInfracciones() {
        listaInfracciones = dbInfraccion.obtenerInfracciones()

        // mapeamos los datos para mostrarlos de forma legible en el ListView
        val adapterList = listaInfracciones.map {
            "Folio: ${it.folio} | Local: ${it.nombreLocal} | Inspector: ${it.rutInspector}"
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, adapterList)
        listViewInfracciones.adapter = adapter
        listViewInfracciones.setOnItemClickListener { _, _, position, _ ->
            val selectedViolation = listaInfracciones[position]

            val intent = Intent(this, EditActivity::class.java).apply {
                putExtra("folio", selectedViolation.folio)
                putExtra("rut", selectedViolation.rutInspector)
                putExtra("local", selectedViolation.nombreLocal)
                putExtra("address", selectedViolation.direccion)
                putExtra("violation", selectedViolation.infraccion)
            }
            startActivity(intent)
        }
    }
}