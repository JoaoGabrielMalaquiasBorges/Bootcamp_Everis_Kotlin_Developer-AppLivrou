package com.example.applivrou

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DemoCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4
    private val booksCategoriesList = arrayListOf("fantasy", "romance", "fiction", "thriller")

    override fun createFragment(position: Int): Fragment {
        val fragment = DemoObjectFragment()

        fragment.arguments = Bundle().apply {
            putString("booksCategory", booksCategoriesList[position])
        }

        return fragment
    }
}