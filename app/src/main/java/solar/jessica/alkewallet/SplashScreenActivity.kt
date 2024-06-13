package solar.jessica.alkewallet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.SharedPreferencesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //esta es la linea que dice que xml va a mostrar
        setContentView(R.layout.activity_splash_screen)

        //se declara la imagen como una variable
        //abriendo la pantalla con un click
        /*val logoApp = findViewById<ImageView>(R.id.logoApp)
        logoApp.setOnClickListener {
            val abrirPantallaLogin = Intent (this, LoginSignupActivity::class.java)
            abrirPantallaLogin.putExtra("nombre", "Jessica")
            abrirPantallaLogin.putExtra("apellido", "Solar")
            abrirPantallaLogin.putExtra("acepto_TyC", false)
            startActivity(abrirPantallaLogin)
        }*/





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.logea)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            delay(2000)
            //Obtenemos token de sesión si es que existe uno
            val preferencias = getSharedPreferences("app", Context.MODE_PRIVATE)
            if (preferencias.getString("accessToken", "") == "") {
                //Sin token, solicitamos que inicie sesión para continuar
                val abrirPantallaLogin = Intent(baseContext, LoginSignupActivity::class.java)
                startActivity(abrirPantallaLogin)
            } else {
                //Con token, directo a la pantalla principal
                val abrirHome = Intent(baseContext, HomePage::class.java)
                startActivity(abrirHome)
            }
            finish()
        }
    }
}