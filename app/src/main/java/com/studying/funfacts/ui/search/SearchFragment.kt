package com.studying.funfacts.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.studying.funfacts.R
import com.studying.funfacts.network.model.LAST_TYPE
import com.studying.funfacts.network.model.RequestBuilder
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val requestBuilder = RequestBuilder(view.context)
        search_text.setText(requestBuilder.lastEnterType.getString(LAST_TYPE, "Cat"))
        search_button.setOnClickListener {
            requestBuilder.buildRequest(search_text.text.toString())
        }
    }
}
