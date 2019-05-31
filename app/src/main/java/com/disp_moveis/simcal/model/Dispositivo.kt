package com.disp_moveis.simcal.model


import java.util.*
import kotlin.collections.ArrayList


class Dispositivo constructor(var nome: String, var comodo: Comodo) {
    var isConectado: Boolean = true
    var lista_mensagem: ArrayList<Mensagem> = arrayListOf()
    var aguarda_retorno : Boolean = false
    lateinit var requisicao_mensagem : Mensagem
    lateinit var requisicao_dispositivo : Dispositivo


    fun mudaEstado() {
        isConectado = !isConectado
    }

    fun conexao() {

        Thread {
            if (this.aguarda_retorno){

                Thread.sleep(1000)
                if (this.aguarda_retorno){
                    this.enviaMensagem(this.requisicao_dispositivo, this.requisicao_mensagem.texto, this.requisicao_mensagem.tipo)
                }
            }

        }.start()
    }

    fun recebeMensagem(mensagem: Mensagem) {
        this.lista_mensagem.add(mensagem)
        if (mensagem.tipo == Mensagem.Resposta){
            this.aguarda_retorno = false
        }
    }

    fun enviaMensagem(destino: Dispositivo, texto: String, tipo : Int) {
        var mensagem = preparaMensagem(texto, tipo)
        destino.recebeMensagem(mensagem)
        if (mensagem.tipo == Mensagem.Requisicao){
            this.aguarda_retorno = true
            this.requisicao_mensagem = mensagem
            this.requisicao_dispositivo = destino
        }
    }

    private fun preparaMensagem(texto: String, flag: Int): Mensagem {
        val data = Date()

        var mensagem = Mensagem(data, texto, this, flag)

        return mensagem
    }




    //GERAR XML BASEADO NAS FUNCOES DO DISPOSITIVO RETORNA
    fun mapeamento() {
    }


}