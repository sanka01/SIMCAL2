package com.disp_moveis.simcal.control

import android.app.AlertDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.disp_moveis.simcal.R
import com.disp_moveis.simcal.model.Comodo
import com.disp_moveis.simcal.model.Dispositivo
import kotlinx.android.synthetic.main.dispositivo_item.view.*
import org.jetbrains.anko.layoutInflater

class ComodoAdapter(private val context: Context) :
    RecyclerView.Adapter<ComodoAdapter.ViewHolder>() {
    var comodo: Comodo = Comodo("NOME")

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.dispositivo_item, viewGroup, false)
        isLigado(view.modo)
        return ViewHolder(view)
    }

    override fun getItemCount() = comodo.dispositivos_conectados.size


    private fun isLigado(modo: Button) {
        if (modo.text.equals("LIGADO")) {
            modo.text = context.getString(R.string.off)
            modo.background = context.getDrawable(R.drawable.btn_right_round_off)
        } else {
            modo.text = context.getString(R.string.on)
            modo.background = context.getDrawable(R.drawable.btn_right_round_on)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindView(comodo.dispositivos_conectados[position])
        holder.modo.setOnClickListener {
            isLigado(holder.modo)
            Log.d("TESTE", "CLICADO")
        }
        holder.itemView.setOnClickListener {

            if (Configuracoes.modo_disp == Configuracoes.DELETE) {

                comodo.dispositivos_conectados.removeAt(position)
                this.notifyItemRemoved(position)

            } else if (Configuracoes.modo_disp == Configuracoes.EDIT) {
                abrePopUp(Configuracoes.EDIT, position)
                this.notifyItemChanged(position)
            }

        }

    }

    fun abrePopUp(modo: Int, pos: Int = 0) {
        var nomeDispositivo: EditText
        val inflater = context.layoutInflater
        val view = inflater.inflate(R.layout.novo_comodo_layout, null)
        // Initialize a new instance of
        val builder = AlertDialog.Builder(context)

        // Set the alert dialog title
        if (Configuracoes.modo_disp == Configuracoes.SELECT)
            builder.setTitle("Novo Dispositivo")
        if (Configuracoes.modo_disp == Configuracoes.EDIT)
            builder.setTitle("Editar Dispositivo")

        builder.setView(view)

        nomeDispositivo = view.findViewById(R.id.nome_comodo)


        builder.setNeutralButton("Cancelar") { _, _ -> }
        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("Okay") { _, _ ->
            // Do something when user press the positive button
            if (nomeDispositivo.text.toString() != "") {
                if (Configuracoes.modo_disp == Configuracoes.SELECT)
                    if (Configuracoes.addDispositivo(nomeDispositivo.text.toString(), this)) {
                        Toast.makeText(context, "Dispositivo adicionado.", Toast.LENGTH_SHORT)
                            .show()

                    } else
                        Toast.makeText(context, "Dispositivo Ja existe.", Toast.LENGTH_SHORT).show()
                if (modo == Configuracoes.EDIT)
                    comodo.dispositivos_conectados[pos].nome = nomeDispositivo.text.toString()
            } else
                Toast.makeText(context, "Nome não pode ser nulo", Toast.LENGTH_SHORT).show()

        }


        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome = itemView.nome_dispositivo
        val modo = itemView.modo
        fun bindView(dispositivo: Dispositivo) {
            nome.text = dispositivo.nome
            dispositivo.isConectado = modo.equals("LIGADO")
        }
    }

}


