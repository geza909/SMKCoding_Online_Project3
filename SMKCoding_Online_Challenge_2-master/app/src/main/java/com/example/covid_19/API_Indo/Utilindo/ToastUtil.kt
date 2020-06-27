package com.example.covid_19.API_Indo.Utilindo

import android.content.Context
import android.widget.Toast


fun tampilToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
