package ie.setu.wishfulgames.ui.screens.library

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.wishfulgames.data.model.GameModel
import ie.setu.wishfulgames.firebase.services.AuthService
import ie.setu.wishfulgames.firebase.services.FirestoreService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject
constructor(private val repository: FirestoreService,
            private val authService: AuthService
) : ViewModel() {
    private val _games = MutableStateFlow<List<GameModel>>(emptyList())
    private val _uiGames = MutableStateFlow<List<GameModel>>(emptyList())
    val uiGames: StateFlow<List<GameModel>> = _uiGames.asStateFlow()
    var isErr = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf(Exception())

    init { getGames() }

    fun getGames() {
        viewModelScope.launch {
            try {
                isLoading.value = true
                repository.getAll(authService.email!!).collect{ items ->
                    _games.value = items
                    _uiGames.value = items
                    isErr.value = false
                    isLoading.value = false
                }
                Timber.i("DVM RVM = : ${_uiGames.value}")
            } catch (e: Exception) {
                isErr.value = true
                isLoading.value = false
                error.value = e
                Timber.i("RVM Error ${e.message}")
            }
        }
    }

    fun deleteGame(game: GameModel)
            = viewModelScope.launch {
        repository.delete(authService.email!!,game._id)
    }

    fun searchGames(query: String) {
        val filteredList = _games.value.filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.description.contains(query, ignoreCase = true) ||
                    it.genre.contains(query, ignoreCase = true)
        }
        _uiGames.value = filteredList
    }

    fun filterByPrice(price: Int?) {
        val filteredList = if (price == null) {
            _games.value
        } else {
            _games.value.filter { it.price == price }
        }
        _uiGames.value = filteredList
    }
}
