package emilio.tolosa.override.dataClases

data class RegisterUser(
    var userName: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var password: String = "",
    var confirmPassword: String = ""
)