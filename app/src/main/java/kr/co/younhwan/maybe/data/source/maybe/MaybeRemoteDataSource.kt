package kr.co.younhwan.maybe.data.source.maybe

import android.util.Log
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException

object MaybeRemoteDataSource : MaybeSource {
    private val client = OkHttpClient()
    private const val serverInfo = "http://43.201.50.115/maybe"
    private val jsonMediaType = "application/json; charset=utf-8".toMediaTypeOrNull()

    override fun read(readCallback: MaybeSource.ReadCallback?) {
        runBlocking {
            launch {
                // API Server address
                val site = serverInfo

                // Request
                val request = Request.Builder().url(serverInfo).get().build()

                // Response
                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        Log.e("abc", e.toString())

                    }

                    override fun onResponse(call: Call, response: Response) {
                        if (response.isSuccessful){
                            val resultText = response.body?.string()!!.trim()
                            val json = JSONObject(resultText)
                            val success = json.getBoolean("success")

                            if (success){
                                val data = json.getJSONObject("data")
                                Log.e("abc", data.toString())
                            }


                        }
                    }
                })
            }
        }
    }
}