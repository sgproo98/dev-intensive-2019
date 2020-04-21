package ru.skillbranch.devintensive.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.extensions.mutableLiveData
import ru.skillbranch.devintensive.models.data.UserItem
import ru.skillbranch.devintensive.repositories.GroupRepository

class GroupViewModel : ViewModel() {
    private val query = mutableLiveData("")
    private val groupRepository = GroupRepository
    private var userItems = mutableLiveData(loadUsers())
    private val selectedItems = Transformations.map(userItems) { users ->
        users.filter { it.isSelected }
    }

    fun getUsersData(): LiveData<List<UserItem>> {
        val result = MediatorLiveData<List<UserItem>>()

        val filter = {
            val queryString = query.value!!
            val users = userItems.value!!

            result.value = if (queryString.isEmpty()) users
            else users.filter {
                it.fullName.contains(queryString, true)
            }
        }

        result.addSource(userItems) {filter.invoke()}

        result.addSource(query) {filter.invoke()}

        return result
    }

    fun getSelected() = selectedItems

    fun handleSelectedItem(userId: String) {
        userItems.value = userItems.value!!.map {
            if (it.id == userId) it.copy(isSelected = !it.isSelected)
            else it
        }
    }


    fun handleRemoveChip(userId: String) {
        userItems.value = userItems.value!!.map {
            if (it.id == userId) it.copy(isSelected = false)
            else it
        }
    }

    fun handleSearchQuery(text: String) {
        query.value = text
    }

    private fun loadUsers(): List<UserItem> = groupRepository.loadUsers().map { it.toUserItem() }

    fun handleCreateGroup() {
        groupRepository.createChat(selectedItems.value!!)
    }
}
