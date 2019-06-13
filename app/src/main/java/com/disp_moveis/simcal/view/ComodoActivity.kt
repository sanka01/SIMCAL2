package com.disp_moveis.simcal.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.disp_moveis.simcal.R

class ComodoActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comodo)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                var trocaTela = Intent(this.baseContext, R.layout.content_main::class.java)
                startActivity(trocaTela)
            }
            R.id.relatorio -> {
                val trocaTela = Intent(this.baseContext, R.layout.activity_relatorio::class.java)
                startActivity(trocaTela)
            }
            R.id.rotina -> {
                val trocaTela = Intent(this.baseContext, R.layout.activity_relatorio::class.java)
                startActivity(trocaTela)
            }
            R.id.sobre -> {
                val trocaTela = Intent(this.baseContext, R.layout.activity_sobre::class.java)
                startActivity(trocaTela)
            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true    }
}