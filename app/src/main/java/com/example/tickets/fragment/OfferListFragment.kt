package com.example.tickets.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.tickets.R
import com.example.tickets.adapter.OfferListAdapter
import com.example.tickets.databinding.FragmentOfferListBinding
import com.example.tickets.model.network.ApiClient
import com.example.tickets.model.service.FakeService
import kotlinx.coroutines.launch


class OfferListFragment : Fragment() {

    companion object {
        fun newInstance() = OfferListFragment()
    }

    private var _binding: FragmentOfferListBinding? = null
    private val binding
        get() = _binding!!

    private val adapter: OfferListAdapter by lazy {
        OfferListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOfferListBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()

        lifecycleScope.launch {
            val list = ApiClient.getFlights()
            adapter.setItems(list)
        }
    }

    private fun setupUI() {
        with(binding) {
            offerList.adapter = adapter

            sortRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.sort_by_price -> {
                        val sortedByPrice = adapter.getCurrentList().sortedBy { it.price }
                        adapter.setItems(sortedByPrice)
                        offerList.scrollToPosition(0)
                    }

                    R.id.sort_by_duration -> {
                        val sortedByDuration =
                            adapter.getCurrentList().sortedBy { it.flight.duration }
                        adapter.setItems(sortedByDuration)
                        offerList.scrollToPosition(0)
                    }
                }
            }
        }
    }
}