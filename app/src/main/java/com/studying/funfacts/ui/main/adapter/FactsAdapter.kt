package com.studying.funfacts.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studying.funfacts.R
import com.studying.funfacts.network.model.Fact
import kotlinx.android.synthetic.main.item.view.*


class FactsAdapter() :
    RecyclerView.Adapter<FactsAdapter.FactHolder>() {
    private val factsList = mutableListOf<Fact>()

    fun update(facts: List<Fact>) {
        factsList.apply {
            clear()
            addAll(facts)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactHolder =
        FactHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))

    override fun getItemCount() = factsList.size

    override fun onBindViewHolder(holder: FactHolder, position: Int) {
        holder.bind(position, factsList[position])
    }

    class FactHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int, fact: Fact) {
            itemView.number_of_fact.text = position.plus(1).toString()
            itemView.fact_buddy.text = fact.text
        }
    }
}