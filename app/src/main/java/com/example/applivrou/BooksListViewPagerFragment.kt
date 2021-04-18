package com.example.applivrou

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class BooksListViewPagerFragment : Fragment() {
    val booksCategoriesList = arrayListOf("fantasy", "romance", "fiction", "thriller")
    private lateinit var viewPager: ViewPager2
    private lateinit var booksListViewPagerAdapter: BooksListViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.books_list_view_pager_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPager = view.findViewById(R.id.view_pager)
        booksListViewPagerAdapter = BooksListViewPagerAdapter(this)
        viewPager.adapter = booksListViewPagerAdapter

        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = booksCategoriesList[position]
        }.attach()
    }
}