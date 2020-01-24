package com.yoshimasa36g.tryjetpackcompose.web

import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersAPI {
    @GET("api")
    fun users(@Query("results") count: Int = 20): Call<UsersAPIResponse>
}

class UsersDownloader {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create<UsersAPI>()

    fun fetch(onSuccess: (List<UserOnAPI>) -> Unit,
              onFailure: (Throwable) -> Unit) {
        api.users().enqueue(object: Callback<UsersAPIResponse> {
            override fun onFailure(call: Call<UsersAPIResponse>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(
                call: Call<UsersAPIResponse>,
                response: Response<UsersAPIResponse>
            ) {
                response.body()?.let {
                    onSuccess(it.results)
                }
            }
        })
    }
}
