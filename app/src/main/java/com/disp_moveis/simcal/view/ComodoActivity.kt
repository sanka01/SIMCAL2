package com.disp_moveis.simcal.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.disp_moveis.simcal.R
import com.disp_moveis.simcal.control.ComodoAdapter
import com.disp_moveis.simcal.control.Configuracoes
import kotlinx.android.synthetic.main.app_bar_comodos.*

class ComodoActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var comodoAdapter: ComodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comodo)
        val toolbar: Toolbar = top_bar_navigation
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout_comodo)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()



        comodoAdapter = ComodoAdapter(this)
        var comodo : Int= intent.getIntExtra("posComodo",0)
        comodoAdapter.comodo = Configuracoes.lista_comodos[comodo]
        titulo.text = comodoAdapter.comodo.nome

        recyclerViewComodos.adapter = comodoAdapter
        recyclerViewComodos.layoutManager = LinearLayoutManager(this)
        recyclerViewComodos.smoothScrollToPosition(comodoAdapter.comodo.dispositivos_conectados.size)
        navView.setNavigationItemSelectedListener(this)
        preparaButtons()

    }


    fun preparaButtons() {

        add_comodo.setOnClickListener {
            abrePopUp(Configuracoes.SELECT)
            comodoAdapter.notifyItemInserted(comodoAdapter.comodo.dispositivos_conectados.size)
        }
        edit_comodo.setOnClickListener {

            if (Configuracoes.modo_disp == Configuracoes.SELECT) {
                Configuracoes.modo_disp = Configuracoes.EDIT
                edit_comodo.background = getDrawable(R.drawable.icon_edit_select)
                Toast.makeText(
                    applicationContext,
                    "Selecione o comodo a ser editado.",
                    Toast.LENGTH_LONG
                ).show()
            } else if (Configuracoes.modo_disp == Configuracoes.EDIT) {
                Configuracoes.modo_disp = Configuracoes.SELECT
                edit_comodo.background = getDrawable(R.drawable.icon_edit_no_select)
            }
        }
        excluir_comodo.setOnClickListener {
            if (Configuracoes.modo_disp == Configuracoes.SELECT) {
                Configuracoes.modo_disp = Configuracoes.DELETE
                excluir_comodo.background = getDrawable(R.drawable.icon_delete_select)
                Toast.makeText(
                    applicationContext,
                    "Selecione o comodo a ser excluido.",
                    Toast.LENGTH_LONG
                ).show()
            } else if (Configuracoes.modo_disp == Configuracoes.DELETE) {
                Configuracoes.modo_disp = Configuracoes.SELECT
                excluir_comodo.background = getDrawable(R.drawable.icon_delete_no_select)
            }

        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                val trocaTela = Intent(this, MainActivity::class.java)
                startActivity(trocaTela)
            }
            R.id.relatorio -> {
                val trocaTela = Intent(this, RelatorioActivity::class.java)
                startActivity(trocaTela)
            }
//                R.id.rotina -> {
//                    val trocaTela = Intent(this, RelatorioActivity::class.java)
//                    startActivity(trocaTela)
//                }
            R.id.sobre -> {
                val trocaTela = Intent(this, SobreActivity::class.java)
                startActivity(trocaTela)
            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout_comodo)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


   fun abrePopUp(modo: Int, pos: Int = 0) {
        var nomeDispositivo: EditText
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.novo_comodo_layout, null)
        // Initialize a new instance of
        val builder = AlertDialog.Builder(this)

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
                    if (Configuracoes.addDispositivo(nomeDispositivo.text.toString(), comodoAdapter)) {
                        Toast.makeText(this, "Dispositivo adicionado.", Toast.LENGTH_SHORT).show()

                    } else
                        Toast.makeText(this, "Dispositivo Ja existe.", Toast.LENGTH_SHORT).show()
                if (modo == Configuracoes.EDIT)
                    Configuracoes.lista_comodos[pos].nome = nomeDispositivo.text.toString()
            } else
                Toast.makeText(this, "Nome não pode ser nulo", Toast.LENGTH_SHORT).show()

        }


        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }

}