package com.example.tamara_munoz_semana6

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tamara_munoz_semana6.database.InfraccionBD

class AddActivity : AppCompatActivity() {

    private lateinit var etRutInspector: EditText
    private lateinit var etNombreLocal: EditText
    private lateinit var etDireccion: EditText
    private lateinit var etInfraccion: EditText
    private lateinit var btnGuardarInfraccion: Button
    private lateinit var dbInfraccion: InfraccionBD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        etRutInspector = findViewById(R.id.etRutInspector)
        etNombreLocal = findViewById(R.id.etNombreLocal)
        etDireccion = findViewById(R.id.etDireccion)
        etInfraccion = findViewById(R.id.etInfraccion)
        btnGuardarInfraccion = findViewById(R.id.btnGuardarInfraccion)

        dbInfraccion = InfraccionBD(this)

        btnGuardarInfraccion.setOnClickListener {
            val rut = etRutInspector.text.toString().trim()
            val local = etNombreLocal.text.toString().trim()
            val direccion = etDireccion.text.toString().trim()
            val infraccion = etInfraccion.text.toString().trim()

            if (rut.isNotEmpty() && local.isNotEmpty() && direccion.isNotEmpty() && infraccion.isNotEmpty()) {
                val resultadoId = dbInfraccion.insertarInfraccion(rut, local, direccion, infraccion)

                if (resultadoId != -1L) {
                    // Muestra en pantalla el folio asignado a la infracción tal como lo pide la tarea
                    Toast.makeText(this, "Infracción registrada con éxito. Folio asignado: $resultadoId", Toast.LENGTH_LONG).show()
                    finish() // Cierra la actividad y regresa al listado principal
                } else {
                    Toast.makeText(this, "Error al registrar la infracción", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}