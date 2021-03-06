package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.view

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.orogersilva.spotmusicalarm.base.shared.app
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.UserRepositoryModule
import com.orogersilva.spotmusicalarm.dashboarddomain.enums.NetworkState
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Playlist
import com.orogersilva.spotmusicalarm.featuredashboard.R
import com.orogersilva.spotmusicalarm.featuredashboard.databinding.ActivityPlaylistBinding
import com.orogersilva.spotmusicalarm.featuredashboard.di.component.DaggerDashboardComponent
import com.orogersilva.spotmusicalarm.featuredashboard.di.component.PlaylistViewComponent
import com.orogersilva.spotmusicalarm.featuredashboard.di.module.PlaylistViewModelModule
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.BaseActivity
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm.view.NewClockAlarmActivity
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.PlaylistViewModel
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.adapter.PlaylistPagedAdapter
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.tracklist.view.TrackListActivity
import kotlinx.android.synthetic.main.activity_playlist.*
import javax.inject.Inject

class PlaylistActivity : BaseActivity() {

    // region PROPERTIES

    private lateinit var playlistViewComponent: PlaylistViewComponent

    private lateinit var playlistBinding: ActivityPlaylistBinding

    @Inject lateinit var playlistViewModel: PlaylistViewModel
    @Inject lateinit var playlistPagedAdapter: PlaylistPagedAdapter

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        injectDependencies()
        prepareUi()
        prepareViewModel()
    }

    override fun onResume() {

        super.onResume()

        playlistViewModel.resume()
    }

    // endregion

    // region OVERRIDED METHODS

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NewClockAlarmActivity.TRACK_REQUEST_CODE &&
                resultCode == RESULT_OK) {

            setResult(resultCode, data)

            finish()
        }
    }

    // endregion

    // region UTILITY METHODS

    private fun injectDependencies() {

        val dashboardComponent = DaggerDashboardComponent.builder()
                .applicationComponent(app().applicationComponent)
                .userRepositoryModule(UserRepositoryModule())
                .build()

        playlistViewComponent = dashboardComponent
                .plusPlaylistViewComponent(PlaylistViewModelModule(this))

        playlistViewComponent.inject(this)
    }

    private fun prepareUi() {

        playlistBinding = DataBindingUtil.setContentView(this, R.layout.activity_playlist)

        val linearLayoutManager = LinearLayoutManager(this)

        playlistRecyclerView.adapter = playlistPagedAdapter
        playlistRecyclerView.layoutManager = linearLayoutManager
        playlistRecyclerView.addItemDecoration(DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL))
    }

    private fun prepareViewModel() {

        playlistViewModel.apply {

            playlistPagedListLiveData.observe(this@PlaylistActivity, Observer<PagedList<Playlist>> { playlistPagedList ->

                playlistPagedList?.let {
                    playlistPagedAdapter.submitList(it)
                }
            })

            initialLoadingLiveData.observe(this@PlaylistActivity, Observer<NetworkState> { networkState ->

                networkState?.let {
                    loadPlaylistsProgressBar.visibility =
                            if (it == NetworkState.LOADING) View.VISIBLE else View.INVISIBLE
                }
            })

            networkStateLiveData.observe(this@PlaylistActivity, Observer<NetworkState> { networkState ->

                networkState?.let {
                    playlistPagedAdapter.setNetworkState(it)
                }
            })

            selectedPlaylistSingleEvent.observe(this@PlaylistActivity, Observer<String> { playlistId ->

                playlistId?.let {
                    redirectToTrackListScreen(it)
                }
            })
        }

        playlistBinding.setLifecycleOwner(this)
    }

    // endregion

    private fun redirectToTrackListScreen(playlistId: String) {

        val trackListIntent = Intent(this, TrackListActivity::class.java)

        trackListIntent.putExtra(TrackListActivity.ARG_PLAYLIST_ID, playlistId)

        startActivityForResult(trackListIntent, NewClockAlarmActivity.TRACK_REQUEST_CODE)
    }
}