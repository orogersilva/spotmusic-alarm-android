package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.orogersilva.spotmusicalarm.base.SpotmusicAlarmApplication

abstract class BaseActivity : AppCompatActivity(), LifecycleOwner {

    // region FIELDS

    private lateinit var lifecycleRegistry: LifecycleRegistry

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
    }

    override fun onDestroy() {

        super.onDestroy()

        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
    }

    // endregion
}