package emilio.tolosa.override.scene.register.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import emilio.tolosa.override.dataClases.RegisterUser
import emilio.tolosa.override.scene.register.model.RegisterModel

class RegisterViewModel : ViewModel() {
    private val model = RegisterModel()

    var user: RegisterUser
        get() = this.model.user
        set(value) { this.model.user = value }

    val isValidForm: LiveData<Boolean> get() = this.model.isValidForm

    // Exponer errores a la vista
    val usernameError: LiveData<String?> get() = model.usernameError
    val firstNameError: LiveData<String?> get() = model.firstNameError
    val lastNameError: LiveData<String?> get() = model.lastNameError
    val passwordError: LiveData<String?> get() = model.passwordError
    val confirmPasswordError: LiveData<String?> get() = model.confirmPasswordError

    fun validateUsername(input: String) {
        val text = input.trim()
        user.userName = text
        when {
            text.isEmpty() -> model.usernameError.value = "El usuario es obligatorio"
            text.length !in 4..20 -> model.usernameError.value = "Debe tener entre 4 y 20 caracteres"
            text.firstOrNull()?.isDigit() == true -> model.usernameError.value = "No puede iniciar con número"
            !text.matches(Regex("^[a-zA-Z0-9]+\$")) -> model.usernameError.value = "No se permiten espacios ni caracteres especiales"
            else -> model.usernameError.value = null
        }
        checkFormValidity()
    }

    fun validateFirstName(input: String) {
        val text = input.trim()
        user.firstName = text
        when {
            text.isEmpty() -> model.firstNameError.value = "El nombre es obligatorio"
            text.length < 2 -> model.firstNameError.value = "Nombre demasiado corto"
            text.length > 30 -> model.firstNameError.value = "Máximo 30 caracteres"
            !text.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+\$")) -> model.firstNameError.value = "Solo se permiten letras"
            else -> model.firstNameError.value = null
        }
        checkFormValidity()
    }

    fun validateLastName(input: String) {
        val text = input.trim()
        user.lastName = text
        when {
            text.isEmpty() -> model.lastNameError.value = "El apellido es obligatorio"
            text.length < 2 -> model.lastNameError.value = "Apellido demasiado corto"
            text.length > 30 -> model.lastNameError.value = "Máximo 30 caracteres"
            !text.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+\$")) -> model.lastNameError.value = "Solo se permiten letras"
            else -> model.lastNameError.value = null
        }
        checkFormValidity()
    }

    fun validatePassword(input: String) {
        val text = input.trim()
        user.password = text
        val hasUpper = text.any { it.isUpperCase() }
        val hasLower = text.any { it.isLowerCase() }
        val hasDigit = text.any { it.isDigit() }
        val hasSpace = text.contains(" ")

        when {
            text.isEmpty() -> model.passwordError.value = "La contraseña es obligatoria"
            text.length < 8 -> model.passwordError.value = "Debe tener al menos 8 caracteres"
            hasSpace -> model.passwordError.value = "No se permiten espacios"
            !(hasUpper && hasLower && hasDigit) -> model.passwordError.value = "Debe incluir mayúsculas, minúsculas y números"
            else -> model.passwordError.value = null
        }
        // Validar confirmación si la contraseña principal cambia
        validateConfirmPassword(user.confirmPassword)
        checkFormValidity()
    }

    fun validateConfirmPassword(input: String) {
        val text = input.trim()
        user.confirmPassword = text
        when {
            text.isEmpty() -> model.confirmPasswordError.value = "Debes confirmar la contraseña"
            text != user.password -> model.confirmPasswordError.value = "Las contraseñas no coinciden"
            else -> model.confirmPasswordError.value = null
        }
        checkFormValidity()
    }

    private fun checkFormValidity() {
        val isUserValid = user.userName.isNotEmpty() && model.usernameError.value == null
        val isFirstValid = user.firstName.isNotEmpty() && model.firstNameError.value == null
        val isLastValid = user.lastName.isNotEmpty() && model.lastNameError.value == null
        val isPassValid = user.password.isNotEmpty() && model.passwordError.value == null
        val isConfValid = user.confirmPassword.isNotEmpty() && model.confirmPasswordError.value == null

        model.isValidForm.value = isUserValid && isFirstValid && isLastValid && isPassValid && isConfValid
    }
}