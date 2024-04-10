package com.yagosouza.android_test_luizalabs.presentation.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.yagosouza.android_test_luizalabs.R
import com.yagosouza.android_test_luizalabs.databinding.FragmentListItemBinding
import com.yagosouza.android_test_luizalabs.domain.model.Gist
import java.lang.Exception

class ListViewHolder(
    private val binding: FragmentListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(gist: Gist, onItemClick: (String) -> Unit) = with(binding) {
        username.text = gist.owner?.login
        name.text = gist.files?.type
        Picasso.get()
            .load(gist.owner?.avatarUrl)
            .error(R.drawable.ic_account_circle)
            .into(binding.picture, object : Callback {
                override fun onSuccess() {
                    binding.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    binding.progressBar.visibility = View.GONE
                }
            })
        imageButtonFavorite.setOnClickListener {
            imageButtonFavorite.setImageResource(R.drawable.ic_star)
        }
        root.setOnClickListener { onItemClick(gist.id!!) }
    }
}