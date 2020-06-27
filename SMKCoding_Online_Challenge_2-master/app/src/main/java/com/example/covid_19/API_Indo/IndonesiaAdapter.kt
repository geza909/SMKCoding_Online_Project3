package com.example.covid_19.API_Indo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.indo_item.*

class IndonesiaAdapter (private val context: Context,
                        private val items:List<indonesiaItem>,
                        private val listener: (indonesiaItem)-> Unit):
    RecyclerView.Adapter<IndonesiaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =

        ViewHolder(
            context,
            LayoutInflater.from(context)
                .inflate(R.layout.indo_item, parent, false)
        )

    override fun getItemCount():Int{return items.size}

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {holder.bindItem(items.get(position), listener) }

    class ViewHolder(val context: Context, override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer
    {fun bindItem(item: indonesiaItem, listener:(indonesiaItem) -> Unit)

    {

        txtPositif.text = item.positif
        txtSembuh.text= item.sembuh
        txtMeninggal.text= item.meninggal





        containerView.setOnClickListener {listener(item) }
    }

    }

}
