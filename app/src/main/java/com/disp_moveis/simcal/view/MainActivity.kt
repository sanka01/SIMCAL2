package com.disp_moveis.simcal.view

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
import android.widget.Toast
import com.disp_moveis.simcal.control.ListComodoAdapter
import com.disp_moveis.simcal.R
import com.disp_moveis.simcal.control.Configuracoes
import kotlinx.android.synthetic.main.app_bar_comodos.*
import kotlinx.android.synthetic.main.app_bar_comodos.top_bar_navigation

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var comodoAdapter: ListComodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = top_bar_navigation
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        comodoAdapter = ListComodoAdapter(this)
        recyclerViewComodos.adapter = comodoAdapter
        recyclerViewComodos.layoutManager = LinearLayoutManager(this)
        recyclerViewComodos.smoothScrollToPosition(Configuracoes.lista_comodos.size)

        navView.setNavigationItemSelectedListener(this)
        preparaButtons()

    }

    fun preparaButtons() {

        add_comodo.setOnClickListener {
           Configuracoes.abrePopUp(this, Configuracoes.SELECT)
            comodoAdapter.notifyItemInserted(Configuracoes.lista_comodos.size)
        }
        edit_comodo.setOnClickListener {

            if (Configuracoes.modo == Configuracoes.SELECT) {
                Configuracoes.modo = Configuracoes.EDIT
                edit_comodo.background = getDrawable(R.drawable.icon_edit_select)
                Toast.makeText(applicationContext, "Selecione o comodo a ser editado.", Toast.LENGTH_LONG).show()
            }else if (Configuracoes.modo == Configuracoes.EDIT){
                Configuracoes.modo = Configuracoes.SELECT
                edit_comodo.background = getDrawable(R.drawable.icon_edit_no_select)
            }
        }
        excluir_comodo.setOnClickListener {
            if (Configuracoes.modo == Configuracoes.SELECT) {
                Configuracoes.modo = Configuracoes.DELETE
                excluir_comodo.background = getDrawable(R.drawable.icon_delete_select)
                Toast.makeText(applicationContext, "Selecione o comodo a ser excluido.", Toast.LENGTH_LONG).show()
            }else if (Configuracoes.modo == Configuracoes.DELETE){
                Configuracoes.modo = Configuracoes.SELECT
                excluir_comodo.background = getDrawable(R.drawable.icon_delete_no_select)
            }

        }
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
                val trocaTela = Intent(this, MainActivity::class.java)
                startActivity(trocaTela)
            }
            R.id.relatorio -> {
                val trocaTela = Intent(this, RelatorioActivity::class.java)
                startActivity(trocaTela)
            }
//            R.id.rotina -> {
//                val trocaTela = Intent(this, RelatorioActivity::class.java)
//                startActivity(trocaTela)
//            }
            R.id.sobre -> {
                val trocaTela = Intent(this, SobreActivity::class.java)
                startActivity(trocaTela)
            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}

