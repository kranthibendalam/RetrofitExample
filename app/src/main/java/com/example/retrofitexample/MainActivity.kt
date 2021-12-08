package com.example.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const  val url = "https://jsonplaceholder.typicode.com/"
class MainActivity : AppCompatActivity() {
    lateinit var myAdapter: MyAdapter
    lateinit var linearlayoutmanager : LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        reUser.setHasFixedSize(true)
        linearlayoutmanager = LinearLayoutManager(this)
        reUser.layoutManager = linearlayoutmanager
        getMyData();
    }
   private fun getMyData(){
        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url).build().create(ApiInter::class.java)
          val retrofitData = retrofitBuilder.getData()

          retrofitData.enqueue(object : Callback<List<MydataItem>?> {
              override fun onResponse(
                  call: Call<List<MydataItem>?>,
                  response: Response<List<MydataItem>?>
              ) {

                  val responseBody = response.body()!!
                  myAdapter = MyAdapter(baseContext,responseBody)
                  myAdapter.notifyDataSetChanged()
                  reUser.adapter = myAdapter



              }

              override fun onFailure(call: Call<List<MydataItem>?>, t: Throwable) {
                  d("MainActivity","OnFailure" +t.message)
              }
          })
    }
}