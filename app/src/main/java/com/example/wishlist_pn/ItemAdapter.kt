package com.example.wishlist_pn

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ItemAdapter(private val items: MutableList<Item>,
                  val longClickListener: OnLongClickListener) : RecyclerView.Adapter<ItemAdapter.ViewHolder>()  {

    interface OnLongClickListener {
        fun onItemLongClicked (position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val itemView = inflater.inflate(R.layout.item_list, parent, false)
        // Return a new holder instance
        return ViewHolder(itemView)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //Store references to elements in our layout view
        val wishItem : TextView
        val wishURL : TextView
        val wishPrice : TextView

        init {
            wishItem = itemView.findViewById(R.id.tvWishItem)
            wishURL = itemView.findViewById(R.id.tvWishURL)
            wishPrice = itemView.findViewById(R.id.tvWishPrice)
            
            itemView.setOnLongClickListener {
                longClickListener.onItemLongClicked(adapterPosition)
                true
            }
        }

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]
        holder.wishItem.text = item.wishItem
        holder.wishPrice.text = item.wishItemPrice.toString()
        holder.wishURL.text = item.wishItemURL

    }

    override fun getItemCount(): Int {
        return  items.size
    }




}