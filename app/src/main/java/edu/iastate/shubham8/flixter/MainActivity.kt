package edu.iastate.shubham8.flixter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MovieAdapter
    private lateinit var recyclerView: RecyclerView // Declare recyclerView here

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView) // Initialize recyclerView here
        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchMovies()
    }

    private fun fetchMovies() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MovieApiService::class.java)

        val apiKey = "a07e22bc18f5cb106bfe4cc1f83ad8ed" // Replace with your actual API key
        val call = service.getNowPlayingMovies(apiKey)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movies = response.body()?.results ?: emptyList()
                    adapter = MovieAdapter(movies)
                    recyclerView.adapter = adapter
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // Handle network failure
            }
        })
    }
}
