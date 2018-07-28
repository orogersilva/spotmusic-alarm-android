package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.tracklist.view

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.orogersilva.spotmusicalarm.base.shared.app
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.TrackDataSourceModule
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.UserRepositoryModule
import com.orogersilva.spotmusicalarm.dashboarddomain.enums.NetworkState
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Track
import com.orogersilva.spotmusicalarm.featuredashboard.R
import com.orogersilva.spotmusicalarm.featuredashboard.databinding.ActivityTrackListBinding
import com.orogersilva.spotmusicalarm.featuredashboard.di.component.DaggerDashboardComponent
import com.orogersilva.spotmusicalarm.featuredashboard.di.component.TrackListViewComponent
import com.orogersilva.spotmusicalarm.featuredashboard.di.module.TrackListViewModelModule
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.BaseActivity
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.tracklist.TrackListViewModel
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.tracklist.adapter.TrackListPagedAdapter
import kotlinx.android.synthetic.main.activity_track_list.*
import javax.inject.Inject

class TrackListActivity : BaseActivity() {

    // region PROPERTIES

    private lateinit var trackListViewComponent: TrackListViewComponent

    private lateinit var trackListBinding: ActivityTrackListBinding

    @Inject lateinit var trackListViewModel: TrackListViewModel
    @Inject lateinit var trackListPagedAdapter: TrackListPagedAdapter

    // endregion

    // region COMPANION OBJECT

    companion object {
        val ARG_PLAYLIST_ID = "com.orogersilva.spotmusicalarm.featuredashboard.ARG_PLAYLIST_ID"
    }

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        injectDependencies(getPassedPlaylistId())
        prepareUi()
        prepareViewModel()
    }

    // endregion

    // region UTILITY METHODS

    private fun injectDependencies(playlistId: String?) {

        playlistId?.let {

            val dashboardComponent = DaggerDashboardComponent.builder()
                    .applicationComponent(app().applicationComponent)
                    .userRepositoryModule(UserRepositoryModule())
                    .build()

            trackListViewComponent = dashboardComponent
                    .plusTrackListViewComponent(
                            TrackDataSourceModule(it),
                            TrackListViewModelModule(this)
                    )

            trackListViewComponent.inject(this)
        }
    }

    private fun prepareUi() {

        trackListBinding = DataBindingUtil.setContentView(this, R.layout.activity_track_list)

        val linearLayoutManager = LinearLayoutManager(this)

        trackRecyclerView.adapter = trackListPagedAdapter
        trackRecyclerView.layoutManager = linearLayoutManager
        trackRecyclerView.addItemDecoration(DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL))
    }

    private fun prepareViewModel() {

        trackListViewModel.apply {

            trackPagedListLiveData.observe(this@TrackListActivity, Observer<PagedList<Track>> { trackPagedList ->

                trackPagedList?.let {
                    trackListPagedAdapter.submitList(it)
                }
            })

            initialLoadingLiveData.observe(this@TrackListActivity, Observer<NetworkState> { networkState ->

                networkState?.let {
                    loadTracksProgressBar.visibility =
                            if (it == NetworkState.LOADING) View.VISIBLE else View.INVISIBLE
                }
            })

            networkStateLiveData.observe(this@TrackListActivity, Observer<NetworkState> { networkState ->

                networkState?.let {
                    trackListPagedAdapter.setNetworkState(it)
                }
            })

            selectedTrackSingleEvent.observe(this@TrackListActivity, Observer<String> { trackId ->

                trackId?.let {
                    goBackToNewClockAlarmActivity()
                }
            })
        }

        trackListBinding.setLifecycleOwner(this)
    }

    private fun goBackToNewClockAlarmActivity() {


    }

    private fun getPassedPlaylistId(): String? {

        var playlistId: String? = null

        val args = intent.extras

        args?.let {

            playlistId = it.getString(ARG_PLAYLIST_ID)
        }

        return playlistId
    }

    // endregion
}