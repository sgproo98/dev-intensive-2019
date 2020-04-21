package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.data.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

fun User.toUserView(): UserView {

    val nickName = Utils.transliteration("$firstName $lastName")
    val initial =  Utils.toInitials(firstName, lastName)
    val status = when {
        lastVisit == null -> "Ещё ни разу не был онлайн."
        isOnline -> "online"
        else -> "Последний раз был ${lastVisit.humanizeDiff(
            Date()
        )}"
    }

    return UserView(
        id,
        fullName = "$firstName $lastName",
        nickName = nickName,
        initials = initial,
        avatar = avatar,
        status = status
    )
}

