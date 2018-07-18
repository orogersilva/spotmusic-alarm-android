package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.view

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.orogersilva.spotmusicalarm.featuredashboard.R
import com.orogersilva.spotmusicalarm.featuredashboard.databinding.ActivityPlaylistBinding
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.BaseActivity
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.PlaylistViewModel
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.adapter.PlaylistAdapter
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.tracklist.view.TrackListActivity
import kotlinx.android.synthetic.main.activity_playlist.*
import javax.inject.Inject

class PlaylistActivity : BaseActivity() {

    // region PROPERTIES

    private lateinit var playlistBinding: ActivityPlaylistBinding

    @Inject lateinit var playlistViewModel: PlaylistViewModel
    @Inject lateinit var playlistAdapter: PlaylistAdapter

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)



        prepareUi()
        prepareViewModel()
    }

    override fun onResume() {

        super.onResume()

        playlistViewModel.resume()
    }

    // endregion

    // region UTILITY METHODS

    private fun prepareUi() {

        playlistBinding = DataBindingUtil.setContentView(this, R.layout.activity_playlist)

        val linearLayoutManager = LinearLayoutManager(this)

        playlistRecyclerView.adapter = playlistAdapter
        playlistRecyclerView.layoutManager = linearLayoutManager
    }

    private fun prepareViewModel() {

        playlistViewModel.apply {

            selectedPlaylistEvent.observe(this@PlaylistActivity, Observer<String> { playlistId ->

                playlistId?.let {
                    redirectToTrackListActivity(it)
                }
            })
        }

        playlistBinding.setLifecycleOwner(this)
    }

    // endregion

    private fun redirectToTrackListActivity(playlistId: String) {

        val trackListIntent = Intent(this, TrackListActivity::class.java)

        trackListIntent.putExtra(TrackListActivity.ARG_PLAYLIST_ID, playlistId)

        startActivity(trackListIntent)
    }
}