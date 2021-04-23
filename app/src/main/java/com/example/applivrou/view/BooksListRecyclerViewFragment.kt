package com.example.applivrou.view

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applivrou.R
import com.example.applivrou.viewmodel.ViewModel

class BooksListRecyclerViewFragment : Fragment() {
    private val recyclerViewAdapter = Adapter { bookDetails: Parcelable ->
        val intent = Intent(context, BookDetailsActivity::class.java)
        intent.putExtra("BOOK_DETAILS", bookDetails)
        startActivity(intent)
    }
    private lateinit var viewModel: ViewModel
    private lateinit var booksCategory: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.books_list_recycler_view_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)

        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = GridLayoutManager(context, 3)

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