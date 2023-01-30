package com.example.bmsAssignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bmsAssignment.base.commonContract.RecyclerviewClickListener
import com.example.bmsAssignment.databinding.ItemShowTimeBinding
import com.example.bmsAssignment.model.ShowTimes
import com.example.bmsAssignment.model.Venue

class ShowTimeAdapter(private val venue: Venue,  listener: RecyclerviewClickListener) : RecyclerView.Adapter<ShowTimeAdapter.ViewHolder>() {

    private lateinit var binding: ItemShowTimeBinding
    private var mVenue: Venue = venue
    private var mShowTimeList: List<ShowTimes?> = this.venue.showTimes
    private var selectedPosition = -1
    private var mListener: RecyclerviewClickListener = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemShowTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mShowTimeList[position].let { holder.bind(it) }
        holder.itemView.isSelected = selectedPosition == position
    }

    override fun getItemCount(): Int {
        return mShowTimeList.size
    }

    inner class ViewHolder(private val binding: ItemShowTimeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(showTime: ShowTimes?) {
            binding.tvShowTime.text = showTime?.showTime
            binding.root.setOnClickListener {
                selectedPosition = adapterPosition
                notifyDataSetChanged()
                val selectedShowList = mutableListOf<ShowTimes?>().also { it.add(showTime) }
                mListener.recyclerviewClickListener(Venue(mVenue.name, mVenue.showDate, selectedShowList))
            }
        }
    }
}