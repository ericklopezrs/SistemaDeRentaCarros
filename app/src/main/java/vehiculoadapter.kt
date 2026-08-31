package com.erick.sistemaderentacarros

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class vehiculoadapter(private val listavehiculos: List<datos>) :
    RecyclerView.Adapter<vehiculoadapter.ViewHolderClass>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderClass {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_holder, parent, false)
        return ViewHolderClass(view)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val item = listavehiculos[position]
        holder.modelo.text = item.modelo
        holder.disponibilidad.text = item.disponibilidad
    }

    override fun getItemCount(): Int = listavehiculos.size

    class ViewHolderClass(view: View) :
        RecyclerView.ViewHolder(view) {
        val modelo = view.findViewById<TextView>(R.id.txtmodelo)
        val disponibilidad = view.findViewById<TextView>(R.id.txtdisponibilidad)
    }
}