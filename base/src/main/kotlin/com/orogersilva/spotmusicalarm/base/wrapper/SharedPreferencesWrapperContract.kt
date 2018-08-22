package com.orogersilva.spotmusicalarm.base.wrapper

interface SharedPreferencesWrapperContract {

    // region METHODS

    fun getString(key: String, defaultValue: String?): String

    fun putString(key: String, value: String?)

    fun commit(): Boolean

    // endregion
}