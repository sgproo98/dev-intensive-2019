package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false
) {
    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(id, "John", "Doe")

    init {
        println(
            "It's Alive!!!\n" +
                    "${if (lastName === "Doe") "hIS NAME IS $firstName $lastName" else "And his name is $firstName $lastName!!!"}\n"
        )
    }

    fun printMe() = println(
        """
        id: $id
        firstName: $firstName
        lastName: $lastName
        avatar: $avatar
        rating: $rating
        respect: $respect
        lastVisit: $lastVisit
        isOnline: $isOnline
    """.trimIndent()
    )

    companion object Factory {
        private var lastId = -1
        fun makeUser(fullName: String?): User {
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName)

            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }

    class Builder{
        var id: String = ""
        var firstName: String? = null
        var lastName: String? = null
        var avatar: String? = null
        var rating: Int = 0
        var respect: Int = 0
        var lastVisit: Date? = null
        var isOnline: Boolean = false

        fun id(s: String) = apply { this.id = s }
        fun firstName(s: String) = apply { this.firstName = s }
        fun lastName(s: String) = apply { this.lastName = s }
        fun avatar(s: String) = apply { this.avatar = s }
        fun rating(n: Int) = apply { this.rating = n }
        fun respect(n: Int) = apply { this.respect = n }
        fun lastVisit(d: Date) = apply { this.lastVisit = d }
        fun isOnline(b: Boolean) = apply { this.isOnline = b }
        fun build() = User(id, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)

    }
}

