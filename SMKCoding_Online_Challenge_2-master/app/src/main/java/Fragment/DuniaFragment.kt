package Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19.*
import com.example.covid_19.API_Dunia.DataDunia.apiRequest
import com.example.covid_19.API_Dunia.DataDunia.httpClient
import com.example.covid_19.API_Dunia.DataDunia.EarthService
import com.example.covid_19.API_Dunia.EarthUserAdapter
import com.example.covid_19.API_Dunia.EarthUserItem
import com.example.covid_19.API_Dunia.UtilDunia.dismissLoading
import com.example.covid_19.API_Dunia.UtilDunia.showLoading
import com.example.covid_19.API_Dunia.UtilDunia.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_profil.swipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_profil.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DuniaFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View,@Nullable savedInstanceState: Bundle?) {

        super.onViewCreated(view,savedInstanceState)
        callApiGetEarthUser()}

    private fun callApiGetEarthUser() {
        showLoading(context!!,swipeRefreshLayout)

        val httpClient = httpClient()
        val apiRequest = apiRequest<EarthService>(httpClient)

        val call = apiRequest.getEarth()
        call.enqueue(object : Callback<List<EarthUserItem>> {

            override fun onFailure(call: Call<List<EarthUserItem>>, t:Throwable)
            {
                dismissLoading (swipeRefreshLayout) }

            override fun onResponse(call: Call<List<EarthUserItem>>, response: Response<List<EarthUserItem>>) {
                dismissLoading(swipeRefreshLayout)
                when {response.isSuccessful->

                    when {response.body()?.size !=0->

                        tampilEarthUser(response.body()!!)

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

    private fun tampilEarthUser(earthUsers:List<EarthUserItem>)
    {listEarthUser.layoutManager = LinearLayoutManager(context)
        listEarthUser.adapter =
            EarthUserAdapter(context!!, earthUsers) {

                val earthUser = it
                tampilToast(context!!, earthUser.attributes.countryRegion)

            }
    }

    override fun onDestroy() {super.onDestroy()
        this.clearFindViewByIdCache()
    }
}

