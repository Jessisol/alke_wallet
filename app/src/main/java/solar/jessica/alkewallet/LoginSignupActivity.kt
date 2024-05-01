package solar.jessica.alkewallet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginSignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_signup)
        //rescatando los valores de la pantalla anterior
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val tyc = intent.getBooleanExtra("acepto_TyC", false)

        //vamos a mostrar el dato en toast
        Toast.makeText(this, "Hola $nombre $apellido -- Acepto los $tyc", Toast.LENGTH_LONG).show()

         //vamos a declarar los botones para la integracion
        val botonYatienesCuenta = findViewById<TextView>(R.id.txt_login)
        botonYatienesCuenta.setOnClickListener {
            val irLogin = Intent(this, LoginActivity::class.java)
            startActivity(irLogin)
        }

        //vamos a declarar los botones para la integracion
        val botonRegistrarse = findViewById<Button>(R.id.button_sign_up)
        botonRegistrarse.setOnClickListener {
            val irSignUp = Intent(this, SignupPage::class.java)
            startActivity(irSignUp)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.logea)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}