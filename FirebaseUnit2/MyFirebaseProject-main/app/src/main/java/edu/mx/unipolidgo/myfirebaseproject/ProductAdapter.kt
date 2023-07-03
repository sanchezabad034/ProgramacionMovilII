package edu.mx.unipolidgo.myfirebaseproject

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlin.coroutines.coroutineContext

class ProductAdapter(context: Context, private val productList: List<Product>) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return productList.size
    }

    override fun getItem(position: Int): Any {
        return productList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val holder: ViewHolder

        if (view == null) {
            val view = inflater.inflate(R.layout.activiity_views, parent, false)

            holder = ViewHolder()
            holder.imageView = view.findViewById<ImageView>(R.id.imageView)
            holder.textViewName = view.findViewById<TextView>(R.id.textViewName)
            holder.textViewPrice = view.findViewById<TextView>(R.id.textViewPrice)
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        val product = productList[position]

        holder.textViewName.text = product.name
        holder.textViewPrice.text = product.price.toString()

        // Cargar la imagen utilizando Glide u otra biblioteca similar
        holder.imageView?.let {
        Glide.with(it.context)
            .load(product.imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(it)
        }
        return view!!
    }

    private class ViewHolder {
        lateinit var imageView: ImageView
        lateinit var textViewName: TextView
        lateinit var textViewPrice: TextView
    }
}
