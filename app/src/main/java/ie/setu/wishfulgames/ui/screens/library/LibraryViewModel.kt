package ie.setu.wishfulgames.ui.screens.library

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.wishfulgames.data.GameModel
import ie.setu.wishfulgames.data.api.RetrofitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import timber.log.Timber

@HiltViewModel
class LibraryViewModel @Inject
constructor(private val repository: RetrofitRepository) : ViewModel() {
    private val _games
            = MutableStateFlow<List<GameModel>>(emptyList())
    val uiGames: StateFlow<List<GameModel>>
            = _games.asStateFlow()
    var isErr = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf(Exception())

    init { getGames() }

    fun getGames() {
        viewModelScope.launch {
            try {
                isLoading.value = true
                _games.value = repository.getAll()
                isErr.value = false
                isLoading.value = false
            }
            catch(e:Exception) {
                isErr.value = true
                isLoading.value = false
                error.value = e
                Timber.i("RVM Error ${e.message}")
            }
        }
    }

    fun deleteGame(game: GameModel) {
        viewModelScope.launch {
        }
    }
}
