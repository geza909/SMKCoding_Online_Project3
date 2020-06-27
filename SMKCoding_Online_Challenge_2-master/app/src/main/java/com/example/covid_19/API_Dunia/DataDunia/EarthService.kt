package com.example.covid_19.API_Dunia.DataDunia

import com.example.covid_19.API_Dunia.EarthUserItem
import retrofit2.Call
import retrofit2.http.GET

interface EarthService {
    @GET(" ")
    fun getEarth(): Call<List<EarthUserItem>>
}