package com.descritas.timetotrip.activity


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.descritas.timetotrip.R
import com.descritas.timetotrip.databinding.FlightCardBinding
import com.google.android.material.button.MaterialButton

interface OnInteractionListener {
    fun onItemClick(flight: Flight)
    fun onLike(flight: Flight) {}
}

class FlightAdapter(
    private val onInteractionListener: OnInteractionListener
) : ListAdapter<Flight, FlightViewHolder>(PostDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val binding = FlightCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlightViewHolder(binding, onInteractionListener)
    }
    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        val flight = getItem(position)
        holder.bind(flight)
    }

}
class FlightViewHolder(
    private val binding: FlightCardBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(flight: Flight) {
        binding.apply {
            departure.text = flight.departure
            arrival.text = flight.arrival
            startDate.text = flight.startDate
            endDate.text = flight.endDate
            price.text = flight.price
            like.isChecked = flight.liked

            like.setOnClickListener {
                onInteractionListener.onLike(flight)
            }
        }
    }


}
class PostDiffCallback : DiffUtil.ItemCallback<Flight>() {
    override fun areItemsTheSame(oldItem: Flight, newItem: Flight): Boolean {
        return oldItem.departure == newItem.departure && oldItem.arrival == newItem.arrival && oldItem.startDate == newItem.startDate
    }

    override fun areContentsTheSame(oldItem: Flight, newItem: Flight): Boolean {
        return oldItem == newItem
    }
}