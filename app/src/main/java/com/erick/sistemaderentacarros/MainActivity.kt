package com.erick.sistemaderentacarros

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val btnRegV: Button = findViewById(R.id.AMRegV)
        val btnRegC: Button = findViewById(R.id.AMRegC)
        val btnConV: Button = findViewById(R.id.AMConV)
        val btnRenV: Button = findViewById(R.id.AMRenV)
        val btnDevV: Button = findViewById(R.id.AMDevV)
        val btnConR: Button = findViewById(R.id.AMConR)

        btnRegV.setOnClickListener {
            startActivity(Intent(this, registrar::class.java))
        }

        btnRegC.setOnClickListener {
            startActivity(Intent(this, clientes::class.java))
        }

        btnConV.setOnClickListener {
            startActivity(Intent(this, convehiculos::class.java))
        }

        btnRenV.setOnClickListener {
            startActivity(Intent(this, renta::class.java))
        }

        btnDevV.setOnClickListener {
            startActivity(Intent(this, devolucion::class.java))
        }

        btnConR.setOnClickListener {
            startActivity(Intent(this, consultarrenta::class.java))
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
            startActivity(Intent(this, clientes::class.java))
        }

        if (item.itemId == R.id.Reg_Vehiculo) {
            startActivity(Intent(this, registrar::class.java))
        }

        if (item.itemId == R.id.Con_Vehiculo) {
            startActivity(Intent(this, convehiculos::class.java))
        }

        if (item.itemId == R.id.Renta) {
            startActivity(Intent(this, renta::class.java))
        }

        if (item.itemId == R.id.Devolucion) {
            startActivity(Intent(this, devolucion::class.java))
        }

        if (item.itemId == R.id.Con_Renta) {
            startActivity(Intent(this, consultarrenta::class.java))
        }

        if (item.itemId == R.id.Principal) {
            Toast.makeText(this, "Ya estás aquí", Toast.LENGTH_LONG).show()
        }

        return super.onOptionsItemSelected(item)
    }
}