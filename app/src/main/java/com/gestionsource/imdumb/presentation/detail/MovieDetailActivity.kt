package com.gestionsource.imdumb.presentation.detail

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gestionsource.imdumb.databinding.ActivityMovieDetailBinding
import com.gestionsource.imdumb.domain.model.MovieDetail
import com.gestionsource.imdumb.presentation.detail.adapter.ActorAdapter
import com.gestionsource.imdumb.presentation.detail.adapter.ImageCarouselAdapter
import com.gestionsource.imdumb.presentation.recommend.RecommendBottomSheet
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.View {

    companion object {
        const val EXTRA_MOVIE_ID = "movie_id"
    }

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var imageCarouselAdapter: ImageCarouselAdapter
    private lateinit var actorAdapter: ActorAdapter
    private var currentMovie: MovieDetail? = null
    @Inject
    lateinit var presenter: MovieDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        setupAdapters()

        presenter.attach(this)

        val movieId = intent.getIntExtra(
            EXTRA_MOVIE_ID,
            -1
        )

        presenter.loadMovie(movieId)

        binding.btnRecommend.setOnClickListener {
            currentMovie?.let { movie ->
                RecommendBottomSheet
                    .newInstance(movie.overviewHtml)
                    .show(supportFragmentManager, "RecommendBottomSheet")
            }
        }
    }

    private fun setupAdapters() {
        imageCarouselAdapter = ImageCarouselAdapter()
        binding.vpImages.adapter = imageCarouselAdapter

        actorAdapter = ActorAdapter()
        binding.rvActors.apply {
            layoutManager = LinearLayoutManager(
                this@MovieDetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = actorAdapter
        }
    }

    override fun showMovie(movie: MovieDetail) {
            currentMovie = movie
            binding.toolbar.title = movie.title
            binding.tvTitle.text = movie.title
            binding.tvRating.text = "Calificación: ${movie.rating}"
            binding.tvOverview.text = Html.fromHtml(movie.overviewHtml, Html.FROM_HTML_MODE_LEGACY)

            imageCarouselAdapter.submitList(movie.imageUrls)
            actorAdapter.submitList(movie.actors)
    }

    override fun showLoading() {
        binding.fullScreenLoader.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.fullScreenLoader.visibility = View.GONE
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}