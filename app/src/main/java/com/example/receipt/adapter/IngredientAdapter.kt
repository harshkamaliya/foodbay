package com.example.receipt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.receipt.Models.Ingredient
import com.example.receipt.R

class IngredientAdapter(private val ingredients: List<Ingredient>):
 RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_ingredients_list, parent, false)
        return IngredientViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(ingredients[position])

    }


    inner class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private val iv_ingredient_image: ImageView = itemView.findViewById(R.id.iv_ingredient_image)
        private val tv_Ingredients_title: TextView = itemView.findViewById(R.id.tv_Ingredients_title)

        fun bind(ingredient: Ingredient) {
            val requestOptions = RequestOptions()
                .transform(RoundedCorners(16))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .diskCacheStrategy(DiskCacheStrategy.ALL)

            Glide.with(itemView.context)
                .load(ingredient.imageUrl)
                .apply(requestOptions)
                .into(iv_ingredient_image)

            tv_Ingredients_title.text = ingredient.name
        }
    }




}