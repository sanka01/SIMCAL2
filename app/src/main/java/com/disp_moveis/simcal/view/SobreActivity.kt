package com.disp_moveis.simcal.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.disp_moveis.simcal.R
import kotlinx.android.synthetic.main.activity_sobre.*

class SobreActivity : AppCompatActivity(),  NavigationView.OnNavigationItemSelectedListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sobre)


        val toolbar: Toolbar = top_bar_navigation_sobre
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout_sobre)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                var trocaTela = Intent(this, MainActivity::class.java)
                startActivity(trocaTela)
            }
            R.id.relatorio -> {
                val trocaTela = Intent(this, RelatorioActivity::class.java)
                startActivity(trocaTela)
            }
            R.id.rotina -> {
                val trocaTela = Intent(this, RelatorioActivity::class.java)
                startActivity(trocaTela)
            }
            R.id.sobre -> {
                val trocaTela = Intent(this, SobreActivity::class.java)
                startActivity(trocaTela)
            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout_sobre)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
