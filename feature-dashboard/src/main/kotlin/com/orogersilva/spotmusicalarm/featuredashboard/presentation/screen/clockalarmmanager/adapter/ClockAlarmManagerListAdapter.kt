package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.clockalarmmanager.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.orogersilva.spotmusicalarm.dashboarddomain.model.ClockAlarm
import com.orogersilva.spotmusicalarm.featuredashboard.R
import com.orogersilva.spotmusicalarm.featuredashboard.databinding.ItemClockAlarmBinding
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.clockalarmmanager.ClockAlarmManagerViewModel
import javax.inject.Inject

class ClockAlarmManagerListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // region PROPERTIES

    private val clockAlarms = mutableListOf<ClockAlarm>()

    // endregion

    // region PUBLIC METHODS

    fun setData(data: List<ClockAlarm>) {

        clockAlarms.clear()
        clockAlarms.addAll(data)

        notifyDataSetChanged()
    }

    // endregion

    // region OVERRIDED METHODS

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_clock_alarm, parent, false))

    override fun getItemCount(): Int = clockAlarms.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val clockAlarm = clockAlarms[position]

        (holder as ViewHolder).bind(clockAlarm)
    }

    // endregion

    // region VIEW HOLDERS

    inner class ViewHolder(private val binding: ItemClockAlarmBinding) : RecyclerView.ViewHolder(binding.root) {

        // region PUBLIC METHODS

        fun bind(clockAlarm: ClockAlarm) {

            binding.clockAlarm = clockAlarm
            binding.executePendingBindings()
        }

        // endregion
    }

    // endregion
}