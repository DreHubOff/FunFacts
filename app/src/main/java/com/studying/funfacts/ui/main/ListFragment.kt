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
        private val factsList = mutableListOf<Fact>()
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
        val context = view.context
        val preferences = context.getSharedPreferences("cat", Context.MODE_PRIVATE)

        if (preferences.getString("0", null) == null) {
            ApiService.getData("cat").enqueue(object : Callback<List<Fact>> {
                override fun onFailure(call: Call<List<Fact>>, t: Throwable) = Unit

                @SuppressLint("DefaultLocale")
                override fun onResponse(call: Call<List<Fact>>, response: Response<List<Fact>>) {
                    factsList.apply {
                        clear()
                        addAll(response.body() as List<Fact>)
                    }
                    factAdapter.update(factsList)
                    val preferences = context.getSharedPreferences(
                        factsList.first().type.toLowerCase().trim(),
                        Context.MODE_PRIVATE
                    )
                    val editor = preferences.edit()
                    for (i in 0..factsList.size.minus(1)) {
                        editor.putString(i.toString(), factsList[i].text)
                    }
                    editor.apply()
                }
            })
        } else {
        }

    }
}


