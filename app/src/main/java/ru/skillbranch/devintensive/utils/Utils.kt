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

        val builder = StringBuilder()

        payload.forEach {
            when (it.toString()) {
                "а" -> builder.append("a")
                "б" -> builder.append("b")
                "в" -> builder.append("v")
                "г" -> builder.append("g")
                "д" -> builder.append("d")
                "е" -> builder.append("e")
                "ё" -> builder.append("e")
                "ж" -> builder.append("zh")
                "з" -> builder.append("z")
                "и" -> builder.append("i")
                "й" -> builder.append("i")
                "к" -> builder.append("k")
                "л" -> builder.append("l")
                "м" -> builder.append("m")
                "н" -> builder.append("n")
                "о" -> builder.append("o")
                "п" -> builder.append("p")
                "р" -> builder.append("r")
                "с" -> builder.append("s")
                "т" -> builder.append("t")
                "у" -> builder.append("u")
                "ф" -> builder.append("f")
                "х" -> builder.append("h")
                "ц" -> builder.append("c")
                "ч" -> builder.append("ch")
                "ш" -> builder.append("sh")
                "щ" -> builder.append("sh")
                "ъ" -> builder.append("")
                "ы" -> builder.append("i")
                "ь" -> builder.append("")
                "э" -> builder.append("e")
                "ю" -> builder.append("yu")
                "я" -> builder.append("ya")
                "а".toUpperCase() -> builder.append("a".toUpperCase())
                "б".toUpperCase() -> builder.append("b".toUpperCase())
                "в".toUpperCase() -> builder.append("v".toUpperCase())
                "г".toUpperCase() -> builder.append("g".toUpperCase())
                "д".toUpperCase() -> builder.append("d".toUpperCase())
                "е".toUpperCase() -> builder.append("e".toUpperCase())
                "ё".toUpperCase() -> builder.append("e".toUpperCase())
                "ж".toUpperCase() -> builder.append("zh".toUpperCase())
                "з".toUpperCase() -> builder.append("z".toUpperCase())
                "и".toUpperCase() -> builder.append("i".toUpperCase())
                "й".toUpperCase() -> builder.append("i".toUpperCase())
                "к".toUpperCase() -> builder.append("k".toUpperCase())
                "л".toUpperCase() -> builder.append("l".toUpperCase())
                "м".toUpperCase() -> builder.append("m".toUpperCase())
                "н".toUpperCase() -> builder.append("n".toUpperCase())
                "о".toUpperCase() -> builder.append("o".toUpperCase())
                "п".toUpperCase() -> builder.append("p".toUpperCase())
                "р".toUpperCase() -> builder.append("r".toUpperCase())
                "с".toUpperCase() -> builder.append("s".toUpperCase())
                "т".toUpperCase() -> builder.append("t".toUpperCase())
                "у".toUpperCase() -> builder.append("u".toUpperCase())
                "ф".toUpperCase() -> builder.append("f".toUpperCase())
                "х".toUpperCase() -> builder.append("h".toUpperCase())
                "ц".toUpperCase() -> builder.append("c".toUpperCase())
                "ч".toUpperCase() -> builder.append("ch".toUpperCase())
                "ш".toUpperCase() -> builder.append("sh".toUpperCase())
                "щ".toUpperCase() -> builder.append("sh".toUpperCase())
                "ъ".toUpperCase() -> builder.append("".toUpperCase())
                "ы".toUpperCase() -> builder.append("i".toUpperCase())
                "ь".toUpperCase() -> builder.append("".toUpperCase())
                "э".toUpperCase() -> builder.append("e".toUpperCase())
                "ю".toUpperCase() -> builder.append("yu".toUpperCase())
                "я".toUpperCase() -> builder.append("ya".toUpperCase())
                " " -> builder.append(divider)
                else -> builder.append(it)
            }
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




