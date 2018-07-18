package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Playlist
import com.orogersilva.spotmusicalarm.featuredashboard.R
import com.orogersilva.spotmusicalarm.featuredashboard.databinding.ItemPlaylistBinding
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.PlaylistViewModel
import javax.inject.Inject

class PlaylistAdapter @Inject constructor(private val playlistViewModel: PlaylistViewModel)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // region PROPERTIES

    private val playlists = mutableListOf<Playlist>()

    // endregion

    // region PUBLIC METHODS

    fun setData(data: List<Playlist>) {

        playlists.clear()
        playlists.addAll(data)

        notifyDataSetChanged()
    }

    // endregion

    // region OVERRIDED METHODS

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_playlist, parent, false))

    override fun getItemCount(): Int = playlists.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val playlist = playlists[position]

        (holder as ViewHolder).bind(playlist)
    }

    // endregion

    // region VIEW HOLDERS

    inner class ViewHolder(private val binding: ItemPlaylistBinding) : RecyclerView.ViewHolder(binding.root) {

        // region PUBLIC METHODS

        fun bind(playlist: Playlist) {

            binding.playlist = playlist
            binding.playlistConstraintLayout.setOnClickListener {
                playlistViewModel.selectPlaylist(playlist.id)
            }

            binding.executePendingBindings()
        }

        // endregion
    }

    // endregion
}