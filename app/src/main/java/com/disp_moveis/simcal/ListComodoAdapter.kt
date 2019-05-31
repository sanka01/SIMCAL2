package com.disp_moveis.simcal

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.disp_moveis.simcal.R
import com.disp_moveis.simcal.model.Comodo
import kotlinx.android.synthetic.main.comodo_item.view.*

class ListComodoAdapter(
    private val list_comodo: List<Comodo>,
    private val context: Context
) : Adapter<ListComodoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.comodo_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount()= list_comodo.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindView(list_comodo[position])

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome = itemView.buttonSala

        fun bindView(comodo: Comodo){
            nome.text = comodo.nome


        }
    }

}



