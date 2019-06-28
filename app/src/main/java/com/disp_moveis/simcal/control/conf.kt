package com.disp_moveis.simcal.control

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText
import android.widget.Toast
import com.disp_moveis.simcal.R
import com.disp_moveis.simcal.model.Comodo
import com.disp_moveis.simcal.model.Dispositivo
import org.jetbrains.anko.layoutInflater

class Configuracoes {
    companion object Factory {
        const val EDIT = 1
        const val DELETE = 2
        const val SELECT = 0

        var modo: Int = 0
        var modo_disp = 0
        var lista_comodos: MutableList<Comodo> = mutableListOf(
            Comodo("teste 0"),
            Comodo("teste 1"),
            Comodo("teste 2"),
            Comodo("teste 3"),
            Comodo("teste 4"),
            Comodo("teste 5"),
            Comodo("teste 6"),
            Comodo("teste 7"),
            Comodo("teste 8"),
            Comodo("teste 9")
        )

        fun abrePopUp(context: Context, modo: Int, pos: Int = 0) {
            var nomeComodo: EditText
            val inflater = context.layoutInflater
            val view = inflater.inflate(R.layout.novo_comodo_layout, null)
            // Initialize a new instance of
            val builder = AlertDialog.Builder(context)

            // Set the alert dialog title
            if (modo == SELECT)
                builder.setTitle("Novo Comodo")
            if (modo == EDIT)
                builder.setTitle("Editar Comodo")

            builder.setView(view)

            nomeComodo = view.findViewById(R.id.nome_comodo)


            builder.setNeutralButton("Cancelar") { _, _ ->}
            // Set a positive button and its click listener on alert dialog
            builder.setPositiveButton("Okay") { _, _ ->
                // Do something when user press the positive button
                if (nomeComodo.text.toString() != "") {
                    if (modo == SELECT)
                        if (addComodo(nomeComodo.text.toString())) {
                            Toast.makeText(context, "Comodo adicionado.", Toast.LENGTH_SHORT).show()

                        } else
                            Toast.makeText(context, "Comodo Ja existe.", Toast.LENGTH_SHORT).show()
                    if (modo == EDIT)
                        lista_comodos[pos].nome = nomeComodo.text.toString()
                } else
                    Toast.makeText(context, "Nome não pode ser nulo", Toast.LENGTH_SHORT).show()

            }


            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()
        }
        fun addDispositivo(nome: String, comodoAdapter: ComodoAdapter): Boolean {
            val novoDispositivo = Dispositivo(nome, comodoAdapter.comodo)
            for (dispositivo: Dispositivo in comodoAdapter.comodo.dispositivos_conectados) {
                if (dispositivo.nome == novoDispositivo.nome)
                    return false
            }

            comodoAdapter.comodo.dispositivos_conectados.add(novoDispositivo)
            return true

        }
        fun addComodo(nome: String): Boolean {
            val novoComodo = Comodo(nome)
            for (comodo: Comodo in lista_comodos) {
                if (comodo.nome == novoComodo.nome)
                    return false
            }
            lista_comodos.add(novoComodo)
            return true

        }
    }
}