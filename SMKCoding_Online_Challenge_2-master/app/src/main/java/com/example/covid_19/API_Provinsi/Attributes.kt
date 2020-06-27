package com.example.covid_19.API_Provinsi


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Attributes(
    @SerializedName("FID")
    val fID: Int,
    @SerializedName("Kasus_Meni")
    val kasusMeni: Int,
    @SerializedName("Kasus_Posi")
    val kasusPosi: Int,
    @SerializedName("Kasus_Semb")
    val kasusSemb: Int,
    @SerializedName("Kode_Provi")
    val kodeProvi: Int,
    @SerializedName("Provinsi")
    val provinsi: String
): Serializable