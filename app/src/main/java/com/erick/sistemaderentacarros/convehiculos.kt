package com.erick.sistemaderentacarros

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class convehiculos : AppCompatActivity() {
    lateinit var rvvehiculos : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_convehiculos)

        rvvehiculos = findViewById<RecyclerView>(R.id.rvvehiculos)
        rvvehiculos.layoutManager = LinearLayoutManager(this)
        var toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val adapter = vehiculoadapter(vehiculo.listaVehiculos)
        rvvehiculos.adapter = adapter

        if(vehiculo.listaVehiculos.size==0){
            Toast.makeText(this,"No hay vehiculos",Toast.LENGTH_LONG).show()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.Reg_Cliente) {
            val cambio = Intent(this, clientes::class.java)
            startActivity(cambio)
        }

        if (item.itemId == R.id.Reg_Vehiculo) {
            val cambio = Intent(this, registrar::class.java)
            startActivity(cambio)
        }

        if (item.itemId == R.id.Con_Vehiculo) {
            Toast.makeText(this, "Ya estas aqui", Toast.LENGTH_LONG).show()
        }

        if (item.itemId == R.id.Renta) {
            val cambio = Intent(this, renta::class.java)
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