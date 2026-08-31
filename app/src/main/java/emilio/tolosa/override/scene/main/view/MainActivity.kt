package emilio.tolosa.override.scene.main.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import emilio.tolosa.override.scene.base.BaseActivity
import emilio.tolosa.override.scene.main.viewModel.MainViewModel

class MainActivity : BaseActivity() {
    private lateinit var binding: emilio.tolosa.override.databinding.ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Iniciar Binding
        initBinding()

        // Setup ViewModel
        viewModel = MainViewModel()

        // 2. Configurar Listeners
        initListeners()

        // 3. Iniciar Componentes
        initComponents()

        // 4. Configurar Observers
        initObservers()
    }

    /**
     * Infla el layout utilizando ViewBinding y configura la vista raíz.
     */
    private fun initBinding() {
        this.binding = emilio.tolosa.override.databinding.ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /**
     * Configura los listeners de los componentes de la UI (clics, cambios de texto, etc.)
     */
    private fun initListeners() {
        binding.btnLogin.setOnClickListener {
            Log.i("MainActivity", "Login successful with user: ${viewModel.user.userName}")
        }

        binding.btnRegisterAccount.setOnClickListener {
            val intent = Intent(this, emilio.tolosa.override.scene.register.view.RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.etUser.addTextChangedListener {
            viewModel.user.userName = it.toString()
            viewModel.validateForm()
        }

        binding.etPassword.addTextChangedListener {
            viewModel.user.password = it.toString()
            viewModel.validateForm()
        }
    }

    /**
     * Inicializa el estado inicial de los componentes de la interfaz.
     */
    private fun initComponents() {
        binding.btnLogin.isEnabled = false
    }

    /**
     * Configura los observadores de LiveData para reaccionar a los cambios en el ViewModel.
     */
    private fun initObservers() {
        viewModel.isValidForm.observe(this) { isValid ->
            binding.btnLogin.isEnabled = isValid

            // Validación visual del campo Usuario
            this.binding.etUser.error = if (isValid || binding.etUser.text.isEmpty()) null else "Ingresa el usuario"
        }

        viewModel.isValidForm.observe(this) { isValid ->
            // Validación visual del campo Contraseña
            this.binding.etPassword.error = if (isValid || binding.etPassword.text.isEmpty()) null else "Ingresa la contrasena"
        }
    }
}
