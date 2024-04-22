package tech.carcher.felipe_rodriguez_20240422

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: PropertyAdapter
//    private var properties = mutableListOf<Property>(Property(1, R.raw.casa1, 6000, "Santiago", "Venta"),
//        Property(2, R.raw.casa2, 300, "Valdivia", "Arriendo"),
//        Property(3, R.raw.casa3, 7000, "La Serena", "Venta")
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val properties = mutableListOf(
            Property(1, R.raw.casa1, 6000, "Santiago", "Venta"),
            Property(2, R.raw.casa2, 300, "Valdivia", "Arriendo"),
            Property(3, R.raw.casa3, 7000, "La Serena", "Venta"),
            Property(4, R.raw.casa1, 8300, "Maitencillo", "Venta"),
            Property(5, R.raw.casa2, 230, "Vitacura", "Arriendo"),
            Property(6, R.raw.casa3, 9000, "La Pintana", "Venta")
        )

        listView = findViewById(R.id.list_view)
        adapter = PropertyAdapter(this, R.layout.item_property, properties)
        listView.adapter = adapter

        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.setHeaderTitle("Acciones")
        menu.add(0, 0, 0, "Eliminar")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        if (item.title == "Eliminar") {
            adapter.remove(info.position)
            return true
        }
        return super.onContextItemSelected(item)
    }
}