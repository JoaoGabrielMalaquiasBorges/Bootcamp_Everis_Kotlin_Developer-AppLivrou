package com.example.applivrou

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private val recyclerView: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.recycler_view)
    }

    private val adapter = Adapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.books_shelf)



        var viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        bindViews()

        viewModel.booksList.observe(this, Observer {
            booksList -> adapter.updateList(booksList)
        })

        viewModel.updateBooksList()

        swapBooksCategory(viewModel)
    }

    private fun bindViews() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun swapBooksCategory(viewModel: ViewModel) {
        val tabLayout: TabLayout  = findViewById(R.id.tab_layout)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.text.toString() == "tab 2") {
                    val toast = Toast.makeText(applicationContext, tab.text.toString(), Toast.LENGTH_SHORT)
                    toast.show()
                } else {
                    viewModel.updateBooksList2()
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })
    }
}