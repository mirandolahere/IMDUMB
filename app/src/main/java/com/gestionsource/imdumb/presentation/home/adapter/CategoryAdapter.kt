package com.gestionsource.imdumb.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gestionsource.imdumb.databinding.ItemCategoryBinding
import com.gestionsource.imdumb.domain.model.MovieCategory

class CategoryAdapter(
    private val onMovieClick: (Int) -> Unit
) : ListAdapter<MovieCategory, CategoryAdapter.CategoryViewHolder>(DiffCallback()) {

    inner class CategoryViewHolder(
        private val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val movieAdapter = MovieAdapter(onMovieClick)

        init {
            binding.rvMovies.apply {
                adapter = movieAdapter
                layoutManager = LinearLayoutManager(
                    binding.root.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                setHasFixedSize(true)
            }
        }

        fun bind(category: MovieCategory) {
            binding.tvCategoryName.text = category.name
            movieAdapter.submitList(category.movies)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<MovieCategory>() {
        override fun areItemsTheSame(
            oldItem: MovieCategory,
            newItem: MovieCategory
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieCategory,
            newItem: MovieCategory
        ): Boolean {
            return oldItem == newItem
        }
    }
}