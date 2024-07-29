package com.example.receipt

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.receipt.Models.Ingredient
import com.example.receipt.Models.Meal
import com.example.receipt.adapter.InnerIngredientInfoAdapter
import com.example.receipt.adapter.MealAdapter

class DetailActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )

        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE



        val iv_image: ImageView = findViewById(R.id.iv_image)
        val cv_close: CardView = findViewById(R.id.cv_close);
        val tv_time: TextView = findViewById(R.id.tv_time);
        val tv_description: TextView = findViewById(R.id.tv_description);
        val tv_header:TextView = findViewById(R.id.tv_header);
        val tv_no_of_item : TextView = findViewById(R.id.tv_no_of_item);
        val ll_youTube : LinearLayout = findViewById(R.id.ll_youTube);
        val rv_Ingredients :RecyclerView = findViewById(R.id.rv_Ingredients);


        val meal = intent.getParcelableExtra<Meal>("meal_data")
        val ingredients: ArrayList<Ingredient>? = intent.getParcelableArrayListExtra("ingredients")







        meal?.let {
            Glide.with(this)
                .load(it.strMealThumb)
                .apply(RequestOptions().transform(RoundedCorners(16)))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(iv_image)

        }

        if (ingredients != null) {
            if (ingredients.size>0)
            {
                val totalItem = ingredients.size;
                tv_no_of_item.text = totalItem.toString()
            }

        }
        if (ingredients != null) {
            rv_Ingredients.layoutManager = LinearLayoutManager(this)
            rv_Ingredients.adapter =  InnerIngredientInfoAdapter(ingredients)
        }
        val des = meal?.strInstructions
        if (!des.isNullOrEmpty())
        {
            tv_description.text = des
        }
        val title = meal?.strMeal

        if (!title.isNullOrEmpty())
        {
            tv_header.text = title
        }

        ll_youTube.setOnClickListener {
            val videoUrl = meal?.strYoutube
            if (!videoUrl.isNullOrEmpty()) {
                val intentYouTube = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
                intentYouTube.putExtra("force_fullscreen", true)
                intentYouTube.putExtra("finish_on_ended", true)
                if (intentYouTube.resolveActivity(packageManager) == null) {
                    intentYouTube.data = Uri.parse(videoUrl)
                }
                startActivity(intentYouTube)
            }else{
                Toast.makeText(this,"Video Not Found",Toast.LENGTH_SHORT).show()
            }

        }



        cv_close.setOnClickListener {
            finish()
        }
    }


    private fun makeStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
            window.statusBarColor = android.graphics.Color.TRANSPARENT
        }
    }
}