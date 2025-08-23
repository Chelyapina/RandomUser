package com.example.randomuser.presentation.oneUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuser.domain.usecases.GetUserByIdUseCase
import com.example.randomuser.presentation.users.DialogState
import com.example.randomuser.presentation.utils.IntentErrorMapper
import com.example.randomuser.presentation.utils.IntentLauncher
import com.example.randomuser.presentation.utils.IntentNotAvailableException
import com.example.randomuser.presentation.utils.IntentType
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OneUserScreenViewModel @AssistedInject constructor(
    private val getUserByIdUseCase : GetUserByIdUseCase ,
    private val intentLauncher : IntentLauncher ,
    private val intentErrorMapper : IntentErrorMapper ,
    @Assisted private val userId : String
) : ViewModel() {

    private val _userState = MutableStateFlow(OneUserState(isLoading = true))
    val userState : StateFlow<OneUserState> = _userState

    private val _dialogState = MutableStateFlow<DialogState?>(null)
    val dialogState : StateFlow<DialogState?> = _dialogState

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            val result = getUserByIdUseCase(userId)
            _userState.value = OneUserState(
                isLoading = false ,
                user = result?.toOneUserUi()
            )
        }
    }

    fun onEmailClick(email : String) {
        viewModelScope.launch {
            try {
                intentLauncher.launchEmail(email)
            } catch (e : IntentNotAvailableException) {
                showAppNotFoundDialog(e.intentType)
            }
        }
    }

    fun onPhoneClick(phone : String) {
        viewModelScope.launch {
            try {
                intentLauncher.launchPhone(phone)
            } catch (e : IntentNotAvailableException) {
                showAppNotFoundDialog(e.intentType)
            }
        }
    }

    fun onCoordinatesClick(latitude : String , longitude : String) {
        viewModelScope.launch {
            try {
                intentLauncher.launchMaps(latitude , longitude)
            } catch (e : IntentNotAvailableException) {
                showAppNotFoundDialog(e.intentType)
            }
        }
    }

    private fun showAppNotFoundDialog(intentType : IntentType) {
        val (titleRes , messageRes) = intentErrorMapper.mapToErrorResources(intentType)
        _dialogState.value = DialogState(titleRes , messageRes)
    }

    fun onDialogDismiss() {
        _dialogState.value = null
    }

    @AssistedFactory
    interface Factory {
        fun create(userId : String) : OneUserScreenViewModel
    }
}