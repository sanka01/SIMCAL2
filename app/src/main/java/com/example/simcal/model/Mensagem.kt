package com.example.simcal.model


import java.util.*

class Mensagem constructor(var data: Date, var texto: String, var origem: Dispositivo, var tipo: Int) {

    companion object {
        const val   Log : Int = 0
        const val   Acao : Int = 1
        const val   Error : Int = 2
        const val   Aviso : Int = 3
        const val   Status : Int = 4
        const val   Requisicao : Int = 5
        const val   Resposta : Int = 6
    }


}