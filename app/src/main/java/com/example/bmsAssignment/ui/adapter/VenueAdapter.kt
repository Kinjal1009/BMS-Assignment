package com.example.bmsAssignment.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bmsAssignment.base.commonContract.RecyclerviewClickListener
import com.example.bmsAssignment.databinding.ItemVenueBinding
import com.example.bmsAssignment.model.Venue

class VenueAdapter(var venueList: List<Venue?>, var listener: RecyclerviewClickListener) : RecyclerView.Adapter<VenueAdapter.ViewHolder>() {

    private lateinit var binding: ItemVenueBinding
    private var mVenueList: List<Venue?> = venueList
    private var mListener: RecyclerviewClickListener = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemVenueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, parent.context, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mVenueList[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return mVenueList.size
    }

    inner class ViewHolder(private val binding: ItemVenueBinding, context: Context, listener: RecyclerviewClickListener) : RecyclerView.ViewHolder(binding.root) {

        private var mContext = context
        private var mListener = listener

        fun bind(venue: Venue) {
            binding.tvVenueName.text = venue.name

            if (venue.showTimes.isNotEmpty()) {
                val adapter = ShowTimeAdapter(venue, mListener)
                binding.rvShowTime.layoutManager = GridLayoutManager(mContext, 3)
                binding.rvShowTime.adapter = adapter
            }

            binding.root.setOnClickListener {
               // Log.d("VenueAdapter", "${venue.name}, ${venue.showDate}, ${venue.showTimes}")
            }
        }
    }
}