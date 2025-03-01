package ie.setu.wishfulgames.ui.screens.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.wishfulgames.data.GameModel
import ie.setu.wishfulgames.data.repository.RoomRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject
constructor(private val repository: RoomRepository) : ViewModel() {
    private val _games
            = MutableStateFlow<List<GameModel>>(emptyList())
    val uiGames: StateFlow<List<GameModel>>
            = _games.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAll().collect { listOfGames ->
                _games.value = listOfGames
            }
        }
    }
}
