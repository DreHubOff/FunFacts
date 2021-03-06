package com.studying.funfacts.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.studying.funfacts.R
import com.studying.funfacts.network.ApiService
import com.studying.funfacts.network.model.Fact
import com.studying.funfacts.ui.main.adapter.FactsAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragment : Fragment() {

    companion object {
        private val factAdapter = FactsAdapter()
        fun newInstance() = ListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        facts_list.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = factAdapter
        }
    }

    fun updateFactsList(requestMap: HashMap<Int, String>){
        factAdapter.update(requestMap.values.toList())
    }
}


