package com.orogersilva.spotmusicalarm.featuredashboard.di.module

import android.arch.lifecycle.ViewModelProviders
import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.dashboard.DashboardViewModel
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.dashboard.DashboardViewModelFactory
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.dashboard.adapter.DashboardListAdapter
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.dashboard.view.DashboardActivity
import dagger.Module
import dagger.Provides

@Module
object DashboardModule {

    // region PROVIDERS

    @Provides @ActivityScope @JvmStatic fun provideDashboardViewModel(dashboardActivity: DashboardActivity): DashboardViewModel {

        val dashboardViewModelFactory = DashboardViewModelFactory()

        return ViewModelProviders.of(dashboardActivity, dashboardViewModelFactory)
                .get(DashboardViewModel::class.java)
    }

    @Provides @ActivityScope @JvmStatic fun provideDashboardAdapter(dashboardViewModel: DashboardViewModel): DashboardListAdapter =
            DashboardListAdapter(dashboardViewModel)

    // endregion
}