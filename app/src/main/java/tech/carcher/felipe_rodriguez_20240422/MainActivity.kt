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
    private var selectedProperty: Property? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val properties = mutableListOf(
            Property(1, R.raw.casa1, 6000, "Location 1", "Venta"),
            Property(2, R.raw.casa2, 300, "Location 2", "Arriendo"),
            Property(3, R.raw.casa3, 7000, "Location 2", "Venta")

        )

        listView = findViewById(R.id.list_view)
        adapter = PropertyAdapter(this, R.layout.item_property, properties)
        listView.adapter = adapter

        registerForContextMenu(listView)

        listView.setOnItemClickListener { _, _, position, _ ->
            // Obtener la propiedad seleccionada según la posición
            val selectedProperty = adapter.getItem(position)

            // Iniciar PropertyDetailActivity pasando la propiedad seleccionada
            selectedProperty?.let {
                val intent = Intent(this, PropertyDetailActivity::class.java).apply {
                    putExtra("property", it)
                }
                startActivity(intent)
            }
        }


    }
    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        selectedProperty = adapter.getItem(info.position)
        menu.add("Eliminar")
    }

    // Manejar la acción del menú contextual
    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (item.title == "Eliminar") {
            selectedProperty?.let { property ->
                adapter.remove(property)
                adapter.notifyDataSetChanged()
            }
        }
        return super.onContextItemSelected(item)
    }


}