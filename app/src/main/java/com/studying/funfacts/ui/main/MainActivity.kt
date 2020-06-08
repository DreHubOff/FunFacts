package com.studying.funfacts.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.studying.funfacts.R
import com.studying.funfacts.network.model.RequestBuilder
import com.studying.funfacts.ui.search.SearchFragment

class MainActivity : AppCompatActivity(), RequestBuilder.RequestResult {

    private lateinit var listFragment: ListFragment
    private lateinit var searchFragment: SearchFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            listFragment = ListFragment.newInstance()
            searchFragment = SearchFragment.newInstance()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.list_container, listFragment)
            .replace(R.id.search_container, searchFragment)
            .commit()

        RequestBuilder(this,"snake").buildRequest()
    }

    override fun onRequestResult(requestMap: HashMap<Int, String>) = listFragment.updateFactsList(requestMap)


}
