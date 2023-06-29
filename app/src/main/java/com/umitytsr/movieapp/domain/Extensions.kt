package com.umitytsr.movieapp.domain.Extensions

import com.umitytsr.movieapp.data.model.movie.ResultMovie
import com.umitytsr.movieapp.data.model.series.Result
import com.umitytsr.movieapp.domain.model.Movie
import com.umitytsr.movieapp.domain.model.TvSeries
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

fun List<Result>.toTvSeries(): List<TvSeries>{
    return this.map {
        TvSeries(
            backdropPath = it.backdropPath,
            firstAirDate = it.firstAirDate,
            genreİds = it.genreİds,
            id = it.id,
            name = it.name,
            overview = it.overview,
            posterPath = it.posterPath,
            voteAverage = it.voteAverage
        )
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

