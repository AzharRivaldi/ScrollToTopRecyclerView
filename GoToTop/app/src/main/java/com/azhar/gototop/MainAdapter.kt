package com.azhar.gototop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.azhar.gototop.MainAdapter.ListViewHolder
import kotlinx.android.synthetic.main.list_item_main.view.*
import java.util.*

/**
 * Created by Azhar Rivaldi on 10-12-2020.
 */

class MainAdapter(private val modelMains: ArrayList<ModelMain>) : RecyclerView.Adapter<ListViewHolder>() {

    //untuk onclick
    /*private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback?) {
        this.onItemClickCallback = onItemClickCallback
    }*/

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_main, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(listViewHolder: ListViewHolder, i: Int) {
        val modelMain = modelMains[i]
        listViewHolder.tvName.text = modelMain.name
        listViewHolder.tvJam.text = modelMain.jam
        listViewHolder.tvAlamat.text = modelMain.alamat
        //listViewHolder.itemView.setOnClickListener { onItemClickCallback?.onItemClicked(modelMain) }
    }

    override fun getItemCount(): Int {
        return modelMains.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView
        var tvJam: TextView
        var tvAlamat: TextView

        init {
            tvName = itemView.tvName
            tvJam = itemView.tvJam
            tvAlamat = itemView.tvAlamat
        }
    }

}