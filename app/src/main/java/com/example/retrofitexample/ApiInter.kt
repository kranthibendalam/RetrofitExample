package com.example.retrofitexample

import retrofit2.Call
import retrofit2.http.GET

interface ApiInter {
    @GET("posts")
   fun getData(): Call<List<MydataItem>>
}