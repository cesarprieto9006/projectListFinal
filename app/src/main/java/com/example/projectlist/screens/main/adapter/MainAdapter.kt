package com.example.projectlist.screens.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectlist.databinding.ItemListBinding
import com.example.projectlist.screens.main.model.Lf
import kotlin.collections.ArrayList

class MainAdapter :
    RecyclerView.Adapter<MainAdapter.HistoryViewHolder>() {

    private var listTicket = ArrayList<Lf>()
    private var filterListRoute: ArrayList<Lf> = listTicket
    private lateinit var context:Context

    fun addAll(collection: Collection<Lf>) {
        listTicket.clear()
        notifyDataSetChanged()
        for (data in collection) {
            listTicket.add(data)
            notifyItemInserted(listTicket.size)
        }
    }

    fun contextViewAdapter(context: Context) {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        return HistoryViewHolder(binding)
    }

    override fun getItemCount() = filterListRoute.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val itemRoute = filterListRoute[position]
        holder.bind(itemRoute)
    }

    inner class HistoryViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ticket: Lf) {
            binding.tvDescription.text = ticket.lf
        }
    }
}