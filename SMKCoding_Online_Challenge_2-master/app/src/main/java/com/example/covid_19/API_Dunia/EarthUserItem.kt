package com.example.covid_19.API_Dunia


import com.google.gson.annotations.SerializedName

data class EarthUserItem(
    @SerializedName("attributes")
    val attributes: Attributes
)