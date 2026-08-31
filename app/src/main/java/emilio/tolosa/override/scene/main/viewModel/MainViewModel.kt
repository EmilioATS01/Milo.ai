package emilio.tolosa.override.scene.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import emilio.tolosa.override.dataClases.User
import emilio.tolosa.override.scene.main.model.MainModel

class MainViewModel : ViewModel() {
    //Variables privadas
    private val model = MainModel()

    /**
     * Variable que permite obtener el usuario y cambiar su valor
     */
    var user: User
        // get() Obtiene el usuario del model
        get() = this.model.user
        // set() Cambia el valor del usuario del model
        set(value) {
            this.model.user = value
        }

    /**
     * Variable que permite obtener el estado del boton
     */
    val isValidForm: LiveData<Boolean>
        get() = this.model.isValidForm

    /**
     * Funcion para validar un login
     */
    fun validateForm() {
        this.model.isValidForm.value = this.user.userName.isNotEmpty() && this.user.password.isNotEmpty()
    }
}
