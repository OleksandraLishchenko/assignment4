package com.example.assignmnetnew4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RecipeActivity : AppCompatActivity() {

    private val queryFlow = MutableStateFlow("")
    private val _uiState = MutableStateFlow(RecipesUIState(false, emptyList()))
    private val uiState: StateFlow<RecipesUIState> = _uiState.asStateFlow()

    private val mockedRecipes = listOf(
        Recipe(1, "Black Karaage with Curry Bento", "This Japanese modern izakaya dish features crispy black ka...", R.drawable.img_1),
        Recipe(2, "Seafood Udon", "A Japanese-style dish that’s quick and easy to prepare...", R.drawable.img_2),
        Recipe(3, "Takoyaki", "Is a Japanese snack that originated in Osaka, Japan.", R.drawable.img_3),
        Recipe(4, "Tempura", "Is a popular Japanese dish that consists of seafood,", R.drawable.img_4),
        Recipe(5, "Yakitori Shrimp", "Is a Japanese dish that consists of skewered and grilled chicken.", R.drawable.img_5)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val recipesRecyclerView = findViewById<RecyclerView>(R.id.recipeRecyclerView)
        val searchView = findViewById<SearchView>(R.id.searchView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val logoutButton = findViewById<Button>(R.id.logoutButton)

        recipesRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = RecipeAdapter(this, emptyList())
        recipesRecyclerView.adapter = adapter
        lifecycleScope.launch {
            uiState.collect { state ->
                progressBar.isVisible = state.isLoading
                recipesRecyclerView.isVisible = !state.isLoading
                adapter.updateRecipes(state.recipes)
            }
        }

        lifecycleScope.launch {
            queryFlow
                .debounce(300)
                .onEach { _uiState.update { it.copy(isLoading = true) } }
                .onEach { delay(2000) }
                .map { query ->
                    mockedRecipes.filter {
                        it.name.contains(query, ignoreCase = true) ||
                                it.description.contains(query, ignoreCase = true)
                    }
                }
                .collect { filteredRecipes ->
                    _uiState.update { it.copy(isLoading = false, recipes = filteredRecipes) }
                }
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true

            override fun onQueryTextChange(newText: String?): Boolean {
                queryFlow.value = newText.orEmpty()
                return true
            }
        })

        logoutButton.setOnClickListener {
            CredentialsManager.getInstance().logout()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    data class RecipesUIState(
        val isLoading: Boolean,
        val recipes: List<Recipe>
    )
}
