package com.yagosouza.android_test_luizalabs.presentation.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yagosouza.android_test_luizalabs.R
import com.yagosouza.android_test_luizalabs.databinding.FragmentListBinding
import com.yagosouza.android_test_luizalabs.domain.model.Gist
import com.yagosouza.android_test_luizalabs.presentation.detail.DetailFragment
import com.yagosouza.android_test_luizalabs.presentation.list.adapter.ListAdapter
import org.koin.android.ext.android.inject


class ListFragment : Fragment(), ListContract.View {

    private lateinit var binding: FragmentListBinding

    private val listAdapter by lazy { ListAdapter(onItemClick = ::onItemSelected) }
    private val presenter: ListPresenterImpl by inject()

    private var page = 1

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

            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (!recyclerView.canScrollVertically(1)) {
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
        binding.progressBarList.isVisible = isLoading
    }

    override fun showError(error: Throwable) {
        Log.d("ERRO_API", error.message ?: "Sem mensagem de erro")
    }

    override fun onItemSelected(id: String) {
        navigateToDetail(id)
    }

    private fun navigateToDetail(id: String) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = DetailFragment.newInstance(id)

        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    companion object {
        fun newInstance() = ListFragment()
    }
}
