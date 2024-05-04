package solar.jessica.alkewallet

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<ImageView>(R.id.imageView_usuario).setOnClickListener {
            startActivity(Intent(this, ProfilePage::class.java))
        }
        findViewById<ImageView>(R.id.imageView_ingresar_dinero).setOnClickListener {
            startActivity(Intent(this, RequestMoney::class.java))
        }
        findViewById<ImageView>(R.id.imageView_enviar_dinero).setOnClickListener {
            startActivity(Intent(this, SendMoney::class.java))
        }
    }
}