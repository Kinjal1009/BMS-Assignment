package com.example.bmsAssignment.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bmsAssignment.R
import com.example.bmsAssignment.base.commonContract.RecyclerviewClickListener
import com.example.bmsAssignment.base.api.Status
import com.example.bmsAssignment.databinding.ActivityMovieScheduleBinding
import com.example.bmsAssignment.model.Venue
import com.example.bmsAssignment.ui.adapter.VenueAdapter
import com.example.bmsAssignment.utils.getDateInSpecificFormat
import com.example.bmsAssignment.viewModel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint class MovieScheduleActivity : AppCompatActivity(), RecyclerviewClickListener {

    private lateinit var binding: ActivityMovieScheduleBinding
    private lateinit var viewModel: MovieViewModel
    private var selectedDate: String = ""
    private var venueList: List<Venue?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_schedule)
        binding.movieViewModel = viewModel
        binding.lifecycleOwner = this
        handleMovieVenueResponse()

        binding.dayScrollDatePicker.setStartDate(1, 5, 2022)
        binding.dayScrollDatePicker.getSelectedDate {
            selectedDate = it.toString()
            setRecycleView(filterMovieVenueByShowDate(venueList))
        }
    }

    private fun handleMovieVenueResponse() {
        viewModel.apiCall.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    if (it.data == null) {
                        binding.rvMovieVenue.visibility = View.GONE
                        binding.tvNoShowMsg.visibility = View.VISIBLE
                    } else {
                        venueList = it.data.venues
                        setRecycleView(filterMovieVenueByShowDate(venueList))
                    }
                }
                Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    it.message?.let { message ->
                        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                    }
                }
                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun filterMovieVenueByShowDate(venues: List<Venue?>?): ArrayList<Venue> {
        return ArrayList<Venue>().apply {
            venues?.forEach { venue ->
                venue?.let {
                    if (it.showDate?.isNotEmpty() == true) {
                        if (getDateInSpecificFormat(it.showDate).equals(selectedDate)) {
                            this.add(venue)
                        }
                    }
                }
            }
        }
    }

    private fun setRecycleView(venueList: List<Venue?>) {
        if (venueList.isEmpty()) {
            binding.rvMovieVenue.visibility = View.GONE
            binding.tvNoShowMsg.visibility = View.VISIBLE
            binding.tvNoShowMsg.text = if (selectedDate.isEmpty()) getString(R.string.select_date) else getString(R.string.no_show_msg)
        } else {
            binding.rvMovieVenue.visibility = View.VISIBLE
            binding.tvNoShowMsg.visibility = View.GONE
            val adapter = VenueAdapter(venueList, this)
            binding.rvMovieVenue.layoutManager = LinearLayoutManager(this)
            binding.rvMovieVenue.adapter = adapter
        }
    }

    override fun recyclerviewClickListener(venue: Venue) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("Venue", venue)
        startActivity(intent)
    }
}