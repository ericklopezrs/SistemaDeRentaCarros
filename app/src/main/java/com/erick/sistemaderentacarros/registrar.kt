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

class registrar : AppCompatActivity() {

    lateinit var placa: EditText
    lateinit var marca: EditText
    lateinit var modelo: EditText
    lateinit var anio: EditText
    lateinit var costo: EditText

    lateinit var disponibilidad: Spinner
    lateinit var guardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar)

        placa = findViewById<EditText>(R.id.ARPlaca)
        marca = findViewById<EditText>(R.id.ARMarca)
        modelo = findViewById<EditText>(R.id.ARModelo)
        anio = findViewById<EditText>(R.id.ARAnio)
        costo = findViewById<EditText>(R.id.ARCosto)
        disponibilidad = findViewById<Spinner>(R.id.ARDisponibilidad)
        guardar = findViewById<Button>(R.id.ARGuardar)


        var toolbar: Toolbar=findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val arrayAdapterDisponibilidad: ArrayAdapter <*>

        val disponibilidarray = arrayOf(
            "Disponible",
            "No Disponible"
        )

        arrayAdapterDisponibilidad = ArrayAdapter(this, android.R.layout.simple_spinner_item, disponibilidarray)
        disponibilidad.adapter = arrayAdapterDisponibilidad

        guardar.setOnClickListener { Guardar() }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun Guardar(){

        if(placa.text.isEmpty() || marca.text.isEmpty() || modelo.text.isEmpty() || anio.text.isEmpty() || costo.text.isEmpty()){
            Toast.makeText(this,"Llene todos los campos",Toast.LENGTH_SHORT).show()
        } else {

            val info = datos(
                placa = placa.text.toString(),
                marca = marca.text.toString(),
                modelo = modelo.text.toString(),
                anio = anio.text.toString(),
                costo = costo.text.toString().toDoubleOrNull() ?: 0.0,
                disponibilidad = disponibilidad.selectedItem.toString()
            )
            vehiculo.listaVehiculos.add(info)
            Toast.makeText(this, "Vehículo guardado", Toast.LENGTH_SHORT).show()


            placa.text.clear()
            marca.text.clear()
            modelo.text.clear()
            anio.text.clear()
            costo.text.clear()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.Reg_Cliente){
            val cambio = Intent(this, clientes::class.java)
            startActivity(cambio)
        }

        if(item.itemId == R.id.Reg_Vehiculo){
            Toast.makeText(this,"Ya estas aqui",Toast.LENGTH_LONG).show()
        }

        if(item.itemId == R.id.Con_Vehiculo){
            val cambio = Intent(this, convehiculos::class.java)
            startActivity(cambio)
        }

        if(item.itemId == R.id.Renta){
            val cambio = Intent(this, rentaobject::class.java)
            startActivity(cambio)
        }

        if(item.itemId == R.id.Devolucion){
            val cambio = Intent(this, devolucion::class.java)
            startActivity(cambio)
        }

        if(item.itemId == R.id.Con_Renta){
            val cambio = Intent(this, consultarrenta::class.java)
            startActivity(cambio)
        }

        if(item.itemId == R.id.Principal){
            val cambio = Intent(this, MainActivity::class.java)
            startActivity(cambio)
        }

        return super.onOptionsItemSelected(item)
    }
}