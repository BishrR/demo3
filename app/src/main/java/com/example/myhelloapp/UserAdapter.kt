package com.example.myhelloapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myhelloapp.databinding.ItemUserBinding


class UserAdapter(private val clickListener: (View, Int) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var users: List<User> = emptyList()

    inner class UserViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                clickListener(it, adapterPosition)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = mDiffer.currentList[position]
        holder.binding.apply {
            nameTextView.text = user.name
            ageTextView.text = "Age : ${user.age}"
        }
        holder.setIsRecyclable(false)

    }

    override fun getItemCount() = mDiffer.currentList.size

    fun addUsers(newUser: User) {
        val updatedUsers = ArrayList(mDiffer.currentList)
        updatedUsers.add(0, newUser)
        submitList(updatedUsers)
    }

    fun addUsers(newUsers: List<User>) {
        val updatedUsers = ArrayList(mDiffer.currentList)
        updatedUsers.addAll(0, newUsers)
        submitList(updatedUsers)
    }

    object UsersDiff : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    private val mDiffer = AsyncListDiffer(this, UsersDiff)

    fun submitList(list: MutableList<User>) {
        mDiffer.submitList(list)
    }

    fun updateUsers(newUsers: List<User>) {
        val diffResult = DiffUtil.calculateDiff(
            object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return users.size
                }

                override fun getNewListSize(): Int {
                    return newUsers.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return users[oldItemPosition].id == newUsers[newItemPosition].id
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    return users[oldItemPosition] == newUsers[newItemPosition]
                }
            }
        )
        users = newUsers
        diffResult.dispatchUpdatesTo(this)
    }
}