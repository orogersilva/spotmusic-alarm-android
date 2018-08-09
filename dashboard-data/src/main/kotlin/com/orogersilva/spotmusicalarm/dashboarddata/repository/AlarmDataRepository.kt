package com.orogersilva.spotmusicalarm.dashboarddata.repository

import com.orogersilva.spotmusicalarm.dashboarddata.contract.AlarmDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.contract.TrackDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.mapper.ArtistMapper
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Track
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.AlarmRepository
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class AlarmDataRepository @Inject constructor(
        private val alarmLocalDataSource: AlarmDataContract.Local,
        private val trackLocalDataSource: TrackDataContract.Local
) : AlarmRepository {

    // region OVERRIDED METHODS

    override fun saveAlarm(
            dateTime: Date,
            isEnabled: Boolean,
            track: Track?
    ): Single<Long> {

        val alarmId: Long

        if (track == null) {

            alarmId = alarmLocalDataSource.saveAlarm(dateTime, isEnabled, null)
        } else {

            trackLocalDataSource.saveTrack(track.id, track.name,
                    ArtistMapper.transformArtistsToArtistEntities(track.artists))

            alarmId = alarmLocalDataSource.saveAlarm(dateTime, isEnabled, track.id)
        }

        return Single.just(alarmId)
    }

    // endregion
}