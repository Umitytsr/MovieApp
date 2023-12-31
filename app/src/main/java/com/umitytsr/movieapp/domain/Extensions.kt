package com.umitytsr.movieapp.domain.Extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.umitytsr.movieapp.R
import com.umitytsr.movieapp.data.model.favorite.Favorite
import com.umitytsr.movieapp.data.model.movie.ResultMovie
import com.umitytsr.movieapp.data.model.series.ResultTvSeries
import com.umitytsr.movieapp.domain.model.Movie
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

fun List<ResultMovie>.toMovie(): List<Movie>{
    return this.map {
        Movie(
            backdropPath = it.backdropPath,
            genreİds = it.genreİds,
            id = it.id,
            overview = it.overview,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            title = it.title,
            voteAverage = it.voteAverage
        )
    }
}

fun List<ResultTvSeries>.toTvSeries(): List<Movie>{
    return this.map {
        Movie(
            backdropPath = it.backdropPath,
            genreİds = it.genreİds,
            id = it.id,
            overview = it.overview,
            posterPath = it.posterPath,
            releaseDate = it.firstAirDate,
            title = it.name,
            voteAverage = it.voteAverage
        )
    }
}

fun Movie.toFavorite(): Favorite {
    return Favorite(
        backdropPath = this.backdropPath,
        genreİds = this.genreİds,
        id = this.id,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage,
    )
}

fun Favorite.toMovieForFavorite(): Movie {
    return Movie(
        backdropPath = this.backdropPath,
        genreİds = this.genreİds,
        id = this.id,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage
    )
}

fun ResultMovie.toMovieForPaging(): Movie {
    return Movie(
        backdropPath = this.backdropPath,
        genreİds = this.genreİds,
        id = this.id,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage,
    )
}

fun ResultTvSeries.toTvSeriesForPaging(): Movie {
    return Movie(
        backdropPath = this.backdropPath,
        genreİds = this.genreİds,
        id = this.id,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.firstAirDate,
        title = this.name,
        voteAverage = this.voteAverage,
    )
}

fun ImageView.loadImage(url: String?) {
    val placeholder = R.drawable.gray_placeholder
    url?.let {
        Glide.with(this.context)
            .load(url)
            .apply(RequestOptions())
            .error(placeholder)
            .into(this)
    } ?: kotlin.run {
        this.setImageResource(placeholder)
    }
}

fun Double.format(digits: Int): String {
    val df = DecimalFormat()
    df.decimalFormatSymbols = DecimalFormatSymbols(Locale.US)
    df.maximumFractionDigits = digits
    return df.format(this)
}

fun getReformatDate(dateInString: String?): String {

    return if (dateInString != null) {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        try {
            val date = parser.parse(dateInString)
            formatter.format(date!!)
        } catch (e: ParseException) {
            "-"
        }
    } else "-"
}

