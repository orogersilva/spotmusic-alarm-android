package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.tracklist.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Track
import com.orogersilva.spotmusicalarm.featuredashboard.R
import com.orogersilva.spotmusicalarm.featuredashboard.databinding.ItemTrackBinding

class TrackListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // region PROPERTIES

    private val tracks = mutableListOf<Track>()

    // endregion

    // region PUBLIC METHODS

    fun setData(data: List<Track>) {

        tracks.clear()
        tracks.addAll(data)

        notifyDataSetChanged()
    }

    // endregion

    // region OVERRIDED METHODS

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_track, parent, false))

    override fun getItemCount(): Int = tracks.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val track = tracks[position]

        (holder as ViewHolder).bind(track)
    }

    // endregion

    // region VIEW HOLDERS

    inner class ViewHolder(private val binding: ItemTrackBinding) : RecyclerView.ViewHolder(binding.root) {

        // region PUBLIC METHODS

        fun bind(track: Track) {

            binding.track = track
            binding.executePendingBindings()
        }

        // endregion
    }

    // endregion
}