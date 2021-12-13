package com.moataz.afternoonhadeeth.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.moataz.afternoonhadeeth.utils.helper.APP_URL
import com.moataz.afternoonhadeeth.utils.helper.URL_FACEBOOK_PAGE
import com.moataz.afternoonhadeeth.utils.helper.URL_Telegram_Channel
import com.moataz.afternoonhadeeth.utils.helper.URL_Twitter_Account
import com.moataz.afternoonhadeeth.databinding.FragmentPremiumBinding
import com.moataz.afternoonhadeeth.utils.helper.Intents

class PremiumFragment : Fragment() {

    private lateinit var binding: FragmentPremiumBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPremiumBinding.inflate(layoutInflater)
        openWebPages()
        return binding.root
    }

    private fun openWebPages() {
        binding.shareAppButton.setOnClickListener {
            Intents.shareAppSnackbar(requireView(), "تم نسخ الرابط", APP_URL, requireContext())
        }

        binding.followUsOnFacebookButton.setOnClickListener {
            Intents.openWebPage((requireActivity() as AppCompatActivity), URL_FACEBOOK_PAGE)
        }
        binding.joinUsInTelegramButton.setOnClickListener {
            Intents.openWebPage((requireActivity() as AppCompatActivity), URL_Telegram_Channel)
        }
        binding.followUsOnTwitterButton.setOnClickListener {
            Intents.openWebPage((requireActivity() as AppCompatActivity), URL_Twitter_Account)
        }
    }
}