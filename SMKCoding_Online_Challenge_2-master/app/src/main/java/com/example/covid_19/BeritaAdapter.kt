package com.example.covid_19

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.covid_19.CRUD_firebase_DB.Berita
import com.google.firebase.database.FirebaseDatabase

class BeritaAdapter (val mCtx : Context, val layoutResId : Int, val brtList :List<Berita> ) :
    ArrayAdapter<Berita>(mCtx,layoutResId,brtList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)

        val view : View = layoutInflater.inflate(layoutResId,null)

        val tvJudul : TextView = view.findViewById(R.id.tv_judul)
        val tvIsi : TextView = view.findViewById(R.id.tv_isi)
        val tvEdit : TextView = view.findViewById(R.id.tv_edit)
        val berita = brtList[position]


        tvEdit.setOnClickListener{
            showUpdateDialog(berita)
        }


        tvJudul.text = berita.judul
        tvIsi.text = berita.isi

        return view
    }

    fun showUpdateDialog(berita: Berita) {
        val builder = AlertDialog.Builder(mCtx)

        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.update_dialog, null)

        val etJudul = view.findViewById<EditText>(R.id.et_judul)
        val etIsi = view.findViewById<EditText>(R.id.et_isi)

        etJudul.setText(berita.judul)
        etIsi.setText(berita.isi)

        builder.setView(view)

        builder.setPositiveButton("update"){
            p0,p1 ->
            val dbBrt = FirebaseDatabase.getInstance().getReference("berita")
            val judul = etJudul.text.toString().trim()
            val isi = etIsi.text.toString().trim()
            if(judul.isEmpty()){
                etJudul.error = "Mohon Isi Judul Berita"
                etJudul.requestFocus()
                return@setPositiveButton
            }
            if(isi.isEmpty()){
                etIsi.error = "Mohon Isi Judul Berita"
                etIsi.requestFocus()
                return@setPositiveButton
            }
            val berita = Berita(berita.id, judul, isi)
            dbBrt.child(berita.id!!).setValue(berita)
            Toast.makeText(mCtx,"Data Berhasil di update", Toast.LENGTH_SHORT).show()
        }

        builder.setNeutralButton("Kembali"){
            p0,p1 ->
        }

        builder.setNegativeButton("hapus"){p0,p1 ->
            val dbBrt= FirebaseDatabase.getInstance().getReference("berita").child(berita.id!!)
            dbBrt.removeValue()

            Toast.makeText(mCtx, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()

        }


        val alert = builder.create()
        alert.show()

    }

}