package com.orogersilva.spotmusicalarm.core.di.module

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.orogersilva.spotmusicalarm.base.di.scope.ModuleScope
import com.orogersilva.spotmusicalarm.core.presentation.screen.dashboard.DashboardViewModel
import com.orogersilva.spotmusicalarm.core.presentation.screen.dashboard.DashboardViewModelFactory
import com.orogersilva.spotmusicalarm.core.presentation.screen.dashboard.view.DashboardActivity
import dagger.Module
import dagger.Provides

@Module
object DashboardModule {

    // region PROVIDERS

    @Provides @ModuleScope @JvmStatic fun provideDashboardViewModel(dashboardActivity: DashboardActivity): DashboardViewModel {

        val dashboardViewModelFactory = DashboardViewModelFactory()

        return ViewModelProviders.of(dashboardActivity, dashboardViewModelFactory)
                .get(DashboardViewModel::class.java)
    }

    // endregion
}