package edu.iastate.shubham8.flixter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    // Implement onCreateViewHolder, onBindViewHolder, and getItemCount methods

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val overviewTextView: TextView = itemView.findViewById(R.id.overviewTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val movieView = inflater.inflate(R.layout.item_movie, parent, false)
        return ViewHolder(movieView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val movie = movies[position]
        val imageView = viewHolder.imageView
        val titleTextView = viewHolder.titleTextView
        val overviewTextView = viewHolder.overviewTextView

        titleTextView.text = movie.title
        overviewTextView.text = movie.overview

        Glide.with(imageView.context)
            .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}") // Append the poster path to the base URL
            .placeholder(R.drawable.ic_launcher_background) // Placeholder image while loading
            .error(R.drawable.ic_launcher_background) // Image to display if loading fails
            .into(imageView)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}
