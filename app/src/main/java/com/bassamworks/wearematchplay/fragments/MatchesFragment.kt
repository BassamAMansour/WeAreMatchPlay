package com.bassamworks.wearematchplay.fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bassamworks.wearematchplay.adapters.MatchesPagedListAdapter
import com.bassamworks.wearematchplay.constatnts.Constants
import com.bassamworks.wearematchplay.databinding.MatchesFragmentBinding
import com.bassamworks.wearematchplay.viewmodels.MatchesViewModel
import kotlinx.android.synthetic.main.matches_fragment.*

class MatchesFragment : androidx.fragment.app.Fragment() {

    private lateinit var viewModel: MatchesViewModel
    private lateinit var binding: MatchesFragmentBinding
    private val matchesAdapter: MatchesPagedListAdapter by lazy { MatchesPagedListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = MatchesFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MatchesViewModel::class.java)

        viewModel.matchesPagedList.observe(this, Observer { matchesAdapter.submitList(it) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_matches.adapter = matchesAdapter
    }

    override fun onStart() {
        super.onStart()

        val token = PreferenceManager.getDefaultSharedPreferences(context)
            .getString(Constants.Preferences.KEY_CURRENT_USER_TOKEN, "No Value")
        Log.i("MatchesFragment", token)
    }
}
