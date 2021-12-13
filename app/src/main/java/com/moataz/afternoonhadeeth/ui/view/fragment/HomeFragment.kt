package com.moataz.afternoonhadeeth.ui.view.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.BillingProcessor.IBillingHandler
import com.anjlab.android.iab.v3.TransactionDetails
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.model.home.HomeResponse
import com.moataz.afternoonhadeeth.utils.helper.URL_Telegram_Channel
import com.moataz.afternoonhadeeth.databinding.FragmentHomeBinding
import com.moataz.afternoonhadeeth.ui.adapter.HomeAdapter
import com.moataz.afternoonhadeeth.ui.viewmodel.HomeViewModel
import com.moataz.afternoonhadeeth.utils.helper.Intents.openWebPage
import com.moataz.afternoonhadeeth.utils.interfaces.IOnBackPressed
import com.moataz.afternoonhadeeth.utils.status.Resource
import com.moataz.afternoonhadeeth.utils.status.Status

class HomeFragment : Fragment(), IOnBackPressed, IBillingHandler {

    private var adapter = HomeAdapter()
    private var viewModel = HomeViewModel()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var billingProcess: BillingProcessor
    private var purchaseTransactionDetails: TransactionDetails? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setOnClickToolbarIcons()
        setupBillingProcess(context)
        initializeAdapter()
        initializeViewModel()
        getTopList()
        return binding.root
    }

    private fun setOnClickToolbarIcons() {
        binding.instagram.setOnClickListener {
            openWebPage((requireActivity() as AppCompatActivity), URL_Telegram_Channel)
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

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    private fun setupBillingProcess(context: Context?) {
        billingProcess = BillingProcessor(
            context, resources.getString(R.string.play_console_license),
            this
        )
        billingProcess.initialize()
    }

    override fun onProductPurchased(productId: String, details: TransactionDetails?) {
        Log.d("MainActivity", "onProductPurchased: ")
    }

    override fun onPurchaseHistoryRestored() {
        Log.d("MainActivity", "onPurchaseHistoryRestored: ")
    }

    override fun onBillingError(errorCode: Int, error: Throwable?) {
        Log.d("MainActivity", "onBillingError: ")
    }

    override fun onBillingInitialized() {
        Log.d("MainActivity", "onBillingInitialized: ")
        val premium = resources.getString(R.string.premium)
        purchaseTransactionDetails = billingProcess.getSubscriptionTransactionDetails(premium)
        billingProcess.loadOwnedPurchasesFromGoogle()
        binding.support.setOnClickListener {
            if (billingProcess.isSubscriptionUpdateSupported) {
                billingProcess.subscribe(requireActivity(), premium)
            } else {
                Log.d(
                    "MainActivity",
                    "onBillingInitialized: Subscription updated is not supported"
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!billingProcess.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onBackPressed(): Boolean {
        requireActivity().moveTaskToBack(true)
        requireActivity().finish()
        return true
    }

    override fun onDestroy() {
        billingProcess.release()
        super.onDestroy()
    }

}