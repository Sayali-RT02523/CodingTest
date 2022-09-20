package com.example.codingtest.repository

import android.app.Application
import com.example.codingtest.model.config
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream


class MainRepository(private val application: Application) {


    fun loadConfig(): config? {
        val json: String?
        json = try {
            val `is`: InputStream =application.assets.open("config.json") // your file name
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        val gson = Gson()
        val response = gson.fromJson(json, config::class.java)

        return response
    }

    fun loadJSONFromAsset(): String? {
        val json: String = try {
            val `is`: InputStream =application.assets.open("pets_list.json") // your file name
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }


//    suspend fun getAllMovies() : NetworkState<List<Movie>> {
//        val response = retrofitService.getAllMovies()
//        return if (response.isSuccessful) {
//            val responseBody = response.body()
//            if (responseBody != null) {
//                NetworkState.Success(responseBody)
//            } else {
//                NetworkState.Error(response)
//            }
//        } else {
//            NetworkState.Error(response)
//        }
//    }

}