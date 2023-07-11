package com.umitytsr.movieapp.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.umitytsr.movieapp.data.model.favorite.Favorite
import com.umitytsr.movieapp.data.model.movie.ResultMovie
import com.umitytsr.movieapp.data.model.series.Result

class DataBaseConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromStringToListDouble(value: String): List<Double?> {
        val listType = object : TypeToken<List<Double?>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromDoubleList(list: List<Double?>) : String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToListString(value: String): List<String?> {
        val listType = object : TypeToken<List<String?>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromListString(list: List<String?>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToListInt(value: String): List<Int?> {
        val listType = object : TypeToken<List<Int?>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromListInt(list: List<Int?>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToListResultmovie(value: String): List<ResultMovie?> {
        val listType = object : TypeToken<List<ResultMovie?>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromListResultMovie(list: List<ResultMovie?>?): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToListResult(value: String): List<Result?> {
        val listType = object : TypeToken<List<Result?>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromListResult(list: List<Result?>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromFavoriteListToString(value: List<Favorite?>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromStringToFavoriteList(value: String): List<Favorite?> {
        val listType = object : TypeToken<List<Favorite?>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringToListBoolean(value: String): List<Boolean?> {
        val listType = object : TypeToken<List<Boolean?>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromListBoolean(list: List<Boolean?>): String {
        return gson.toJson(list)
    }

}