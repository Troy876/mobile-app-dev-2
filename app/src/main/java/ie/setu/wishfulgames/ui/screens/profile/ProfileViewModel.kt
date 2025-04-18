package ie.setu.wishfulgames.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.wishfulgames.firebase.services.AuthService
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authService: AuthService,
    private val auth: FirebaseAuth,
) : ViewModel() {

    val displayName get() = auth.currentUser?.displayName.toString()
    val photoUrl get() = auth.currentUser?.photoUrl.toString()
    val email get() = auth.currentUser?.email.toString()

    fun signOut() {
        viewModelScope.launch { authService.signOut() }
    }
}
