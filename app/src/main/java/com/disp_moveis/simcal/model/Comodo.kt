package com.disp_moveis.simcal.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Comodo constructor(var nome : String) : Parcelable {


    var dispositivos_conectados : ArrayList<Dispositivo> = arrayListOf(
        Dispositivo("teste1", this),
        Dispositivo("teste2", this)
    )

    constructor(parcel: Parcel) : this(parcel.readString()) {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nome)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Comodo> {
        override fun createFromParcel(parcel: Parcel): Comodo {
            return Comodo(parcel)
        }

        override fun newArray(size: Int): Array<Comodo?> {
            return arrayOfNulls(size)
        }
    }

}