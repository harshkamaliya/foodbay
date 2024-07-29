package com.example.receipt.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.receipt.Models.Meal
import com.example.receipt.repository.MealRepository
import kotlinx.coroutines.launch

class MealViewModel(private val repository: MealRepository):ViewModel() {


    private val _meals = MutableLiveData<List<Meal>>()
    val meals: LiveData<List<Meal>> get() = _meals

    init {
        fetchRandomMeals()
    }

    private fun fetchRandomMeals() {
        viewModelScope.launch {
            try {
                _meals.value = repository.getRandomMeals();
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }






}