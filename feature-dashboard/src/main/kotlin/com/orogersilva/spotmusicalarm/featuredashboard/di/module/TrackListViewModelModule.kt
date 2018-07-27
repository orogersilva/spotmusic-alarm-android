package com.orogersilva.spotmusicalarm.featuredashboard.di.module

import android.arch.lifecycle.ViewModelProviders
import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.dashboarddata.factory.TrackDataSourceFactory
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.tracklist.TrackListViewModel
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.tracklist.TrackListViewModelFactory
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.tracklist.adapter.TrackListPagedAdapter
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.tracklist.view.TrackListActivity
import dagger.Module
import dagger.Provides

@Module
open class TrackListViewModelModule(private val trackListActivity: TrackListActivity) {

    // region PROVIDERS

    @Provides @ActivityScope open fun provideTrackListViewModel(trackDataSourceFactory: TrackDataSourceFactory): TrackListViewModel {

        val trackListViewModelFactory = TrackListViewModelFactory(trackDataSourceFactory)

        return ViewModelProviders.of(trackListActivity, trackListViewModelFactory)
                .get(TrackListViewModel::class.java)
    }

    @Provides @ActivityScope open fun provideTrackPagedAdapter(trackListViewModel: TrackListViewModel): TrackListPagedAdapter =
            TrackListPagedAdapter(trackListViewModel)

    // endregion
}