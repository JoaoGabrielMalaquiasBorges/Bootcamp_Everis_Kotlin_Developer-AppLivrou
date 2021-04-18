package com.example.applivrou.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BooksListViewPagerAdapter(private val booksListViewPagerFragment: BooksListViewPagerFragment)
    : FragmentStateAdapter(booksListViewPagerFragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        val fragment = BooksListRecyclerViewFragment()

        fragment.arguments = Bundle().apply {
            putString("booksCategory", booksListViewPagerFragment.booksCategoriesList[position])
        }

        return fragment
    }
}