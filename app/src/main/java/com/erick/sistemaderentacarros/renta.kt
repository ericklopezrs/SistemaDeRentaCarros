package com.erick.sistemaderentacarros

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class rentaobject : AppCompatActivity() {

    lateinit var spIdentificacion: Spinner
    lateinit var spVehiculo: Spinner
    lateinit var etNumDias: EditText
    lateinit var etCosto: EditText
    lateinit var etCostoTotal: EditText
    lateinit var btnConfirmar: Button
    lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_renta)

        // Inicializar vistas
        spIdentificacion = findViewById(R.id.AREIdentificacion)
        spVehiculo = findViewById(R.id.AREVehiculo)
        etNumDias = findViewById(R.id.ARENumDias)
        etCosto = findViewById(R.id.ARECosto)
        etCostoTotal = findViewById(R.id.ARECostoTotal)
        btnConfirmar = findViewById(R.id.AREConfirmar)
        btnCancelar = findViewById(R.id.ARECancelar)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Llenar Spinner de Identificaciones (Clientes)
        val listaIdentificaciones = cliente.listaClientes.map { it.identificacion }
        val adapterClientes = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaIdentificaciones)
        adapterClientes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spIdentificacion.adapter = adapterClientes

        // Llenar Spinner de Vehículos (Solo los disponibles)
        val listaVehiculosDisponibles = vehiculo.listaVehiculos.filter { it.disponibilidad == "Disponible" }
        val listaNombresVehiculos = listaVehiculosDisponibles.map { it.placa + " - " + it.modelo }
        val adapterVehiculos = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaNombresVehiculos)
        adapterVehiculos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spVehiculo.adapter = adapterVehiculos

        btnConfirmar.setOnClickListener { Guardar() }
        btnCancelar.setOnClickListener { finish() }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun Guardar() {
        if (spIdentificacion.selectedItem == null || spVehiculo.selectedItem == null || 
            etNumDias.text.isEmpty() || etCosto.text.isEmpty() || etCostoTotal.text.isEmpty()) {
            Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show()
        } else {
            val nuevaRenta = datosrenta(
                identificacion = spIdentificacion.selectedItem.toString(),
                vehiculo = spVehiculo.selectedItem.toString(),
                diasRenta = etNumDias.text.toString(),
                costoDia = etCosto.text.toString().toDoubleOrNull() ?: 0.0,
                total = etCostoTotal.text.toString().toDoubleOrNull() ?: 0.0
            )
            
            renta_lista.listaRentas.add(nuevaRenta)
            
            // Cambiar disponibilidad del vehículo a "No Disponible"
            val vehiculoSeleccionadoStr = spVehiculo.selectedItem.toString()
            val placa = vehiculoSeleccionadoStr.split(" - ")[0]
            vehiculo.listaVehiculos.find { it.placa == placa }?.disponibilidad = "No Disponible"

            Toast.makeText(this, "Renta guardada correctamente", Toast.LENGTH_SHORT).show()
            
            // Limpiar campos y cerrar o refrescar
            etNumDias.text.clear()
            etCosto.text.clear()
            etCostoTotal.text.clear()
            
            // Opcional: recargar el spinner de vehículos para quitar el que ya se rentó
            val listaVehiculosDisponibles = vehiculo.listaVehiculos.filter { it.disponibilidad == "Disponible" }
            val listaNombresVehiculos = listaVehiculosDisponibles.map { it.placa + " - " + it.modelo }
            val adapterVehiculos = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaNombresVehiculos)
            adapterVehiculos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spVehiculo.adapter = adapterVehiculos
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.Reg_Cliente) {
            startActivity(Intent(this, clientes::class.java))
        }
        if (item.itemId == R.id.Reg_Vehiculo) {
            startActivity(Intent(this, registrar::class.java))
        }
        if (item.itemId == R.id.Con_Vehiculo) {
            startActivity(Intent(this, convehiculos::class.java))
        }
        if (item.itemId == R.id.Renta) {
            Toast.makeText(this, "Ya estas aqui", Toast.LENGTH_LONG).show()
        }
        if (item.itemId == R.id.Devolucion) {
            startActivity(Intent(this, devolucion::class.java))
        }
        if (item.itemId == R.id.Con_Renta) {
            startActivity(Intent(this, consultarrenta::class.java))
        }
        if (item.itemId == R.id.Principal) {
            startActivity(Intent(this, MainActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}