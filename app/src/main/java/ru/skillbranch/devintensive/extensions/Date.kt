package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

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

    val value = this.time - date.time

    return when {
        value > TimeUnit.DAYS.toMillis(360) -> "более года назад"
        value > TimeUnit.HOURS.toMillis(26) -> getWordOfValues(
            TimeUnit.MILLISECONDS.toDays(value),
            TimeUnits.DAY
        )
        value >= TimeUnit.HOURS.toMillis(22) -> "день назад"
        value > TimeUnit.MINUTES.toMillis(75) -> getWordOfValues(
            TimeUnit.MILLISECONDS.toHours(value),
            TimeUnits.HOUR
        )
        value > TimeUnit.MINUTES.toMillis(45) -> "час назад"
        value > TimeUnit.SECONDS.toMillis(75) -> getWordOfValues(
            TimeUnit.MILLISECONDS.toMinutes(
                value
            ), TimeUnits.MINUTE
        )
        value > TimeUnit.SECONDS.toMillis(45) -> "минуту назад"
        value > TimeUnit.SECONDS.toMillis(1) -> "несколько секунд назад"
        else -> "только что"
    }
}

private fun getWordOfValues(value: Long, type: TimeUnits): String {
    val mod = value % 10


    return when (type) {
        TimeUnits.SECOND -> "$value ${TimeUnits.SECOND.plural(mod)}"
        TimeUnits.MINUTE -> "$value ${TimeUnits.MINUTE.plural(mod)}"
        TimeUnits.HOUR -> "$value ${TimeUnits.HOUR.plural(mod)}"
        TimeUnits.DAY -> "$value ${TimeUnits.DAY.plural(mod)}"
    }

}

enum class TimeUnits {
    SECOND {
        override fun plural(value: Long): String {
            return when (value) {
                1L -> "секунда"
                2L, 3L, 4L -> "секунды"
                else -> "секунд"
            }
        }

    },
    MINUTE {
        override fun plural(value: Long): String {
            return when (value) {
                1L -> "минута"
                2L, 3L, 4L -> "минуты"
                else -> "минут"
            }
        }
    },
    HOUR {
        override fun plural(value: Long): String {
            return when (value) {
                1L -> "час"
                2L, 3L, 4L -> "часа"
                else -> "часов"
            }
        }
    },
    DAY {
        override fun plural(value: Long): String {
            return when (value) {
                1L -> "день"
                2L, 3L, 4L -> "дня"
                else -> "дней"
            }
        }
    };

    abstract fun plural(value: Long): String
}