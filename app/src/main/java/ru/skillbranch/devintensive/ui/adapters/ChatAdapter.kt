package ru.skillbranch.devintensive.ui.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_chat_group.*
import kotlinx.android.synthetic.main.item_chat_single.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.data.ChatItem
import ru.skillbranch.devintensive.models.data.ChatType


class ChatAdapter(private val listener: (ChatItem) -> Unit) :
    RecyclerView.Adapter<ChatAdapter.ChatItemViewHolder>() {

    companion object {
        private const val ARCHIVE_TYPE = 0
        private const val SINGLE_TYPE = 1
        private const val GROUP_TYPE = 2
    }

    override fun getItemViewType(position: Int) = when (items[position].chatType) {
        ChatType.ARCHIVE -> ARCHIVE_TYPE
        ChatType.SINGLE -> SINGLE_TYPE
        ChatType.GROUP -> GROUP_TYPE
    }

    var items: List<ChatItem> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            SINGLE_TYPE -> SingleViewHolder(
                inflater.inflate(
                    R.layout.item_chat_single,
                    parent,
                    false
                )
            )
            GROUP_TYPE -> GroupViewHolder(inflater.inflate(R.layout.item_chat_group, parent, false))
            else -> SingleViewHolder(inflater.inflate(R.layout.item_chat_single, parent, false))

        }
/*        val convertView = inflater.inflate(R.layout.item_chat_single, parent, false)
        Log.d("M_ChatAdapter", "onCreateViewHolder")
        return SingleViewHolder(convertView)*/
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ChatItemViewHolder, position: Int) {
        Log.d("M_ChatAdapter", "onBindViewHolder $position")
        holder.bind(items[position], listener)
    }

    fun updateData(data: List<ChatItem>) {
        Log.d(
            "M_ChatAdapter",
            "update data adapter - new data ${data.size} hash: ${data.hashCode()}" +
                    "old data ${items.size} hash: ${items.hashCode()}"
        )
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

    inner class SingleViewHolder(convertView: View) : ChatItemViewHolder(convertView),
        LayoutContainer, ItemTouchViewHolder {
        override val containerView: View?
            get() = itemView


        override fun bind(item: ChatItem, listener: (ChatItem) -> Unit) {
            tv_title_single.text = item.title

            if (item.avatar == null) {
                Glide.with(itemView)
                    .clear(iv_avatar_single)
            } else {
                Glide.with(itemView)
                    .load(item.avatar)
                    .into(iv_avatar_single)
            }

            sv_indicator.visibility = if (item.isOnline) View.VISIBLE else View.GONE

            with(tv_date_single) {
                if (item.lastMessageDate != null) View.VISIBLE else View.GONE
                text = item.lastMessageDate
            }

            with(tv_counter_single) {
                if (item.messageCount > 0) View.VISIBLE else View.GONE
                text = item.messageCount.toString()
            }

            tv_title_single.text = item.title
            tv_message_single.text = item.shortDescription
            itemView.setOnClickListener {
                listener.invoke(item)
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemCleared() {
            itemView.setBackgroundColor(Color.WHITE)
        }


    }

    abstract inner class ChatItemViewHolder(convertView: View) :
        RecyclerView.ViewHolder(convertView), LayoutContainer {
        override val containerView: View?
            get() = itemView

        abstract fun bind(item: ChatItem, listener: (ChatItem) -> Unit)
    }

    inner class GroupViewHolder(convertView: View) : ChatItemViewHolder(convertView),
        LayoutContainer, ItemTouchViewHolder {
        override val containerView: View?
            get() = itemView


        override fun bind(item: ChatItem, listener: (ChatItem) -> Unit) {
            tv_title_group.text = item.title

            if (item.avatar == null) {
                Glide.with(itemView)
                    .clear(iv_avatar_group)
            } else {
                Glide.with(itemView)
                    .load(item.avatar)
                    .into(iv_avatar_group)
            }


            with(tv_date_group) {
                if (item.lastMessageDate != null) View.VISIBLE else View.GONE
                text = item.lastMessageDate
            }

            with(tv_counter_group) {
                if (item.messageCount > 0) View.VISIBLE else View.GONE
                text = item.messageCount.toString()
            }

            tv_message_group.text = item.shortDescription
            with(tv_message_author) {
                if (item.messageCount > 0) View.VISIBLE else View.GONE
                text = item.author
            }

            itemView.setOnClickListener {
                listener.invoke(item)
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemCleared() {
            itemView.setBackgroundColor(Color.WHITE)
        }


    }
}