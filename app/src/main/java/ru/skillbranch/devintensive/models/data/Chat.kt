package ru.skillbranch.devintensive.models.data

import ru.skillbranch.devintensive.extensions.shortFormat
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class Chat(
    val id: String,
    val title: String,
    var members: MutableList<User> = mutableListOf(),
    var messages: MutableList<BaseMessage> = mutableListOf(),
    var isArchived: Boolean = false
) {


    fun unreadableMessageCount() : Int{
        return (0..10).random()
    }

    private fun lastMessageDate(): Date?{

        return Date()
    }

    private fun lastMessageShort(): Pair<String, String>{
        return "Сообщений ещё нет" to "@John_Doe"
    }

    private fun isSingle() : Boolean = members.size == 1

    fun toChatItem() : ChatItem{
        return if(isSingle()){
            val user = members.first()

            ChatItem(
                id,
                user.avatar,
                Utils.toInitials(user.firstName, user.lastName) ?: "??",
                "${user.firstName ?: ""} ${user.lastName ?: ""}",
                lastMessageShort().first,
                unreadableMessageCount(),
                lastMessageDate()?.shortFormat(),
                user.isOnline

            )
        }
        else{
            ChatItem(
                id,
                null,
                "",
                title,
                lastMessageShort().first,
                unreadableMessageCount(),
                lastMessageDate()?.shortFormat(),
                false,
                ChatType.GROUP,
                if((0..1).random() == 0) lastMessageShort().second else null
            )
        }
    }
}

enum class ChatType{
    SINGLE,
    GROUP,
    ARCHIVE
}