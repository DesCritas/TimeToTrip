package com.descritas.timetotrip.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.descritas.timetotrip.databinding.FlightCardBinding
import com.descritas.timetotrip.dto.Flight
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

interface OnInteractionListener {
    fun onItemClick(flight: Flight)
    fun onLike(flight: Flight) {}
}

class FlightAdapter(
    private val onInteractionListener: OnInteractionListener
) : ListAdapter<Flight, FlightViewHolder>(FlightDiffCallback()) {


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
    private val binding: FlightCardBinding, private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(flight: Flight) {
        with(binding) {
            departure.text = flight.startCity
            arrival.text = flight.endCity
            startDate.text = formatStringToDate(flight.startDate)
            endDate.text = formatStringToDate(flight.endDate)
            price.text = concatPrice(flight.price)
            like.isChecked = flight.liked

            like.setOnClickListener {
                onInteractionListener.onLike(flight)
            }
            root.setOnClickListener {
                onInteractionListener.onItemClick(flight)
            }
        }
    }


}

class FlightDiffCallback : DiffUtil.ItemCallback<Flight>() {
    override fun areItemsTheSame(oldItem: Flight, newItem: Flight): Boolean {
        return oldItem.departure == newItem.departure &&
                oldItem.arrival == newItem.arrival &&
                oldItem.startDate == newItem.startDate
    }

    override fun areContentsTheSame(oldItem: Flight, newItem: Flight): Boolean {
        return oldItem == newItem
    }
}

fun formatStringToDate(dateString: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z z")
    val dateTime = LocalDateTime.parse(dateString, formatter)

    val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")

    return dateTime.format(outputFormatter)
}
fun concatPrice(priceString: String): String{
    return "$priceString руб."
}
