package ru.skillbranch.devintensive.repositories

import ru.skillbranch.devintensive.models.data.Chat
import ru.skillbranch.devintensive.models.data.User
import ru.skillbranch.devintensive.models.data.UserItem
import ru.skillbranch.devintensive.models.data.managers.CacheManager
import ru.skillbranch.devintensive.utils.DataGenerator

object GroupRepository {
    fun loadUsers(): List<User>  = DataGenerator.stabUsers
    fun createChat(items: List<UserItem>) {
        val ids = items.map { it.id }
        val users = CacheManager.findUsersByIds(ids)
        val title = users.map { it.firstName }.joinToString { ", " }
        val chat = Chat(CacheManager.nextChatId(), title, users.toMutableList())
        CacheManager.insertChat(chat)
    }
}