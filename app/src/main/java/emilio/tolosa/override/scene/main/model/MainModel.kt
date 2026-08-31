package emilio.tolosa.override.scene.main.model

import androidx.lifecycle.MutableLiveData
import emilio.tolosa.override.dataClases.User

class MainModel {
    var user = User()
    val isValidForm = MutableLiveData<Boolean>(false)
}
