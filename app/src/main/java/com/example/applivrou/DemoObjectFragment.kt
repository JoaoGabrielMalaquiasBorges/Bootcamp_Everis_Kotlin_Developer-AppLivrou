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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        val recyclerViewAdapter = Adapter()

        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.booksList.observe(viewLifecycleOwner, Observer {
            booksList -> recyclerViewAdapter.updateList(booksList)
        })

        arguments?.takeIf { it.containsKey("ARG_OBJECT") }?.apply {
            viewModel.updateBooksList("romance")

            /*val textView: TextView = view.findViewById(R.id.text)
            textView.text = getInt("ARG_OBJECT").toString()*/
        }
    }
}