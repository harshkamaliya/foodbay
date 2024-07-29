package com.example.receipt.repository

import com.example.receipt.Models.Ingredient
import com.example.receipt.Models.Meal
import com.example.receipt.NetworkController.RetrofitClient

class MealRepository {

    private val apiService = RetrofitClient.apiService

    suspend fun getRandomMeals(): List<Meal> {
        return apiService.getRandomMeals().meals
    }



}