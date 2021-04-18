package com.example.applivrou

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DemoObjectFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collection_object, container, false)
    }

    private lateinit var viewModel: ViewModel
    private lateinit var booksCategory: String
    private val recyclerViewAdapter = Adapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)

        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        arguments?.takeIf { it.containsKey("booksCategory") }?.apply {
            booksCategory = getString("booksCategory")!!
        }
    }

    override fun onResume() {
        super.onResume()
        if (!viewModel.booksList.hasObservers()) {
            viewModel.booksList.observe(viewLifecycleOwner, Observer {
                    booksList -> recyclerViewAdapter.updateList(booksList)
            })
            viewModel.updateBooksList(booksCategory)
        }
    }
}