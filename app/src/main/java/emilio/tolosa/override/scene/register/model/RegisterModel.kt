package emilio.tolosa.override.scene.register.model

import androidx.lifecycle.MutableLiveData
import emilio.tolosa.override.dataClases.RegisterUser

class RegisterModel {
    var user = RegisterUser()
    val isValidForm = MutableLiveData<Boolean>(false)

    // Estados de error para cada campo
    val usernameError = MutableLiveData<String?>()
    val firstNameError = MutableLiveData<String?>()
    val lastNameError = MutableLiveData<String?>()
    val passwordError = MutableLiveData<String?>()
    val confirmPasswordError = MutableLiveData<String?>()
}