package com.disp_moveis.simcal

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
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.disp_moveis.simcal.R.layout.*
import com.disp_moveis.simcal.model.Comodo
import com.disp_moveis.simcal.model.Dispositivo
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var lista_dispositivos: ArrayList<Dispositivo> = arrayListOf()

    internal var lista_comodos: MutableList<Comodo> = mutableListOf(
        Comodo("Sala"),
        Comodo("Cozinha"),
        Comodo("Quarto")
    )
    lateinit var comodoAdapter: ListComodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.top_bar_navigation)
        setSupportActionBar(toolbar)

//        val fab: FloatingActionButton = findViewById(R.id.fab)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        comodoAdapter = ListComodoAdapter(lista_comodos, this)
        recyclerViewComodos.adapter = comodoAdapter
        recyclerViewComodos.layoutManager = LinearLayoutManager(this)
        recyclerViewComodos.smoothScrollToPosition(lista_comodos.size)


        navView.setNavigationItemSelectedListener(this)
        preparaButtons()

    }

    fun preparaButtons() {
//        var botaoSala = findViewById<Button>(R.id.buttonSala)
        var botaoAddComodo = findViewById<Button>(R.id.add_locale)

        botaoAddComodo.setOnClickListener {
            abrePopUp()


        }
//        botaoSala.setOnClickListener {

//        }

    }

    fun addComodo(nome: String): Boolean {
        val novo_comodo = Comodo(nome)
        for (comodo: Comodo in lista_comodos) {
            if (comodo.nome == novo_comodo.nome)
                return false
        }
        lista_comodos.add(novo_comodo)
        return true

    }

    fun abrePopUp() {
        var nomeComodo: EditText
        val inflater = this.layoutInflater
        val view = inflater.inflate(novo_comodo_layout, null)
        // Initialize a new instance of
        val builder = AlertDialog.Builder(this@MainActivity)

        // Set the alert dialog title
        builder.setTitle("Novo Comodo")

        builder.setView(view)

        nomeComodo = view.findViewById(R.id.nome_comodo)


        builder.setNeutralButton("Cancelar") { _, _ ->

        }
        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("Okay") { _, _ ->
            // Do something when user press the positive button
            if (nomeComodo.text.toString() != "")
                if (addComodo(nomeComodo.text.toString()))
                    Toast.makeText(applicationContext, "Comodo adicionado.", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(applicationContext, "Comodo Ja existe.", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(applicationContext, "Nome não pode ser nulo", Toast.LENGTH_SHORT).show()

        }


        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }


    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                var trocaTela = Intent(this.baseContext, content_main::class.java)
                startActivity(trocaTela)
            }
            R.id.relatorio -> {
                val trocaTela = Intent(this.baseContext, activity_relatorio::class.java)
                startActivity(trocaTela)
            }
            R.id.rotina -> {
                val trocaTela = Intent(this.baseContext, activity_relatorio::class.java)
                startActivity(trocaTela)
            }
            R.id.sobre -> {
                val trocaTela = Intent(this.baseContext, activity_sobre::class.java)
                startActivity(trocaTela)
            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}

