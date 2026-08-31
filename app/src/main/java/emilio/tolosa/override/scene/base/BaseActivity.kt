package emilio.tolosa.override.scene.base

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }

    override fun onStart(){
        super.onStart()
        Log.i("BASE ACTIVITY", "On Start. . .")
    }

    override fun onResume(){
        super.onResume()
        Log.i("BASE ACTIVITY", "On Resume. . .")
    }

    override fun onPause() {
        super.onPause()
        Log.i("BASE ACTIVITY", "On Pause. . .")
    }

    override fun onStop(){
        super.onStop()
        Log.i("BASE ACTIVITY", "On Stop. . .")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.i("BASE ACTIVITY", "On Destroy. . .")
    }
}
