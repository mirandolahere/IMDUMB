package com.gestionsource.imdumb.presentation.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gestionsource.imdumb.databinding.ActivityHomeBinding
import com.gestionsource.imdumb.domain.model.MovieCategory
import com.gestionsource.imdumb.presentation.detail.MovieDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.gestionsource.imdumb.presentation.home.adapter.CategoryAdapter

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), HomeContract.View {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var categoryAdapter: CategoryAdapter

    @Inject
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "IMDUMB"
        presenter.attach(this)
        setupRecyclerView()
        presenter.loadCategories()
    }

    private fun setupRecyclerView() {
        categoryAdapter = CategoryAdapter { movieId ->
            presenter.onMovieSelected(movieId)
        }

            binding.rvCategories.apply {
                layoutManager = LinearLayoutManager(this@HomeActivity)
                adapter = categoryAdapter
            }

    }

    override fun showWelcome(text: String) {
        binding.tvWelcome.text = text
    }

    override fun showCategories(categories: List<MovieCategory>) {
        categoryAdapter.submitList(categories)
    }

    override fun navigateToDetail(movieId: Int) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, movieId)
        startActivity(intent)
    }

    override fun showLoading() {
        binding.fullScreenLoader.visibility = android.view.View.VISIBLE
    }

    override fun hideLoading() {
        binding.fullScreenLoader.visibility = android.view.View.GONE
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}