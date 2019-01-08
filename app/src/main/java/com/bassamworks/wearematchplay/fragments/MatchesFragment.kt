package com.bassamworks.wearematchplay.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bassamworks.wearematchplay.constatnts.Constants
import com.bassamworks.wearematchplay.databinding.MatchesFragmentBinding
import com.bassamworks.wearematchplay.viewmodels.MatchesViewModel

class MatchesFragment : androidx.fragment.app.Fragment() {

    private lateinit var viewModel: MatchesViewModel

    private lateinit var binding: MatchesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = MatchesFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MatchesViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        val token = PreferenceManager.getDefaultSharedPreferences(context)
            .getString(Constants.Preferences.KEY_CURRENT_USER_TOKEN, "No Value")
        Log.i("MatchesFragment", token)
    }
}
