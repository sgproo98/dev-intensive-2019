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
        return ""
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val builder = StringBuilder()

        if(firstName.isNullOrBlank() && lastName.isNullOrBlank()){
            return null
        }

        firstName?.let {
            builder.append(it[0].toUpperCase())
        }

        lastName?.let{
            builder.append(it[0].toUpperCase())
        }

        return builder.toString()
    }
}