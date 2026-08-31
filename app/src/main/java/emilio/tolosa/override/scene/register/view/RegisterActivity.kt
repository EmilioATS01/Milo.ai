package emilio.tolosa.override.scene.register.view

import android.os.Bundle
import android.util.Log
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import emilio.tolosa.override.databinding.ActivityRegisterBinding
import emilio.tolosa.override.scene.base.BaseActivity
import emilio.tolosa.override.scene.register.viewModel.RegisterViewModel

class RegisterActivity : BaseActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        viewModel = RegisterViewModel()
        initListeners()
        initComponents()
        initObservers()
    }

    private fun initBinding() {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initListeners() {
        binding.etUsername.addTextChangedListener { viewModel.validateUsername(it.toString()) }
        binding.etFirstName.addTextChangedListener { viewModel.validateFirstName(it.toString()) }
        binding.etLastName.addTextChangedListener { viewModel.validateLastName(it.toString()) }
        binding.etPassword.addTextChangedListener { viewModel.validatePassword(it.toString()) }
        binding.etConfirmPassword.addTextChangedListener { viewModel.validateConfirmPassword(it.toString()) }

        binding.btnRegister.setOnClickListener {
            binding.btnRegister.isEnabled = false
            Log.i("RegisterActivity", "Registrando usuario: ${viewModel.user.userName}")
        }
    }

    private fun initComponents() {
        binding.btnRegister.isEnabled = false
    }

    private fun initObservers() {
        viewModel.isValidForm.observe(this) { isValid ->
            binding.btnRegister.isEnabled = isValid
        }

        viewModel.usernameError.observe(this) { error -> binding.etUsername.error = error }
        viewModel.firstNameError.observe(this) { error -> binding.etFirstName.error = error }
        viewModel.lastNameError.observe(this) { error -> binding.etLastName.error = error }
        viewModel.passwordError.observe(this) { error -> binding.etPassword.error = error }
        viewModel.confirmPasswordError.observe(this) { error -> binding.etConfirmPassword.error = error }
    }
}
