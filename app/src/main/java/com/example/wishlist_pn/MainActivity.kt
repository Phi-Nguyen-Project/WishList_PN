package com.example.wishlist_pn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TestLooperManager
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val items = mutableListOf<Item>()
    private lateinit var rvWishItems : RecyclerView
    lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListener = object : ItemAdapter.OnLongClickListener{
            override fun onItemLongClicked(position: Int) {
                //remove item
                items.removeAt(position)

                //Notify the adapter that our data has changed
                itemAdapter.notifyDataSetChanged()


            }
        }


        // Look up items in the recycler view
        rvWishItems = findViewById(R.id.rvItems)

        // Attach the adapter to the recycler view to populate items
        itemAdapter = ItemAdapter(items, onLongClickListener)

        rvWishItems.adapter = itemAdapter
        rvWishItems.layoutManager = LinearLayoutManager(this)

        val itemName = findViewById<EditText>(R.id.etItemName)
        val itemPrice = findViewById<EditText>(R.id.etPrice)
        val itemURL = findViewById<EditText>(R.id.etURL)

        findViewById<Button>(R.id.btnSubmit).setOnClickListener{

            var newItem: Item = Item(itemName.text.toString(), itemPrice.text.toString().toDouble(), itemURL.text.toString())
            // Add new item into the List
            items.add(newItem)

            // Notify the adapter that data has changed
            itemAdapter.notifyItemInserted(items.size - 1)
            rvWishItems.scrollToPosition(items.size - 1)

            // Clear all the text boxes
            findViewById<EditText>(R.id.etItemName).text.clear()
            findViewById<EditText>(R.id.etURL).text.clear()
            findViewById<EditText>(R.id.etPrice).text.clear()

        }
    }
}