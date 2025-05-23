package ie.setu.wishfulgames.ui.screens.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.wishfulgames.firebase.services.AuthService
import ie.setu.wishfulgames.firebase.services.FirestoreService
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authService: AuthService,
    private val auth: FirebaseAuth,
    // private val firestoreService: FirestoreService
) : ViewModel() {

    val displayName get() = auth.currentUser?.displayName.toString()
    val photoUri get() = authService.customPhotoUri
    val email get() = auth.currentUser?.email.toString()

    fun signOut() {
        viewModelScope.launch { authService.signOut() }
    }

    fun updatePhotoUri(uri: Uri) {
        viewModelScope.launch { authService.updatePhoto(uri) }
    }

//    fun updatePhotoUri(uri: Uri) {
//        viewModelScope.launch {
//            authService.updatePhoto(uri)
//            firestoreService.updatePhotoUris(email,photoUri!!)
//        }
//    }
}
