package com.example.covid_19.API_Provinsi.DataProv


import com.example.covid_19.API_Provinsi.ProvinsiItem
import retrofit2.Call
import retrofit2.http.GET

interface ProvinsiService {
    @GET("indonesia/provinsi/")
    fun getProvinsi(): Call<List<ProvinsiItem>>
}
