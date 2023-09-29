package com.descritas.timetotrip.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs

import com.descritas.timetotrip.R
import com.descritas.timetotrip.dto.Flight
import com.descritas.timetotrip.viewModel.FlightViewModel


class FlightDetail : Fragment() {
    private val args: FlightDetailArgs by navArgs()
    private lateinit var flight: Flight
    private val viewModel: FlightViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        flight = args.flight
        val view = inflater.inflate(R.layout.fragment_flight_detail, container, false)


        val detailDeparture: TextView = view.findViewById(R.id.detailDeparture)
        val detailArrival: TextView = view.findViewById(R.id.detailArrival)
        val detailStartDate: TextView = view.findViewById(R.id.detailStartTime)
        val detailEndDate: TextView = view.findViewById(R.id.detailEndTime)
        val detailPrice: TextView = view.findViewById(R.id.detailPrice)

        detailDeparture.text = flight.departure
        detailArrival.text = flight.arrival
        detailStartDate.text = flight.startDate
        detailEndDate.text = flight.endDate
        detailPrice.text = flight.price


        return view
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