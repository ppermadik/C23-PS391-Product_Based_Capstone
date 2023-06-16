package com.dicoding.submission.capstone.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submission.capstone.FrontActivity
import com.dicoding.submission.capstone.R
import com.dicoding.submission.capstone.Result
import com.dicoding.submission.capstone.databinding.FragmentNewsBinding
import com.dicoding.submission.capstone.viewmodel.NewsViewModel
import com.dicoding.submission.capstone.viewmodel.ViewModelFactory


class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private lateinit var adapter: NewsAdapter
    private val binding get() = _binding!!

    private val newsViewModel: NewsViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        val root: View = binding.root
//        requireActivity().supportActionBar?.title = "News"

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        newsViewModel.getSession()

        adapter = NewsAdapter(mutableListOf())
        recyclerView.adapter = adapter
        newsViewModel.session.observe(requireActivity()) {
            if (!it) {
                val intent = Intent(requireActivity(), FrontActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }
        newsViewModel.listStory.observe(requireActivity()) {
            adapter = NewsAdapter(it.toMutableList())
            binding.recyclerView.adapter = adapter
        }

        refreshList()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun refreshList() {
        newsViewModel.getListStoryByToken().observe(requireActivity()) { result ->
            when (result) {
                is Result.Loading -> {
                    if (newsViewModel.listStory.value == null) {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE

                    newsViewModel.setListStory(result.data)
                }
                is Result.Error -> {
                    if (newsViewModel.listStory.value == null) {
                        binding.progressBar.visibility = View.GONE

                        Toast.makeText(
                            requireActivity(),
                            resources.getString(R.string.internet_error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

}
