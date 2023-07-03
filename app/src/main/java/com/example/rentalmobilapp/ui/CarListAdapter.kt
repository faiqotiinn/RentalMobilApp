package com.example.rentalmobilapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rentalmobilapp.R
import com.example.rentalmobilapp.model.Car

class CarListAdapter(
    private val onItemClickListener: (Car) -> Unit
): ListAdapter<Car, CarListAdapter.CarViewHolder>(WORDS_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = getItem(position)
        holder.bind(car)
        holder.itemView.setOnClickListener {
            onItemClickListener(car)
        }
    }

    class CarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.NameTextView)
        private val addressTextView: TextView = itemView.findViewById(R.id.AddressTextView)

        fun bind(car: Car?) {
            nameTextView.text = car?.name
            addressTextView.text = car?.address

        }

        companion object {
            fun create(parent: ViewGroup): CarViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_car, parent, false )
                return CarViewHolder(view)
            }
        }
    }
    companion object{
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Car>() {
            override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}