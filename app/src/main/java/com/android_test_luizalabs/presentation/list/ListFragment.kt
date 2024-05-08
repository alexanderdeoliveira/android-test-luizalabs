package com.android_test_luizalabs.presentation.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android_test_luizalabs.core.extensions.NetworkException
import com.android_test_luizalabs.core.extensions.navigateToFragment
import com.android_test_luizalabs.databinding.FragmentListBinding
import com.android_test_luizalabs.domain.model.Gist
import com.android_test_luizalabs.presentation.detail.DetailFragment
import com.android_test_luizalabs.presentation.list.adapter.ListAdapter
import org.koin.android.ext.android.inject


class ListFragment : Fragment(), ListContract.View {

    private lateinit var binding: FragmentListBinding

    private val listAdapter by lazy {
        ListAdapter(
            onItemClick = ::onItemSelected,
            onItemFavoriteClick = ::onItemFavoriteSelected
        )
    }
    private val presenter: ListPresenterImpl by inject()

    private val listGists: MutableList<Gist> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater, container, false)

        setupView()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)

    }

    private fun FragmentListBinding.setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        with(recyclerView) {
            layoutManager = linearLayoutManager
            adapter = listAdapter
            isNestedScrollingEnabled = false
        }
    }

    private fun FragmentListBinding.setupSearchBox() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                listAdapter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                listAdapter.filter(newText)
                return false
            }
        })
    }

    private fun setupView() {
        binding.setupRecyclerView()
        binding.setupSearchBox()
        binding.includeError.buttonTryAgain.setOnClickListener { onClickTryAgain() }
        binding.includeUnrecognizedError.buttonTryAgain.setOnClickListener { onClickTryAgain() }

        lifecycle.addObserver(presenter)
        presenter.fetchGist()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun displayGist(list: List<Gist>) {
        listGists.addAll(list)
        listAdapter.submitList(listGists)
    }

    override fun displayLoading(isLoading: Boolean) {
        binding.progressBarList.isVisible = isLoading
    }

    override fun showError(error: Throwable) {
        when (error) {
            is NetworkException -> {
                binding.includeError.textView.text = error.message
                binding.includeError.root.isVisible = true
            }

            else -> {
                binding.includeUnrecognizedError.textView.text = error.message
                binding.includeUnrecognizedError.root.isVisible = true
            }
        }

        Log.d("ERRO_API", error.message ?: "Sem mensagem de erro")
    }

    private fun onItemFavoriteSelected(isFavorite: Boolean, gist: Gist) {
        val textToast = if (isFavorite) "Adicionado aos Favoritos" else "Removido dos Favoritos"
        Toast.makeText(context, textToast, Toast.LENGTH_SHORT).show()
        presenter.saveFavorite(gist)
    }

    private fun onClickTryAgain() {
        presenter.fetchGist()
        binding.includeError.root.isVisible = false
        binding.includeUnrecognizedError.root.isVisible = false
    }

    override fun onItemSelected(id: String) {
        navigateToDetail(id)
    }

    private fun navigateToDetail(id: String) {
        navigateToFragment(DetailFragment.newInstance(id), DetailFragment::class.java.name)
    }

    companion object {
        fun newInstance() = ListFragment()
    }
}
