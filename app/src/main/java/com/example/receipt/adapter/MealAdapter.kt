package com.example.receipt.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.receipt.DetailActivity
import com.example.receipt.Models.Ingredient
import com.example.receipt.Models.Meal
import com.example.receipt.R

class MealAdapter() : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    private var meals: List<Meal> = listOf()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_of_receipt, parent, false)
        return MealViewHolder(view)
    }

    override fun getItemCount(): Int {
       return meals.size
    }


    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]
        val ingredients = extractIngredients(meal)
        holder.bind(meals[position],ingredients)
        holder.itemView.setOnClickListener{
            val context = holder.itemView.context
            val intent = Intent(context,DetailActivity::class.java).apply {

                putExtra("meal_data",meal)
                putParcelableArrayListExtra("ingredients", ArrayList(ingredients))

            }
            context.startActivity(intent)

        }

    }

    fun setMeals(newMeals: List<Meal>) {
        meals = newMeals
        notifyDataSetChanged()
    }



    class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iv_receipt: ImageView = itemView.findViewById(R.id.iv_receipt)
        private val tv_ReceiptTitle: TextView = itemView.findViewById(R.id.tv_ReceiptTitle)
        private val tv_ReceiptDescription: TextView = itemView.findViewById(R.id.tv_ReceiptDescription)
        private val inner_recycler_view : RecyclerView = itemView.findViewById(R.id.inner_recycler_view);

        fun bind(meal: Meal, ingredients: List<Ingredient>) {
            tv_ReceiptTitle.text = meal.strMeal
            tv_ReceiptDescription.text = meal.strInstructions



            val requestOptions = RequestOptions()
                .transform(RoundedCorners(50)) // Radius in pixels
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)

            Glide.with(itemView.context)
                .load(meal.strMealThumb)
                .apply(requestOptions)
                .into(iv_receipt)


            val ingredientAdapter = IngredientAdapter(ingredients)
            inner_recycler_view.apply {
                layoutManager = LinearLayoutManager(itemView.context,LinearLayoutManager.HORIZONTAL,false)
                adapter = ingredientAdapter
            }


        }






    }
    fun extractIngredients(meal: Meal): List<Ingredient> {
        val ingredients = mutableListOf<Ingredient>()

        for (i in 1..20) {
            // Get the ingredient and measurement fields dynamically
            val ingredientField = meal.javaClass.getDeclaredField("strIngredient$i").apply { isAccessible = true }.get(meal) as? String
            val measureField = meal.javaClass.getDeclaredField("strMeasure$i").apply { isAccessible = true }.get(meal) as? String

            // Check if the ingredient field is not null or empty
            if (!ingredientField.isNullOrEmpty()) {
                val imageUrl = "https://www.themealdb.com/images/ingredients/${ingredientField}.png"
                ingredients.add(Ingredient(name = ingredientField, measure = measureField ?: "", imageUrl = imageUrl))
            }
        }

        return ingredients
    }






}