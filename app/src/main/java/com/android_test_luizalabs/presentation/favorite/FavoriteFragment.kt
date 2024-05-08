package com.android_test_luizalabs.presentation.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android_test_luizalabs.databinding.FragmentFavoriteBinding
import com.android_test_luizalabs.domain.model.Gist
import com.android_test_luizalabs.presentation.favorite.adapter.FavoriteAdapter
import org.koin.android.ext.android.inject

class FavoriteFragment : Fragment(), FavoriteContract.View {

    private lateinit var binding: FragmentFavoriteBinding

    private val favoriteAdapter by lazy {
        FavoriteAdapter(
            onItemFavoriteClick = ::onItemFavoriteSelected
        )
    }

    private val presenter: FavoritePresenterImpl by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)

        setupView()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    private fun FragmentFavoriteBinding.setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        with(recyclerView) {
            layoutManager = linearLayoutManager
            adapter = favoriteAdapter
            isNestedScrollingEnabled = false
        }
    }

    private fun setupView() {
        binding.setupRecyclerView()

        lifecycle.addObserver(presenter)
        presenter.fetchGist()
    }

    private fun onItemFavoriteSelected(isFavorite: Boolean, gist: Gist) {
        val textToast = if (isFavorite) "Adicionado aos Favoritos" else "Removido dos Favoritos"
        Toast.makeText(context, textToast, Toast.LENGTH_SHORT).show()
        if (isFavorite) presenter.addFavorite(gist) else presenter.removeFavorite(gist.id)
    }

    override fun displayGist(list: List<Gist>) {
        favoriteAdapter.submitList(list)
    }

    override fun displayLoading(isLoading: Boolean) {
        binding.progressBarList.isVisible = isLoading
    }

    override fun showError(error: Throwable) {
        Log.d("ERRO_API", error.message ?: "Sem mensagem de erro")
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}