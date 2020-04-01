package ru.skillbranch.devintensive.utils

import ru.skillbranch.devintensive.models.User

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
        TODO("Not yet implemented")
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        TODO("Not yet implemented")
    }
}