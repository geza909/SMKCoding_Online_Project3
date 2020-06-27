package com.example.covid_19

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.example.covid_19.FirebaseAuth.LoginActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_akun.*

class MainActivity : AppCompatActivity() {
    val menuTeks = arrayOf("Home", "Indonesia", "Dunia", "Provinsi", "Profil")
    val menuIcon = arrayOf(R.drawable.ic_home, R.drawable.ic_world, R.drawable.ic_maps_and_flags, R.drawable.ic_shape, R.drawable.ic_profil)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val adapter = ViewPagerAdapter(this)
        view_pager.setAdapter(adapter);
        TabLayoutMediator(tab_layout, view_pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = menuTeks[position]
                tab.icon = ResourcesCompat.getDrawable(
                    resources, menuIcon[position], null)
            }).attach()
        }
    }

