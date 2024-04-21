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
                if (property.location != null) {
                    append(property.location)
                } else {
                    append("No informada")
                }
            }
            findViewById<TextView>(R.id.price_text_view).text = buildString {
                if (property.price != null) {
                    append("Valor: UF ")
                    append(property.price)
                } else {
                    append("Valor no declarado, consulte directamente ")
                }
            }
            findViewById<TextView>(R.id.type_text_view).text = buildString {
                append("Categoría:  ")
                if (property.type != null) {
                    append(property.type)
                } else {
                    append("No declarada, consulte directamente ")
                }
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}