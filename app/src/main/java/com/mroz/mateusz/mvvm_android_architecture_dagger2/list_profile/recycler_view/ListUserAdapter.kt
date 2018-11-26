package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.recycler_view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mroz.mateusz.mvvm_android_architecture_dagger2.databinding.UserItemBinding
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.Results


class ListUserAdapter(var listener: ClickListener): RecyclerView.Adapter<ListUserAdapter.ListUserViewHolder>() {
    private var listUsers: MutableList<Results> = ArrayList<Results>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListUserViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        val view = UserItemBinding.inflate(inflater)
        return ListUserViewHolder(view, listener)
    }

    override fun getItemCount(): Int = listUsers.size

    override fun onBindViewHolder(p0: ListUserViewHolder, p1: Int) = p0.bind(listUsers.get(p1))

    fun setListUsers(updateListUsers: List<Results>) {
        listUsers.clear()
        listUsers.addAll(updateListUsers)
        notifyDataSetChanged()
    }

    inner class ListUserViewHolder(val binding: UserItemBinding, clickListener: ClickListener): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Results) {
            binding.user = item
            binding.executePendingBindings()
        }
    }

    interface ClickListener {
        fun onClick(user: Results)
    }
}