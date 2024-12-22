package com.example.assignmnetnew4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val recipes = listOf(
            Recipe(1, "Black Karaage with Curry Bento", "This Japanese modern izakaya dish features crispy black ka...", R.drawable.img_1),
            Recipe(2, "Seafood Udon", "A Japanese-style dish that’s quick and easy to prepare...", R.drawable.img_2),
            Recipe(3, "Takoyaki", "Is a Japanese snack that originated in Osaka, Japan. It is a ball-shaped cake..", R.drawable.img_3),
            Recipe(4, "Tempura", "Is a popular Japanese dish that consists of seafood, vegetable..", R.drawable.img_4),
            Recipe(5, "Yakitori Shrimp", "Is a Japanese dish that consists of skewered and grilled chicken. However, it..", R.drawable.img_5),
            Recipe(6, "Black Karaage with Curry Bento", "This Japanese modern izakaya dish features crispy black ka...", R.drawable.img_1),
            Recipe(7, "Seafood Udon", "A Japanese-style dish that’s quick and easy to prepare...", R.drawable.img_2),
            Recipe(8, "Takoyaki", "Is a Japanese snack that originated in Osaka, Japan. It is a ball-shaped cake..", R.drawable.img_3),
            Recipe(9, "Tempura", "Is a popular Japanese dish that consists of seafood, vegetable..", R.drawable.img_4),
            Recipe(10, "Yakitori Shrimp", "Is a Japanese dish that consists of skewered and grilled chicken. However, it..", R.drawable.img_5),
            Recipe(11, "Black Karaage with Curry Bento", "This Japanese modern izakaya dish features crispy black ka...", R.drawable.img_1),
            Recipe(12, "Seafood Udon", "A Japanese-style dish that’s quick and easy to prepare...", R.drawable.img_2),
            Recipe(13, "Takoyaki", "Is a Japanese snack that originated in Osaka, Japan. It is a ball-shaped cake..", R.drawable.img_3),
            Recipe(14, "Tempura", "Is a popular Japanese dish that consists of seafood, vegetable..", R.drawable.img_4),
            Recipe(15, "Yakitori Shrimp", "Is a Japanese dish that consists of skewered and grilled chicken. However, it..", R.drawable.img_5)
        )

        val recipesRecyclerView = findViewById<RecyclerView>(R.id.recipeRecyclerView)
        recipesRecyclerView.layoutManager = LinearLayoutManager(this)
        recipesRecyclerView.adapter = RecipeAdapter(this, recipes)
    }
}
