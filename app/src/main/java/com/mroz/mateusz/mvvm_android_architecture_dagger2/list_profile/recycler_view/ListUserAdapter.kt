package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.recycler_view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mroz.mateusz.mvvm_android_architecture_dagger2.databinding.UserItemBinding
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.Results
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.User


class ListUserAdapter(private val users: User): RecyclerView.Adapter<ListUserAdapter.ListUserViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListUserViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        val view = UserItemBinding.inflate(inflater)
        return ListUserViewHolder(view)
    }

    override fun getItemCount(): Int = users.listUsers!!.size

    override fun onBindViewHolder(p0: ListUserViewHolder, p1: Int) = p0.bind(users.listUsers!!.get(p1))

    inner class ListUserViewHolder(val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Results) {
            with(binding) {
                name.text = item.name!!.first
                lastName.text = item.name!!.last
                age.text = item.dateOfBirthday!!.age.toString()
            }
            binding.executePendingBindings()
        }
    }
}