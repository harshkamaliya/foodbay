package com.example.receipt.NetworkController

import com.example.receipt.Models.MealResponse
import retrofit2.http.GET

public interface ApiServices {

    @GET("randomselection.php")
    suspend fun getRandomMeals(): MealResponse
}