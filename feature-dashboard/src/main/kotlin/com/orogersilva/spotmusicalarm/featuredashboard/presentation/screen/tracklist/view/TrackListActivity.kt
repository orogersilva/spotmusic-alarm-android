package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.tracklist.view

import android.os.Bundle
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.BaseActivity

class TrackListActivity : BaseActivity() {

    // region PROPERTIES



    // endregion

    // region COMPANION OBJECT

    companion object {
        val ARG_PLAYLIST_ID = "com.orogersilva.spotmusicalarm.featuredashboard.ARG_PLAYLIST_ID"
    }

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)



        prepareUi()
        prepareViewModel()
    }

    // endregion

    // region UTILITY METHODS

    private fun prepareUi() {


    }

    private fun prepareViewModel() {


    }

    // endregion
}