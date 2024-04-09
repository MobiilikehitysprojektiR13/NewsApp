package fi.mobiilikehitysprojektir13.newsapp.screens.favorites

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import fi.mobiilikehitysprojektir13.newsapp.data.store.NewsStore
import fi.mobiilikehitysprojektir13.newsapp.screens.favorites.components.FavoritesItem
import fi.mobiilikehitysprojektir13.newsapp.screens.news.viewmodel.NewsViewModel

@Composable
fun FavoritesScreen(navController: NavHostController) {

    val newsViewModel: NewsViewModel = viewModel()
    val savedArticles by newsViewModel.savedArticles.collectAsState()

    val context = LocalContext.current
    val newsStore = NewsStore(context)

    LaunchedEffect("getSavedArticles") {
        newsStore.getSavedArticles.collect {
            newsViewModel.getSavedNews(it)
        }
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    ) {
        items(savedArticles.toList()) { article ->
            FavoritesItem(navController, article)
        }
    }
}