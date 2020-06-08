package com.studying.funfacts.network.model

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.studying.funfacts.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.HashMap

private const val LAST_TYPE_FILE = "Last animal type file LAST_TYPE_FILE"
const val LAST_TYPE = "Last animal type LAST_TYPE"

class RequestBuilder(private val context: Context) {

    private val requestResult = context as RequestResult
    val lastEnterType: SharedPreferences =
        context.getSharedPreferences(LAST_TYPE_FILE, Context.MODE_PRIVATE)

    fun buildRequest(animal: String) {
        val validAnimal = animal.toLowerCase(Locale.ROOT).trim()
        val requestMap = HashMap<Int, String>()

        lastEnterType.edit().clear().apply()

        val preferences = context.getSharedPreferences(validAnimal, Context.MODE_PRIVATE)

        if (preferences.getString("0", null).isNullOrEmpty()) {

            ApiService.getData(validAnimal).enqueue(object : Callback<List<Fact>> {
                override fun onFailure(call: Call<List<Fact>>, t: Throwable) {
                    Toast.makeText(context, "Connection error", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<List<Fact>>, response: Response<List<Fact>>) {
                    if (response.body().isNullOrEmpty()) {
                        Toast.makeText(context, "No matches found", Toast.LENGTH_SHORT).show()
                    } else {
                        val editor = preferences.edit()
                        response.body()?.forEachIndexed { pos, fact ->
                            requestMap[pos] = fact.text
                            editor.putString(pos.toString(), fact.text)
                        }
                        requestResult.onRequestResult(requestMap)
                        editor.apply()
                        lastEnterType.edit().putString(LAST_TYPE, validAnimal).apply()
                    }
                }
            })
        } else {
            preferences.all.forEach { (pos, text) -> requestMap[pos.toInt()] = text as String }
            requestResult.onRequestResult(requestMap)
            lastEnterType.edit().putString(LAST_TYPE, validAnimal).apply()
        }
    }

    interface RequestResult {
        fun onRequestResult(requestMap: HashMap<Int, String>)
    }
}