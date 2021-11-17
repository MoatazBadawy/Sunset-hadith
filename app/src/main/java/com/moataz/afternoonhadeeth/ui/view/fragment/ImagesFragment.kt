package com.moataz.afternoonhadeeth.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.databinding.FragmentImagesBinding
import com.moataz.afternoonhadeeth.utils.interfaces.IOnBackPressed

class ImagesFragment : Fragment(), IOnBackPressed {

    private lateinit var binding: FragmentImagesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onBackPressed(): Boolean {
        val mainFragment = HomeFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_layout, mainFragment, "findThisFragment")
            .addToBackStack(null)
            .commit()
        return true
    }
}