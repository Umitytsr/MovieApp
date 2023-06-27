package com.umitytsr.movieapp.domain.Extensions

import com.umitytsr.movieapp.data.model.movie.ResultMovie
import com.umitytsr.movieapp.domain.model.Movie
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun List<ResultMovie>.toMovie(): List<Movie>{
    return this.map {
        Movie(
            id = it.id,
            title = it.title,
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

