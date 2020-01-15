package com.example.tchoutchou.logic.train

import android.animation.ValueAnimator
import android.view.Display
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginBottom
import com.example.tchoutchou.constants.*
import com.example.tchoutchou.logic.character.Character
import com.example.tchoutchou.utils.Size
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import java.lang.Exception

class Train private constructor(val driver: Character, val stats: Statistics, currentStation: Station) {

    val stationHistory = mutableListOf<Station>()
    var currentStation: Station
        get() = stationHistory[stationHistory.size - 1]
        set(value: Station) {
            stationHistory.add(value)
        }

    val railcars = mutableListOf<Railcar>()
    val upgrades = HashMap<Upgrades, Upgrade>()
    lateinit var trainElements: TrainElements
    lateinit var display: Display

    init {
        stationHistory.add(currentStation)

        upgrades.set(
            Upgrades.POWER,
            Upgrade(
                Upgrades.POWER,
                1.0
            )
        )
        upgrades.set(
            Upgrades.RAILCAR,
            Upgrade(
                Upgrades.RAILCAR,
                1.0
            )
        )
        upgrades.set(
            Upgrades.RESISTANCE,
            Upgrade(
                Upgrades.RESISTANCE,
                1.0
            )
        )
        upgrades.set(
            Upgrades.WEAPON,
            Upgrade(
                Upgrades.WEAPON,
                0.0
            )
        )

        for (i in 0 until (upgrades[Upgrades.RAILCAR]?.value?.toInt() ?: 1)) {
            railcars.add(Railcar(5))
        }
    }

    data class Builder (var driver: Character = Character(), var maxFuel: Int =  10, var stats: Statistics = Statistics.Builder().build(), var currentStation: Station = Station("")) {
        fun driver(driver: Character) = apply { this.driver = driver }
        fun maxFuel(maxFuel: Int) = apply { this.maxFuel = maxFuel }
        fun stats(stats: Statistics) = apply { this.stats = stats }
        fun currentStation(currentStation: Station) = apply { this.currentStation = currentStation }

        fun build(): Train {
            if (driver == null) {
                throw Exception("The owner of the train is not defined, please provide one")
            }

            return Train(
                driver,
                stats,
                currentStation
            )
        }
    }

    fun upgrade(upgrade: Upgrade) {
        upgrades[upgrade.type] = upgrade
    }

    fun canAddPassenger(number: Int = 1): Boolean {
        var freeCount = 0
        for (railcar in railcars) {
            if (railcar.slots.hasFreeSlot()) freeCount++
            if (freeCount == number) return true
        }

        return false
    }

    fun addPassenger(passenger: Character): Boolean {
        for (railcar in railcars) {
            if (railcar.canAddPassenger()) {
                railcar.addPassenger(passenger)
                return true
            }
        }

        return false
    }

    fun setElements(display: Display, trainElements: TrainElements) {
        this.display = display
        this.trainElements = trainElements

        val windowHeight =  Size.getHeightFromDisplay(display).toDouble()
        val backgroundHeightRatio = backgroundHeight / windowHeight


        trainElements.train.layoutParams.height = (windowHeight * 0.5).toInt()
        trainElements.train.layoutParams.width = (trainElements.train.layoutParams.height.toDouble() * trainRatio).toInt()

        trainElements.train.animate().duration = 0
        trainElements.train.animate().translationY(
            - ((backgroundHeightRatio + 1) * trackHeight).toFloat()
        ).translationX(- trainElements.train.layoutParams.width.toFloat())

        val windowTrainWidthRatio = trainElements.train.layoutParams.width.toDouble() / Size.getWidthFromDisplay(display).toDouble()


        trainElements.smoke.layoutParams.width = (fireplaceWidth * (1 + windowTrainWidthRatio)).toInt() * 10
        trainElements.smoke.layoutParams.height = trainElements.smoke.layoutParams.width
        setSmokePosition()

    }

    suspend fun setTrainOutLeft() {
        trainElements.train.post {
            trainElements.train.animate()
                .setDuration(0)
                .translationX(- trainElements.train.width.toFloat())
        }

        delay(100)
    }

    suspend fun animateFromPositionToOutsideRight() {
        val animationTime = 1750L
        trainElements.train.post {
            trainElements
                .train
                .animate()
                .setDuration(animationTime)
                .setInterpolator(AccelerateInterpolator())
                .translationX(Size.getWidthFromDisplay(display).toFloat() + trainElements.train.layoutParams.width.toFloat())
                .setUpdateListener {
                    setSmokePosition()
                }

        }

        delay(animationTime)
    }

    suspend fun animateFromOutsideToLeft(blocking: Boolean = true) {
            setTrainOutLeft()
            val animationTime = 1000L
            trainElements.train.post {
                trainElements
                    .train
                    .animate()
                    .setDuration(animationTime)
                    .setInterpolator(DecelerateInterpolator())
                    .translationX(100f)
                    .setUpdateListener {
                        setSmokePosition()
                    }
            }

            if (blocking) delay(animationTime)
    }

    fun setSmokePosition() {
        trainElements.smoke.post {
            trainElements
                .smoke
                .animate()
                .setDuration(0)
                .translationX(
                    (trainElements.train.translationX + fireplaceWidthRatio * trainElements.train.layoutParams.width - trainElements.smoke.layoutParams.width / 3.4).toFloat()
                )
                .translationY((- trainElements.train.translationY - trainElements.train.layoutParams.height + trainElements.train.layoutParams.height * (1 - fireplaceHeightRatio)).toFloat())
                .setUpdateListener {
                    setSmokePosition()
                }
        }
    }

    suspend fun animateFromOutsideLeftToOutsideRight() {
        val animationTime = 1000L
        setTrainOutLeft()
        trainElements.train.post {
            trainElements
                .train
                .animate()
                .setDuration(animationTime)
                .translationX(
                    Size.getWidthFromDisplay(display).toFloat() + trainElements.train.layoutParams.width.toFloat()
                )
                .setUpdateListener {
                    setSmokePosition()
                }
        }

        delay(animationTime)
    }
}