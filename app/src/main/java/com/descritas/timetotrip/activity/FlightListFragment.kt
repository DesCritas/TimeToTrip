package com.descritas.timetotrip.activity;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment;
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.descritas.timetotrip.R
import com.descritas.timetotrip.databinding.FlightListBinding

class FlightListFragment  : Fragment() {

    private val viewModel: FlightViewModel by activityViewModels()

    //private lateinit var flights: List<Flight>
    //private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val binding = FlightListBinding.inflate(inflater, container, false)

        // TODO: Загрузить список карточек из источника данных
        //flights = getFlights()

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

        viewModel.data.observe(viewLifecycleOwner) { state -> flightAdapter.submitList(state.posts)
        binding.emptyText.isVisible = state.empty
        }
        //viewModel.state.observe(viewLifecycleOwner) { state ->
        //    binding.progress.isVisible = state is FeedModelState.Loading
        //    if (state is FeedModelState.Error) {
        //        Snackbar.make(binding.root, R.string.error_loading, Snackbar.LENGTH_LONG)
        //            .setAction(R.string.retry_loading) {
        //                viewModel.refresh()
        //            }
        //            .show()
        //    }
        //    binding.swipeRefresh.isRefreshing = state is FeedModelState.Refresh
        //}


        return binding.root

    }


    //private fun getFlights(): List<Flight> {
    //    val flights = ArrayList<Flight>()
    //    flights.add(Flight("SVO1", "FRA1", "2023-09-01", "2023-09-01", "2056", liked = false))
    //    flights.add(Flight("SVO2", "FRA2", "2023-09-01", "2023-09-01", "2056", liked = false))
    //    flights.add(Flight("SVO3", "FRA3", "2023-09-01", "2023-09-01", "2056", liked = false))
    //    flights.add(Flight("SVO4", "FRA4", "2023-09-01", "2023-09-01", "2056", liked = false))
    //    flights.add(Flight("SVO5", "FRA5", "2023-09-01", "2023-09-01", "2056", liked = false))
//
    //    return flights
    //}

}
