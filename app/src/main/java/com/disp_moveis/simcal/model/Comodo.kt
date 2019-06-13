package com.disp_moveis.simcal.model

import java.io.Serializable


data class Comodo constructor(var nome : String) : Serializable{
    var dispositivos_conectados : ArrayList<Dispositivo> = arrayListOf()

}