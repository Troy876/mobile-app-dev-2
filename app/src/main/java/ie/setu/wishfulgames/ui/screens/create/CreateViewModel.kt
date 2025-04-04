package ie.setu.wishfulgames.ui.screens.create

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.wishfulgames.data.GameModel
import ie.setu.wishfulgames.data.api.RetrofitRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject
constructor(private val repository: RetrofitRepository) : ViewModel() {

    var isErr = mutableStateOf(false)
    var error = mutableStateOf(Exception())
    var isLoading = mutableStateOf(false)

    fun insert(game: GameModel) =
        viewModelScope.launch {
            try {
                isLoading.value = true
                repository.insert(game)
                isErr.value = false
                isLoading.value = false
            } catch (e: Exception) {
                isErr.value = true
                error.value = e
                isLoading.value = false
            }
        }
}
