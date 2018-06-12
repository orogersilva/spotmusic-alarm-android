package com.orogersilva.spotmusicalarm.core.di.component

import android.support.v7.app.AppCompatActivity
import com.orogersilva.spotmusicalarm.base.di.component.ApplicationComponent
import com.orogersilva.spotmusicalarm.base.di.scope.ModuleScope
import com.orogersilva.spotmusicalarm.core.di.module.DashboardModule
import com.orogersilva.spotmusicalarm.core.presentation.screen.dashboard.view.DashboardActivity
import com.orogersilva.spotmusicalarm.spotifyapi.di.module.SpotifyModule
import dagger.Component
import dagger.android.AndroidInjector

@ModuleScope
@Component(modules = [DashboardModule::class, SpotifyModule::class],
        dependencies = [ApplicationComponent::class]
)
interface DashboardComponent : AndroidInjector<DashboardActivity> {

    // region BUILDERS

    @Component.Builder abstract class Builder : AndroidInjector.Builder<DashboardActivity>() {

        abstract operator fun plus(component: ApplicationComponent): Builder

        fun inject(activity: DashboardActivity) {

            plus(activity.provideApplication().applicationComponent)

            create(activity)
                    .inject(activity)
        }
    }

    // endregion
}