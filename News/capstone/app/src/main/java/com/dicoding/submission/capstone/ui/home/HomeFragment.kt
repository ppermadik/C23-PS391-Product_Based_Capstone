package com.dicoding.submission.capstone.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.widget.PopupMenu
import com.dicoding.submission.capstone.R
import com.dicoding.submission.capstone.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val searchBox = root.findViewById<EditText>(R.id.searchBox)
        val filterIcon = root.findViewById<ImageView>(R.id.filterIconImageView)

        filterIcon.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), it)
            popupMenu.menuInflater.inflate(R.menu.filter_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { item ->
                // Handle menu item click
                when (item.itemId) {
                    R.id.option1 -> {
                        // Handle option 1 selection
                        true
                    }
                    R.id.option2 -> {
                        // Handle option 2 selection
                        true
                    }
                    R.id.option3 -> {
                        // Handle option 3 selection
                        true
                    }
                    else -> false
                }
            }

            popupMenu.show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
