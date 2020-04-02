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

        val (firstName, lastName) = parseFullName(payload)

        val builder = StringBuilder()

        val newFirstName = firstName?.let {
            builder.append(getReplaceString(it).capitalize() + divider)
        }

        val newLastName = lastName?.let {
            builder.append(getReplaceString(it).capitalize())
        }

        return builder.toString()
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

private fun getReplaceString(name: String): String {
    return name.replace(Regex("[абвгдеёжзийклмнопрстуфхцчъьыэюя]")) {
        when (it.value.toLowerCase()) {
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
            else -> ""

        }
    }
}


