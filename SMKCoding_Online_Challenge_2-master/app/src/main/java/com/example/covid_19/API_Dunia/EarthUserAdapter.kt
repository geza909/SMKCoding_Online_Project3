package com.example.covid_19.API_Dunia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.earth_item.*

class EarthUserAdapter(private val context: Context,
                       private val items:List<EarthUserItem>,
                       private val listener: (EarthUserItem)-> Unit):
    RecyclerView.Adapter<EarthUserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =

        ViewHolder(context,
            LayoutInflater.from(context)
                .inflate(R.layout.earth_item, parent, false)
        )

    override fun getItemCount():Int{return items.size}

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {holder.bindItem(items.get(position), listener) }

    class ViewHolder(val context: Context, override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer
    {fun bindItem(item: EarthUserItem, listener:(EarthUserItem) -> Unit)

    {

        txtCountryName.text = item.attributes.countryRegion
        txtConfirmed.text= item.attributes.confirmed.toString()
        txtRecovered.text= item.attributes.recovered.toString()
        txtDeath.text = item.attributes.deaths.toString()
        txtActive.text = item.attributes.active.toString()






        containerView.setOnClickListener {listener(item) }
    }

    }

}
