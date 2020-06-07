package com.studying.funfacts.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.studying.funfacts.R
import com.studying.funfacts.network.ApiService
import com.studying.funfacts.network.model.ResultHolder
import com.studying.funfacts.ui.search.SearchFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var listFragment: ListFragment
    private lateinit var searchFragment: SearchFragment
    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            listFragment = ListFragment.newInstance()
            searchFragment = SearchFragment.newInstance()
        }
        disposable = ApiService.getData("cat")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ facts: ResultHolder -> showInfo(facts) }) { t: Throwable? -> showError(t) }

        supportFragmentManager.beginTransaction()
            .replace(R.id.search_container, searchFragment)
    }

    private fun showError(t: Throwable?) {
        println("ERROR")
    }

    private fun showInfo(facts: ResultHolder) {
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.list_container, listFragment.apply { updateFactsList(facts) })
//            .commit()
    }
}