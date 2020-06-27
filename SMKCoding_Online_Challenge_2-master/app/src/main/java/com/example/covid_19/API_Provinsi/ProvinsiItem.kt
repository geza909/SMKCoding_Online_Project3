package com.example.covid_19.API_Provinsi


import com.example.covid_19.API_Provinsi.Attributes
import com.google.gson.annotations.SerializedName

data class ProvinsiItem(
    @SerializedName("attributes")
    val attributes: Attributes
)