package Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19.API_Provinsi.DataProv.ProvinsiService
import com.example.covid_19.API_Provinsi.DataProv.apiRequest
import com.example.covid_19.API_Provinsi.DataProv.httpClient
import com.example.covid_19.API_Provinsi.ProvinsiAdapter
import com.example.covid_19.API_Provinsi.ProvinsiItem
import com.example.covid_19.R
import com.example.covid_19.API_Provinsi.UtilProv.dismissLoading
import com.example.covid_19.API_Provinsi.UtilProv.showLoading
import com.example.covid_19.API_Provinsi.UtilProv.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_province.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProvinsiFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        return inflater.inflate(R.layout.fragment_province,container, false)
    }
    override fun onViewCreated(view: View,
                               @Nullable savedInstanceState: Bundle?) {

        super.onViewCreated(view,savedInstanceState)
        callApiGetIndo()}

    private fun callApiGetIndo() {
        showLoading(context!!,swipeRefreshLayout)

        val httpClient = httpClient()
        val apiRequest = apiRequest<ProvinsiService>(httpClient)

        val call = apiRequest.getProvinsi()
        call.enqueue(object : Callback<List<ProvinsiItem>> {

            override fun onFailure(call: Call<List<ProvinsiItem>>, t:Throwable)
            {
                dismissLoading (swipeRefreshLayout) }

            override fun onResponse(call:Call<List<ProvinsiItem>>, response:Response<List<ProvinsiItem>>) {
                dismissLoading(swipeRefreshLayout)
                when {response.isSuccessful->

                    when {response.body()?.size !=0->

                        tampilIndo(response.body()!!)

                        else -> {
                            tampilToast(context!!,  "Berhasil")
                        }
                    }

                    else -> {
                        tampilToast(context!!, " Gagal")
                    }
                }

            }
        })
    }

    private fun tampilIndo(indousers:List<ProvinsiItem>)
    {listProv.layoutManager = LinearLayoutManager(context)
        listProv.adapter = ProvinsiAdapter(
            context!!,
            indousers
        ) {

            val Province = it
            tampilToast(context!!, Province.attributes.provinsi)

        }
    }

    override fun onDestroy() {super.onDestroy()
        this.clearFindViewByIdCache()
    }
}
