package com.example.simcal

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.simcal.model.Comodo
import kotlinx.android.synthetic.main.comodo_item.view.*
import kotlinx.android.synthetic.main.novo_comodo_layout.view.*

class ListComodoAdapter(
    private val list_comodo: List<Comodo>,
    private val context: Context
) : Adapter<ListComodoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.comodo_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list_comodo.size

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comodo = list_comodo[position]
        holder.nome.text = comodo.nome

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome = itemView.buttonSala

        fun bindView(comodo: Comodo){
            val nome = itemView.nome
            nome.text = comodo.nome


        }
    }

}



