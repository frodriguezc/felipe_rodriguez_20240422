package tech.carcher.felipe_rodriguez_20240422

import android.content.Context
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class PropertyAdapter(
    context: Context,
    private val resource: Int,
    private val properties: List<Property>
) : ArrayAdapter<Property>(context, resource, properties) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val property = getItem(position)!!

        val view = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)

        val imageView: ImageView = view.findViewById(R.id.image_view)
        val priceTextView: TextView = view.findViewById(R.id.price_text_view)

        Glide.with(context).load(property.imageUrl).into(imageView)
        priceTextView.text = buildString {
            append("UF ")
            append(property.price)
        }

        view.setOnCreateContextMenuListener { menu, v, _ ->
            menu.add(Menu.NONE, position, Menu.NONE, "Eliminar").setOnMenuItemClickListener {
                remove(property)
                notifyDataSetChanged()
                true
            }
        }

        imageView.setOnClickListener {
            val intent = PropertyDetailActivity.newIntent(context, property)
            context.startActivity(intent)
        }

        return view
    }
}