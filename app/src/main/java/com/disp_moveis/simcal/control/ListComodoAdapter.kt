package com.disp_moveis.simcal.control

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.disp_moveis.simcal.R
import com.disp_moveis.simcal.model.Comodo
import com.disp_moveis.simcal.view.ComodoActivity
import kotlinx.android.synthetic.main.app_bar_comodos.view.excluir_comodo
import kotlinx.android.synthetic.main.comodo_item.view.*

class ListComodoAdapter(private val context: Context) : Adapter<ListComodoAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.comodo_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = Configuracoes.lista_comodos.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindView(Configuracoes.lista_comodos[position])
        holder.itemView.room_item.setOnClickListener {
            val trocaTela = Intent(context, ComodoActivity::class.java)

            if (Configuracoes.modo == Configuracoes.DELETE) {
                Log.d("delete_recycle", ": $position")
                Log.d("deleta_lista", ": ${Configuracoes.lista_comodos.get(position)}")
                Configuracoes.lista_comodos.removeAt(position)
                this.notifyItemRemoved(position)

            }
            else if (Configuracoes.modo == Configuracoes.EDIT){
                Configuracoes.abrePopUp(context, Configuracoes.EDIT,position)
                this.notifyItemChanged(position)
            }
            else
                context.startActivity(trocaTela)
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome = itemView.room_item

        fun bindView(comodo: Comodo) {
            nome.text = comodo.nome

        }
    }

}



