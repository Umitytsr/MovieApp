package com.umitytsr.movieapp.domain.Extensions

import com.umitytsr.movieapp.data.model.favorite.Favorite
import com.umitytsr.movieapp.data.model.movie.ResultMovie
import com.umitytsr.movieapp.data.model.series.Result
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

fun List<Result>.toTvSeries(): List<Movie>{
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

