package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, utils: TimeUnits = TimeUnits.SECOND): Date {

    var time = this.time

    time += when (utils) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time

    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {

    val value = abs(date.time - this.time)

    val result = when {
        value > TimeUnit.DAYS.toMillis(360) -> {
            return if(date.time < this.time)
                "более чем через год"
            else{
                "более года назад"
            }
        }
        value > TimeUnit.HOURS.toMillis(26) -> getWordOfValues(
            TimeUnit.MILLISECONDS.toDays(value),
            TimeUnits.DAY
        )
        value >= TimeUnit.HOURS.toMillis(22) -> "день"
        value > TimeUnit.MINUTES.toMillis(75) -> getWordOfValues(
            TimeUnit.MILLISECONDS.toHours(value),
            TimeUnits.HOUR
        )
        value > TimeUnit.MINUTES.toMillis(45) -> "час"
        value > TimeUnit.SECONDS.toMillis(75) -> getWordOfValues(
            TimeUnit.MILLISECONDS.toMinutes(
                value
            ), TimeUnits.MINUTE
        )
        value > TimeUnit.SECONDS.toMillis(45) -> "минуту"
        value > TimeUnit.SECONDS.toMillis(1) -> "несколько секунд"
        else -> return "только что"
    }
    return if(date.time < this.time) "через $result" else "$result назад"
}

private fun getWordOfValues(value: Long, type: TimeUnits): String {

    return when (type) {
        TimeUnits.SECOND -> TimeUnits.SECOND.plural(value)
        TimeUnits.MINUTE -> TimeUnits.MINUTE.plural(value)
        TimeUnits.HOUR -> TimeUnits.HOUR.plural(value)
        TimeUnits.DAY -> TimeUnits.DAY.plural(value)
    }

}

enum class TimeUnits {
    SECOND {
        override fun plural(value: Long): String {
            return when (value % 10) {
                1L -> "$value секунду"
                2L, 3L, 4L -> "$value секунды"
                else -> "$value секунд"
            }
        }

    },
    MINUTE {
        override fun plural(value: Long): String {
            return when (value % 10) {
                1L -> "$value минуту"
                2L, 3L, 4L -> "$value минуты"
                else -> "$value минут"
            }
        }
    },
    HOUR {
        override fun plural(value: Long): String {
            return when (value % 10) {
                1L -> "$value час"
                2L, 3L, 4L -> "$value часа"
                else -> "$value часов"
            }
        }
    },
    DAY {
        override fun plural(value: Long): String {
            return when (value % 10) {
                1L -> "$value день"
                2L, 3L, 4L -> "$value дня"
                else -> "$value дней"
            }
        }
    };

    abstract fun plural(value: Long): String
}