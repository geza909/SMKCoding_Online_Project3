package com.example.covid_19.API_Indo


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class indonesiaItem(
    @SerializedName("meninggal")
    val meninggal: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("positif")
    val positif: String,
    @SerializedName("sembuh")
    val sembuh: String
)