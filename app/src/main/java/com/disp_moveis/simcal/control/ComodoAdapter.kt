package com.disp_moveis.simcal.control

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.disp_moveis.simcal.R
import com.disp_moveis.simcal.model.Comodo
import com.disp_moveis.simcal.model.Dispositivo
import com.disp_moveis.simcal.view.ComodoActivity
import kotlinx.android.synthetic.main.comodo_item.view.*
import kotlinx.android.synthetic.main.dispositivo_item.view.*

class ComodoAdapter(private val context: Context) : RecyclerView.Adapter<ComodoAdapter.ViewHolder>() {
    var comodo : Comodo = Comodo("a")

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.dispositivo_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = comodo.dispositivos_conectados.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindView(comodo.dispositivos_conectados[position])
        holder.itemView.nome_dispositivo.setOnClickListener {
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
        val nome = itemView.nome_dispositivo

        fun bindView(dispositivo: Dispositivo) {
            nome.text = dispositivo.nome

        }
    }

}


