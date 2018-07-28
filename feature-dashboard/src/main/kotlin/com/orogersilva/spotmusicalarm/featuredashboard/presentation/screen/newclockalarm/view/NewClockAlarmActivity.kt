package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm.view

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.orogersilva.spotmusicalarm.base.shared.app
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.UserRepositoryModule
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Track
import com.orogersilva.spotmusicalarm.featuredashboard.R
import com.orogersilva.spotmusicalarm.featuredashboard.databinding.ActivityNewClockAlarmBinding
import com.orogersilva.spotmusicalarm.featuredashboard.di.component.DaggerDashboardComponent
import com.orogersilva.spotmusicalarm.featuredashboard.di.component.NewClockAlarmViewComponent
import com.orogersilva.spotmusicalarm.featuredashboard.di.module.NewClockAlarmViewModelModule
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.BaseActivity
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm.NewClockAlarmViewModel
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.view.PlaylistActivity
import com.orogersilva.spotmusicalarm.spotifyapi.SpotifyAdapterHelper
import com.orogersilva.spotmusicalarm.spotifyapi.di.module.SpotifyModule
import kotlinx.android.synthetic.main.activity_new_clock_alarm.*
import javax.inject.Inject

class NewClockAlarmActivity : BaseActivity() {

    // region PROPERTIES

    private lateinit var newClockAlarmViewComponent: NewClockAlarmViewComponent

    private lateinit var newClockAlarmBinding: ActivityNewClockAlarmBinding

    @Inject lateinit var newClockAlarmViewModel: NewClockAlarmViewModel
    @Inject lateinit var spotifyAdapterHelper: SpotifyAdapterHelper

    private var selectedTrack: Track? = null

    // endregion

    // region COMPANION OBJECT

    companion object RequestCode {

        // region REQUEST CODES

        val SPOTIFY_AUTH_REQUEST_CODE = 1
        val TRACK_REQUEST_CODE = 2

        // endregion

        // region INTENT KEYS

        val ARG_TRACK = "com.orogersilva.spotmusicalarm.ARG_TRACK"

        // endregion
    }

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        injectDependencies()
        prepareUi()
        prepareListeners()
        prepareViewModel()
    }

    override fun onResume() {

        super.onResume()

        newClockAlarmViewModel.resume()
    }

    override fun onDestroy() {

        spotifyAdapterHelper.destroyPlayer(this)

        super.onDestroy()
    }

    // endregion

    // region OVERRIDED METHODS

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SPOTIFY_AUTH_REQUEST_CODE) {

            val accessToken = spotifyAdapterHelper.getAccessToken(resultCode, data)

            accessToken?.let {

                newClockAlarmViewModel.saveAccessToken(it)

                redirectToPlaylistScreen()
            }
        }

        if (requestCode == TRACK_REQUEST_CODE &&
                resultCode == RESULT_OK) {

            selectedTrack = data?.getSerializableExtra(ARG_TRACK) as Track

            trackTextInputEditText.setText(selectedTrack?.name)
        }
    }

    // endregion

    // region UTILITY METHODS

    private fun injectDependencies() {

        val dashboardComponent = DaggerDashboardComponent.builder()
                .applicationComponent(app().applicationComponent)
                .userRepositoryModule(UserRepositoryModule())
                .build()

        newClockAlarmViewComponent = dashboardComponent
                .plusNewClockAlarmViewComponent(NewClockAlarmViewModelModule(this), SpotifyModule())

        newClockAlarmViewComponent.inject(this)
    }

    private fun prepareUi() {

        newClockAlarmBinding = DataBindingUtil.setContentView<ActivityNewClockAlarmBinding>(
                this, R.layout.activity_new_clock_alarm)
    }

    private fun prepareListeners() {

        timeTextInputEditText.keyListener = null
        trackTextInputEditText.keyListener = null
    }

    private fun prepareViewModel() {

        newClockAlarmViewModel.apply {

            setClockAlarmMusicLiveData.observe(this@NewClockAlarmActivity, Observer<Void> {

                spotifyAdapterHelper.openLoginScreen(this@NewClockAlarmActivity, SPOTIFY_AUTH_REQUEST_CODE)
            })
        }

        newClockAlarmBinding.setLifecycleOwner(this)
        newClockAlarmBinding.newClockAlarmViewModel = newClockAlarmViewModel
    }

    private fun redirectToPlaylistScreen() {

        val playlistIntent = Intent(this, PlaylistActivity::class.java)

        startActivityForResult(playlistIntent, TRACK_REQUEST_CODE)
    }

    // endregion
}