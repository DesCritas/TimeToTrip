package com.descritas.timetotrip.activity;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment;
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.descritas.timetotrip.R
import com.descritas.timetotrip.adapter.FlightAdapter
import com.descritas.timetotrip.adapter.OnInteractionListener
import com.descritas.timetotrip.databinding.FlightListBinding
import com.descritas.timetotrip.dto.Flight
import com.descritas.timetotrip.model.FlightModelState
import com.descritas.timetotrip.viewModel.FlightViewModel
import com.google.android.material.snackbar.Snackbar

class FlightListFragment  : Fragment() {

    private val viewModel: FlightViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FlightListBinding.inflate(inflater, container, false)

        val flightAdapter = FlightAdapter(object: OnInteractionListener{

            override fun onItemClick(flight: Flight) {
                val action = FlightListFragmentDirections.actionFlightListFragmentToFlightDetailFragment(flight)
                findNavController().navigate(action)
            }

            override fun onLike(flight: Flight) {
                super.onLike(flight)
                //viewModel.likeById(flight)
            }

        })
        binding.list.adapter = flightAdapter

        viewModel.data1.observe(viewLifecycleOwner) {state->
            //val dataList: ArrayList<Flight> = viewModel.cardFiller2()
            //flightAdapter.submitList(dataList)
            flightAdapter.submitList(state.flights)
        }
        viewModel.state.observe(viewLifecycleOwner) { state ->

            binding.progress.isVisible = state is FlightModelState.Loading

            if (state is FlightModelState.Error) {
                Snackbar.make(binding.root, R.string.error_loading, Snackbar.LENGTH_LONG)
                    .setAction(R.string.retry_loading) {
                        viewModel.refresh()
                    }
                    .show()
            }

        }


        return binding.root

    }



}
