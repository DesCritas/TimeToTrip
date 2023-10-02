package com.descritas.timetotrip.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.descritas.timetotrip.databinding.FragmentFlightDetailBinding
import com.descritas.timetotrip.dto.Flight
import com.descritas.timetotrip.viewModel.FlightViewModel

class FlightDetail : Fragment() {
    private val args: FlightDetailArgs by navArgs()
    private lateinit var flight: Flight
    private val viewModel: FlightViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        flight = args.flight
        val binding = FragmentFlightDetailBinding.inflate(
            inflater,
            container,
            false
        )
        bindLayout(binding)

        return binding.root

    }

    private fun bindLayout(binding: FragmentFlightDetailBinding) {
        with(binding) {
            detailDeparture.text = flight.departure
            detailArrival.text = flight.arrival
            detailStartTime.text = flight.startDate
            detailEndTime.text = flight.endDate
            detailPrice.text = flight.price
            like.isChecked = flight.liked
            like.setOnClickListener {
                viewModel.likeById(flight)
            }
        }
    }

    companion object {
        fun newInstance(flight: Flight): FlightDetail {
            val bundle = Bundle().apply {
                putSerializable("flight", flight)
            }
            val fragment = FlightDetail()
            fragment.arguments = bundle
            return fragment
        }
    }
}