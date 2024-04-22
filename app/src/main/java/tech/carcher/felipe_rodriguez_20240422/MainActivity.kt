package tech.carcher.felipe_rodriguez_20240422

import android.os.Bundle
import android.util.Log
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

        listView.setOnItemClickListener { _, _, position, _ ->
            Log.d("FRC", "posicion: $position")
            val property = adapter.getItem(position)
            if (property != null) {
                Log.d("FRC", "propiedad: ${property.id}")
                val intent = PropertyDetailActivity.newIntent(this, property)
                startActivity(intent)
            } else {
                Log.d("FRC", "propiedad null")
            }
        }

    }


    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.setHeaderTitle("Acciones")
        menu.add(0, 0, 0, "Eliminar")
    }

//    override fun onContextItemSelected(item: MenuItem): Boolean {
//        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
//        if (item.title == "Eliminar") {
//            adapter.remove(info.position)
//            return true
//        }
//        return super.onContextItemSelected(item)
//    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        Log.d("FRC", "posicion a eliminar: ${info.position}, tamanho lista previo: ${adapter.count}")
        if (item.title == "Eliminar") {
            adapter.remove(info.position)
            Log.d("FRC", "tamanho lista post: ${adapter.count}")
            return true
        }
        return super.onContextItemSelected(item)
    }
}