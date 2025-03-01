package ie.setu.wishfulgames.ui.screens.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.wishfulgames.data.GameModel
import ie.setu.wishfulgames.data.repository.RoomRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject
constructor(private val repository: RoomRepository) : ViewModel() {

    fun insert(games: GameModel)
            = viewModelScope.launch {
        repository.insert(games)
    }
}
