package com.example.covid_19.CRUD_firebase_DB


data class Berita(
    val id :String?,
    val judul : String,
    val isi : String

){
    constructor() : this("","","")
}
