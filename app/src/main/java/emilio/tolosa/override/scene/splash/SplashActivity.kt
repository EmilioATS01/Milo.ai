package emilio.tolosa.override.scene.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import emilio.tolosa.override.R
import emilio.tolosa.override.scene.main.view.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun countdown(){
        Log.i("SplashActivity", "Inicia cuenta atras")
        Handler(android.os.Looper.getMainLooper()).postDelayed(
            {
                Log.i("SplashActivity", "Navegamos al login")
                //intent es un ojeto de mensajeria
                val intent = Intent(this, MainActivity::class.java)

                //Inicia otra actividad
                startActivity(intent)

                //Finaliza la actividad actual para poder verla
                finish()
            }, 2500
        )

    }
}