package tech.carcher.felipe_rodriguez_20240422

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PropertyDetailActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context, property: Property): Intent {
            return Intent(context, PropertyDetailActivity::class.java).apply {
                putExtra("property", property)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val property = intent.getParcelableExtra<Property>("property")

        if (property != null) {
            findViewById<TextView>(R.id.location_text_view).text = buildString {
                append("Ubicacion:  ")
                if (property != null) {
                    append(property.location)
                } else {
                    append("No informada")
                }
            }

        }
        findViewById<TextView>(R.id.price_text_view).text = buildString {
            append("Valor: UF ")
            if (property != null) {
                append(property.price)
            }
        }
        if (property != null) {
            findViewById<TextView>(R.id.type_text_view).text = property.type
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}