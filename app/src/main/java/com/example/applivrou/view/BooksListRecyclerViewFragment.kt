package com.example.applivrou.view

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applivrou.R
import com.example.applivrou.viewmodel.BooksListViewModel

class BooksListRecyclerViewFragment : Fragment() {
    private val recyclerViewAdapter = BooksListRecyclerViewAdapter { bookDetails: Parcelable ->
        val intent = Intent(context, BookDetailsActivity::class.java)
        intent.putExtra("BOOK_DETAILS", bookDetails)
        startActivity(intent)
    }

    private lateinit var booksListViewModel: BooksListViewModel
    private lateinit var booksCategory: String
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.books_list_recycler_view_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        booksListViewModel = ViewModelProvider(this).get(BooksListViewModel::class.java)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        val booksListRecyclerViewItemsDecoration = BooksListRecyclerViewItemsDecoration()

        recyclerView.addItemDecoration(booksListRecyclerViewItemsDecoration)
        progressBar = view.findViewById(R.id.progress_bar)

        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = GridLayoutManager(context, 3)

        arguments?.takeIf { it.containsKey("booksCategory") }?.apply {
            booksCategory = getString("booksCategory")!!
        }
    }

    override fun onResume() {
        super.onResume()
        if (!booksListViewModel.booksList.hasObservers()) {
            booksListViewModel.booksList.observe(viewLifecycleOwner, Observer {
                booksList ->
                    progressBar.visibility = View.GONE
                    recyclerViewAdapter.updateList(booksList)
            })
            booksListViewModel.updateBooksList(booksCategory)
        }
    }
}