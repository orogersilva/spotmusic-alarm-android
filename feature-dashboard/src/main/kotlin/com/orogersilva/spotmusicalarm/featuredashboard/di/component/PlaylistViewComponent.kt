package com.orogersilva.spotmusicalarm.featuredashboard.di.component

import com.orogersilva.spotmusicalarm.base.di.component.ApplicationComponent
import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.PlaylistRepositoryModule
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.PreferencesModule
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.UserRepositoryModule
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.PlaylistRepository
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.UserRepository
import com.orogersilva.spotmusicalarm.featuredashboard.di.module.PlaylistViewModelModule
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.view.PlaylistActivity
import dagger.Component
import dagger.android.AndroidInjector

@ActivityScope
@Component(
        modules = [
            PlaylistRepositoryModule::class,
            PlaylistViewModelModule::class,
            PreferencesModule::class,
            UserRepositoryModule::class
        ],
        dependencies = [ApplicationComponent::class]
)
interface PlaylistViewComponent : AndroidInjector<PlaylistActivity> {

    // region BUILDERS

    @Component.Builder abstract class Builder : AndroidInjector.Builder<PlaylistActivity>() {

        abstract operator fun plus(component: ApplicationComponent): Builder

        fun inject(activity: PlaylistActivity) {

            plus(activity.provideApplication().applicationComponent)

            create(activity)
                    .inject(activity)
        }
    }

    // endregion
}