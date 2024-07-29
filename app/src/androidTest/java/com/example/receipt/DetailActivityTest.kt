package com.example.receipt

import android.content.Intent
import android.provider.Contacts
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.receipt.adapter.InnerIngredientInfoAdapter
import junit.framework.TestCase.assertEquals
import org.hamcrest.Matchers.allOf

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.receipt.Models.Ingredient
import com.example.receipt.Models.Meal
import junit.framework.TestCase.assertEquals
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DetailActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<DetailActivity> = ActivityTestRule(DetailActivity::class.java, false, false)


    @Test
    fun testDetailActivityDisplaysMealInfo() {
        val meal = Meal(
            idMeal = "52922",
            strMeal = "Prawn & Fennel Bisque",
            strDrinkAlternate = null,
            strCategory = "Side",
            strArea = "French",
            strInstructions = "Shell the prawns, then fry the shells...",
            strMealThumb = "https://www.themealdb.com/images/media/meals/rtwwvv1511799504.jpg",
            strTags = "Soup,Warm,Seafood,Shellfish",
            strYoutube = "https://www.youtube.com/watch?v=4thpMbDakgM",
            strIngredient1 = "Tiger Prawns",
            strIngredient2 = "Olive Oil",
            strIngredient3 = "Onion",
            strIngredient4 = "Fennel",
            strIngredient5 = "Carrots",
            strIngredient6 = "Dry White Wine",
            strIngredient7 = "Brandy",
            strIngredient8 = "Chopped Tomatoes",
            strIngredient9 = "Fish Stock",
            strIngredient10 = "Paprika",
            strIngredient11 = "Double Cream",
            strIngredient12 = "Prawns",
            strIngredient13 = "",
            strIngredient14 = "",
            strIngredient15 = "",
            strIngredient16 = "",
            strIngredient17 = "",
            strIngredient18 = "",
            strIngredient19 = "",
            strIngredient20 = "",
            strMeasure1 = "450g",
            strMeasure2 = "4 tbs",
            strMeasure3 = "1 large",
            strMeasure4 = "1 large",
            strMeasure5 = "2 chopped",
            strMeasure6 = "150ml",
            strMeasure7 = "1 tbs",
            strMeasure8 = "400g",
            strMeasure9 = "1L",
            strMeasure10 = "2 pinches",
            strMeasure11 = "150ml",
            strMeasure12 = "8",
            strMeasure13 = "",
            strMeasure14 = "",
            strMeasure15 = "",
            strMeasure16 = "",
            strMeasure17 = "",
            strMeasure18 = "",
            strMeasure19 = "",
            strMeasure20 = "",
            strSource = "https://www.bbcgoodfood.com/recipes/71600/prawn-and-fennel-bisque",
            strImageSource = null,
            strCreativeCommonsConfirmed = null,
            dateModified = null
        )

        val ingredients = arrayListOf(
            Ingredient(name = "Tiger Prawns", measure = "450g", imageUrl = "https://www.themealdb.com/images/ingredients/Tiger Prawns.png"),
            Ingredient(name = "Olive Oil", measure = "4 tbs", imageUrl = "https://www.themealdb.com/images/ingredients/Olive Oil.png")
        )

        val intent = Intent(ApplicationProvider.getApplicationContext(), DetailActivity::class.java).apply {
            putExtra("meal_data", meal)
            putParcelableArrayListExtra("ingredients", ingredients)
        }

        ActivityScenario.launch<DetailActivity>(intent).use {
            onView(withId(R.id.tv_header)).check(matches(withText("Prawn & Fennel Bisque")))
            onView(withId(R.id.tv_description)).check(matches(withText("Shell the prawns, then fry the shells...")))
            onView(withId(R.id.tv_no_of_item)).check(matches(withText("2")))
            onView(withId(R.id.rv_Ingredients)).check { view, _ ->
                val recyclerView = view as RecyclerView
                assertEquals(2, recyclerView.adapter?.itemCount)
            }
        }
    }

    @Test
    fun testYouTubeIntent() {
        val meal = Meal(
            idMeal = "52922",
            strMeal = "Prawn & Fennel Bisque",
            strDrinkAlternate = null,
            strCategory = "Side",
            strArea = "French",
            strInstructions = "Shell the prawns, then fry the shells...",
            strMealThumb = "https://www.themealdb.com/images/media/meals/rtwwvv1511799504.jpg",
            strTags = "Soup,Warm,Seafood,Shellfish",
            strYoutube = "https://www.youtube.com/watch?v=4thpMbDakgM",
            strIngredient1 = "Tiger Prawns",
            strIngredient2 = "Olive Oil",
            strIngredient3 = "Onion",
            strIngredient4 = "Fennel",
            strIngredient5 = "Carrots",
            strIngredient6 = "Dry White Wine",
            strIngredient7 = "Brandy",
            strIngredient8 = "Chopped Tomatoes",
            strIngredient9 = "Fish Stock",
            strIngredient10 = "Paprika",
            strIngredient11 = "Double Cream",
            strIngredient12 = "Prawns",
            strIngredient13 = "",
            strIngredient14 = "",
            strIngredient15 = "",
            strIngredient16 = "",
            strIngredient17 = "",
            strIngredient18 = "",
            strIngredient19 = "",
            strIngredient20 = "",
            strMeasure1 = "450g",
            strMeasure2 = "4 tbs",
            strMeasure3 = "1 large",
            strMeasure4 = "1 large",
            strMeasure5 = "2 chopped",
            strMeasure6 = "150ml",
            strMeasure7 = "1 tbs",
            strMeasure8 = "400g",
            strMeasure9 = "1L",
            strMeasure10 = "2 pinches",
            strMeasure11 = "150ml",
            strMeasure12 = "8",
            strMeasure13 = "",
            strMeasure14 = "",
            strMeasure15 = "",
            strMeasure16 = "",
            strMeasure17 = "",
            strMeasure18 = "",
            strMeasure19 = "",
            strMeasure20 = "",
            strSource = "https://www.bbcgoodfood.com/recipes/71600/prawn-and-fennel-bisque",
            strImageSource = null,
            strCreativeCommonsConfirmed = null,
            dateModified = null
        )

        val ingredients = arrayListOf(
            Ingredient(name = "Tiger Prawns", measure = "450g", imageUrl = "https://www.themealdb.com/images/ingredients/Tiger Prawns.png"),
            Ingredient(name = "Olive Oil", measure = "4 tbs", imageUrl = "https://www.themealdb.com/images/ingredients/Olive Oil.png")
        )

        val intent = Intent(ApplicationProvider.getApplicationContext(), DetailActivity::class.java).apply {
            putExtra("meal_data", meal)
            putParcelableArrayListExtra("ingredients", ingredients)
        }

        Intents.init()

        activityRule.launchActivity(intent)

        onView(withId(R.id.ll_youTube)).perform(click())

        intended(allOf(
            hasAction(Intent.ACTION_VIEW),
            hasData(Uri.parse("https://www.youtube.com/watch?v=4thpMbDakgM"))
        ))
        Intents.release()



    }
}
