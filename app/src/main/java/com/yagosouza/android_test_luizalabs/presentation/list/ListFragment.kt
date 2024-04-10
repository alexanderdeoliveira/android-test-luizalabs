package com.yagosouza.android_test_luizalabs.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yagosouza.android_test_luizalabs.databinding.FragmentListBinding
import com.yagosouza.android_test_luizalabs.domain.model.Gist
import com.yagosouza.android_test_luizalabs.presentation.list.adapter.ListAdapter
import org.koin.android.ext.android.inject

class ListFragment : Fragment(), ListContract.View {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = _binding!!

    private val listAdapter by lazy { ListAdapter() }
    private val presenter: ListPresenterImpl by inject()

    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(layoutInflater)

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

            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                        presenter.fetchGist(page++)
                    }
                }
            })
        }
    }

    private fun setupView() {
        binding.setupRecyclerView()

        lifecycle.addObserver(presenter)
        presenter.fetchGist()
    }

    override fun displayGist(list: List<Gist>) {
        listAdapter.submitList(list)
    }

    override fun displayLoading(isLoading: Boolean) {
        binding.listProgressBar.isVisible = isLoading
    }

    override fun showError(error: Throwable) {
        error.message
    }

    companion object {
        fun newInstance() = ListFragment()
    }
}
