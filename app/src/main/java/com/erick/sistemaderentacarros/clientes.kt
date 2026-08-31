package com.erick.sistemaderentacarros

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class clientes : AppCompatActivity() {

    lateinit var identificacion: EditText
    lateinit var nombre: EditText
    lateinit var telefono: EditText
    lateinit var guardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_clientes)

        identificacion = findViewById(R.id.ACIdentificacion)
        nombre = findViewById(R.id.ARMarca) // ID del XML
        telefono = findViewById(R.id.ARAnio) // ID del XML
        guardar = findViewById(R.id.ARGuardar) // ID del XML

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        guardar.setOnClickListener { Guardar() }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun Guardar() {
        if (identificacion.text.isEmpty() || nombre.text.isEmpty() || telefono.text.isEmpty()) {
            Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show()
        } else {
            val info = datoscliente(
                identificacion = identificacion.text.toString(),
                nombre = nombre.text.toString(),
                telefono = telefono.text.toString()
            )
            cliente.listaClientes.add(info)
            Toast.makeText(this, "Cliente guardado", Toast.LENGTH_SHORT).show()

            identificacion.text.clear()
            nombre.text.clear()
            telefono.text.clear()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.Reg_Cliente) {
            Toast.makeText(this, "Ya estas aqui", Toast.LENGTH_LONG).show()
        }

        if (item.itemId == R.id.Reg_Vehiculo) {
            val cambio = Intent(this, registrar::class.java)
            startActivity(cambio)
        }

        if (item.itemId == R.id.Con_Vehiculo) {
            val cambio = Intent(this, convehiculos::class.java)
            startActivity(cambio)
        }

        if (item.itemId == R.id.Renta) {
            val cambio = Intent(this, rentaobject::class.java)
            startActivity(cambio)
        }

        if (item.itemId == R.id.Devolucion) {
            val cambio = Intent(this, devolucion::class.java)
            startActivity(cambio)
        }

        if (item.itemId == R.id.Con_Renta) {
            val cambio = Intent(this, consultarrenta::class.java)
            startActivity(cambio)
        }

        if (item.itemId == R.id.Principal) {
            val cambio = Intent(this, MainActivity::class.java)
            startActivity(cambio)
        }

        return super.onOptionsItemSelected(item)
    }
}