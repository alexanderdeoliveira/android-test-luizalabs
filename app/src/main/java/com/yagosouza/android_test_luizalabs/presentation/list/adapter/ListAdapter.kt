package com.yagosouza.android_test_luizalabs.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.yagosouza.android_test_luizalabs.core.extensions.DefaultDiffCallback

import com.yagosouza.android_test_luizalabs.databinding.FragmentListItemBinding
import com.yagosouza.android_test_luizalabs.domain.model.Gist

class ListAdapter: ListAdapter<Gist, ListViewHolder>(DefaultDiffCallback<Gist>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentListItemBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val gist = getItem(position)
        holder.bind(gist)
    }

}