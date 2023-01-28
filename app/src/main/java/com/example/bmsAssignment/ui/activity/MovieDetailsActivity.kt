package com.example.bmsAssignment.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.bmsAssignment.R
import com.example.bmsAssignment.databinding.ActivityMovieDetailsBinding
import com.example.bmsAssignment.model.Venue

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val venue: Venue? = intent.extras?.getParcelable("Venue")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)

        binding.tvText.text = """
        You have book a show on below venue: 
        
        Venue Name : ${venue?.name}
        Venue Show Date : ${venue?.showDate}
        Venue Show Time : ${venue?.showTimes?.get(0)}
        """
    }
}
