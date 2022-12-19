package com.offline.form.builder.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.offline.form.builder.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val questionAdapter: QuestionAdapter by lazy {
        QuestionAdapter(homeViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        _binding!!.vm = homeViewModel
        _binding!!.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textHome.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = questionAdapter
            questionAdapter.submitList(homeViewModel.questions)
        }
        binding.submitData.setOnClickListener {
            if (it.isEnabled) {
                homeViewModel.insertData()
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.textHome.adapter = null
                    questionAdapter.submitList(homeViewModel.questions)
                    binding.textHome.adapter = questionAdapter
                    Toast.makeText(requireContext(), "Data added in the list", Toast.LENGTH_SHORT).show()
                }, 1000)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}