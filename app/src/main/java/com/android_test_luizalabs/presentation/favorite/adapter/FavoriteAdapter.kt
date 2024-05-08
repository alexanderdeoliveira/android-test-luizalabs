package com.android_test_luizalabs.presentation.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.android_test_luizalabs.core.extensions.DefaultDiffCallback
import com.android_test_luizalabs.databinding.FragmentListItemBinding
import com.android_test_luizalabs.domain.model.Gist

class FavoriteAdapter(
    private val onItemFavoriteClick: (Boolean, Gist) -> Unit
) : ListAdapter<Gist, FavoriteViewHolder>(DefaultDiffCallback<Gist>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentListItemBinding.inflate(inflater, parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val gist = getItem(position)
        holder.bind(gist, onItemFavoriteClick)
    }
}