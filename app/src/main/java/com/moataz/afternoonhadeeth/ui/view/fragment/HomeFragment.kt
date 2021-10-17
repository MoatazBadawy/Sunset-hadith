package com.moataz.afternoonhadeeth.ui.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.moataz.afternoonhadeeth.data.model.HomeResponse
import com.moataz.afternoonhadeeth.databinding.FragmentHomeBinding
import com.moataz.afternoonhadeeth.ui.adapter.HomeAdapter
import com.moataz.afternoonhadeeth.ui.viewmodel.HomeViewModel
import com.moataz.afternoonhadeeth.utils.IOnBackPressed
import com.moataz.afternoonhadeeth.utils.helper.Intents.openInstagramAccountIntent
import com.moataz.afternoonhadeeth.utils.status.Resource
import com.moataz.afternoonhadeeth.utils.status.Status

class HomeFragment : Fragment(), IOnBackPressed {

    private var adapter = HomeAdapter()
    private var viewModel = HomeViewModel()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setOnClickToolbarIcons()
        initializeAdapter()
        initializeViewModel()
        getTopList()
        onSwipeRefresh()
        return binding.root
    }

    private fun setOnClickToolbarIcons() {
        binding.instagram.setOnClickListener {
            openInstagramAccountIntent((requireActivity() as AppCompatActivity))
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initializeAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        // disable the touch on items when scroll the recyclerview
        binding.recyclerView.setOnTouchListener { _, motionEvent ->
            binding.recyclerView.onTouchEvent(motionEvent)
            true
        }
    }

    private fun getTopList() {
        viewModel.makeApiCallHome().observe(requireActivity(),
            { response: Resource<HomeResponse> ->
                when (response.status) {
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        adapter.setHomeList(response.data)
                    }
                }
            }
        )
    }

    private fun onSwipeRefresh() {
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.makeApiCallHome().observe(requireActivity(),
                { response: Resource<HomeResponse> ->
                    when (response.status) {
                        Status.ERROR -> {
                            binding.swipeToRefresh.isRefreshing = false
                        }
                        Status.LOADING -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        Status.SUCCESS -> {
                            binding.swipeToRefresh.isRefreshing = false
                            adapter.setHomeList(response.data)
                        }
                    }
                }
            )
        }
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onBackPressed(): Boolean {
        requireActivity().moveTaskToBack(true)
        requireActivity().finish()
        return true
    }
}