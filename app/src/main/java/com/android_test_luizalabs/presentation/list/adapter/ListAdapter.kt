package com.android_test_luizalabs.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.android_test_luizalabs.core.extensions.DefaultDiffCallback

import com.android_test_luizalabs.databinding.FragmentListItemBinding
import com.android_test_luizalabs.domain.model.Gist

class ListAdapter(
    private val onItemClick: (String) -> Unit,
    private val onItemFavoriteClick: (Boolean, Gist) -> Unit
) : ListAdapter<Gist, ListViewHolder>(DefaultDiffCallback<Gist>()) {

    private var gistList: List<Gist> = listOf()
    private var filteredGistList = gistList.toMutableList()
    var isFiltered: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentListItemBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun submitList(list: MutableList<Gist>?) {
        if (!isFiltered) gistList = list?.toList() ?: listOf()
        super.submitList(list)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val gist = getItem(position)
        holder.bind(gist, onItemClick, onItemFavoriteClick)
    }

    fun filter(query: String?) {
        if (query.isNullOrEmpty()) {
            filteredGistList = gistList.toMutableList()
            isFiltered = false
        } else {
            filteredGistList = gistList.filterTo(mutableListOf()) { item ->
                item.owner!!.login!!.contains(query.toString(), ignoreCase = true)
            }
            isFiltered = true
        }

        submitList(filteredGistList)
    }

}