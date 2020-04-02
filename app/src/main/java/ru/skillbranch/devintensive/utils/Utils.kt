package ru.skillbranch.devintensive.utils


object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {

        val parts = fullName?.split(" ")

        var firstName = parts?.getOrNull(0)?.also {
            it.trim()
        }

        var lastName = parts?.getOrNull(1)?.also {
            it.trim()
        }

        if (firstName.isNullOrBlank()) firstName = null
        if (lastName.isNullOrBlank()) lastName = null

        return firstName to lastName
    }


    fun transliteration(payload: String, divider: String = " "): String {
        return payload.replace(Regex("[абвгдеёжзийклмнопрстуфхцчъьыэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЬЭЮЯ]")) {
            when (it.value) {
                "а" -> "a"
                "б" -> "b"
                "в" -> "v"
                "г" -> "g"
                "д" -> "d"
                "е" -> "e"
                "ё" -> "e"
                "ж" -> "zh"
                "з" -> "z"
                "и" -> "i"
                "й" -> "i"
                "к" -> "k"
                "л" -> "l"
                "м" -> "m"
                "н" -> "n"
                "о" -> "o"
                "п" -> "p"
                "р" -> "r"
                "с" -> "s"
                "т" -> "t"
                "у" -> "u"
                "ф" -> "f"
                "х" -> "h"
                "ц" -> "c"
                "ч" -> "ch"
                "ш" -> "sh"
                "щ" -> "sh"
                "ъ" -> ""
                "ы" -> "i"
                "ь" -> ""
                "э" -> "e"
                "ю" -> "yu"
                "я" -> "ya"
                "а".toUpperCase() -> "a".toUpperCase()
                "б".toUpperCase() -> "b".toUpperCase()
                "в".toUpperCase() -> "v".toUpperCase()
                "г".toUpperCase() -> "g".toUpperCase()
                "д".toUpperCase() -> "d".toUpperCase()
                "е".toUpperCase() -> "e".toUpperCase()
                "ё".toUpperCase() -> "e".toUpperCase()
                "ж".toUpperCase() -> "zh".toUpperCase()
                "з".toUpperCase() -> "z".toUpperCase()
                "и".toUpperCase() -> "i".toUpperCase()
                "й".toUpperCase() -> "i".toUpperCase()
                "к".toUpperCase() -> "k".toUpperCase()
                "л".toUpperCase() -> "l".toUpperCase()
                "м".toUpperCase() -> "m".toUpperCase()
                "н".toUpperCase() -> "n".toUpperCase()
                "о".toUpperCase() -> "o".toUpperCase()
                "п".toUpperCase() -> "p".toUpperCase()
                "р".toUpperCase() -> "r".toUpperCase()
                "с".toUpperCase() -> "s".toUpperCase()
                "т".toUpperCase() -> "t".toUpperCase()
                "у".toUpperCase() -> "u".toUpperCase()
                "ф".toUpperCase() -> "f".toUpperCase()
                "х".toUpperCase() -> "h".toUpperCase()
                "ц".toUpperCase() -> "c".toUpperCase()
                "ч".toUpperCase() -> "ch".toUpperCase()
                "ш".toUpperCase() -> "sh".toUpperCase()
                "щ".toUpperCase() -> "sh".toUpperCase()
                "ъ".toUpperCase() -> "".toUpperCase()
                "ы".toUpperCase() -> "i".toUpperCase()
                "ь".toUpperCase() -> "".toUpperCase()
                "э".toUpperCase() -> "e".toUpperCase()
                "ю".toUpperCase() -> "yu".toUpperCase()
                "я".toUpperCase() -> "ya".toUpperCase()
                else -> divider

            }
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val builder = StringBuilder()

        if (firstName.isNullOrBlank() && lastName.isNullOrBlank()) {
            return null
        }

        firstName?.let {
            builder.append(it[0].toUpperCase())
        }

        lastName?.let {
            builder.append(it[0].toUpperCase())
        }

        return builder.toString()
    }

}




