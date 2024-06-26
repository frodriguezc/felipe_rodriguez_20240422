package tech.carcher.felipe_rodriguez_20240422

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class PropertyAdapter(

    context: Context,
    private val resource: Int,
    properties: List<Property>
) : ArrayAdapter<Property>(context, resource, properties) {

    private val mutableProperties = properties.toMutableList()
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val property = getItem(position)!!
//        val property = getItem(position) ?: return super.getView(position, convertView, parent)
        val property = mutableProperties[position]

        val view = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)

        val imageView: ImageView = view.findViewById(R.id.image_view)
        val priceTextView: TextView = view.findViewById(R.id.price_text_view)

        Glide.with(context).load(property.imageUrl).into(imageView)
        priceTextView.text = buildString {
            append("UF ")
            append(property.price)
        }
        return view
    }

//    fun remove(property: Property) {
//        properties.drop(property.id)
//        notifyDataSetChanged()
//    }

    fun remove(position: Int) {
        mutableProperties.removeAt(position)
        notifyDataSetChanged()
    }
}