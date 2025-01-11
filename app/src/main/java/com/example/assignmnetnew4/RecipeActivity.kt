package com.example.assignmnetnew4

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope

class RecipeActivity : AppCompatActivity() {

    private val queryFlow = MutableStateFlow("")
    private val _recipesFlow = MutableStateFlow<List<Recipe>>(emptyList())
    private val recipesFlow: StateFlow<List<Recipe>> = _recipesFlow.asStateFlow()

    private val mockedRecipes = listOf(
        Recipe(1, "Black Karaage with Curry Bento", "This Japanese modern izakaya dish features crispy black ka...", R.drawable.img_1),
        Recipe(2, "Seafood Udon", "A Japanese-style dish that’s quick and easy to prepare...", R.drawable.img_2),
        Recipe(3, "Takoyaki", "Is a Japanese snack that originated in Osaka, Japan. ", R.drawable.img_3),
        Recipe(4, "Tempura", "Is a popular Japanese dish that consists of seafood, ", R.drawable.img_4),
        Recipe(5, "Yakitori Shrimp", "Is a Japanese dish that consists of skewered and grilled chicken.", R.drawable.img_5)

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val recipesRecyclerView = findViewById<RecyclerView>(R.id.recipeRecyclerView)
        val searchView = findViewById<SearchView>(R.id.searchView)

        recipesRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = RecipeAdapter(this, emptyList())
        recipesRecyclerView.adapter = adapter
        requestData()

        lifecycleScope.launch {
            recipesFlow.collect { recipes ->
                adapter.updateRecipes(recipes)
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("QUERY", newText.orEmpty())
                setQuery(newText.orEmpty())
                return true
            }
        })
    }

    private fun requestData() {
        _recipesFlow.value = mockedRecipes
    }

    private fun setQuery(query: String) {
        queryFlow.value = query
        filterRecipes(query)
    }

    private fun filterRecipes(query: String) {
        val filteredRecipes = if (query.length < 3) {
            mockedRecipes
        } else {
            mockedRecipes.filter {
                it.name.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
            }
        }
        _recipesFlow.update { filteredRecipes }
    }
}

