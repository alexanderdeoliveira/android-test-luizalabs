package com.android_test_luizalabs.presentation.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.android_test_luizalabs.R
import com.android_test_luizalabs.databinding.FragmentListItemBinding
import com.android_test_luizalabs.domain.model.Gist
import java.lang.Exception

class ListViewHolder(
    private val binding: FragmentListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        gist: Gist,
        onItemClick: (String) -> Unit,
        onItemFavoriteClick: (Boolean, Gist) -> Unit
    ) =
        with(binding) {
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

            handleImageButtonFavorite(gist)
            imageButtonFavorite.setOnClickListener {
                gist.isFavorite = !gist.isFavorite
                handleImageButtonFavorite(gist)
                onItemFavoriteClick(gist.isFavorite, gist)
            }
            root.setOnClickListener { onItemClick(gist.id) }
        }

    fun FragmentListItemBinding.handleImageButtonFavorite(gist: Gist) {
        if (gist.isFavorite)
            imageButtonFavorite.setImageResource(R.drawable.ic_star)
        else
            imageButtonFavorite.setImageResource(R.drawable.ic_star_outline)
    }
}