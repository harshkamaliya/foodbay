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

class InnerIngredientInfoAdapter(private val ingredients: List<Ingredient>) :
    RecyclerView.Adapter<InnerIngredientInfoAdapter.InnerIngredientInfoViewHolder>() {


    inner class InnerIngredientInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private val iv_ingredient_image: ImageView = itemView.findViewById(R.id.iv_ingredient_image)
        private val tv_Ingredients_title: TextView =
            itemView.findViewById(R.id.tv_Ingredients_title)
        private val tv_quantity: TextView = itemView.findViewById(R.id.tv_quantity);

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
            tv_quantity.text = ingredient.measure
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InnerIngredientInfoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_iinner_ingredients_list, parent, false)
        return InnerIngredientInfoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ingredients.size

    }

    override fun onBindViewHolder(holder: InnerIngredientInfoViewHolder, position: Int) {
        holder.bind(ingredients[position])

    }

}