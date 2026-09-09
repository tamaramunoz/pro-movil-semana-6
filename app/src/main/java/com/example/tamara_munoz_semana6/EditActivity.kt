package com.example.tamara_munoz_semana6

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tamara_munoz_semana6.database.InfraccionBD

class EditActivity : AppCompatActivity() {

    private lateinit var etFolioEdit: EditText
    private lateinit var etInspectorRutEdit: EditText
    private lateinit var etLocalNameEdit: EditText
    private lateinit var etAddressEdit: EditText
    private lateinit var etViolationEdit: EditText
    private lateinit var btnUpdateViolation: Button
    private lateinit var btnShareViolation: Button
    private lateinit var dbInfraccion: InfraccionBD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        etFolioEdit = findViewById(R.id.etFolioEdit)
        etInspectorRutEdit = findViewById(R.id.etInspectorRutEdit)
        etLocalNameEdit = findViewById(R.id.etLocalNameEdit)
        etAddressEdit = findViewById(R.id.etAddressEdit)
        etViolationEdit = findViewById(R.id.etViolationEdit)
        btnUpdateViolation = findViewById(R.id.btnUpdateViolation)
        btnShareViolation = findViewById(R.id.btnShareViolation)

        dbInfraccion = InfraccionBD(this)

        val folio = intent.getIntExtra("folio", -1)
        val rut = intent.getStringExtra("rut") ?: ""
        val local = intent.getStringExtra("local") ?: ""
        val address = intent.getStringExtra("address") ?: ""
        val violation = intent.getStringExtra("violation") ?: ""

        etFolioEdit.setText(folio.toString())
        etInspectorRutEdit.setText(rut)
        etLocalNameEdit.setText(local)
        etAddressEdit.setText(address)
        etViolationEdit.setText(violation)

        btnUpdateViolation.setOnClickListener {
            val newRut = etInspectorRutEdit.text.toString().trim()
            val newLocal = etLocalNameEdit.text.toString().trim()
            val newAddress = etAddressEdit.text.toString().trim()
            val newViolation = etViolationEdit.text.toString().trim()

            if (newRut.isNotEmpty() && newLocal.isNotEmpty() && newAddress.isNotEmpty() && newViolation.isNotEmpty()) {
                val rowsAffected = dbInfraccion.actualizarInfraccion(folio, newRut, newLocal, newAddress, newViolation)

                if (rowsAffected > 0) {
                    Toast.makeText(this, "Violation successfully updated", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error updating violation", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all editable fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Funcionalidad para compartir los datos de la infracción con otras aplicaciones
        btnShareViolation.setOnClickListener {
            val shareText = """
                --- Reporte de Infracción (Pelotillehue) ---
                Folio: $folio
                Inspector RUT: ${etInspectorRutEdit.text}
                Local Comercial: ${etLocalNameEdit.text}
                Dirección: ${etAddressEdit.text}
                Infracción: ${etViolationEdit.text}
            """.trimIndent()

            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareText)
                type = "text/plain"
            }

            startActivity(Intent.createChooser(shareIntent, "Share violation via"))
        }
    }
}