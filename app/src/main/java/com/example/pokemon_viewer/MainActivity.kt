package com.example.pokemon_viewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    var photoUrl=""
    var title = ""
    var date = ""
    private lateinit var recyclerViewPic:RecyclerView
    private lateinit var urlArray: MutableList<String>
    private lateinit var idArray: MutableList<String>
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val button = findViewById<Button>(R.id.Next_data)
//        val imageView = findViewById<ImageView>(R.id.mars)
//        val title_img = findViewById<TextView>(R.id.Title_image)
//        val date_img = findViewById<TextView>(R.id.date)
        recyclerViewPic = findViewById<RecyclerView>(R.id.pics_list)
        urlArray = mutableListOf<String>()
        idArray = mutableListOf<String>()
        getNewASTR()
//        getNextImage(button, imageView,title_img,date_img)
//        getNextImage(imageView,title_img,date_img)
    }

//    private fun dataHelper(value: Int){
//        photoUrl = urlArray[value]
//        title = urlArray[value]
//        date = idArray[value]
//    }

    private fun getNewASTR(){

        val client = AsyncHttpClient()

        client["https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&page=2&api_key=DEMO_KEY", object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                // Access a JSON object response with `json.jsonObject`
                Log.d("DEBUG OBJECT", json.jsonObject.toString())
                val photo_array = json.jsonObject.getJSONArray("photos")
                for ( i  in 0 until photo_array.length()){
                    urlArray.add(photo_array.getJSONObject(i).getString("img_src"))
                    idArray.add(photo_array.getJSONObject(i).getString("id"))
                }

                val adapter = CustomAdapter(urlArray,idArray)
                recyclerViewPic.adapter = adapter
                recyclerViewPic.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerViewPic.addItemDecoration(DividerItemDecoration(this@MainActivity,LinearLayoutManager.VERTICAL))

                Log.d("Mars Details", "URL: $photoUrl, Name: $title, Date: $date")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
                Log.d("MARS FAILURE",response)
            }
        }]

    }

//    private fun getNextImage(imageView: ImageView, textView1: TextView, textView2: TextView){
//            dataHelper(counter)
//            counter += 1
//            if(counter >= urlArray.size){
//                counter = 0
//            }
//            Glide.with(this)
//                .load(photoUrl)
//                .timeout(5000)
//                .fitCenter()
//                .into(imageView)
//
//            textView1.text = title
//            textView2.text = date
//    }

}
