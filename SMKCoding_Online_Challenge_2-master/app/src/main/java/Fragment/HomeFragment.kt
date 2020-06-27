package Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.example.covid_19.BeritaAdapter
import com.example.covid_19.CRUD_firebase_DB.Berita
import com.example.covid_19.R
import com.google.firebase.database.*

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var etJudul : EditText
    private lateinit var etIsi : EditText
    private lateinit var btnSimpan : Button
    private lateinit var listBrt : ListView
    private lateinit var ref : DatabaseReference
    private lateinit var brtList : MutableList<Berita>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

        ): View?{
        return inflater.inflate(R.layout.fragment_home,container, false)


    }
    override fun onClick(p0: View?) {
        saveData()
    }

    private fun saveData() {
        val judul = etJudul.text.toString().trim()
        val isi = etIsi.text.toString().trim()

        if(judul.isEmpty()){
            etJudul.error = "Mohon Isi Judul Berita!"
            return
        }
        if(isi.isEmpty()){
            etIsi.error = "Mohon Isi Berita!"
            return
        }

        val brtId = ref.push().key
        val brt = Berita(brtId, judul, isi)
        if (brtId != null) {
            ref.child(brtId).setValue(brt).addOnCompleteListener{
                Toast.makeText(context, "Data Berita Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override  fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ref = FirebaseDatabase.getInstance().getReference("berita")
        listBrt = view.findViewById(R.id.lv_brt)
        etJudul = view.findViewById(R.id.et_judul)
        etIsi = view.findViewById(R.id.et_isi)
        btnSimpan = view.findViewById(R.id.btn_simpan)
        btnSimpan.setOnClickListener(this)

        brtList = mutableListOf()

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    brtList.clear()
                    for(h in snapshot.children){
                        val berita = h.getValue(Berita::class.java)
                        if(berita!=null){
                            brtList.add(berita)
                        }
                    }
                    val adapter = context?.let { BeritaAdapter(it, R.layout.item_brt,brtList) }
                    listBrt.adapter = adapter
                }
            }

        })

    }


}