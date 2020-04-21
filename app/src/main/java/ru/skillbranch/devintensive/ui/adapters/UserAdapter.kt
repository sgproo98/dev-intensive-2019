package ru.skillbranch.devintensive.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user_list.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.data.UserItem

class UserAdapter(private val listener: (UserItem) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var items: List<UserItem> = listOf()

    fun update(data: List<UserItem>) {
        val diffCallback = object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                items[oldItemPosition].id == data[newItemPosition].id

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                items[oldItemPosition].hashCode() == data[newItemPosition].hashCode()

            override fun getOldListSize() = items.size

            override fun getNewListSize() = data.size

        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items = data
        diffResult.dispatchUpdatesTo(this)
    }


    inner class UserViewHolder(convertView: View) : RecyclerView.ViewHolder(convertView),
        LayoutContainer {
        override val containerView: View?
            get() = itemView


        fun bind(user: UserItem, listener: (UserItem) -> Unit) {
            if (user.avatar != null) {
                Glide.with(itemView)
                    .load(user.avatar)
                    .into(iv_avatar_user)
            } else {
                Glide.with(itemView)
                    .clear(iv_avatar_user)
            }

            sv_indicator.visibility = if (user.isOnline) View.VISIBLE else View.GONE
            tv_user_name.text = user.fullName
            tv_last_activity.text = user.lastActivity
            iv_selected.visibility =
                if (user.isSelected) View.VISIBLE else View.GONE
            itemView.setOnClickListener {
                listener.invoke(user)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_user_list, parent, false)
        return UserViewHolder(v)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(items[position], listener)
}