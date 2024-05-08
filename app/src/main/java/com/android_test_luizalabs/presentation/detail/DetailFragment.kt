package com.android_test_luizalabs.presentation.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.squareup.picasso.Picasso
import com.android_test_luizalabs.R
import com.android_test_luizalabs.databinding.FragmentDetailBinding
import com.android_test_luizalabs.domain.model.Gist
import org.koin.android.ext.android.inject

private const val ARG_PARAM1 = "ID"

class DetailFragment : Fragment(), DetailContract.View {

    private lateinit var binding: FragmentDetailBinding

    private val presenter: DetailPresenterImpl by inject()

    private var id: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString(ARG_PARAM1) ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)

        setupView()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    private fun setupView() {
        lifecycle.addObserver(presenter)
        presenter.fetchDetail(id)
    }

    override fun displayGist(gistDetail: Gist) {
        binding.textUserName.text = gistDetail.owner?.login
        Picasso.get()
            .load(gistDetail.owner?.avatarUrl)
            .error(R.drawable.ic_account_circle)
            .into(binding.imageViewUser)

    }

    override fun displayLoading(isLoading: Boolean) {
        binding.progressBarDetail.isVisible = isLoading
    }

    override fun showError(error: Throwable) {
        Log.d("ERRO_API", error.message ?: "Sem mensagem de erro")
    }

    companion object {
        fun newInstance(id: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, id)
                }
            }
    }
}