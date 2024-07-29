package com.example.receipt

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.receipt.adapter.MealAdapter
import com.example.receipt.repository.MealRepository
import com.example.receipt.viewModel.MealViewModel
import com.example.receipt.viewModel.MealViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MealViewModel
    private lateinit var adapter: MealAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeStatusBarColor(R.color.white, "light1")

        val repository = MealRepository()
        val viewModelFactory = MealViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MealViewModel::class.java)


        adapter = MealAdapter()
        this.findViewById<RecyclerView>(R.id.parent_recycler_view).adapter = adapter
        findViewById<RecyclerView>(R.id.parent_recycler_view).layoutManager = LinearLayoutManager(this)



        viewModel.meals.observe(this, Observer { meals ->
            meals?.let {
                adapter.setMeals(it)
            }
        })

    }


    fun Activity.changeStatusBarColor(color: Int, iconColor: String) {
        window.statusBarColor = ContextCompat.getColor(this, color)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = if (iconColor == "light") {
                window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            } else {
                window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }
}