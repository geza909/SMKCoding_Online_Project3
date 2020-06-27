package Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19.API_Indo.DataIndo.IndoService
import com.example.covid_19.API_Indo.DataIndo.apiRequest
import com.example.covid_19.API_Indo.DataIndo.httpClient
import com.example.covid_19.API_Indo.IndonesiaAdapter
import com.example.covid_19.R
import com.example.covid_19.API_Indo.Utilindo.dismissLoading
import com.example.covid_19.API_Indo.Utilindo.showLoading
import com.example.covid_19.API_Indo.Utilindo.tampilToast
import com.example.covid_19.API_Indo.indonesiaItem
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_news.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class IndoFragment : Fragment() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        }
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
            //Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_news, container, false)
        }
        override fun onViewCreated(view: View,
        @Nullable savedInstanceState: Bundle?) {

            super.onViewCreated(view,savedInstanceState)
            callApiGetIndo()}

        private fun callApiGetIndo() {
            showLoading(context!!,swipeRefreshLayout)

            val httpClient = httpClient()
            val apiRequest = apiRequest<IndoService>(httpClient)

            val call = apiRequest.getUsers()
            call.enqueue(object : Callback<List<indonesiaItem>> {

                override fun onFailure(call: Call<List<indonesiaItem>>, t:Throwable)
                {
                    dismissLoading (swipeRefreshLayout) }

                override fun onResponse(call:Call<List<indonesiaItem>>, response:Response<List<indonesiaItem>>) {
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

        private fun tampilIndo(indousers:List<indonesiaItem>)
        {listIndo.layoutManager = LinearLayoutManager(context)
            listIndo.adapter = IndonesiaAdapter(
                context!!,
                indousers
            ) {

                val indonesiaX = it
                tampilToast(context!!, indonesiaX.name)

            }
        }

        override fun onDestroy() {super.onDestroy()
            this.clearFindViewByIdCache()
        }
    }
