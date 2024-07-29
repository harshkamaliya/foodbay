import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import com.example.receipt.Models.Meal
import com.example.receipt.R
import com.example.receipt.adapter.MealAdapter
import org.junit.Assert.assertEquals
import org.junit.Test

class MealAdapterTest {

    @Test
    fun testMealAdapterBindView() {
        val mealAdapter = MealAdapter()
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



        val parent = LayoutInflater.from(ApplicationProvider.getApplicationContext())
            .inflate(R.layout.item_layout_of_receipt, null) as ViewGroup
        val viewHolder = mealAdapter.onCreateViewHolder(parent, 0)
        mealAdapter.onBindViewHolder(viewHolder, 0)

        mealAdapter.setMeals(listOf(meal))


        assertEquals(
            "Prawn & Fennel Bisque",
            viewHolder.itemView.findViewById<TextView>(R.id.tv_ReceiptTitle).text
        )
        assertEquals(
            "Shell the prawns, then fry the shells...",
            viewHolder.itemView.findViewById<TextView>(R.id.tv_ReceiptDescription).text
        )
    }
}
